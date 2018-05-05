/*
 * Contains all classes pertaining to the GUI
 */
package GUI;

//imports
import Files.*;
import java.util.HashMap;

/**
 * Contains panel which allows you to customize your match in terms of who vs
 * who
 *
 * @author User
 */
public class AccountSetPanel extends javax.swing.JPanel {

    //A hashmap of the accounts in the file
    HashMap<String, Account> HashOfAccounts;
    //A list of the players in play
    Account[] ListOfPlayers;
    //A list of the accounts in the file
    Account[] ListOfAccounts;
    //A list of the SetDifficulty buttons
    javax.swing.JButton[] buttons;

    /**
     * Updates the lists with the information in the file
     */
    void updateLists() {
        //updates the hashmap and the list of accounts
        HashOfAccounts = FileManipFunctions.GetAccountHashMap();
        ListOfAccounts = FileManipFunctions.GetAccountList();
    }

    /**
     * allows the JButtons to be disabled or enabled
     *
     * @param isEnabled
     */
    public void comboBoxEnable(boolean isEnabled) {
        Player1Select.setEnabled(isEnabled);
        Player2Select.setEnabled(isEnabled);

    }

    /**
     * Constructor for the AccountSetPanel
     *
     * @param inputtedList - links list of players to a list of players from
     * controlPanel
     */
    public AccountSetPanel(Account[] inputtedList) {
        //ListOfPlayers to inputted list
        ListOfPlayers = inputtedList;

        initComponents();

        //updates the Account lists
        updateLists();

        //sets buttons equal to a list containing the select difficulty buttons
        buttons = new javax.swing.JButton[2];
        SelectDiff1.setVisible(false);
        SelectDiff2.setVisible(false);
        buttons[0] = SelectDiff1;
        buttons[1] = SelectDiff2;

        //updates the comboBoxes with new options
        updateComboBox(Player1Select, 0);
        updateComboBox(Player2Select, 1);
    }

    /**
     * returns a comboBox "address"
     *
     * @param i - helps to determine which comboBox to return
     * @return
     */
    public javax.swing.JComboBox getComboBox(int i) {

        if (i == 1) {
            return Player1Select;
        } else {
            return Player2Select;
        }
    }

    /**
     * updates a comboBox based on options contained in the Account list and
     * Hashmap
     *
     * @param whichBox1 - which comboBox is updated
     * @param index - what is the index of the corresponding selected player
     */
    void updateComboBox(javax.swing.JComboBox whichBox1, int index) {
        //gets the current selected player
        String selectedName = ListOfPlayers[index].getName();

        //prevents a selection error due to the account not existing
        //also accounts for if the account is a computer account or not
        if (HashOfAccounts.get(selectedName) == null && !selectedName.equals("Computer" + Integer.toString(index + 1))) {
            selectedName = "Player " + Integer.toString(index + 1);
        }

        //gets an organized list of accounts (on name by filter)
        Account[] organizedListOfAccounts = SortSearchFunctions.nameFinder(InputFilter.getText(), HashOfAccounts);

        //removes all items from the comboBox
        whichBox1.removeAllItems();

        //adds the selected back to the comboBox
        whichBox1.addItem(selectedName);

        //adds the default options if they were not selected
        if (!selectedName.equals("Player " + Integer.toString(index + 1))) {
            whichBox1.addItem("Player " + Integer.toString(index + 1));
        }
        if (!selectedName.equals("Computer" + Integer.toString(index + 1))) {
            whichBox1.addItem("Computer" + Integer.toString(index + 1));
        }

        //adds the options from the organized list
        for (int i = 0; i < organizedListOfAccounts.length; i++) {
            if (!selectedName.equals(organizedListOfAccounts[i].getName())) {
                whichBox1.addItem(organizedListOfAccounts[i].getName());
            }
        }
    }

    /**
     * sets the account list at index i to the Account selected in the
     * corresponding comboBox
     *
     * @param whichBox
     * @param index
     */
    void setAccount(javax.swing.JComboBox whichBox, int index) {
        //sets the difficulty button at index to not displayed
        buttons[index].setVisible(false);

        //if there is nothing selected it returns false
        if (whichBox.getSelectedItem() == null) {
            return;
        }

        //checks if option selected is a default option and updates accordingly
        String name = whichBox.getSelectedItem().toString();
        if (name.equals("Computer" + Integer.toString(index + 1))) {
            ListOfPlayers[index] = new Account("Computer" + Integer.toString(index + 1));
            ListOfPlayers[index].setWins(3);
            buttons[index].setVisible(true);
        } else if (name.equals("Player " + Integer.toString(index + 1))) {
            ListOfPlayers[index] = new Account("Player " + Integer.toString(index + 1));
        } //otherwise it sets it to an account in the list
        else {
            ListOfPlayers[index] = HashOfAccounts.get(whichBox.getSelectedItem().toString());
        }
    }

