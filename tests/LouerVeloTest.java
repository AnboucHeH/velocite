package velocite;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
import org.junit.jupiter.api.Test;

/** Un test par operande du fragment alt du diagramme de sequence "louer". */
class LouerVeloTest {
    @Test
    void scenarioNominal() {
        // Preparation
        Velo v = new Velo();
        Station s = Fixtures.station(v);          // v est sur la borne 1
        Usager u = Fixtures.usagerValide();

        // Scenario
        Location loc = s.louer(u, 1);

        // Verifications : l'etat du systeme apres le scenario
        assertEquals(EtatVelo.LOUE, v.getEtat());
        assertTrue(s.getBornes().get(0).estLibre());
        assertSame(v, loc.getVelo());
        assertSame(s, loc.getDepart());
        assertTrue(u.aLocationEnCours());
    }

    @Test
    void abonnementExpireRefuse() {
        // Preparation
        Velo v = new Velo();
        Station s = Fixtures.station(v);
        Usager u = Fixtures.usager(TypeAbonnement.MENSUEL, LocalDate.now().minusDays(1));

        // Scenario : la location doit etre refusee
        // TODO : verifier que s.louer(u, 1) leve une IllegalStateException
        //        (utilisez assertThrows, voir le cours)

        // Verifications : rien n'a change
        // TODO : le velo est toujours DISPONIBLE
        // TODO : le velo est toujours sur la borne 1
        // TODO : l'usager n'a aucune location en cours
    }

    @Test
    void deuxiemeLocationRefusee() {
        Velo v1 = new Velo();
        Station s = Fixtures.station(v1);
        Velo v2 = new Velo();
        s.getBornes().get(1).setVelo(v2);
        Usager u = Fixtures.usagerValide();
        s.louer(u, 1);

        assertThrows(IllegalStateException.class, () -> s.louer(u, 2));
        assertEquals(EtatVelo.DISPONIBLE, v2.getEtat());
    }
}
