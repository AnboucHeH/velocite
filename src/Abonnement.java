package velocite;

import java.time.*;
import java.util.*;

public class Abonnement {

    private TypeAbonnement type;
    public TypeAbonnement getType() { return type; }
    public void setType(TypeAbonnement type) { this.type = type; }

    private LocalDate debut;
    public LocalDate getDebut() { return debut; }
    public void setDebut(LocalDate debut) { this.debut = debut; }

    private LocalDate fin;
    public LocalDate getFin() { return fin; }
    public void setFin(LocalDate fin) { this.fin = fin; }

    public boolean estValide(LocalDate date) {
        throw new UnsupportedOperationException("TODO");
    }

}
