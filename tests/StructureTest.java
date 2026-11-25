package velocite;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
import org.junit.jupiter.api.Test;

/** Seance 2 : verifie que le code genere respecte les conventions. */
class StructureTest {
    @Test
    void attributsEtAssociations() {
        Station s = new Station();
        s.setNom("Gare");
        Borne b = new Borne();
        b.setNumero(1);
        s.getBornes().add(b);
        Velo v = new Velo();
        v.setKilometrage(12.5);
        b.setVelo(v);
        assertSame(v, s.getBornes().get(0).getVelo());

        Usager u = new Usager();
        Abonnement a = new Abonnement();
        a.setType(TypeAbonnement.ANNUEL);
        a.setFin(LocalDate.of(2027, 1, 1));
        u.setAbonnement(a);
        Location l = new Location();
        l.setUsager(u);
        l.setVelo(v);
        l.setDepart(s);
        l.setDebut(LocalDateTime.of(2026, 3, 1, 10, 0));
        u.getLocations().add(l);
        assertEquals(1, u.getLocations().size());
        assertNull(l.getArrivee());
    }
}