    /**
     * sets the difficulty for a computer account (wins)
     *
     * @param whichButton
     * @param index
     */
    void setDifficulty(javax.swing.JButton whichButton, int index) {
        //gets the current difficulty
        int layers = ListOfPlayers[index].getWins();
        //adds two and takes a modulus 6 to flip between 1, 3, 5
        layers = (layers + 2) % 6;
        //sets wins to layers
        ListOfPlayers[index].setWins(layers);

        //replaces the text displayed to reflect on the change in difficulty
        switch (layers) {
            case 1:
                whichButton.setText("Easy");
                break;
            case 3:
                whichButton.setText("Normal");
                break;
            case 5:
                whichButton.setText("Hard");
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Player1Select = new javax.swing.JComboBox<>();
        Player2Select = new javax.swing.JComboBox<>();
        Title1 = new javax.swing.JLabel();
        FilterLabel = new javax.swing.JLabel();
        InputFilter = new javax.swing.JTextField();
        SelectDiff1 = new javax.swing.JButton();
        SelectDiff2 = new javax.swing.JButton();
        Title2 = new javax.swing.JLabel();
        Title3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(200, 200, 200));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Player1Select.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Player1Select.setForeground(new java.awt.Color(255, 0, 0));
        Player1Select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Player1Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Player1SelectActionPerformed(evt);
            }
        });
        add(Player1Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 150, 32));

        Player2Select.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Player2Select.setForeground(new java.awt.Color(0, 0, 255));
        Player2Select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Player2Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Player2SelectActionPerformed(evt);
            }
        });
        add(Player2Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 150, 33));

        Title1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        Title1.setForeground(new java.awt.Color(255, 0, 0));
        Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title1.setText("<--");
        add(Title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 70, -1));

        FilterLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FilterLabel.setText("Filter ->");
        add(FilterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        InputFilter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        InputFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputFilterActionPerformed(evt);
            }
        });
        InputFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                InputFilterKeyReleased(evt);
            }
        });
        add(InputFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 167, 29));

        SelectDiff1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SelectDiff1.setForeground(new java.awt.Color(255, 0, 0));
        SelectDiff1.setText("Normal");
        SelectDiff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectDiff1ActionPerformed(evt);
            }
        });
        add(SelectDiff1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 150, -1));

        SelectDiff2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SelectDiff2.setForeground(new java.awt.Color(0, 0, 255));
        SelectDiff2.setText("Normal");
        SelectDiff2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectDiff2ActionPerformed(evt);
            }
        });
        add(SelectDiff2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 150, -1));

        Title2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        Title2.setForeground(new java.awt.Color(255, 0, 255));
        Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title2.setText("Vs");
        add(Title2, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 30, 70, -1));

        Title3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        Title3.setForeground(new java.awt.Color(0, 0, 255));
        Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title3.setText("-->");
        add(Title3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 70, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void InputFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputFilterActionPerformed
    /**
     * updates the comboBoxes each time a key is pressed in the filter
     *
     * @param evt - does nothing
     */
    private void InputFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InputFilterKeyReleased
        updateComboBox(Player1Select, 0);
        updateComboBox(Player2Select, 1);

    }//GEN-LAST:event_InputFilterKeyReleased
    /**
     * sets an account a listofplayers[0]
     *
     * @param evt - does nothing
     */
    private void Player1SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Player1SelectActionPerformed
        setAccount(Player1Select, 0);
    }//GEN-LAST:event_Player1SelectActionPerformed
    /**
     * sets an account a listofplayers[1]
     *
     * @param evt - does nothing
     */
    private void Player2SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Player2SelectActionPerformed
        setAccount(Player2Select, 1);
    }//GEN-LAST:event_Player2SelectActionPerformed
    /**
     * sets the difficulty a listofplayers[0]
     *
     * @param evt - does nothing
     */
    private void SelectDiff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectDiff1ActionPerformed
        setDifficulty(SelectDiff1, 0);
    }//GEN-LAST:event_SelectDiff1ActionPerformed
    /**
     * sets the difficulty a listofplayers[1]
     *
     * @param evt - does nothing
     */
    private void SelectDiff2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectDiff2ActionPerformed
        setDifficulty(SelectDiff2, 1);
    }//GEN-LAST:event_SelectDiff2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FilterLabel;
    private javax.swing.JTextField InputFilter;
    private javax.swing.JComboBox<String> Player1Select;
    private javax.swing.JComboBox<String> Player2Select;
    private javax.swing.JButton SelectDiff1;
    private javax.swing.JButton SelectDiff2;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel Title2;
    private javax.swing.JLabel Title3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
