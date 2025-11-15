package dam.m6.uf2;

public class Esport {
    private int cod;
    private String nom;

    public Esport(int cod, String nom) {
        this.cod = cod;
        this.nom = nom;
    }

    public Esport(String nom) {
        this.nom = nom;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return cod + " - " + nom;
    }
}
