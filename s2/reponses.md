# Séance 2 – Réponses

## Exercice 2.1 – Exécuter et comparer

### Ce qui manque dans le code produit
- **Les setters des attributs** (`setNom`, `setNumero`…) → TROU 1.
- **Les associations multivaluées** : champ `List` et getter (`getBornes()`, `getLocations()`) → TROU 2.
- **Les méthodes des opérations** (`bornesLibres`, `louer`, `estLibre`…) → TROU 3.

Autres différences avec mon code de la S1, sans conséquence :
- pas de constructeur écrit : Java crée automatiquement le constructeur sans paramètre ;
- imports larges (`java.time.*`, `java.util.*`) dans toutes les classes ;
- getters et setters sur une ligne, multiplicité notée en commentaire (`// 1..40`), listes en `final`.

Écarts entre mon diagramme et le modèle de référence : `Velo.miseEnService` (j'avais `dateMiseEnService`), `Abonnement.debut/fin` (j'avais `dateDebut/dateFin`), et Usager–Abonnement est une composition dans le modèle de référence.

### Correspondance entre les blocs du template et la table de traduction

| Bloc de `class.java.j2` | Ligne de la table de traduction |
|---|---|
| `package`, imports, `public class {{ class_name }}` | Classe |
| `{% if superclass %} extends {{ superclass }}` | Héritage |
| Boucle `attributes` : champ + getter + setter (TROU 1) | Attribut |
| `{{ a.type }}`, traduit par `java_type()` dans `gen.py` | Type primitif |
| Boucle `single_refs` | Association 0..1 et 1 |
| Boucle `many_refs` (TROU 2) | Association 0..* et 1..40 |
| Boucle `operations` (TROU 3) | Opération |
| Template `enum.java.j2` | Énumération |
| Aucun bloc | Composition (non traduite) |

## Exercice 2.2 – Compléter les templates
Les 3 trous sont complétés, `StructureTest` passe : 1 test réussi (capture `structuretest_ok.png`).
- TROU 1 : `public void set{{ a.Name }}({{ a.type }} {{ a.name }}) { this.{{ a.name }} = {{ a.name }}; }`
- TROU 2 : `private final List<{{ r.target }}> {{ r.role }} = new ArrayList<>();` + `get{{ r.Role }}()`
- TROU 3 : `public {{ o.returns }} {{ o.name }}({{ o.params_decl }}) { throw new UnsupportedOperationException("TODO"); }`

## Exercice 2.3 – Le modèle commande le code

**(a)** **0 ligne de Java.** J'ai ajouté 2 lignes dans `model.json`, et le générateur a produit le champ `telephone` avec son getter et son setter, ainsi que la méthode `estPleine()`.

**(b)** **Oui, les fichiers sont identiques** : après deux générations, `git status` ne montre aucune modification. Le générateur est déterministe : le même modèle avec les mêmes templates donne toujours le même code.
C'est important parce que :
- `git diff` ne montre que les vrais changements du modèle, sans bruit ;
- le binôme, ou une intégration continue, obtient exactement le même code ;
- on peut régénérer à tout moment sans risque ;
- on peut faire confiance au résultat et le relire facilement.

## Exercice 2.4 – Ce que le code ne dit pas

- **Multiplicité 1..40** : on la retrouve seulement en commentaire (`// 1..40`) à côté de `List<Borne> bornes`. Rien n'empêche une station d'avoir 0 ou 41 bornes.
- **Composition** : aucune trace. L'information `"composition": true` du modèle est ignorée par le générateur. Le code de `Station.bornes` est identique à celui d'une association simple comme `Usager.locations`.

**Conclusion** : le code généré est plus pauvre que le modèle, car des contraintes sont perdues (multiplicités, composition, unicité du numéro de borne). Le modèle reste donc la référence : on ne pourrait pas le reconstruire à partir du code. Pour conserver ces contraintes, il faudrait enrichir le générateur (par exemple une méthode `ajouterBorne` qui refuse plus de 40 bornes, ou une suppression en cascade) ou les vérifier par des tests.