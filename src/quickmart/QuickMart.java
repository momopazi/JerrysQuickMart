package quickmart;
//@author Maria Paz Cortez
//This is the project's main class, where all the objects are created and the main procedures are called

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class QuickMart {

    public static Lista pr = new Lista();
    public static Lista prBought = new Lista();
    static Scanner sc = new Scanner(System.in);
    static String name, ts;
    static int q, userMenu, customerStat, op;
    static float price, mPrice, rPrice, saved;
    private static DecimalFormat df2 = new DecimalFormat(".##");


    public static void main(String[] args) {
        int tNumber=0;
        
        try {
            do {
                System.out.println("\n***************MENU***************");
                System.out.println("1. Load inventory from text file.");
                System.out.println("2. Register a transaction");
                System.out.println("Inser any other number to EXIT");
                userMenu = sc.nextInt();
                switch (userMenu) {
                    case 1:
                        readFile();
                        break;
                    case 2:
                        transaction(tNumber);
                        tNumber++;
                        prBought.clearAll();
                        break;
                    default:
                        System.out.println("Please enter only one of the numbers listed on the menu.");
                        break;
                }
            } while (userMenu > 0 && userMenu < 3);
        } catch (Exception e) {
            System.out.println("Please use onlye de given options." + "\n" + " ERROR:" + e);
        }
    }

    private static void readFile() {
        //This Try is meant to impede the application from crashing if any problem with the text file shall occur.
        try {
            //The txt will be read from the c:\\ location, which is the most common folder in all computers and language independent
            BufferedReader br = new BufferedReader(new FileReader("C:\\inventory.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(":|,");
                name = tokens[0];
                q = Integer.parseInt(tokens[1].trim());
                rPrice = Float.parseFloat(tokens[2].trim().replace("$", ""));
                mPrice = Float.parseFloat(tokens[3].trim().replace("$", ""));
                ts = tokens[4].trim();
                pr.ingresarFin(new Product(name, q, mPrice, rPrice, ts));
                System.out.println(line + " ----> Was added to the inventory");
            }
        } catch (Exception e) {
            //I the file is not located or something else happens, this catch will tell the user to try again.
            System.out.print("Your 'inventory.txt' file could not be opened. Please make sure it is located in the correct folder (C:\\) and has the proper file name." + "\n" + "ERROR: " + e);
        }
    }

    private static void printReceipt(int tNumber, Lista prB) {
        ++tNumber;
        try {
            float total = prB.totalMoney();
            float savedT = prB.totalSaved();
            float uCash = 0;
            do {
                System.out.println("Insert the costumer's chash: $");
                uCash = sc.nextFloat();
                if (uCash < total) {
                    System.out.println("That is not enough cash.");
                }
            } while (uCash < total);
            String dateTime = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
            PrintWriter writer = new PrintWriter("transaction_" + tNumber + "_" + dateTime + ".txt", "UTF-8");
            java.util.Date date = new java.util.Date();
            writer.println(date);
            writer.println("TRANSACTION: " + tNumber + "\n");
            writer.println(Arrays.deepToString(prB.imprimir()));
            writer.println("NUMBER OF ITEMS SOLD:" + prB.totalItems());
            writer.println("SUB-TOTAL: $" + df2.format(total));
            writer.println("TAX (6.5%): $" + prB.Taxation());
            writer.println("TOTAL: $" + df2.format(total + prB.Taxation()));
            writer.println("CASH: $" + uCash);
            String change = df2.format((uCash - total));
            writer.println("CHANGE: $" + change);
            writer.println("YOU SAVED: $" + df2.format(savedT) + "!");
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("There was a problem printing the receipt, please retry. ERROR:" + e);
        }
        prBought.clearAll();
    }

    private static void transaction(int tNumber) {
        try {
            System.out.println("Enter: \n1.Rewards Member \n2.Regular Customer");
            customerStat = sc.nextInt();
            do {
                System.out.println("\n************TRANSACTION************\nPlease Choose an option.");
                System.out.println("1. Insert a product.");
                System.out.println("2. Print Receipt");
                System.out.println("Any other number to cancel");
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Enter product name: ");
                        name = sc.nextLine().trim();
                        name = sc.nextLine().trim();
                        System.out.println("Enter quantity bought:");
                        q = sc.nextInt();
                        if (customerStat == 1) {
                            price = pr.buscar(name).getMemberprice();
                            saved = pr.buscar(name).getRegularPrice() - price;
                        } else {
                            price = pr.buscar(name).getRegularPrice();
                            saved = 0;
                        }
                        //pr.buyProduct(name, q);
                        ts = pr.buscar(name).getTaxStatus();
                        if (pr.buyProduct(name, q)) {
                            prBought.ingresarInicio(new Product(name, q, price, saved, ts));
                        }
                        break;
                    case 2:
                        printReceipt(tNumber, prBought);
                        tNumber++;
                        prBought.clearAll();
                        break;
                    default:
                        System.out.println("Please select valid options.");
                        break;
                }

            } while (op==1);

        } catch (Exception e) {
            System.out.println("Something went wrong during the transaction!" + "\n" + "Remember to load an inventory first and choose only valid options." + "\n" + " ERROR:" + e);
        }
    }
}
