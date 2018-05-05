/**
 * Contains all files pertaining to Files
 */
package Files;

/**
 * This class links a name with a set of wins, losses and ties
 *
 * @author Richard Tu
 */
public class Account {

    //stores the name of the account
    String name;
    //stores the wins of the account
    int wins;
    //stores the losses of the account
    int losses;
    //stores the ties of the account
    int ties;

    /**
     * Creates an account with base stats
     *
     * @param name - sets the name
     */
    public Account(String name) {
        this.name = name;
        wins = 0;
        losses = 0;
        ties = 0;
    }

    /**
     * Sets the name
     *
     * @param place - stores a name
     */
    public void setName(String place) {
        name = place;
    }

    /**
     * Sets the wins
     *
     * @param place - stores wins
     */
    public void setWins(int place) {
        wins = place;
    }

    /**
     * Sets the losses
     *
     * @param place - stores losses
     */
    public void setLosses(int place) {
        losses = place;
    }

    /**
     * Stores ties
     *
     * @param place - stores ties
     */
    public void setTies(int place) {
        ties = place;
    }

    /**
     * returns the name
     *
     * @return - the stored name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the wins
     *
     * @return - the stored wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * returns the losses
     *
     * @return - the stored losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * returns the ties
     *
     * @return - the stored ties
     */
    public int getTies() {
        return ties;
    }
}
