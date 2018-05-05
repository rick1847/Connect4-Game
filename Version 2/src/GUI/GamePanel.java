/*
 * Contains all classes pertaining to the GUI
 */
package GUI;

//imports
import PanelLinking.SendChangeSignal;
import Play.*;
import Files.Account;
import Files.FileManipFunctions;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.util.HashMap;

/**
 * Contains the buttons and board used to play the game
 *
 * @author User
 */
public class GamePanel extends javax.swing.JPanel {

    //the board used for checking the state of the game and for determining computer moves
    Board currentBoard;
    //stores the accounts of the players currently in game
    Account[] players;
    //stores the input buttons (1-7) in the game
    javax.swing.JButton[] Inputbuttons;
    //stores current turn, true for first player, false for the second
    boolean turn;
    //stores an object which determines the best current move
    Computer compPlayer;
    //stores the jtextFields used in the GUI
    javax.swing.JTextField squares[][];
    //stores a PropertyChangeListener which allows a win to update the scores
    SendChangeSignal changeTopScores;

    /**
     * maps all buttons and textfields to two arrays
     */
    void mapButtons() {
        int count = 0;
        //row 1
        squares[0][count] = Square1;
        squares[1][count] = Square2;
        squares[2][count] = Square3;
        squares[3][count] = Square4;
        squares[4][count] = Square5;
        squares[5][count] = Square6;
        squares[6][count] = Square7;
        count++;
        //row 2
        squares[0][count] = Square8;
        squares[1][count] = Square9;
        squares[2][count] = Square10;
        squares[3][count] = Square11;
        squares[4][count] = Square12;
        squares[5][count] = Square13;
        squares[6][count] = Square14;
        count++;
        //row3
        squares[0][count] = Square15;
        squares[1][count] = Square16;
        squares[2][count] = Square17;
        squares[3][count] = Square18;
        squares[4][count] = Square19;
        squares[5][count] = Square20;
        squares[6][count] = Square21;
        count++;
        //row4
        squares[0][count] = Square22;
        squares[1][count] = Square23;
        squares[2][count] = Square24;
        squares[3][count] = Square25;
        squares[4][count] = Square26;
        squares[5][count] = Square27;
        squares[6][count] = Square28;
        count++;
        //row5
        squares[0][count] = Square29;
        squares[1][count] = Square30;
        squares[2][count] = Square31;
        squares[3][count] = Square32;
        squares[4][count] = Square33;
        squares[5][count] = Square34;
        squares[6][count] = Square35;
        count++;
        //row6
        squares[0][count] = Square36;
        squares[1][count] = Square37;
        squares[2][count] = Square38;
        squares[3][count] = Square39;
        squares[4][count] = Square40;
        squares[5][count] = Square41;
        squares[6][count] = Square42;

        //buttons
        Inputbuttons[0] = Input1;
        Inputbuttons[1] = Input2;
        Inputbuttons[2] = Input3;
        Inputbuttons[3] = Input4;
        Inputbuttons[4] = Input5;
        Inputbuttons[5] = Input6;
        Inputbuttons[6] = Input7;
    }

    /**
     * resets both the GUI board and the offscreen board used for checking and
     * the computer. Also sets turn back to player that goes first
     */
    public void resetBoard() {
        //sets current board to a new board
        currentBoard = new Board(7, 6);

        //sets all text fields back to white
        for (int i = 0; i < currentBoard.getSizeX(); i++) {
            for (int v = 0; v < currentBoard.getSizeY(); v++) {
                squares[i][v].setBackground(Color.white);
            }
        }
        //sets turn to true (first player)
        turn = true;
    }

    /**
     * Enables all input move buttons
     */
    public void enableButtons() {
        //a loop to go through all buttons
        for (int i = 0; i < currentBoard.getSizeX(); i++) {
            Inputbuttons[i].setEnabled(true);
        }
    }

    /**
     * Disables all input move buttons
     */
    public void disableButtons() {
        //a loop to go through all buttons
        for (int i = 0; i < currentBoard.getSizeX(); i++) {
            Inputbuttons[i].setEnabled(false);
        }
    }

