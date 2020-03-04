package com.codehunt.banks;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeBank implements Serializable {
    private transient Stack<String> newCodes = new Stack<>();
    private Set<String> foundCodes = new HashSet<>();


    /* Helper method for storeSiteCode.
    *  Assists in finding and placing codes into the
    *  HashSet instance variable.  */
    public int findCodeInString(String code){

        // Changed pattern from \w to a more strict pattern.
        Pattern pattern = Pattern.compile("([A-Z0-9]{5}-[A-Z0-9]{5}-[A-Z0-9]{5}-[A-Z0-9]{5}-[A-Z0-9]{5})");
        Matcher matcher = pattern.matcher(code);

        if (matcher.find()) {
            // If the code is not a copy, push it onto the newCodes Stack
            if (!checkForNewCode(matcher.group())){
                this.newCodes.push(matcher.group());
                return 0;
            }
        }
        // returns 1 if there wasn't a unique code to push onto the newCodes Stack
        return 1;
    }

    // A custom method for those who would like to pass in their own pattern searches
    public int findCodeInString(String code, String stringPattern){

        // Changed pattern from \w to a more strict pattern.
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(code);

        if (matcher.find()) {
            // If the code is not a copy, push it onto the newCodes Stack
            if (!checkForNewCode(matcher.group())){
                this.newCodes.push(matcher.group());
                return 0;
            }
        }
        // returns 1 if there wasn't a unique code to push onto the newCodes Stack
        return 1;
    }

    private boolean checkForNewCode(String code){
        // If the code is already in this.foundCodes, return true, else return false
        return this.foundCodes.contains(code);
    }

    // This saves the state of foundCodes into a file called codes.dat
    public void saveCodes(){

        try(FileOutputStream fileOutput = new FileOutputStream("codes.dat");
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){

            for (String code : this.foundCodes){
                objectOutput.writeObject(code);
            }
            // Try block automatically closes OOS and FOS
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    // Retrieves String codes of foundCodes from previous run
    public void readCodes(){

        try(FileInputStream fileStream = new FileInputStream("codes.dat");
            ObjectInputStream objectStream = new ObjectInputStream(fileStream)){

            String codeFromDead;
            if ( this.foundCodes.isEmpty() ){

                while((codeFromDead = (String) objectStream.readObject()) != null){

                    this.foundCodes.add(codeFromDead);
                }
            }
        } catch (Exception e) {
            System.out.println("Done reading codes...");; }
    }

    public String yankCode(){
        return this.newCodes.pop();
    }

    public void showCodes(){

        for(String code: this.foundCodes){
            System.out.println(code);
        }
        System.out.println("Total codes: " + this.foundCodes.size());
    }
}
