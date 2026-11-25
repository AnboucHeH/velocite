package velocite;

import java.time.*;
import java.util.*;

public class Station {

    private String nom;
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    private String adresse;
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    private double latitude;
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    private double longitude;
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    private final List<Borne> bornes = new ArrayList<>();  // 1..40
    public List<Borne> getBornes() { return bornes; }

    public int bornesLibres() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean estPleine() {
        throw new UnsupportedOperationException("TODO");
    }

    public Location louer(Usager usager, int numeroBorne) {
        throw new UnsupportedOperationException("TODO");
    }

    public void restituer(Location location, int numeroBorne) {
        throw new UnsupportedOperationException("TODO");
    }

}
