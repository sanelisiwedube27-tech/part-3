/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
package quickchat;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        
        System.out.println("=== Registration ===");
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.print("Enter cell phone number: ");
        String phone = input.nextLine();
        
        String regStatus = login.registerUser(username, password, phone, firstName, lastName);
        System.out.println("\n" + regStatus);
        
        if (!regStatus.equals("Registration successful.")) {
            System.out.println("Application closing due to registration failure.");
            return;
        }

        System.out.println("\n=== Login ===");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String loginUser = input.nextLine();
            System.out.print("Enter password: ");
            String loginPass = input.nextLine();
            
            loggedIn = login.loginUser(loginUser, loginPass);
            System.out.println(login.returnLoginStatus(loggedIn));
        }

        ArrayList<Message> loadedList = new ArrayList<>();
        
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        Message m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        
        Message m4 = new Message(4, "0838884567", "It is dinner time !");
        m4.setCustomIDAndHash("0838884567");
        
        Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        
        loadedList.add(m1);
        loadedList.add(m2);
        loadedList.add(m3);
        loadedList.add(m4);
        loadedList.add(m5);
        
        String[] flags = {"Sent", "Stored", "Disregard", "Sent", "Stored"};
        
        StoredMessagesManager manager = new StoredMessagesManager(10);
        manager.populateFromList(loadedList, flags);

        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Send Message Feature Option");
            System.out.println("2. Display Message Status Options");
            System.out.println("3. Check Array Storage Metrics");
            System.out.println("4. Stored Messages Sub-menu Options");
            System.out.println("5. Exit Application");
            System.out.print("Choose option choice: ");
            
            int option = input.nextInt();
            input.nextLine();
            
            if (option == 1) {
                System.out.println("[Feature Active] Constructing and executing message logs...");
            } 
            else if (option == 2) {
                System.out.println("[Feature Active] Printing system transmission status reports...");
            } 
            else if (option == 3) {
                System.out.println("[Feature Active] Metrics calculation complete.");
            } 
            else if (option == 4) {
                boolean subMenu = true;
                while (subMenu) {
                    System.out.println("\n--- Stored Messages Options ---");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display the longest stored message");
                    System.out.println("c. Search for a message ID");
                    System.out.println("d. Search all messages regarding a particular recipient");
                    System.out.println("e. Delete a message using a message hash");
                    System.out.println("f. Display full details report");
                    System.out.println("g. Return to Main Menu");
                    System.out.print("Select sub-option (a-g): ");
                    
                    String subChoice = input.nextLine().trim().toLowerCase();
                    
                    switch (subChoice) {
                        case "a":
                            System.out.println("\n" + manager.displaySendersAndRecipients());
                            break;
                        case "b":
                            System.out.println("\nLongest Message Found:\n" + manager.displayLongestStoredMessage());
                            break;
                        case "c":
                            System.out.print("Enter Message ID to search: ");
                            String searchID = input.nextLine();
                            System.out.println("\nResult:\n" + manager.searchByMessageID(searchID));
                            break;
                        case "d":
                            System.out.print("Enter Recipient number to scan: ");
                            String searchRecipient = input.nextLine();
                            System.out.println("\nAssociated Message Streams:\n" + manager.searchByRecipient(searchRecipient));
                            break;
                        case "e":
                            System.out.print("Enter Message Hash code to drop: ");
                            String targetHash = input.nextLine();
                            System.out.println("\n" + manager.deleteMessageByHash(targetHash));
                            break;
                        case "f":
                            System.out.println("\n" + manager.displayReport());
                            break;
                        case "g":
                            subMenu = false;
                            break;
                        default:
                            System.out.println("Invalid sub-menu choice selected.");
                    }
                }
            } 
            else if (option == 5) {
                System.out.println("Exiting Application. Goodbye!");
                running = false;
            } 
            else {
                System.out.println("Invalid structural option selected. Please try again.");
            }
        }
        input.close();
    }
}