package velocite;

import java.time.*;
import java.util.*;

public class Borne {

    private int numero;
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    private Velo velo;  // 0..1
    public Velo getVelo() { return velo; }
    public void setVelo(Velo velo) { this.velo = velo; }

    public boolean estLibre() {
        throw new UnsupportedOperationException("TODO");
    }

}
