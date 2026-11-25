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

}
