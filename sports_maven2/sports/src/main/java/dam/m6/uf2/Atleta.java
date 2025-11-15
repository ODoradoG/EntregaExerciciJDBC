package dam.m6.uf2;

public class Atleta {
    private int cod;
    private String nom;
    private int codEsport;

    public Atleta(int cod, String nom, int codEsport) {
        this.cod = cod;
        this.nom = nom;
        this.codEsport = codEsport;
    }

    public Atleta(String nom, int codEsport) {
        this.nom = nom;
        this.codEsport = codEsport;
    }

    public int getCod() {
        return cod;
    }

    public String getNom() {
        return nom;
    }

    public int getCodEsport() {
        return codEsport;
    }
}
