package velocite;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class VeloEtatsTest {
    @Test
    void etatInitialDisponible() {
        assertEquals(EtatVelo.DISPONIBLE, new Velo().getEtat());
    }

    @Test
    void cycleDeReparation() {
        Velo v = new Velo();
        v.signalerPanne();
        assertEquals(EtatVelo.EN_PANNE, v.getEtat());
        v.prendreEnCharge();
        assertEquals(EtatVelo.EN_REPARATION, v.getEtat());
        Borne b = new Borne();
        v.remettreEnService(b);
        assertEquals(EtatVelo.DISPONIBLE, v.getEtat());
        assertSame(v, b.getVelo());
    }

    @Test
    void evenementInterditLaisseEtatInchange() {
        Velo v = new Velo();
        assertThrows(IllegalStateException.class, () -> v.reformer());
        assertEquals(EtatVelo.DISPONIBLE, v.getEtat());
    }

    @Test
    void reformeEstFinal() {
        Velo v = new Velo();
        v.signalerPanne();
        v.prendreEnCharge();
        v.reformer();
        assertThrows(IllegalStateException.class, () -> v.remettreEnService(new Borne()));
        assertThrows(IllegalStateException.class, () -> v.signalerPanne());
        assertEquals(EtatVelo.REFORME, v.getEtat());
    }
}
