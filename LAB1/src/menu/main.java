package menu;

import controller.ManagingDealers;
import java.util.ArrayList;
import java.util.Scanner;
import model.Users;

public class main {

    public static void main(String[] args) {
        int choice = 0;
        Scanner sc = null;
        boolean flag = false;

        String pathDealers = "src\\input\\dealers.csv";
        String pathWrite = "src\\output\\newdealer.csv";
        String[] options = {"Add new Dealer", "Search a Dealer(by Name)", "Remove a Dealer(by ID)", "Update a Dealer(by ID)", "Print all Dealer(all/active/inactive)",
            "Save all to file", "Log out", "Quit"};

        ManagingDealers obj = new ManagingDealers(pathDealers);
        LoginLogout log = new LoginLogout();
        
        flag = log.loginLogout();
        if (flag) {
            do {
                choice = Menu.getChoice(options);
                switch (choice) {
                    case 1:
                        obj.addNewDealer();
                        break;
                    case 2:
                        String name;
                        System.out.println("Input name to find: ");
                        sc = new Scanner(System.in);
                        name = sc.nextLine().trim();
                        obj.SearchADealer(name);
                        break;
                    case 3:
                        String id;
                        System.out.println("Input Dealer's ID: ");
                        sc = new Scanner(System.in);
                        id = sc.nextLine().trim().toUpperCase().trim();
                        obj.removeADealer(id);
                        break;
                    case 4:
                        String id1;
                        System.out.println("Input Dealer's ID: ");
                        sc = new Scanner(System.in);
                        id1 = sc.nextLine().trim().toUpperCase();
                        obj.updateADealer(id1);
                        break;
                    case 5:
                        obj.printAll();
                        break;
                    case 6:
                        obj.saveToFile(pathWrite);
                        break;
                    case 7:
                        if(!log.loginLogout()) return;
                        break;
                }
            } while (choice >= 1 && choice < 8);
        }
    }
}
