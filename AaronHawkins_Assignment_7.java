/*
Aaron Hawkins
12/5/17
 */
package aaronhawkins_assignment_7;

import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import static java.nio.file.StandardOpenOption.CREATE;

public class AaronHawkins_Assignment_7 {

    static String dirPath;
    static String phoneList;

    public static void main(String[] args) {

        displayMenu();

    }

    public static void displayMenu() {

        Scanner scan = new Scanner(System.in);
        int choice = 0;
        //Uses a do/while loop to create interactive menu.
        do {

            System.out.println("\t MENU");
            System.out.println("0. Exit");
            System.out.println("1. Create Directory ");
            System.out.println("2. Create File");
            System.out.println("3. Read Phone List. ");
            System.out.println("4. Delete Phone List. ");
            System.out.println("Please enter a choice");
//Encloses the switch/case/break with a try/catch to check for a number format exception
            try {
                choice = scan.nextInt();
                scan.nextLine();
//Uses a case/break to create the menu and create the "options" using numbers 0-4
                switch (choice) {
                    case 0:
                        System.out.println("Exiting");
                        break;
                    case 1:
                        System.out.println("Create Directory");
                        createDirectories();
                        break;
                    case 2:
                        System.out.println("Create Phone List");
                        CreateTextFile();
                        break;
                    case 3:
                        System.out.println("Read Phone List.");
                        readPhoneList();
                        break;
                    case 4:
                        System.out.println("Delete Phone List");
                        deletePhoneList();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;

                }
            } catch (Exception nfe) {
                System.out.println(nfe.getMessage());
                choice = 99;
                scan.nextLine();
            }
//Closes the program if case 0 is chosen 
        } while (choice != 0);

    }

    static void createDirectories() {
        //Create directories
        Path pathDirectory = Paths.get("C:\\CSCI111\\Hawkins");
        if (Files.exists(pathDirectory)) {
            System.out.println("Directory already exists.");
        } else {

            try {
                Files.createDirectories(pathDirectory);
                System.out.println("Created Directory.");
            } catch (IOException e) {
                System.out.println("IO error in createDirectories()");
            }
        }
    }

    static void CreateTextFile() {
//Creates the phonelist.txt file
        Scanner input = new Scanner(System.in);
        Path file = Paths.get("C:\\CSCI111\\Hawkins\\Phonelist.txt");
        String s = "";
        String delimiter = ",";
        String fName;
        String lName;
        String phonenumber;

        final String QUIT = "999";
        try {
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.print("Enter first name. >> ");
            fName = input.nextLine();
            while (!fName.equals(QUIT)) {
                System.out.print("Enter last name for " + fName + " >> ");
                lName = input.nextLine();
                System.out.print("Enter phone number >> ");
                phonenumber = input.nextLine();
                s = fName + delimiter + lName + delimiter + phonenumber;
                writer.write(s, 0, s.length());
                writer.newLine();
                System.out.print("Enter new person or " + QUIT + " to quit >> ");
                fName = input.nextLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Message: " + e);
        }
    }
//reads the phone list using InputStream and BufferedReader.
    static void readPhoneList() {
        Path file = Paths.get("C:\\CSCI111\\Hawkins\\Phonelist.txt");
        try {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = reader.readLine();
            while (s != null) {
                System.out.println(s);
                s = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Message: " + e);
        }
    }
//deletes the phone list using Files.delete. 
    static void deletePhoneList() {
        Path filePath
                = Paths.get("C:\\CSCI111\\Hawkins\\Phonelist.txt");
        try {
            Files.delete(filePath);
            System.out.println("Deleted file.");
        }
            catch (IOException e) {
            System.out.println("IO Exception.");
        }

        }
    }
