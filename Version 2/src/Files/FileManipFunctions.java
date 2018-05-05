/**
 * Contains all files pertaining to Files
 */
package Files;
//imports

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * Note: XML structure
 * <Accounts>
 * <Account>
 * <Name></Name>
 * <Wins></Wins>
 * <Losses></Losses>
 * <Ties></Ties>
 * </Account>
 * </Accounts>
 */
/**
 * This class contains static methods which facilitate file interaction
 *
 * @author Richard Tu
 */
public class FileManipFunctions {

    /**
     * deletes the existing file
     */
    private static void deleteFile() {
        try {
            //attempts to delete the file
            String directory = System.getProperty("user.dir");
            File deletedFile = new File(directory + "\\Scoreboard.xml");
            deletedFile.delete();
        } catch (Exception e) {
            ;
        }
    }

    /**
     * used to reset the save file (do not use)
     */
    public static void createFile() {
        //deletes any previous file
        FileManipFunctions.deleteFile();

        //creates a new blank file
        try {
            OutputStream newFile = new FileOutputStream("Scoreboard.xml");
            OutputStream base = new BufferedOutputStream(newFile);
            OutputStreamWriter skeleton = new OutputStreamWriter(base, "8859_1");

            skeleton.write("<?xml version=\"1.0\" ");
            skeleton.write("encoding=\"ISO-8859-1\" standalone=\"no\"?>\r\n");

            //adds the accounts tag
            skeleton.write("<accounts>\r\n");

            //closes the accounts tag
            skeleton.write("</accounts>\r\n");
            //writes the file
            skeleton.flush();
            skeleton.close();
        } catch (Exception e) {
            System.exit(1);
        }

    }

