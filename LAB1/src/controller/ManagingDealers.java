package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Dealers;
import model.Users;

public class ManagingDealers implements Functions {

    ArrayList<Dealers> deal = new ArrayList<Dealers>();
    ArrayList<Users> use = new ArrayList<Users>();
    
    status sta = null;
    Scanner sc = null;

    public ManagingDealers() {

    }

    public ManagingDealers(String path) {
        deal = getDealerFromFIle(path);

    }

    public ArrayList<Dealers> getDealerFromFIle(String path) {
        ArrayList<Dealers> deal1 = new ArrayList<Dealers>();
        try {
            status s;
            String line = "";
            String temp = " | ";
            File f = new File(path);
            String fullpath = f.getAbsolutePath();
            FileInputStream file = new FileInputStream(fullpath);
            BufferedReader myInput = new BufferedReader(new InputStreamReader(file));
            line = myInput.readLine();
            Dealers dea = null;
            while ((line = myInput.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] split = line.split(" \\| ");
                    s = sta.valueOf(split[4].toUpperCase());
                    dea = new Dealers(split[0], split[1], split[2], (split[3]), s);
                    deal1.add(dea);
                }
            }
            myInput.close();
        } catch (Exception e) {
            System.out.println("Error" + e);

        }

        return deal1;

    }

    public ArrayList<Users> getUsersFromFile(String path) {
        ArrayList<Users> user1 = new ArrayList<Users>();
        try {
            status s;
            String line = "";
            File f = new File(path);
            String fullpath = f.getAbsolutePath();
            FileInputStream file = new FileInputStream(fullpath);
            BufferedReader myInput = new BufferedReader(new InputStreamReader(file));
            line = myInput.readLine();
            Users use = null;
            while ((line = myInput.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] split = line.split(" \\| ");
                    s = sta.valueOf(split[3].toUpperCase());
                    use = new Users(split[0], split[1], split[2], s);
                }
                user1.add(use);
            }
            myInput.close();
        } catch (Exception e) {
            System.out.println("Error");

        }
        use.addAll(user1);
        return use;
    }

    public void addNewDealer() {
        boolean proceed = false;
        String id = "", name = "", address = "", phone = "";
        status status = null;
        String matName = "[D]\\d{3}";
        String matPhone = "\\d{11}";
        do {
            proceed = false;
            System.out.println("Input Dealer ID");
            sc = new Scanner(System.in);
            id = sc.nextLine().toUpperCase().trim();
            if (!id.matches(matName)) {
                System.out.println("Format\"Dxxx\"");
                proceed = true;
                continue;
            }

            for (Dealers dea : deal) {
                if (dea.getId().equalsIgnoreCase(id)) {
                    proceed = true;
                    System.out.println("This ID already existed");
                    continue;
                }

            }

        } while (proceed);
        System.out.println("Input name");
        name = sc.nextLine().trim();
        System.out.println("Input address");
        address = sc.nextLine().trim();
        sc = new Scanner(System.in);
        System.out.println("Input phone");
        do {
            proceed = false;
            phone = sc.nextLine();
            if (!phone.matches(matPhone)) {
                System.out.println("Contain 11 digits");
                proceed = true;
                continue;
            }
            phone = "+" +phone;
        } while (proceed);
        sc = new Scanner(System.in);
        System.out.println("Input status(Enabled or Disabled)");
        do {
            try {

                status = sta.valueOf(sc.nextLine().toUpperCase().trim());

            } catch (Exception e) {
                System.out.println("ENABLED or DISABLED");
            };
        } while (!sta.ENABLED.equals(status) && !sta.DISABLED.equals(status));

        Dealers d = new Dealers(id, name, address, phone, status);
        System.out.println("Added");
        deal.add(d);
    }

    
    public void SearchADealer(String name) {
        for (Dealers p : deal) {
            if (p.getName().toUpperCase().contains(name.toUpperCase())) {
                System.out.println(p);
            }
        }
    }

    
    public void removeADealer(String id) {
        for (Dealers dea : deal) {
            if (dea.getId().equals(id)) {
                deal.remove(dea);
                System.out.println("Removed");
                return;
            }
        }
        System.out.println("Id does not exist");
    }
        
    

    
    public void updateADealer(String id) {
        status status = null;
        boolean proceed = false, find= false;
        String phone ="";
        String matPhone = "\\d{11}";
        for (Dealers dea : deal) {
            if (dea.getId().equals(id)) {
                find= true;
                System.out.println("Update phone number");
                sc = new Scanner(System.in);
                do {
                    proceed = false;
                    phone = sc.nextLine();
                    if (!phone.matches(matPhone)) {
                        System.out.println("Contain 11 digits");
                        proceed = true;
                        continue;
                    }
                    phone = "+" +phone;
                } while (proceed);
                dea.setPhone(phone);
                System.out.println("Update address");
                sc = new Scanner(System.in);
                dea.setAddress(sc.nextLine().trim());
                System.out.println("Update Status(Enable or Disable)");
                sc = new Scanner(System.in);
                do {
                    try {

                        status = sta.valueOf(sc.nextLine().toUpperCase().trim());

                    } catch (Exception e) {
                        System.out.println("ENABLED or DISABLED");
                    };
                } while (!sta.ENABLED.equals(status) && !sta.DISABLED.equals(status));
                dea.setStatus(status);

            }
        }if(!find){
            System.out.println("Id does not exist");
        }else
        System.out.println("Updated");
    }

    public void printAll() {
        String message = "";
        System.out.println("All/Active/Inactive");
        sc = new Scanner(System.in);
        message = sc.nextLine().toUpperCase();
        switch (message) {
            case "ALL":
                for (Dealers dea : deal) {
                    System.out.println(dea);
                }
                break;
            case "ACTIVE":
                for (Dealers dea : deal) {
                    if (dea.getStatus().equals(sta.ENABLED)) {
                        System.out.println(dea);
                    }
                }
                break;
            case "INACTIVE":
                for (Dealers dea : deal) {
                    if (dea.getStatus().equals(sta.DISABLED)) {
                        System.out.println(dea);
                    }
                }
                break;

        }
    }

    ;
    public void saveToFile(String path) {
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Dealers dea : deal) {
                pw.println(dea);
            }
            System.out.println("Completed");

            fw.close();
            pw.close();

        } catch (Exception e) {

        }

    }
;
}
