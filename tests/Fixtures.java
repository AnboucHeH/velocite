package velocite;

import java.time.*;

class Fixtures {
    static Usager usager(TypeAbonnement type, LocalDate fin) {
        Abonnement a = new Abonnement();
        a.setType(type);
        a.setDebut(LocalDate.now().minusMonths(1));
        a.setFin(fin);
        Usager u = new Usager();
        u.setIdentifiant("U1");
        u.setAbonnement(a);
        return u;
    }

    static Usager usagerValide() {
        return usager(TypeAbonnement.MENSUEL, LocalDate.now().plusMonths(1));
    }

    static Station station(Velo velo) {
        Station s = new Station();
        s.setNom("Gare");
        for (int i = 1; i <= 3; i++) {
            Borne b = new Borne();
            b.setNumero(i);
            s.getBornes().add(b);
        }
        s.getBornes().get(0).setVelo(velo);   // le velo est sur la borne 1
        return s;
    }
}
