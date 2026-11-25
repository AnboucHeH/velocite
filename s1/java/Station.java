package velocite;

import java.util.ArrayList;
import java.util.List;

public class Station {

    // Attributs
    private String nom;
    private String adresse;
    private double latitude;
    private double longitude;

    // Associations
    private List<Borne> bornes = new ArrayList<>();

    // Constructeur sans paramètre
    public Station() {
    }

    // Accesseurs et modificateurs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Borne> getBornes() {
        return bornes;
    }

    // Opérations
    public int bornesLibres() {
        throw new UnsupportedOperationException("TODO");
    }

    public Location louer(Usager usager, int numeroBorne) {
        throw new UnsupportedOperationException("TODO");
    }

    public void restituer(Location location, int numeroBorne) {
        throw new UnsupportedOperationException("TODO");
    }
}
