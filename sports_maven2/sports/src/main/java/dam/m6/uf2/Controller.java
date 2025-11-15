package dam.m6.uf2;

import java.sql.Connection;

public class Controller {
	Connection conn;

	public static void main(String[] args) {
		System.out.println("Current directory (aquí heu de posar el XML): " + System.getProperty("user.dir"));
		Controller controller = new Controller();
		controller.init();
	}

	private void init() {

		this.conn = ConnectionManager.getConnection("database.xml");
		MainView mainView = new MainView();
		UserPgDAO userPgDAO = new UserPgDAO(conn);
		EsportPgDAO esportPgDAO = new EsportPgDAO(conn);

		int option = -1;
		while (option != 0) {
			option = mainView.mainMenu();
			switch (option) {
				case 1:
					java.util.List<Esport> esports = esportPgDAO.getAll();
					mainView.mostrarLlistaEsports(esports);
					break;
				case 2:
					Esport nou = mainView.esportForm();
					if (nou != null) {
						esportPgDAO.add(nou);
						System.out.println("Esport afegit amb codi: " + nou.getCod());
					} else {
						System.out.println("No s'ha creat l'esport (nom buit).");
					}
					break;
				case 3:
					mainView.showUsers(userPgDAO.getAll());
					break;
				case 4:
					User newUser = mainView.addUserForm();
					if (newUser != null) {
						userPgDAO.add(newUser);
					}
					break;
				case 0:
					System.out.println("Sortint...");
					try {
						if (conn != null) conn.close();
					} catch (Exception e) {
                        System.err.println("Error tancant la connexió: " + e.getMessage());
                    }
					break;
				default:
					System.out.println("Opció no vàlida.");
			}
		}




	}
}
