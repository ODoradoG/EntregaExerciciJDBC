package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {

	public static final Scanner sc = new Scanner(System.in);

	public int menuPrincipal() {
		int opcio = -1;
		while (opcio < 0 || opcio > 5) {
			System.out.println("--- Menú pràctica JDBC ---");
			System.out.println("1. Llistar esports");
			System.out.println("2. Afegir esport");
			System.out.println("3. Llistar atletes per esport");
			System.out.println("4. Afegir atleta");
			System.out.println("5. Buscar atletes per nom");
			System.out.println("0. Sortir");
			System.out.print("Tria una opció: ");
			if (sc.hasNextInt()) {
				opcio = sc.nextInt();
				sc.nextLine();
			} else {
				sc.nextLine();
				opcio = -1;
			}
			if (opcio < 0 || opcio > 5) {
				System.out.println("Opció no vàlida, prova una altra vegada.");
			}
		}
		return opcio;
	}

	public Esport esportForm() {
		System.out.println("Introdueix el nom de l'esport:");
		String nom = sc.nextLine();
		if (nom == null) {
			return null;
		}
		return new Esport(nom.trim());
	}

	public Atleta atletaForm() {
		System.out.println("Introdueix el nom de l'atleta:");
		String nom = sc.nextLine();
		if (nom == null) {
			return null;
		}
		System.out.println("Introdueix l'ID de l'esport:");
		int id = -1;
		try { id = Integer.parseInt(sc.nextLine()); } catch (Exception e) { id = -1; }
		if (id < 0) {
			return null;
		}
		return new Atleta(nom.trim(), id);
	}

	public Integer demanaEsport() {
		System.out.println("Introdueix l'ID de l'esport:");
		try { return Integer.parseInt(sc.nextLine()); } catch (Exception e) { return null; }
	}

	public void llistaEsports(List<Esport> llista) {
		System.out.println("LLISTA D'ESPORTS:");
		if (llista == null) {
			System.out.println("(cap esport registrat)");
			return;
		}
		for (Esport e : llista) {
			System.out.println(e.getId() + ") " + e.getName());
		}
	}

	public void llistaAtletes(List<Atleta> llista) {
		System.out.println("LLISTA D'ATLETES:");
		if (llista == null) {
			System.out.println("(cap atleta)");
			return;
		}
		for (Atleta a : llista) {
			System.out.println(a.getId() + ") " + a.getName() + " - " + (a.getSportName() == null ? "(desconegut)" : a.getSportName()));
		}
	}



}

