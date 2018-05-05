/**
 * Contains all files pertaining to Files
 */
package Files;

import java.util.HashMap;
import java.util.Map;

/**
 * A class which contains static sort methods for names, highest score Also
 * contains methods for searching for names that start with a key
 *
 * @author Richard Tu
 */
public class SortSearchFunctions {

    /**
     * Adds a value to the end of the list
     *
     * @param addTo - stores the array which the account add is added to
     * @param add - stores the account added to addTo
     * @return returns an array with the added account
     */
    static Account[] addOneToArray(Account[] addTo, Account add) {
        //creates a new array that is 1 index larger than addTo
        Account[] finAdded = new Account[addTo.length + 1];

        //copies addTo to finAdded
        for (int i = 0; i < addTo.length; i++) {
            finAdded[i] = addTo[i];
        }
        //adds add to the end
        finAdded[addTo.length] = add;

        return finAdded;
    }

    /**
     * Finds all the names in a hashMap which starts with a certain string and
     * returns the result as an Account array
     *
     * @param partialKey - stores a String used to find accounts
     * @param inputNames - stores a HashMap of all current accounts
     * @return - returns a list of Accounts with a name matching the partial key
     */
    public static Account[] nameFinder(String partialKey, HashMap<String, Account> inputNames) {
        //creates a new list of accounts called names
        Account[] names = new Account[0];

        //goes through every entry in inputNames
        for (Map.Entry<String, Account> findNames : inputNames.entrySet()) {

            //if the entry's key starts with partial key, add it to the list
            if (findNames.getKey().startsWith(partialKey)) {
                names = addOneToArray(names, inputNames.get(findNames.getKey()));
            }
        }

        return names;
    }

    /**
     * Sorts the accounts based on a highest win, lowest loss, highest tie ratio
     *
     * @param accountList - a list of accounts which need to be sorted
     * @return -returns a sorted list of accounts
     */
    public static Account[] scoreSorter(Account[] accountList) {
        //merge sorts the account
        accountList = scoreSplitter(accountList);

        return accountList;
    }

    /**
     * Splits an account array into 2 and sorts them using scoreMerger
     *
     * @param accountList
     * @return - an a sorted account list
     */
    static Account[] scoreSplitter(Account[] accountList) {
        //gets the midpoint of the account array
        int mid = (accountList.length) / 2;

        //checks if the length is greater than 2, if it is then return a list either
        //length 0 or length 1
        if (accountList.length < 2) {
            return accountList;
        } //otherwise it splits the list into two halves along the mid point
        else {
            //creates two lists which store the two halves the list
            Account[] accountlistCopy1 = new Account[mid];
            Account[] accountlistCopy2 = new Account[accountList.length - mid];

            //copies the first half the list over
            for (int i = 0; i < mid; i++) {
                accountlistCopy1[i] = accountList[i];
            }
            //this is for inputting into the second account list
            int index = 0;
            //copies the second half of the list over
            for (int i = mid; i < accountList.length; i++) {
                accountlistCopy2[index] = accountList[i];
                index++;
            }

            //splits both lists
            accountlistCopy1 = scoreSplitter(accountlistCopy1);
            accountlistCopy2 = scoreSplitter(accountlistCopy2);

            //merges and sorts both halves of the list into one  list
            accountList = scoreMerger(accountlistCopy1, accountlistCopy2);

            return accountList;
        }
    }

    /**
     * increments an index and adds a certain value from addFrom to addTo
     *
     * @param addTo - stores the merged list
     * @param addFrom - stores the list the value is coming from
     * @param indexes - stores an array containing each index
     * @param indexOfindexes - stores the which index is being incremented
     */
    static void incrementor(Account[] addTo, Account[] addFrom, int[] indexes, int indexOfindexes) {
        addTo[indexes[2]] = addFrom[indexes[indexOfindexes]];
        indexes[2] = indexes[2] + 1;
        indexes[indexOfindexes] = indexes[indexOfindexes] + 1;
    }

    /**
     * combines and sorts two arrays based on wins losses and ties
     *
     * @param accountList1 - one half of an account list
     * @param accountList2 - other half of an account list
     * @return - a fully combined and sorted account list
     */
    static Account[] scoreMerger(Account[] accountList1, Account[] accountList2) {
        //stores the indexes of each array
        int[] indexes = {0, 0, 0};
        //creates a list that can store both the lists
        Account[] accountList3 = new Account[accountList1.length + accountList2.length];

        //while both lists have uncompared elements in them
        while (indexes[0] < accountList1.length && indexes[1] < accountList2.length) {

            //if compares the win element of both accounts (at their current indexes
            //add the account that has the larger number of wins to the list
            if (accountList1[indexes[0]].getWins() > accountList2[indexes[1]].getWins()) {
                incrementor(accountList3, accountList1, indexes, 0);
            } else if (accountList2[indexes[1]].getWins() > accountList1[indexes[0]].getWins()) {
                incrementor(accountList3, accountList2, indexes, 1);
            } //if they are the same compare the losses
            else {

                //add the account that has the least losses
                if (accountList1[indexes[0]].getLosses() < accountList2[indexes[1]].getLosses()) {
                    incrementor(accountList3, accountList1, indexes, 0);
                } else if (accountList2[indexes[1]].getLosses() < accountList1[indexes[0]].getLosses()) {
                    incrementor(accountList3, accountList2, indexes, 1);
                } //if losses are equal compare ties
                else {
                    //add the account that has the most ties
                    if (accountList1[indexes[0]].getTies() > accountList2[indexes[1]].getTies()) {
                        incrementor(accountList3, accountList1, indexes, 0);
                    } else if (accountList2[indexes[1]].getTies() > accountList1[indexes[0]].getTies()) {
                        incrementor(accountList3, accountList2, indexes, 1);
                    } //if ties are equal just add one of the accounts
                    else {
                        incrementor(accountList3, accountList2, indexes, 1);
                    }
                }
            }
        }
        //adds the rest of accountList1 if there are any elements left over
        while (indexes[0] < accountList1.length) {
            incrementor(accountList3, accountList1, indexes, 0);
        }
        //adds the rest of accountList2 if there are still any elements left over
        while (indexes[1] < accountList2.length) {
            incrementor(accountList3, accountList2, indexes, 1);
        }
        return accountList3;

    }
}
