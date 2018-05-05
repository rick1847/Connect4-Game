/*
 * Contains all classes pertaining to the GUI
 */
package GUI;

//imports
import PanelLinking.SendChangeSignal;
import PanelLinking.SendChangeString;
import Files.Account;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Contains an overall panel used to display all other non-SplashScreen panels
 * and contains the play button, reset button and exit button
 *
 * @author User
 */
public class ControlPanel extends javax.swing.JPanel {

    //stores the current players
    Account[] players;
    //stores the set account class
    AccountSetPanel SetAccountPanel;
    //stores the account management class
    AccountManagementPanel accountManagementPanel;
    //stores the play game panel
    GamePanel PlayGamePanel;
    //stores the help panel class
    HelpPanel helpPanel;
    //stpres the score panel class
    ScorePanel scorePanel;
    //facilitates cross panel communication between control panel and account management panel
    SendChangeSignal updatePanels;
    //facilitates cross panel communication between the splash screen and the control panel
    SendChangeSignal quickSetup;
    //facilitates cross panel communication between the game panel and the leaderboard panel
    SendChangeSignal updateTopScores;

    /**
     * initiates the components which are not auto generated by Netbeans
     */
    void initiateOtherComponents() {

        //sets up players to be both default accounts
        players = new Account[2];
        players[0] = new Account("Player 1");
        players[1] = new Account("Player 2");

        //sets updatePanels to a new SendChangeString and adds a propertyChangeListener
        updatePanels = new SendChangeString();

        //when a game ends it enables the play button
        updatePanels.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                //updates the comboBoxes in set account
                SetAccountPanel.updateLists();
                SetAccountPanel.updateComboBox(SetAccountPanel.getComboBox(1), 0);
                SetAccountPanel.updateComboBox(SetAccountPanel.getComboBox(2), 1);

                //updates the leaderboards
                scorePanel.TopScoreOutput("");
            }
        });

        //sets updatePanels to a new SendChangeString
        updateTopScores = new SendChangeString();
        updateTopScores.addPropertyChangeListener(new PropertyChangeListener() {

            //enables  the play button if the condition is met
            @Override
            public void propertyChange(PropertyChangeEvent pce) {

                PlayButton.setEnabled(true);

            }
        });

        //creates new panels and links them to their respectice SendChangeSignals
        helpPanel = new HelpPanel();
        scorePanel = new ScorePanel(updateTopScores);
        accountManagementPanel = new AccountManagementPanel(updatePanels);
        SetAccountPanel = new AccountSetPanel(players);
        PlayGamePanel = new GamePanel(players, updateTopScores);

        //adds panels to containers
        HelpContainer.add(helpPanel);
        ScoreContainer.add(scorePanel);
        AccountManagementContainer.add(accountManagementPanel);
        SetAccountContainer.add(SetAccountPanel);
        PlayContainer.add(PlayGamePanel, "play");
    }

    /**
     * Creates new form ControlPanel
     *
     * @param quickSetup1 - stores the "address" of the quickSetup in the splash
     * screen
     */
    public ControlPanel(SendChangeSignal quickSetup1) {
        initComponents();
        initiateOtherComponents();

        //sets quickSetup equal to the splash screen's quickSetup and adds a PropertyChangeListener
        quickSetup = quickSetup1;
        quickSetup.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                //if the signal is named "delete" it updates the accoountSet
                //account management and leaderboards panels
                if (pce.getOldValue().equals("delete")) {
                    scorePanel.TopScoreOutput("");
                    accountManagementPanel.updateComboBox();
                    SetAccountPanel.updateComboBox(SetAccountPanel.getComboBox(1), 0);
                    SetAccountPanel.updateComboBox(SetAccountPanel.getComboBox(2), 1);
                }
                //determines the signal as either PvP signal (b) or PvC signal(c)
                //and does the necessary setup accordingly
                if (pce.getOldValue().equals("b")) {
                    playButtonAction();
                } else if (pce.getOldValue().equals("c")) {
                    //sets up the computer
                    players[1] = new Account("Computer2");
                    players[1].setWins(3);
                    //updates the set account panel
                    SetAccountPanel.updateComboBox(SetAccountPanel.getComboBox(2), 1);

                    playButtonAction();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScoreContainer = new javax.swing.JPanel();
        gap1 = new javax.swing.JPanel();
        gap2 = new javax.swing.JPanel();
        ResetButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        SetAccountContainer = new javax.swing.JPanel();
        AccountManagementContainer = new javax.swing.JPanel();
        PlayButton = new javax.swing.JButton();
        PlayContainer = new javax.swing.JPanel();
        HelpContainer = new HelpPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10), new java.awt.Dimension(10, 10));
        gap3 = new javax.swing.JPanel();

        ScoreContainer.setBackground(new java.awt.Color(200, 200, 200));

        gap1.setDoubleBuffered(false);
        gap1.setEnabled(false);

        javax.swing.GroupLayout gap1Layout = new javax.swing.GroupLayout(gap1);
        gap1.setLayout(gap1Layout);
        gap1Layout.setHorizontalGroup(
            gap1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
        gap1Layout.setVerticalGroup(
            gap1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );

        gap2.setDoubleBuffered(false);
        gap2.setEnabled(false);

        javax.swing.GroupLayout gap2Layout = new javax.swing.GroupLayout(gap2);
        gap2.setLayout(gap2Layout);
        gap2Layout.setHorizontalGroup(
            gap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        gap2Layout.setVerticalGroup(
            gap2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        ResetButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        ResetButton.setText("Reset");
        ResetButton.setPreferredSize(new java.awt.Dimension(150, 50));
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        ExitButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.setPreferredSize(new java.awt.Dimension(150, 50));
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        Title.setFont(new java.awt.Font("Segoe UI Black", 2, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(0, 0, 255));
        Title.setText("Connect 4");

        SetAccountContainer.setBackground(new java.awt.Color(200, 200, 200));

        AccountManagementContainer.setBackground(new java.awt.Color(200, 200, 200));

        PlayButton.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 36)); // NOI18N
        PlayButton.setText("Play");
        PlayButton.setPreferredSize(new java.awt.Dimension(150, 50));
        PlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayButtonActionPerformed(evt);
            }
        });

        PlayContainer.setBackground(new java.awt.Color(200, 200, 200));
        PlayContainer.setName(""); // NOI18N
        PlayContainer.setLayout(new java.awt.CardLayout());

        HelpContainer.setBackground(new java.awt.Color(200, 200, 200));
        HelpContainer.add(filler1);

        gap3.setDoubleBuffered(false);
        gap3.setEnabled(false);

        javax.swing.GroupLayout gap3Layout = new javax.swing.GroupLayout(gap3);
        gap3.setLayout(gap3Layout);
        gap3Layout.setHorizontalGroup(
            gap3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gap3Layout.setVerticalGroup(
            gap3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(718, 718, 718)
                .addComponent(gap2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(345, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gap3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(PlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(195, 195, 195))
                                    .addComponent(ResetButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(SetAccountContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PlayContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Title)
                        .addGap(219, 219, 219)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AccountManagementContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HelpContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ScoreContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Title)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SetAccountContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(PlayContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(gap3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScoreContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gap1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(AccountManagementContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(HelpContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gap2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * defines what happens when the reset button is clicked
     *
     * @param evt - does nothing
     */
    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        //sets the players back to the default
        players[0] = new Account("Player 1");
        players[1] = new Account("Player 2");

        //resets the set account panel
        SetAccountContainer.removeAll();
        SetAccountPanel = new AccountSetPanel((players));
        SetAccountContainer.add(SetAccountPanel);

        //disables and clears the play game panel
        PlayGamePanel.disableButtons();
        PlayGamePanel.resetBoard();

        //changes the colour to black
        PlayGamePanel.changeColourButton(new java.awt.Color(0, 0, 0));
        //enables the play button
        PlayButton.setEnabled(true);
    }//GEN-LAST:event_ResetButtonActionPerformed

    /**
     * exits the game
     *
     * @param evt - not used
     */
    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    /**
     * defines what the play button does
     */
    void playButtonAction() {
        //resets the board
        PlayGamePanel.resetBoard();

        //disables the comboBoxes in set account
        SetAccountPanel.comboBoxEnable(false);

        //disables the play button
        PlayButton.setEnabled(false);

        //if the accounts are the same nothing happens
        if (players[0] == players[1]) {
            PlayButton.setEnabled(true);
            SetAccountPanel.comboBoxEnable(true);
            return;
        }

        //enables buttons on the play screen
        PlayGamePanel.enableButtons();
        //changes the colour to red
        PlayGamePanel.changeColourButton(new java.awt.Color(255, 0, 0));

        //if the first player is a computer player it fdoes a computer move
        if (players[0].getName().equals("Computer1")) {
            PlayGamePanel.computerFirst();

        }
    }

    /**
     * play button action
     *
     * @param evt - not used
     */
    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayButtonActionPerformed
        playButtonAction();
    }//GEN-LAST:event_PlayButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AccountManagementContainer;
    private javax.swing.JButton ExitButton;
    private javax.swing.JPanel HelpContainer;
    private javax.swing.JButton PlayButton;
    private javax.swing.JPanel PlayContainer;
    private javax.swing.JButton ResetButton;
    private javax.swing.JPanel ScoreContainer;
    private javax.swing.JPanel SetAccountContainer;
    private javax.swing.JLabel Title;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JPanel gap1;
    private javax.swing.JPanel gap2;
    private javax.swing.JPanel gap3;
    // End of variables declaration//GEN-END:variables
}