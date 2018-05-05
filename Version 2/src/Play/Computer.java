/*
* contains all classes pertaining to playing the game
 */
package Play;

/**
 * Contains functions which allow a computer to return the best move
 *
 * @author User
 */
public class Computer {

    /**
     * creates a list of possible moves
     *
     * @param board - a game state
     * @return - returns a list of the possible moves
     */
    int[] possibleMoves(Board board) {
        //assumes there are 0 moves
        int numbPossMoves = 0;
        //stores all possible moves (every move assumed possible)
        int possMoves1[] = new int[board.getSizeX()];

        //checks for possible moves. If there numbPossMoves is incremented by 1
        //and the move is added to the possMoves1 list
        for (int i = 0; i < board.getSizeX(); i++) {
            if (Checker.validMove(board, i)) {
                possMoves1[numbPossMoves] = i;
                numbPossMoves++;
            }
        }

        //creates a new list that is the size of the numbPossMoves
        int possMoves2[] = new int[numbPossMoves];

        ///copies all possible moves over
        for (int i = 0; i < numbPossMoves; i++) {
            possMoves2[i] = possMoves1[i];
        }

        //returns the array possMoves2
        return possMoves2;
    }

    /**
     * calculates the best move the computer can make
     *
     * @param board - stores the game state
     * @param difficulty - stores how many layers it searches
     * @param piece - controls which piece is making the move
     * @return - returns the best move
     */
    public int getMove(Board board, int difficulty, int piece) {
        //copies over the current game state into copyBoard
        Board copyBoard = new Board(board.getSizeX(), board.getSizeY());
        copyBoard.copyBoard(board.getBoard());

        //stores a list of possible moves
        int[] possMoves = possibleMoves(board);
        //stores a list of corresponding values
        int values[] = new int[possMoves.length];

        //returns -1 if there are no possible moves
        if (possMoves.length == 0) {
            return -1;
        }

        //gets the values for each move
        for (int i = 0; i < possMoves.length; i++) {
            values[i] = futureBoards(copyBoard, difficulty, possMoves[i], piece, true);
            System.out.println("Move: " + (possMoves[i] + 1) + " Value: " + values[i]);
        }

        //stores the returned move and the corresponding value
        int returnedMove = possMoves[0];
        int bestValue = values[0];

        //determines the best value in values and assigns the correponding move to
        //returnedMove
        for (int i = 1; i < possMoves.length; i++) {
            if (bestValue < values[i]) {
                bestValue = values[i];
                returnedMove = possMoves[i];
            }
        }

        //returns the best move
        return returnedMove;
    }

    //
    /**
     * gets the best value
     *
     * @param board - stores the current game state
     * @param layer - stores the depth
     * @param move - stores which move is being made
     * @param piece - stores the corresponding piece of the player going
     * @param maximising - stores whether or not the player is maximizing
     * @return - returns the best value calculated
     */
    int futureBoards(Board board, int layer, int move, int piece, boolean maximising) {
        //copies the current game state into copyBoard (because java does weird things
        //when manipulating objects from arguments)
        Board copyBoard = new Board(board.getSizeX(), board.getSizeY());
        copyBoard.copyBoard(board.getBoard());

        //makes the move in the copy of the board
        copyBoard.makeMove(move, piece);

        //checks if there is a tie
        if (Checker.tieChecker(copyBoard)) {
            return 0;
        }

        //checks if there is a winner and returns a multiple of 1000 based on layer
        //and maximising
        if (Checker.checker(copyBoard, piece) == piece) {
            if (maximising) {
                return 10000 * layer;
            } else if (!maximising) {
                return -10000 * layer;
            }
        }

        //returns a heuristic value if the value of layer is at 1
        if (layer == 1) {
            return evaluate(copyBoard, piece) - evaluate(copyBoard, piece % 2 + 1);
        }

        //if none of the above conditions are satisfied it generate a new list of
        //possible moves and correponding values
        int possMoves[] = possibleMoves(copyBoard);
        int values[] = new int[possMoves.length];

        //gets the values of the next set of possible moves
        for (int i = 0; i < possMoves.length; i++) {
            //cycles piece between(1, 2) and maximising
            values[i] = futureBoards(copyBoard, layer - 1, possMoves[i], piece % 2 + 1, !maximising);
        }
        //returns the best value
        return minimumMaximum(values, !maximising);
    }

