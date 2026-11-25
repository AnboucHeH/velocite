package velocite;

public class Borne {

    // Attributs
    private int numero;

    // Associations
    private Velo velo;

    // Constructeur sans paramètre
    public Borne() {
    }

    // Accesseurs et modificateurs
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    // Opérations
    public boolean estLibre() {
        throw new UnsupportedOperationException("TODO");
    }
}
