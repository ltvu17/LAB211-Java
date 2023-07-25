package menu;

import controller.ManagingDealers;
import java.util.ArrayList;
import java.util.Scanner;
import model.Users;

public class LoginLogout {

    public LoginLogout() {

    }

    public boolean loginLogout() {
        int choice = 0;
        boolean temp = true, role = false;
        String[] login = {"Log in", "Exit"};
        String[] logout = {"Log out", "Exit"};
        Scanner sc = null;
        String userName = "", passWord = "";
        String pathUsers = "src\\input\\users.csv";
        ManagingDealers obj = new ManagingDealers();

        ArrayList<Users> user = obj.getUsersFromFile(pathUsers);
        do {
            while ((choice = Menu.getChoice(login)) != 1) {
                if (choice == 2) {
                    return false;
                }
            }
            System.out.print("User Name: ");
            sc = new Scanner(System.in);
            userName = sc.nextLine();
            System.out.print("Pass Word: ");
            sc = new Scanner(System.in);
            passWord = sc.nextLine();
            role = true;
            for (Users u : user) {
                if (userName.equals(u.getUserName()) && passWord.equals(u.getPassWord())) {
                    if (u.getRole().equals("R001")) {
                        System.out.println("Your Role: " + u.getRole());
                        temp = false;
                        break;                        
                    } else {
                        System.out.println(u.getRole() + " role is Invalid now");
                        role = false;
                        while ((choice = Menu.getChoice(logout)) != 1) {
                            if (choice == 2) {
                                return false;
                            }
                        }
                        break;
                    }

                }
            }
            if (role == true && temp == true) {
                System.out.println("Invalid account");
            }
        } while (temp);

        return true;

    }
}
