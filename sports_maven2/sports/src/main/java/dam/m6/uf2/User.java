package dam.m6.uf2;

public class User  {
	String nom = "";
	String contrasenya = "";

	public User(String pnom, String pcontrasenya) {
		nom = pnom;
		contrasenya = pcontrasenya;
	}

	public String getName() {
		return nom;
	}

	public String getPassword() {
		return contrasenya;
	}
}
