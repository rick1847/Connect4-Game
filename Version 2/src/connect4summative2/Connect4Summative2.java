/**
 * @author: Richard Tu
 * @Teacher: Mr. Wilhelm
 *
 * @Course: ICS4UI
 * @Assignment: Summative
 * @Description: This is a game of connect four. It allows you to play a game of
 * connect 4. Additionally, it saves wins, losses, and ties of created accounts
 * to a scoreboard and displays it. It also features a computer game
 *
 * @Date: 12/19/2016
 */
package connect4summative2;

import Files.FileManipFunctions;

/**
 * Contains the main function and starts the game after checking for the score
 * file
 *
 * @author Richard Tu
 */
public class Connect4Summative2 {

    /**
     * Starts the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //checks if there is a file and creates a new file if there isn't
        if (!FileManipFunctions.IsFile()) {
            FileManipFunctions.createFile();
        }

        //creates a new base frame
        BaseFrame a = new BaseFrame();
        //makes the screen visible
        a.makeVisible();
    }

}
