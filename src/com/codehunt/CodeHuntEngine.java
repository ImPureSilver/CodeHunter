package com.codehunt;

import com.codehunt.banks.CodeBank;
import com.codehunt.banks.WebsiteBank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class CodeHuntEngine {

    private WebsiteBank websiteBank;
    private CodeBank codeBank;


    public CodeHuntEngine() {
        this.codeBank = new CodeBank();
        this.websiteBank = new WebsiteBank();
    }

    public int recoverSavedStuff(){

        codeBank.readCodes();
        websiteBank.readWebsites();
        return 0;
    }

    public void showAllCodes(){

        this.codeBank.showCodes();
    }

    public int removeSite(String site){
        // If websiteBank succeeds in removing the site, it'll return 0
        if (this.websiteBank.removeWebsite(site) == 0){
            System.out.printf("Done removing %s\n" , site);
            return 0;
        }

        System.out.printf("Can't remove %s\n" , site);
        return 1;
    }

    public int addSite(String site) {
        // If websiteBank succeeds in adding the site, it'll return 0
        if (this.websiteBank.addWebsite(site) == 0) {
            System.out.printf("Done adding %s\n" , site);
            return 0;
        }

        System.out.printf("Can't add %s\n" , site);
        return 1;
    }

    public void showAllWebsites(){
        this.websiteBank.showSites();
    }

    public void pullAllCodesFromSites() {

        URL             url;
        InputStream     inputStream;
        BufferedReader  reader;

        for (String site : this.websiteBank.pullSites()) {

            try {

                url = new URL(site);
                inputStream = url.openStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line;

                while ((line = reader.readLine()) != null){

                    /* Pass the String value to CodeBank to determine
                     * whether or not the code is new or old. */
                    this.codeBank.findCodeInString(line);
                }
            } catch (MalformedURLException mue) {
                System.out.printf("Link %s may not be typed correctly.\n", site);
            } catch (IOException ioe){
                System.out.printf("Link %s blocked me from scraping :(\n", site);
            }
        }
    }
}
