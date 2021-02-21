package org.example;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args ) throws IOException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
                try {
                    WorkDBImpl.init();
            try {

                while (true) {
                    System.out.println("1: add client");
                    System.out.println("2: delete client");
                    System.out.println("3: view clients");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            WorkDBImpl.addClient();
                            break;
                        case "2":
                            WorkDBImpl.deleteClient();
                            break;
                        case "3":
                            WorkDBImpl.viewClients();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }




    }
}