    /**
     * returns either the minimum or the maximum
     *
     * @param listOfNumbers - stores the list of numbers being compared
     * @param max - stores a boolean determining whether or not it is finding
     * the maximum or minimum
     * @return - returns the smallest or largest value
     */
    int minimumMaximum(int[] listOfNumbers, boolean max) {
        //starts by comparing the first number
        int minMax = listOfNumbers[0];

        //compares all numbers in the list to find the smallest or largest value
        for (int i = 1; i < listOfNumbers.length; i++) {

            if (max && minMax < listOfNumbers[i]) {
                minMax = listOfNumbers[i];
            } else if (!max && minMax > listOfNumbers[i]) {
                minMax = listOfNumbers[i];
            } else {
                ;
            }
        }

        //returns minMax
        return minMax;

    }

    /**
     * evaluates the value of a set of 4 consecutive elements
     *
     * @param strip - stores an array of size four
     * @param piece - stores the piece compared
     * @return - returns the value of the sum
     */
    int evaluateStripValue(int[] strip, int piece) {
        //counts the number of pieces matching the input piece
        int pieceCounter = 0;
        //stores the heuristic score
        int sum = 0;
        //stores the index of x[]
        int index = 0;
        //stores the non-piece values
        int[] x = new int[4];

        //checks through all elements of the strip
        for (int i = 0; i < strip.length; i++) {
            //if the element is equal to piece increment the piece counter
            //otherwise add it to x[] and increment index
            if (strip[i] == piece) {
                pieceCounter += 1;
            } else {
                x[index] = strip[i];
                index++;
            }
        }

        //if the pieceCounter is equal to 3 and the piece at x[0] is equal to 0
        //add 50 to the sum
        if (pieceCounter == 3 && x[0] == 0) {
            sum += 100;
        } //otherwise if pieceCounter is equal to 2 and the other two pieces are both
        //zero only add 5
        else if (pieceCounter == 2 && (x[0] == 0 && x[1] == 0)) {
            sum += 10;
        } //if there is a lone piece in a strip it adds one to show it has an advantage
        else if (pieceCounter == 1 && (x[0] == 0 && x[1] == 0 && x[2] == 0)) {
            sum += 1;
        }

        //returns the sum
        return sum;
    }

    /**
     * evaluates the current game state
     *
     * @param board - stores the current game state
     * @param piece - stores the piece being checked
     * @return - returns a heuristic value
     */
    int evaluate(Board board, int piece) {
        //gets the board array for evaluation
        int[][] evaluatedBoard = board.getBoard();
        //stores the section of evaluatedBoard being evaluated
        int[] sectionEvaluated = new int[4];
        //stoers the heuristic value
        int sum = 0;

        //checks all vertical strips
        for (int x = 0; x < board.getSizeX(); x++) {
            for (int y = 0; y < board.getSizeY() - 3; y++) {
                //copies over a set of four to be evaluated
                for (int z = 0; z < 4; z++) {
                    sectionEvaluated[z] = evaluatedBoard[x][y + z];
                }
                //evaluates the strip
                sum += evaluateStripValue(sectionEvaluated, piece);

            }
        }

        //checks all horizontal strips
        for (int y = 0; y < board.getSizeY(); y++) {
            for (int x = 0; x < board.getSizeX() - 3; x++) {
                //copies over a set of four to be evaluated
                for (int z = 0; z < 4; z++) {
                    sectionEvaluated[z] = evaluatedBoard[x + z][y];
                }

                //evaluates the strip
                sum += evaluateStripValue(sectionEvaluated, piece);
            }
        }

        //checks all \ direction strips
        for (int x = 0; x < board.getSizeX() - 3; x++) {
            for (int y = 0; y < board.getSizeY() - 3; y++) {
                //copies over a set of four to be evaluated
                for (int z = 0; z < 4; z++) {
                    sectionEvaluated[z] = evaluatedBoard[x + z][y + z];
                }
                //evaluates the strip
                sum += evaluateStripValue(sectionEvaluated, piece);

            }
        }
        //for / direction
        for (int x = 0; x < board.getSizeX() - 3; x++) {
            for (int y = 0; y < board.getSizeY() - 3; y++) {
                //copies over a set of four to be evaluated
                for (int z = 0; z < 4; z++) {
                    sectionEvaluated[z] = evaluatedBoard[x + z][y + 3 - z];
                }
                //evaluates the strip
                sum += evaluateStripValue(sectionEvaluated, piece);

            }
        }
        //returns the heuristic value
        return sum;
    }
}
