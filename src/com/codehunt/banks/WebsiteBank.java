package com.codehunt.banks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WebsiteBank implements Serializable{

    private List<String> websites;

    public WebsiteBank() {

        this.websites = new ArrayList<>();
    }

    public void saveWebsites(){

        try(FileOutputStream fileStream = new FileOutputStream("websites.dat");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)){

            for (String website : this.websites){
                objectStream.writeObject(website);
            }
            System.out.println("\n:::Done saving websites:::\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readWebsites(){

        try(FileInputStream inputStream = new FileInputStream("websites.dat");
            ObjectInputStream objectStream = new ObjectInputStream(inputStream)){

            String websiteObject;
//            // If the list already has loaded websites, let user know that's already done
//            if (!this.websites.isEmpty()) { System.out.println("List already has values!"); }
            while ((websiteObject = (String) objectStream.readObject()) != null) { this.websites.add(websiteObject); }

        } catch (Exception e) {
            System.out.println("\n:::Done reading websites:::\n");
        }
    }

    public int addWebsite(String website){

        // Delete any whitespace in the string
        website.trim();

        // If the website list is empty, reload it
        if (this.websites.isEmpty()) {
            readWebsites();
        }

        // If the site is already in the list, let the user know it's there
        if (this.websites.contains(website)){
            System.out.println("Already exists");
            return 1;
        }

        // If all if statements fail, then the site can be added and saved
        this.websites.add(website);
        saveWebsites();
        return 0;
    }

    public int removeWebsite(String website){

        // Remove any whitespace on the string.
        website.trim();

        // If the link is in NOT in the list, there's nothing to remove.
        if (!this.websites.contains(website)) {
            System.out.println("Not in the list to be removed.");
            return 1;
        }

        // The site is in the list and can be removed and saved.
        this.websites.remove(website);
        saveWebsites();
        return 0;
    }

    public void showSites(){

        // If site list is empty, populate it
        if (this.websites.isEmpty()) {
            readWebsites();
        }

        // If site list isn't empty, print each one out.
        for (String site : this.websites){
            System.out.println(site);
        }
        // Display total number of sites
        System.out.println("Number of sites: " + this.websites.size());
    }

    // Return a copy of the websites
    public List<String> pullSites() {

        List<String> websiteListCopy = new ArrayList<>();

        // Takes instance data, copies it to new array, returns new array
        for (String weblink : this.websites){
            websiteListCopy.add(weblink);
        }

        return websiteListCopy;
    }
}
