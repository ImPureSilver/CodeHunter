package com.codehunt.menu;

import com.codehunt.CodeHuntEngine;

import java.util.Scanner;

public class Menu {

    /* TODO:
    *   This class is suppose to act as the main class.
    *   only difference is... I have it used as a menu that
    *   my users can interact with the program while it's
    *   running in the background.*/

    public Menu(){
        // nothing
    }

    private void greeting(){
        System.out.println("Welcome to CodeHunter!!!\n" + "Below are the following actions:\n");
    }

    public void show(boolean menuIsRunning){

        while (menuIsRunning){

            System.out.println("(1) Add a website");
            System.out.println("(2) remove a website");
            System.out.println("(3) Scan for codes");
            System.out.println("(4) SHiFT Account Login");
            System.out.println("(5) Show Codes");
            System.out.println("(6) Settings");
            System.out.println("(7) Let CodeHunter work in background!");
            System.out.println("(8) Show all saved websites");
            System.out.println("(9) ");

            switch (new Scanner(System.in).nextLine()){

                case "exit":
                    System.out.println("Goodbye!");
                    menuIsRunning = false;
//                    System.exit(0);
                    break;


                case "quit":
                    System.out.println("Goodbye!");
                    menuIsRunning = false;
//                    System.exit(0);
                    break;

                case "1":
                    // Ask user for the site and pass it to CodeHuntEngine to add it
                    System.out.println("Website to add:");
                    String websiteToAdd = new Scanner(System.in).nextLine();
                    // Pass website into CodeHuntEngine for storage
                    if (new CodeHuntEngine().addSite(websiteToAdd) == 1)
                        System.out.println("Can't add the website");

                    System.out.println("Website added!");
                    break;
                case "2":
                    // Ask user for site and pass it to CodeHuntEngine to remove it
                    System.out.println("Website to remove:");
                    String websiteToRemove = new Scanner(System.in).nextLine();
                    // Pass website into CodeHuntEngine for removal
                    if (new CodeHuntEngine().removeSite(websiteToRemove) == 1)
                        System.out.println("That site isn't in the list");

                    System.out.println("Site has been removed!");
//                    show(true);
                    break;
                case "3":
                    // Tell CodeHuntEngine to use all the sites and get codes
                    System.out.println("Scanning for codes...");
                    new CodeHuntEngine().pullAllCodesFromSites();
                    System.out.println("Done!");
                    show(true);
                    break;
                case "4":
                    // Ask user for login info and pass it to CodeHuntEngine for it to attempt login

//                    System.out.println("Enter Email:");
//                    String email = new Scanner(System.in).nextLine();
//                    System.out.println("Enter password:");
//                    String password = new Scanner(System.in).nextLine();
//
//                    new CodeHuntEngine().login(email,password);
//                    CodeHuntEngine.confirmlogin();
//
                    break;
                case "5":
                    // Show all of the codes
                    System.out.println("Displaying code...");
                    new CodeHuntEngine().showAllCodes();
                    break;
                case "6":
                    /* Settings Menu
                     * (1) Change code RegEx (Advanced)
                     * (2) Check Login
                     * (3) Change Login Form (Advanced)
                     * (4) Change sleep timer
                     * (5) */
                    break;

                case "7":
                    /* Spawn a thread that allows the program to run
                     * without it needing the main menu and
                     * have it not be a problem also. Meaning, don't make
                     * it so that it's a resource hog and causes issues
                     * with the user's PC!*/
                    break;

                case "8":
                    new CodeHuntEngine().showAllWebsites();
                    break;

                default:
                    System.out.println("Invalid option...");
                    show(true);
                    // mainMenu();
            }
        }
    }
}
