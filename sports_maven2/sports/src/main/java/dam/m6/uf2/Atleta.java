package dam.m6.uf2;

public class Atleta {
    private int cod;
    private String nom;
    private int codEsport;
    private String esportNom;

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

    public int getId() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public String getName() {
        return nom;
    }

    public int getCodEsport() {
        return codEsport;
    }

    public int getSportId() {
        return codEsport;
    }

    public void setCodEsport(int codEsport) {
        this.codEsport = codEsport;
    }

    public String getEsportNom() {
        return esportNom;
    }

    public String getSportName() {
        return esportNom;
    }

    public void setEsportNom(String esportNom) {
        this.esportNom = esportNom;
    }
}