    /**
     * determines if there is a file
     *
     * @return - returns whether or not the file exists. true for if it does
     */
    public static boolean IsFile() {
        try {
            //attempts to open the file
            DocumentBuilderFactory construct = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = construct.newDocumentBuilder();
            Document ScoreFile = build.parse("Scoreboard.xml");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //saves the Account array to the file
    /**
     * Saves an account list as an XML file
     *
     * @param accounts - a list of accounts to be written to an XML
     */
    public static void saveFile(Account[] accounts) {

        //deletes the save file if it exists
        if (FileManipFunctions.IsFile()) {
            FileManipFunctions.deleteFile();
        }

        try {

            OutputStream newFile = new FileOutputStream("Scoreboard.xml");
            OutputStream writeToFile = new BufferedOutputStream(newFile);
            OutputStreamWriter information = new OutputStreamWriter(writeToFile, "8859_1");

            //writes standard info
            information.write("<?xml version=\"1.0\" ");
            information.write("encoding=\"ISO-8859-1\" standalone=\"no\"?>\r\n");

            //writes the top level of the file
            information.write("<accounts>\r\n");
            //writes each account to the savefile
            for (int i = 0; i < accounts.length; i++) {
                information.write("<account>\r\n");
                information.write("<name>" + accounts[i].getName() + "</name>\r\n");
                information.write("<wins>" + Integer.toString(accounts[i].getWins()) + "</wins>\r\n");
                information.write("<losses>" + Integer.toString(accounts[i].getLosses()) + "</losses>\r\n");
                information.write("<ties>" + Integer.toString(accounts[i].getTies()) + "</ties>\r\n");
                information.write("</account>\r\n");
            }

            //closes the top level
            information.write("</accounts>\r\n");

            //writes the file
            information.flush();
            information.close();
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Saves a HashMap as an XML file
     *
     * @param accounts - a HashMap of accounts
     */
    public static void saveFile(HashMap<String, Account> accounts) {
        //deletes the save file if it exists
        if (FileManipFunctions.IsFile()) {
            FileManipFunctions.deleteFile();
        }

        try {

            OutputStream newFile = new FileOutputStream("Scoreboard.xml");
            OutputStream writeToFile = new BufferedOutputStream(newFile);
            OutputStreamWriter information = new OutputStreamWriter(writeToFile, "8859_1");

            //writes standard info
            information.write("<?xml version=\"1.0\" ");
            information.write("encoding=\"ISO-8859-1\" standalone=\"no\"?>\r\n");

            //writes the top level of the file
            information.write("<accounts>\r\n");
            //writes each account to the savefile
            Map<String, Account> saveAccounts = accounts;

            //goes through all entries of HashMap
            for (Map.Entry<String, Account> accountN : saveAccounts.entrySet()) {
                information.write("<account>\r\n");
                information.write("<name>" + accountN.getValue().getName() + "</name>\r\n");
                information.write("<wins>" + Integer.toString(accountN.getValue().getWins()) + "</wins>\r\n");
                information.write("<losses>" + Integer.toString(accountN.getValue().getLosses()) + "</losses>\r\n");
                information.write("<ties>" + Integer.toString(accountN.getValue().getTies()) + "</ties>\r\n");
                information.write("</account>\r\n");
            }

            //closes the top level
            information.write("</accounts>\r\n");

            //writes the file
            information.flush();
            information.close();
        } catch (Exception e) {
            return;
        }
    }

    //
    /**
     * converts the XML file into an account list
     *
     * @return - the list of accounts on the XML
     */
    public static Account[] GetAccountList() {
        //Nodelists for Account properties
        NodeList Names;
        NodeList Wins;
        NodeList Losses;
        NodeList Ties;

        try {
            //gets nodelists from the file
            DocumentBuilderFactory construct = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = construct.newDocumentBuilder();
            Document ScoreFile = build.parse("Scoreboard.xml");

            Names = ScoreFile.getElementsByTagName("name");
            Wins = ScoreFile.getElementsByTagName("wins");
            Losses = ScoreFile.getElementsByTagName("losses");
            Ties = ScoreFile.getElementsByTagName("ties");
        } catch (Exception e) {
            return null;
        }

        //contains the list of the accounts in the XML
        Account[] ListOfAllAccounts = new Account[Names.getLength()];

        //goes through values in each nodeList and copies them into the account list
        for (int i = 0; i < Names.getLength(); i++) {
            Account place = new Account("asdf");
            place.setName(Names.item(i).getTextContent());
            place.setWins(Integer.parseInt(Wins.item(i).getTextContent()));
            place.setLosses(Integer.parseInt(Losses.item(i).getTextContent()));
            place.setTies(Integer.parseInt(Ties.item(i).getTextContent()));
            ListOfAllAccounts[i] = place;

        }

        return ListOfAllAccounts;
    }

    /**
     * converts the XML file into an HashMap
     *
     * @return
     */
    public static HashMap<String, Account> GetAccountHashMap() {
        //Nodelists for Account properties
        NodeList Names;
        NodeList Wins;
        NodeList Losses;
        NodeList Ties;
        try {
            //gets nodelists from the file
            DocumentBuilderFactory construct = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = construct.newDocumentBuilder();
            Document ScoreFile = build.parse("Scoreboard.xml");

            Names = ScoreFile.getElementsByTagName("name");
            Wins = ScoreFile.getElementsByTagName("wins");
            Losses = ScoreFile.getElementsByTagName("losses");
            Ties = ScoreFile.getElementsByTagName("ties");
        } catch (Exception e) {
            return null;
        }

        //contains the HashMap of the accounts in the XML
        HashMap<String, Account> HashMapOfAllAccounts = new <String, Account> HashMap();

        //goes through values in each nodeList and copies them into the HashMap
        //the key is the name of the account
        for (int i = 0; i < Names.getLength(); i++) {
            Account place = new Account("asdf");
            place.setName(Names.item(i).getTextContent());
            place.setWins(Integer.parseInt(Wins.item(i).getTextContent()));
            place.setLosses(Integer.parseInt(Losses.item(i).getTextContent()));
            place.setTies(Integer.parseInt(Ties.item(i).getTextContent()));
            HashMapOfAllAccounts.put(place.getName(), place);
        }

        return HashMapOfAllAccounts;
    }
}
