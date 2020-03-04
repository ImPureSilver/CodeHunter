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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readWebsites(){

        try(FileInputStream inputStream = new FileInputStream("websites.dat");
            ObjectInputStream objectStream = new ObjectInputStream(inputStream)){

            String websiteObject;
            // If the list already has loaded websites, let user know that's already done
            if (!this.websites.isEmpty()) { System.out.println("List already has values!"); }

            while ((websiteObject = (String) objectStream.readObject()) != null) { this.websites.add(websiteObject); }

        } catch (Exception e) {
            System.out.println("Done reading websites...");
        }
    }

    public int addWebsite(String website){

        // Delete any whitespace in the string
        website = website.trim();
        // If the website is anlready in the list, it can't be added again
        if (this.websites.contains(website)){
            System.out.println("Already exists");
        }
        // If the website list is empty, reload it
        if (this.websites.isEmpty()) {
            readWebsites();
            // If the website list is still empty, there are no elements to load.
            if (this.websites.isEmpty()){
                System.out.println("There's nothing to be loaded");
                return 1;
            }
            // If the site is already in the list, let the user know it's there
            if (this.websites.contains(website)){
                System.out.println("Already exists");
                return 1;
            }
        }
        // If all if statements fail, then the site can be added and saved
        this.websites.add(website);
        saveWebsites();
        return 0;
    }

    public int removeWebsite(String website){
        // Remover any whitespace on the string.
        website = website.trim();
        // If the link is in NOT in the list, there's nothing to remove.
        if (!this.websites.contains(website)) {
            System.out.println("Not in the list to be removed.");
            return 1;
        }
        // If the site list is empty, repopulate it.
        if (this.websites.isEmpty()){
            readWebsites();
            // If the list is empty, then there are no sites to be loaded
            if (this.websites.isEmpty()){
                System.out.println("There's nothing to reload.");
                return 1;
            }
            // If the site is NOT in the website list, then it can't be removed
            if (!this.websites.contains(website)){
                System.out.println("Not in the list to be removed.");
                return 1;
            }
        }
        // If all other conditions fail, then the site is in the list and can be removed and saved.
        this.websites.remove(website);
        saveWebsites();

        return 0;
    }

    public void showSites(){

        // If site list is empty, popelate it
        if (this.websites.isEmpty()) {
            readWebsites();
            // If the site list is still empty, say, "There's nothing to reload."
            if (this.websites.isEmpty()) { System.out.println("There's nothing to reload."); }
        }
        // If site list isn't empty, print each one out.
        for (String site : this.websites){
            System.out.println(site);
        }
        // Display total number of sites
        System.out.println("Number of sites: " + this.websites.size());
    }

    public List<String> pullSites() {

        List<String> websiteListCopy = new ArrayList<>();

        // Takes instance data, copies it to new array, returns new array
        for (String weblink : this.websites){
            websiteListCopy.add(weblink);
        }

        return websiteListCopy;
    }
}
