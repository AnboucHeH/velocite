"""Generateur VeloCite : lit model.json et produit du code Java.

Usage :
  python3 gen.py model.json sortie/          (une classe Java par classe du modele)
  python3 gen.py model.json sortie/ --gap    (patron generation gap, seance 4)

Vous n'avez pas besoin de modifier ce fichier. Le code Java produit
est decrit dans le dossier templates/.
"""
import json, os, sys
from jinja2 import Environment, FileSystemLoader

# Traduction des types du modele vers Java.
TYPES = {"String": "String", "Integer": "int", "Real": "double", "Boolean": "boolean",
         "Date": "LocalDate", "DateTime": "LocalDateTime", None: "void"}


def java_type(t):
    return TYPES.get(t, t)          # une classe ou une enum garde son nom


def cap(s):
    return s[0].upper() + s[1:]     # nom -> Nom (pour getNom, setNom)


def is_many(multiplicity):
    upper = multiplicity.split("..")[-1]
    return upper == "*" or int(upper) > 1


def params_decl(ps):
    return ", ".join(f"{java_type(p['type'])} {p['name']}" for p in ps)


def params_call(ps):
    return ", ".join(p["name"] for p in ps)


def prepare_class(c, model, gap):
    """Prepare toutes les informations dont le template a besoin."""
    refs = [r for r in model["associations"] if r["source"] == c["name"]]
    for r in refs:
        r["Role"] = cap(r["role"])
    sm = next((s for s in model.get("stateMachines", []) if s["context"] == c["name"]), None)
    if sm:
        sm = prepare_state_machine(sm)
    return {
        "package": model["package"],
        "name": c["name"],
        "class_name": c["name"] + ("Base" if gap else ""),
        "superclass": c.get("superclass"),
        "is_abstract": gap or c.get("abstract", False),
        "gap": gap,
        "attributes": [{"name": a["name"], "Name": cap(a["name"]), "type": java_type(a["type"])}
                       for a in c["attributes"]],
        "single_refs": [r for r in refs if not is_many(r["multiplicity"])],
        "many_refs": [r for r in refs if is_many(r["multiplicity"])],
        "operations": [{"name": o["name"], "returns": java_type(o["returns"]),
                        "params_decl": params_decl(o["params"])} for o in c["operations"]],
        "sm": sm,
    }


def prepare_state_machine(sm):
    hooks = {}
    events = []
    for ev in sm["events"]:
        decl, call = params_decl(ev["params"]), params_call(ev["params"])
        transitions = [t for t in sm["transitions"] if t["event"] == ev["name"]]
        for t in transitions:
            if t.get("guard"):
                hooks[t["guard"]] = {"name": t["guard"], "returns": "boolean", "params_decl": decl}
            if t.get("action"):
                hooks[t["action"]] = {"name": t["action"], "returns": "void", "params_decl": decl}
        events.append({"name": ev["name"], "params_decl": decl, "params_call": call,
                       "transitions": transitions})
    return {"enum": sm["enum"], "initial": sm["initial"], "events": events,
            "hooks": list(hooks.values())}


def write(path, text, overwrite=True):
    if not overwrite and os.path.exists(path):
        print("  conserve  ", path)
        return
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        f.write(text)
    print("  genere    ", path)


def main(model_file, out, gap):
    here = os.path.dirname(os.path.abspath(__file__))
    env = Environment(loader=FileSystemLoader(os.path.join(here, "templates")),
                      trim_blocks=True, lstrip_blocks=True, keep_trailing_newline=True)
    model = json.load(open(model_file, encoding="utf-8"))
    gen_dir = os.path.join(out, "src-gen" if gap else "src")
    enums = [(e["name"], e["literals"]) for e in model["enums"]]
    enums += [(s["enum"], s["states"]) for s in model.get("stateMachines", [])]
    for name, literals in enums:
        text = env.get_template("enum.java.j2").render(package=model["package"], name=name, literals=literals)
        write(f"{gen_dir}/{name}.java", text)
    for c in model["classes"]:
        data = prepare_class(c, model, gap)
        write(f"{gen_dir}/{data['class_name']}.java", env.get_template("class.java.j2").render(**data))
        if gap:   # classe manuelle : creee une seule fois, jamais ecrasee
            write(f"{out}/src/{c['name']}.java", env.get_template("custom.java.j2").render(**data),
                  overwrite=False)


if __name__ == "__main__":
    if len(sys.argv) < 3:
        sys.exit(__doc__)
    main(sys.argv[1], sys.argv[2], "--gap" in sys.argv)
