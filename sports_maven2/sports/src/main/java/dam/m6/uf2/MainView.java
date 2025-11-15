package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {

    /**
     * @return
     */
    public int mainMenu() {
        // TODO Auto-generated method stub
        System.out.println("1. Llistar esports\n2. Afegir esport\n3. Llistar usuaris\n4. Afegir usuari\n0. Sortir");
        Scanner sc = new Scanner(System.in);
        int option = -1;
        try {
            option = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            option = -1;
        }
        return option;
        //throw new UnsupportedOperationException("Unimplemented method 'mainMenu'");
    }

    public void showUsers(List<User> list) {
        System.out.println("MOSTRANT LLISTAT USUARIS....");
        for (User user : list) {
            System.out.println(user.name + "    " + user.password);
        }
    }


    public User addUserForm(){
        System.out.println("FORMULARI USUARI....nom, pass, etc");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        String password = "1234";

        return new User(username,password);
    }

    public Esport esportForm(){
        System.out.println("FORMULARI ESPORT - Introdueix el nom de l'esport:");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        if (nom == null || nom.trim().isEmpty()) {
            return null;
        }
        return new Esport(nom.trim());
    }

    public void mostrarLlistaEsports(java.util.List<Esport> llista){
        System.out.println("LLISTA D'ESPORTS:");
        if (llista == null || llista.isEmpty()){
            System.out.println("(cap esport registrat)");
            return;
        }
        for (Esport e : llista){
            System.out.println(e.getCod() + ") " + e.getNom());
        }
    }


}
