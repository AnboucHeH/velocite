package velocite;

import java.time.LocalDateTime;

public class Location {

    // Attributs
    private LocalDateTime debut;
    private LocalDateTime fin;
    private double cout;

    // Associations
    private Usager usager;
    private Velo velo;
    private Station depart;
    private Station arrivee;

    // Constructeur sans paramètre
    public Location() {
    }

    // Accesseurs et modificateurs
    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    public Station getDepart() {
        return depart;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public void setArrivee(Station arrivee) {
        this.arrivee = arrivee;
    }

    // Opérations
    public int dureeMinutes() {
        throw new UnsupportedOperationException("TODO");
    }

    public double calculerCout() {
        throw new UnsupportedOperationException("TODO");
    }
}