package velocite;

import java.time.*;
import java.util.*;

public class Usager {

    private String identifiant;
    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    private String nom;
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    private String telephone;
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    private Abonnement abonnement;  // 0..1
    public Abonnement getAbonnement() { return abonnement; }
    public void setAbonnement(Abonnement abonnement) { this.abonnement = abonnement; }

    private final List<Location> locations = new ArrayList<>();  // 0..*
    public List<Location> getLocations() { return locations; }

    public boolean peutLouer() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean aLocationEnCours() {
        throw new UnsupportedOperationException("TODO");
    }

}