    /**
     * Creates new form GamePanel
     *
     * @param playersCopy - an array storing the current players
     * @param changeTopScoresCopy - a "propertyChange" interface facilitating
     * communication between panels
     */
    public GamePanel(Account[] playersCopy, SendChangeSignal changeTopScoresCopy) {
        //sets players equal to the players selected in the account selection panel
        players = playersCopy;
        //initiates the components
        initComponents();
        //creates new board
        currentBoard = new Board(7, 6);
        //creates a new array based on the size of the board
        squares = new javax.swing.JTextField[currentBoard.getSizeX()][currentBoard.getSizeY()];
        //creates a new array based on the size of the board along the x-axis (7)
        Inputbuttons = new javax.swing.JButton[7];
        //adds the grid of JTextFields and Buttons to the two above arrays
        mapButtons();
        //creates a new computer player
        compPlayer = new Computer();
        //disables the buttons on the bottom;
        disableButtons();
        //sets the "pointer" of changeTopScores to changeTopScoresCopy
        changeTopScores = changeTopScoresCopy;
        //adds a propertyChangeListener to changeTopScores
        changeTopScores.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                ;
            }
        });
        //sets turn to true
        turn = true;
    }

    /**
     * changes the colour of a JTextField based on move (i) and the turn
     *
     * @param i - the move of the player
     */
    public void changeColourSquare(int i) {
        //determines the y-index of the move, set to 0 as a default
        int colourPlace = 0;
        //searches from the top (bottom of the display) down to 0 (the top of the display)
        for (int y = currentBoard.getSizeY() - 1; y >= 0; y--) {
            //saves y + 1 to colourPlace ( + 1 offset is due to using a board where the 
            //move at int i was made before this function call) when the piece is 0 and exits the loop
            if (currentBoard.getBoardValue(i, y) == 0) {
                colourPlace = y + 1;
                y = -1;
            }
        }

        //determines which colour to use based on the value of turn
        if (turn) {
            squares[i][colourPlace].setBackground(Color.red);
        } else {
            squares[i][colourPlace].setBackground(Color.blue);
        }

    }

    /**
     * Change the colour of all the buttons
     *
     * @param color - stores a the desired colour
     */
    public void changeColourButton(Color color) {
        //goes through to each button
        for (int i = 0; i < 7; i++) {
            Inputbuttons[i].setForeground(color);
        }
    }

    /**
     * checks the top row of currentBoard and disables a button when the
     * corresponding column is filled
     */
    void killButton() {
        //goes through all columns
        for (int i = 0; i < 7; i++) {
            //if the not of valid move is true, disable the buttons
            if (!Checker.validMove(currentBoard, i)) {
                Inputbuttons[i].setEnabled(false);
            }
        }
    }

    /**
     * Starts game if computer is selected as the first player
     */
    public void computerFirst() {
        game(0);
    }

    /**
     * does a single move
     *
     * @param move - stores the index of the move
     * @param piece - stores either 1 (first player) or 2 (second player)
     */
    void doMove(int move, int piece) {
        //makes a move on the offscreen board
        currentBoard.makeMove(move, piece);
        //displays the move in the GUI
        changeColourSquare(move);
        //changes turn
        turn = !turn;
    }

    /**
     * Ends the game and saves the win or tie
     *
     * @param message - stores the message displayed
     * @param player - stores the player
     */
    void endGame(String message, int player) {
        //changes the colour of the buttons back to the winner's colour (red or blue)
        if (player == 0) {
            changeColourButton(new Color(255, 0, 0));
        } else {
            changeColourButton(new Color(0, 0, 255));
        }
        //stops the game from continuing
        disableButtons();
        //saves the winner to the leaderboards
        saveGame(message, player);
        //Determmines if the message is "It's a Tie!". If it is the pop up has the 
        //message "It's a Tie!". Otherwise the pop up has the message "The winner is: " 
        //plus the winner.
        if (message.equals("It's a Tie!")) {
            EndPopUp.showMessageDialog(this, message);
        } else {
            EndPopUp.showMessageDialog(this, "The winner is: " + message);
        }

        //updates leaderboard (garbage String argument)
        changeTopScores.setName("asdf");
    }

    /**
     * does a single turn
     *
     * @param move - stores what move the player made
     * @param piece - stores the current player's piece
     * @param player - stores the current index of the player (players array)
     */
    void turn(int move, int piece, int player) {
        //checks if the player is a computer. If it is it gets a move from the computer
        //otherwise it inputs the move from the argument
        if (!players[player].getName().equals("Computer" + Integer.toString(piece))) {
            doMove(move, piece);
        } else {
            move = compPlayer.getMove(currentBoard, players[player].getWins(), piece);
            doMove(move, piece);
        }
        //checks if there is a winner. If there is it runs endGame
        if (Checker.checker(currentBoard, piece) == piece) {
            endGame(players[player].getName(), player);
            return;

        } //checks if there is a tie. If there is it runs end game
        else if (Checker.tieChecker(currentBoard)) {
            endGame("It's a Tie!", player);
            return;
        }

        //ensures that the computer will always go next
        if (players[(player + 1) % 2].getName().equals("Computer" + Integer.toString((piece % 2) + 1))) {
            game(0);
        }

    }

    /**
     *
     * @param winner
     * @param player
     */
    void saveGame(String winner, int player) {
        //loads the current save file as a HashMap
        HashMap<String, Account> saveHash = FileManipFunctions.GetAccountHashMap();

        //increments wins by 1 if the winner exists in the HashMap (defaults will be null)
        if (saveHash.get(winner) == null) {
            //increments losses by 1 if the other player exists in the HashMap (defaults will be null)
            if (saveHash.get(players[(player + 1) % 2].getName()) == null) {
                ;
            } else {
                saveHash.get(players[(player + 1) % 2].getName()).setLosses(saveHash.get(players[(player + 1) % 2].getName()).getLosses() + 1);
            }
        } else {
            saveHash.get(winner).setWins(saveHash.get(winner).getWins() + 1);
            //increments losses by 1 if the other player exists in the HashMap (defaults will be null)
            if (saveHash.get(players[(player + 1) % 2].getName()) == null) {
                ;
            } else {
                saveHash.get(players[(player + 1) % 2].getName()).setLosses(saveHash.get(players[(player + 1) % 2].getName()).getLosses() + 1);
            }
        }

        //sets ties if winner is equal to "It's a Tie!".
        if (winner.equals("It's a Tie!")) {
            //checks if the player[1] is a default account. If it isn't it increments ties by 1
            if (saveHash.get(players[1].getName()) != null) {
                saveHash.get(players[1].getName()).setTies(saveHash.get(players[1].getName()).getTies() + 1);
            }
            //checks if the player[0] is a default account. If it isn't it increments ties by 1
            if (saveHash.get(players[0].getName()) != null) {
                saveHash.get(players[0].getName()).setTies(saveHash.get(players[0].getName()).getTies() + 1);
            }
        }

        //saves the file
        FileManipFunctions.saveFile(saveHash);
    }

    /**
     * determines which player is taking their turn Game is structured as a
     * series of fired functions
     *
     * @param i - stores the move
     */
    public void game(int i) {
        //if it is true it is player 1, other wise it is player 2
        //also changes the colour to indicate who moves next
        if (turn) {
            changeColourButton(new Color(0, 0, 255));
            turn(i, 1, 0);
        } else {
            changeColourButton(new Color(255, 0, 0));
            turn(i, 2, 1);
        }

        //prevents additional inputs if the column is full
        killButton();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EndPopUp = new javax.swing.JOptionPane();
        Square1 = new javax.swing.JTextField();
        Square2 = new javax.swing.JTextField();
        Square3 = new javax.swing.JTextField();
        Square4 = new javax.swing.JTextField();
        Square5 = new javax.swing.JTextField();
        Square6 = new javax.swing.JTextField();
        Square7 = new javax.swing.JTextField();
        Square8 = new javax.swing.JTextField();
        Square9 = new javax.swing.JTextField();
        Square10 = new javax.swing.JTextField();
        Square11 = new javax.swing.JTextField();
        Square12 = new javax.swing.JTextField();
        Square13 = new javax.swing.JTextField();
        Square14 = new javax.swing.JTextField();
        Square15 = new javax.swing.JTextField();
        Square16 = new javax.swing.JTextField();
        Square17 = new javax.swing.JTextField();
        Square18 = new javax.swing.JTextField();
        Square19 = new javax.swing.JTextField();
        Square20 = new javax.swing.JTextField();
        Square21 = new javax.swing.JTextField();
        Square22 = new javax.swing.JTextField();
        Square23 = new javax.swing.JTextField();
        Square24 = new javax.swing.JTextField();
        Square25 = new javax.swing.JTextField();
        Square26 = new javax.swing.JTextField();
        Square27 = new javax.swing.JTextField();
        Square28 = new javax.swing.JTextField();
        Square29 = new javax.swing.JTextField();
        Square30 = new javax.swing.JTextField();
        Square31 = new javax.swing.JTextField();
        Square32 = new javax.swing.JTextField();
        Square33 = new javax.swing.JTextField();
        Square34 = new javax.swing.JTextField();
        Square35 = new javax.swing.JTextField();
        Square36 = new javax.swing.JTextField();
        Square37 = new javax.swing.JTextField();
        Square38 = new javax.swing.JTextField();
        Square39 = new javax.swing.JTextField();
        Square40 = new javax.swing.JTextField();
        Square41 = new javax.swing.JTextField();
        Square42 = new javax.swing.JTextField();
        Input1 = new javax.swing.JButton();
        Input2 = new javax.swing.JButton();
        Input3 = new javax.swing.JButton();
        Input4 = new javax.swing.JButton();
        Input5 = new javax.swing.JButton();
        Input6 = new javax.swing.JButton();
        Input7 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 600));
        setLayout(new java.awt.GridLayout(7, 0));

        Square1.setEditable(false);
        add(Square1);

        Square2.setEditable(false);
        add(Square2);

        Square3.setEditable(false);
        add(Square3);

        Square4.setEditable(false);
        add(Square4);

        Square5.setEditable(false);
        add(Square5);

        Square6.setEditable(false);
        add(Square6);

        Square7.setEditable(false);
        add(Square7);

        Square8.setEditable(false);
        add(Square8);

        Square9.setEditable(false);
        add(Square9);

        Square10.setEditable(false);
        add(Square10);

        Square11.setEditable(false);
        add(Square11);

        Square12.setEditable(false);
        add(Square12);

        Square13.setEditable(false);
        add(Square13);

        Square14.setEditable(false);
        add(Square14);

        Square15.setEditable(false);
        add(Square15);

        Square16.setEditable(false);
        add(Square16);

        Square17.setEditable(false);
        add(Square17);

        Square18.setEditable(false);
        add(Square18);

        Square19.setEditable(false);
        add(Square19);

        Square20.setEditable(false);
        add(Square20);

        Square21.setEditable(false);
        add(Square21);

        Square22.setEditable(false);
        add(Square22);

        Square23.setEditable(false);
        add(Square23);

        Square24.setEditable(false);
        add(Square24);

        Square25.setEditable(false);
        add(Square25);

        Square26.setEditable(false);
        add(Square26);

        Square27.setEditable(false);
        add(Square27);

        Square28.setEditable(false);
        add(Square28);

        Square29.setEditable(false);
        add(Square29);

        Square30.setEditable(false);
        add(Square30);

        Square31.setEditable(false);
        add(Square31);

        Square32.setEditable(false);
        add(Square32);

        Square33.setEditable(false);
        add(Square33);

        Square34.setEditable(false);
        add(Square34);

        Square35.setEditable(false);
        add(Square35);

        Square36.setEditable(false);
        add(Square36);

        Square37.setEditable(false);
        add(Square37);

        Square38.setEditable(false);
        add(Square38);

        Square39.setEditable(false);
        add(Square39);

        Square40.setEditable(false);
        add(Square40);

        Square41.setEditable(false);
        add(Square41);

        Square42.setEditable(false);
        add(Square42);

        Input1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input1.setText("1");
        Input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input1ActionPerformed(evt);
            }
        });
        add(Input1);

        Input2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input2.setText("2");
        Input2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input2ActionPerformed(evt);
            }
        });
        add(Input2);

        Input3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input3.setText("3");
        Input3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input3ActionPerformed(evt);
            }
        });
        add(Input3);

        Input4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input4.setText("4");
        Input4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input4ActionPerformed(evt);
            }
        });
        add(Input4);

        Input5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input5.setText("5");
        Input5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input5ActionPerformed(evt);
            }
        });
        add(Input5);

        Input6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input6.setText("6");
        Input6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input6ActionPerformed(evt);
            }
        });
        add(Input6);

        Input7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        Input7.setText("7");
        Input7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Input7ActionPerformed(evt);
            }
        });
        add(Input7);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Defines action events for buttons (same format for all)
     *
     * @param evt - does nothing in this case
     */
    private void Input1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input1ActionPerformed
        game(0);
    }//GEN-LAST:event_Input1ActionPerformed

    private void Input2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input2ActionPerformed
        game(1);
    }//GEN-LAST:event_Input2ActionPerformed

    private void Input3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input3ActionPerformed
        game(2);
    }//GEN-LAST:event_Input3ActionPerformed

    private void Input4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input4ActionPerformed
        game(3);
    }//GEN-LAST:event_Input4ActionPerformed

    private void Input5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input5ActionPerformed
        game(4);
    }//GEN-LAST:event_Input5ActionPerformed

    private void Input6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input6ActionPerformed
        game(5);
    }//GEN-LAST:event_Input6ActionPerformed

    private void Input7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Input7ActionPerformed
        game(6);
    }//GEN-LAST:event_Input7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JOptionPane EndPopUp;
    private javax.swing.JButton Input1;
    private javax.swing.JButton Input2;
    private javax.swing.JButton Input3;
    private javax.swing.JButton Input4;
    private javax.swing.JButton Input5;
    private javax.swing.JButton Input6;
    private javax.swing.JButton Input7;
    private javax.swing.JTextField Square1;
    private javax.swing.JTextField Square10;
    private javax.swing.JTextField Square11;
    private javax.swing.JTextField Square12;
    private javax.swing.JTextField Square13;
    private javax.swing.JTextField Square14;
    private javax.swing.JTextField Square15;
    private javax.swing.JTextField Square16;
    private javax.swing.JTextField Square17;
    private javax.swing.JTextField Square18;
    private javax.swing.JTextField Square19;
    private javax.swing.JTextField Square2;
    private javax.swing.JTextField Square20;
    private javax.swing.JTextField Square21;
    private javax.swing.JTextField Square22;
    private javax.swing.JTextField Square23;
    private javax.swing.JTextField Square24;
    private javax.swing.JTextField Square25;
    private javax.swing.JTextField Square26;
    private javax.swing.JTextField Square27;
    private javax.swing.JTextField Square28;
    private javax.swing.JTextField Square29;
    private javax.swing.JTextField Square3;
    private javax.swing.JTextField Square30;
    private javax.swing.JTextField Square31;
    private javax.swing.JTextField Square32;
    private javax.swing.JTextField Square33;
    private javax.swing.JTextField Square34;
    private javax.swing.JTextField Square35;
    private javax.swing.JTextField Square36;
    private javax.swing.JTextField Square37;
    private javax.swing.JTextField Square38;
    private javax.swing.JTextField Square39;
    private javax.swing.JTextField Square4;
    private javax.swing.JTextField Square40;
    private javax.swing.JTextField Square41;
    private javax.swing.JTextField Square42;
    private javax.swing.JTextField Square5;
    private javax.swing.JTextField Square6;
    private javax.swing.JTextField Square7;
    private javax.swing.JTextField Square8;
    private javax.swing.JTextField Square9;
    // End of variables declaration//GEN-END:variables
}
