/*
* contains all classes pertaining to playing the game
 */
package Play;

/**
 * Contains checking functions for the board (all static)
 *
 * @author User
 */
public class Checker {

    //
    /**
     * determines if the move is valid
     *
     * @param board - stores a board object for checking purposes
     * @param x - stores the column chosen for the move
     * @return - returns a boolean based on if the row is filled
     */
    public static boolean validMove(Board board, int x) {
        //gets the board from inside the object
        int innerBoard[][] = board.getBoard();

        //checks if the top of the row is equal to 0. If it is return true
        if (innerBoard[x][0] == 0) {
            return true;
        }

        //return false when the row is filled
        return false;
    }

    /**
     * determines if there is a tie
     *
     * @param board - stores a board object for checking purposes
     * @return - returns a boolean based on if all rows are filled
     */
    public static boolean tieChecker(Board board) {
        //gets the board from inside the object
        int testBoard[][] = board.getBoard();

        //checks through all columns of the board
        for (int i = 0; i < board.getSizeX(); i++) {
            //if the top of the board is zero in any column return fales
            if (testBoard[i][0] == 0) {
                return false;
            }

        }
        //returns true if all the columns are filled
        return true;
    }

    /**
     * determines if there is a winner
     *
     * @param board - stores a board object for checking purposes
     * @param piece - stores the piece that is being checked
     * @return - either the piece or 0 depending on if there was a win
     */
    public static int checker(Board board, int piece) {
        //gets the board from inside the object
        int[][] checkingBoard = board.getBoard();

        //checks vertical for wins
        for (int x = 0; x < board.getSizeX(); x++) {
            //minus 3 because it is impossible to have a win start from the top - 3
            for (int y = 0; y < board.getSizeY() - 3; y++) {
                //checks the next four to see if they are equal
                if (checkingBoard[x][y] == checkingBoard[x][y + 1]
                        && checkingBoard[x][y + 1] == checkingBoard[x][y + 2]
                        && checkingBoard[x][y + 2] == checkingBoard[x][y + 3]
                        && checkingBoard[x][y] == piece) {
                    //returns the piece if they are
                    return piece;
                }
            }
        }
        //note, similar rational for the next 3 blocks

        //checks horizontal for wins
        for (int y = 0; y < board.getSizeY(); y++) {
            for (int x = 0; x < board.getSizeX() - 3; x++) {
                if (checkingBoard[x][y] == checkingBoard[x + 1][y]
                        && checkingBoard[x + 1][y] == checkingBoard[x + 2][y]
                        && checkingBoard[x + 2][y] == checkingBoard[x + 3][y]
                        && checkingBoard[x][y] == piece) {
                    return piece;
                }
            }
        }

        //checks \ direction for wins
        for (int x = 0; x < board.getSizeX() - 3; x++) {
            for (int y = 0; y < board.getSizeY() - 3; y++) {

                if (checkingBoard[x][y] == checkingBoard[x + 1][y + 1]
                        && checkingBoard[x + 1][y + 1] == checkingBoard[x + 2][y + 2]
                        && checkingBoard[x + 2][y + 2] == checkingBoard[x + 3][y + 3]
                        && checkingBoard[x][y] == piece) {
                    return piece;
                }
            }
        }
        //checks / direction for wins
        for (int x = 0; x < board.getSizeX() - 3; x++) {
            for (int y = 0; y < board.getSizeY() - 3; y++) {

                if (checkingBoard[x][y + 3] == checkingBoard[x + 1][y + 2]
                        && checkingBoard[x + 1][y + 2] == checkingBoard[x + 2][y + 1]
                        && checkingBoard[x + 2][y + 1] == checkingBoard[x + 3][y]
                        && checkingBoard[x][y + 3] == piece) {

                    return piece;
                }
            }
        }

        //returns 0 if there are no wins
        return 0;
    }
}
