package velocite;

import java.time.*;
import java.util.*;

public class Location {

    private LocalDateTime debut;
    public LocalDateTime getDebut() { return debut; }
    public void setDebut(LocalDateTime debut) { this.debut = debut; }

    private LocalDateTime fin;
    public LocalDateTime getFin() { return fin; }
    public void setFin(LocalDateTime fin) { this.fin = fin; }

    private double cout;
    public double getCout() { return cout; }
    public void setCout(double cout) { this.cout = cout; }

    private Usager usager;  // 1
    public Usager getUsager() { return usager; }
    public void setUsager(Usager usager) { this.usager = usager; }

    private Velo velo;  // 1
    public Velo getVelo() { return velo; }
    public void setVelo(Velo velo) { this.velo = velo; }

    private Station depart;  // 1
    public Station getDepart() { return depart; }
    public void setDepart(Station depart) { this.depart = depart; }

    private Station arrivee;  // 0..1
    public Station getArrivee() { return arrivee; }
    public void setArrivee(Station arrivee) { this.arrivee = arrivee; }

    public int dureeMinutes() {
        throw new UnsupportedOperationException("TODO");
    }

    public double calculerCout() {
        throw new UnsupportedOperationException("TODO");
    }

}
