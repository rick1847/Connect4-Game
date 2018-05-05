/*
* contains all classes pertaining to playing the game
 */
package Play;

/**
 * Contains the object that holds the board and functions to manipulate it
 *
 * @author Richard Tu
 */
public class Board {

    //piece 0 for none, 1 for p1, 2 for p2
    //stores the board
    int board[][];
    //stores the size in the x and y direction
    int size_x;
    int size_y;

    /**
     * constructs a new board object
     *
     * @param x - gets size in the x direction of the board
     * @param y - gets size in the y direction of the board
     */
    public Board(int x, int y) {
        //sets the size of x and y equal to their corresponding arguments
        size_x = x;
        size_y = y;
        //creats a new array and sets all values to 0
        board = new int[size_x][size_y];
        for (int i = 0; i < size_x; i++) {
            for (int v = 0; v < size_y; v++) {
                board[i][v] = 0;
            }
        }
    }

    /**
     * returns the board
     *
     * @return - the address of the board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * returns a value in the board
     *
     * @param x - x position
     * @param y - y position
     * @return - value at coordinates x and y
     */
    public int getBoardValue(int x, int y) {
        return board[x][y];
    }

    /**
     * makes a move on the board
     *
     * @param x - which column to put the move in
     * @param piece - the piece the current player is using
     */
    public void makeMove(int x, int piece) {

        //starts from the top of the array (since array is inverted in display)
        //and loops until it reaches 0 (top of the display)
        for (int v = size_y - 1; v >= 0; v--) {
            //checks if the piece at that cooredinate is equal to 0
            //if it is then it sets that element to the value in piece and returns
            if (board[x][v] == 0) {

                board[x][v] = piece;
                return;
            }
        }
    }

    /**
     * copies all elements of an inputted array into the board
     *
     * @param copied - the inputted array
     */
    public void copyBoard(int[][] copied) {
        //copies all elements of the inputted array into the array inside the object
        for (int i = 0; i < size_x; i++) {
            for (int v = 0; v < size_y; v++) {
                board[i][v] = copied[i][v];
            }
        }
    }

    /**
     * gets the size of x direction
     *
     * @return - the size in the x direction
     */
    public int getSizeX() {
        return size_x;
    }

    /**
     * gets the size of y direction
     *
     * @return - the size in the y direction
     */
    public int getSizeY() {
        return size_y;
    }
}
