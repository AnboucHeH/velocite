package velocite;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
import org.junit.jupiter.api.Test;

/** Bonus de la seance 3 : restitution et tarification (R7, R8). */
class TarificationTest {
    static Location location(TypeAbonnement type, int minutes) {
        Location l = new Location();
        l.setUsager(Fixtures.usager(type, LocalDate.now().plusMonths(1)));
        l.setVelo(new Velo());
        LocalDateTime t0 = LocalDateTime.of(2026, 3, 1, 10, 0);
        l.setDebut(t0);
        l.setFin(t0.plusMinutes(minutes));
        return l;
    }

    @Test
    void restitutionClotureLaLocation() {
        Velo v = new Velo();
        Station s = Fixtures.station(v);
        Usager u = Fixtures.usagerValide();
        Location loc = s.louer(u, 1);

        s.restituer(loc, 3);

        assertEquals(EtatVelo.DISPONIBLE, v.getEtat());
        assertSame(v, s.getBornes().get(2).getVelo());
        assertSame(s, loc.getArrivee());
        assertFalse(u.aLocationEnCours());
    }

    @Test
    void occasionnelPaieDesLaPremiereTranche() {
        assertEquals(2.0, location(TypeAbonnement.OCCASIONNEL, 45).calculerCout(), 1e-9);
    }

    @Test
    void abonnePremiereTrancheGratuite() {
        assertEquals(0.0, location(TypeAbonnement.MENSUEL, 20).calculerCout(), 1e-9);
        assertEquals(1.0, location(TypeAbonnement.ANNUEL, 45).calculerCout(), 1e-9);
    }

    @Test
    void trancheEntameeEstDue() {
        assertEquals(1.0, location(TypeAbonnement.OCCASIONNEL, 30).calculerCout(), 1e-9);
        assertEquals(2.0, location(TypeAbonnement.OCCASIONNEL, 31).calculerCout(), 1e-9);
    }
}
