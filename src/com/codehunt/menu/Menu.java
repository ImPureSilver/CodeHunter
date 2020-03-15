package com.codehunt.menu;

import com.codehunt.CodeHuntEngine;

import java.util.Scanner;

// This class is the menu for the program.
public class Menu {

    public Menu(){
        // Empty constructor
    }

    private void greeting(){
        System.out.println("\n\n[C O D E  H U N T E R]\n");
    }

    public void show(boolean menuIsRunning){

        CodeHuntEngine engine = new CodeHuntEngine();
        greeting();
        while (menuIsRunning){

            System.out.println("(1) Show Codes");
            System.out.println("(2) Add a website");
            System.out.println("(3) Remove a website");
            System.out.println("(4) Scan for codes");
            System.out.println("(5) Show all saved websites");

            switch (new Scanner(System.in).nextLine()){

                case "exit":
                    System.out.println("Goodbye!");
                    menuIsRunning = false;
                    break;

                case "1":
                    // Show all of the codes
                    System.out.println("Displaying code...");
                    engine.showAllCodes();
                    break;

                case "2":
                    // Add the site
                    // Ask user for the site and pass it to CodeHuntEngine to add it
                    System.out.println("Website to add:");
                    String websiteToAdd = new Scanner(System.in).nextLine();

                    if (websiteToAdd.equals("") || websiteToAdd.equals(" "))
                    System.out.println("returning to Main menu...");

                    // Pass website into CodeHuntEngine for storage
                    else if (engine.addSite(websiteToAdd) == 1)
                        System.out.println("Can't add the website");
                        engine.saveState();

                    System.out.println("Website added!");
                    break;

                case "3":
                    // Remove a site
                    // Ask user for site and pass it to CodeHuntEngine to remove it
                    engine.showAllWebsites();
                    System.out.println("Website to remove:");
                    String websiteToRemove = new Scanner(System.in).nextLine();

                    if (websiteToRemove.equals("") || websiteToRemove.equals(" ")) {
                        System.out.println("Returning to Main menu...");
                        break;
                    }

                    engine.removeSite(websiteToRemove);
                    // Pass website into CodeHuntEngine for removal
//                    if (engine.removeSite(websiteToRemove) == 1)
//                        System.out.println("That site isn't in the list");
                    engine.saveState();

                    System.out.println("Site has been removed!");
                    break;

                case "4":
                    // Tell CodeHuntEngine to use all the sites and get codes
                    System.out.println("Scanning for codes...");
                    engine.pullAllCodesFromSites();
                    System.out.println("Done!");
                    break;

                case "5":
                    System.out.println("Showing all sites...");
                    engine.showAllWebsites();
                    break;

                default:
                    System.out.println("Invalid option...");
            }
        }
    }
}
