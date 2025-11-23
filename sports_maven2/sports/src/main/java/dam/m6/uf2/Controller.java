package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private Connection conn;

	public static void main(String[] args) {
		System.out.println("Directori actual: " + System.getProperty("user.dir"));
		Controller app = new Controller();
		app.run();
	}

	private void run() {
		conn = ConnectionManager.getConnection("database.xml");
		if (conn == null) {
			System.err.println("No s'ha pogut connectar a la base de dades. Revisa database.xml");
			return;
		}

		MainView view = new MainView();

		int opt = -1;
		while (opt != 0) {
			opt = view.menuPrincipal();
			switch (opt) {
				case 1:
					llistarEsports(view);
					break;
				case 2:
					afegirEsport(view);
					break;
				case 3:
					llistarAtletesPerEsport(view);
					break;
				case 4:
					afegirAtleta(view);
					break;
				case 5:
					buscarAtletes(view);
					break;
				case 0:
					System.out.println("Sortint...");
					break;
				default:
					System.out.println("Opció no vàlida, prova de nou.");
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	private List<Esport> getAllSports() {
		List<Esport> llista = new ArrayList<>();
		String sql = "SELECT * FROM llista_esports()";
		try (PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Esport e = new Esport(rs.getInt("cod"), rs.getString("nom"));
				llista.add(e);
			}
		} catch (SQLException e) {
			System.err.println("Error obtenint esports: " + e.getMessage());
		}
		return llista;
	}

	private void llistarEsports(MainView view) {
		List<Esport> esports = getAllSports();
		view.llistaEsports(esports);
	}

	private void afegirEsport(MainView view) {
		Esport e = view.esportForm();
		if (e == null || e.getName() == null) {
			System.out.println("Nom d'esport invàlid.");
			return;
		}
		String sql = "INSERT INTO DEPORTES(NOMBRE) VALUES(?) RETURNING COD";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, e.getName());
				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						System.out.println("Esport afegit amb codi: " + rs.getInt(1));
					}
				}
		} catch (SQLException ex) {
			System.err.println("Error afegint esport: " + ex.getMessage());
		}
	}

	private void llistarAtletesPerEsport(MainView view) {
		List<Esport> esports = getAllSports();
		view.llistaEsports(esports);
		Integer id = view.demanaEsport();
		if (id == null || id < 0) {
			System.out.println("ID invàlid.");
			return;
		}
		String sql = "SELECT * FROM llista_atletes(?)";
		List<Atleta> atletes = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Atleta a = new Atleta(rs.getInt("cod"), rs.getString("nom"), id);
					a.setEsportNom(rs.getString("esport_nom"));
					atletes.add(a);
				}
			}
		} catch (SQLException ex) {
			System.err.println("Error llistant atletes: " + ex.getMessage());
		}
		view.llistaAtletes(atletes);
	}

	private void afegirAtleta(MainView view) {
		Atleta a = view.atletaForm();
		if (a == null || a.getName() == null || a.getSportId() <= 0) {
			System.out.println("Dades d'atleta invàlides.");
			return;
		}
		String sql = "INSERT INTO ATLETAS(NOMBRE, DEPORTE_COD) VALUES(?,?) RETURNING COD";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, a.getName());
			ps.setInt(2, a.getSportId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					System.out.println("Atleta afegit amb codi: " + rs.getInt(1));
				}
			}
		} catch (SQLException ex) {
			System.err.println("Error afegint atleta: " + ex.getMessage());
		}
	}

	private void buscarAtletes(MainView view) {
		System.out.println("Introdueix què vols buscar:");
		String cadena = MainView.sc.nextLine();
		if (cadena == null) {
            System.out.println("Escriu alguna cosa per buscar.");
            return;
        }
		String sql = "SELECT a.cod, a.nombre AS nom, d.nombre AS esport_nom FROM ATLETAS a LEFT JOIN DEPORTES d ON a.deporte_cod = d.cod WHERE LOWER(a.nombre) LIKE ? ORDER BY a.cod";
		List<Atleta> res = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + cadena.toLowerCase() + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Atleta a = new Atleta(rs.getInt("cod"), rs.getString("nom"), 0);
					a.setEsportNom(rs.getString("esport_nom"));
					res.add(a);
				}
			}
		} catch (SQLException ex) {
			System.err.println("Error buscant atletes: " + ex.getMessage());
		}
		view.llistaAtletes(res);
	}
}

