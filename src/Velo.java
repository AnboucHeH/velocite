package velocite;

import java.time.*;
import java.util.*;

public class Velo {

    private String identifiant;
    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    private LocalDate miseEnService;
    public LocalDate getMiseEnService() { return miseEnService; }
    public void setMiseEnService(LocalDate miseEnService) { this.miseEnService = miseEnService; }

    private double kilometrage;
    public double getKilometrage() { return kilometrage; }
    public void setKilometrage(double kilometrage) { this.kilometrage = kilometrage; }

    // ----- Machine a etats -----
    private EtatVelo etat = EtatVelo.DISPONIBLE;
    public EtatVelo getEtat() { return etat; }

    public void deverrouiller(Usager usager) {
        if (etat == EtatVelo.DISPONIBLE && peutEtreDeverrouille(usager)) {
            etat = EtatVelo.LOUE;
            return;
        }
        throw new IllegalStateException("deverrouiller refuse dans l'etat " + etat);
    }

    public void restituer(Borne borne) {
        if (etat == EtatVelo.LOUE && borneLibre(borne)) {
            garer(borne);
            etat = EtatVelo.DISPONIBLE;
            return;
        }
        throw new IllegalStateException("restituer refuse dans l'etat " + etat);
    }

    public void signalerPanne() {
        if (etat == EtatVelo.DISPONIBLE) {
            etat = EtatVelo.EN_PANNE;
            return;
        }
        throw new IllegalStateException("signalerPanne refuse dans l'etat " + etat);
    }

    public void prendreEnCharge() {
        if (etat == EtatVelo.EN_PANNE) {
            etat = EtatVelo.EN_REPARATION;
            return;
        }
        throw new IllegalStateException("prendreEnCharge refuse dans l'etat " + etat);
    }

    public void remettreEnService(Borne borne) {
        if (etat == EtatVelo.EN_REPARATION && borneLibre(borne)) {
            garer(borne);
            etat = EtatVelo.DISPONIBLE;
            return;
        }
        throw new IllegalStateException("remettreEnService refuse dans l'etat " + etat);
    }

    public void reformer() {
        if (etat == EtatVelo.EN_REPARATION) {
            etat = EtatVelo.REFORME;
            return;
        }
        throw new IllegalStateException("reformer refuse dans l'etat " + etat);
    }

    // Gardes et effets : a redefinir dans le code manuel.
    protected boolean peutEtreDeverrouille(Usager usager) { return true; }
    protected boolean borneLibre(Borne borne) { return true; }
    protected void garer(Borne borne) { }

}
