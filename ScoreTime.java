package scoretime;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ScoreTime extends javax.swing.JFrame {

    public static int team1Score = 0;
    public static int team2Score = 0;
    public static int userTime = 60;
    private volatile boolean timerRunning = false;
    public static boolean timerInterrupted = false;
    public static int timer = userTime;
    public static File beepSoundFile = new File("resources/beep.wav");
    public static File blipSoundFile = new File("resources/blip.wav");
    public static File buzzerSoundFile = new File("resources/buzzer.wav");
    public static boolean soundOn = true;
    public static boolean timerMade = false;
    public static  javax.swing.Timer sTimer;
   
    public ScoreTime() {
        initComponents();
        sTimer = new javax.swing.Timer(1000, timerStartButton.getActionListeners()[0]);
        displayScores();
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScoreTime().setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        team1Panel = new javax.swing.JPanel();
        team1Label = new javax.swing.JLabel();
        team1ScoreLabel = new javax.swing.JLabel();
        team1SubtractButton = new javax.swing.JButton();
        team1AddButton = new javax.swing.JButton();
        team1NameButton = new javax.swing.JButton();
        team2Panel = new javax.swing.JPanel();
        team2Label = new javax.swing.JLabel();
        team2ScoreLabel = new javax.swing.JLabel();
        team2SubtractButton = new javax.swing.JButton();
        team2AddButton = new javax.swing.JButton();
        team2NameButton = new javax.swing.JButton();
        timerPanel = new javax.swing.JPanel();
        setTimeButton = new javax.swing.JButton();
        resetTimerButton = new javax.swing.JButton();
        startStopPanel = new javax.swing.JPanel();
        timerLabel = new javax.swing.JLabel();
        timerStartButton = new javax.swing.JButton();
        timerStopButton = new javax.swing.JButton();
        soundCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(153, 0, 0));

        team1Panel.setBackground(new java.awt.Color(255, 255, 204));

        team1Label.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        team1Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        team1Label.setText("Team 1");

        team1ScoreLabel.setFont(new java.awt.Font("Tahoma", 0, 96)); // NOI18N
        team1ScoreLabel.setText("jLabel1");

        team1SubtractButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        team1SubtractButton.setText("-");
        team1SubtractButton.setToolTipText("Subtract a Point");
        team1SubtractButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team1SubtractButtonActionPerformed(evt);
            }
        });

        team1AddButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        team1AddButton.setText("+");
        team1AddButton.setToolTipText("Add a Point");
        team1AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team1AddButtonActionPerformed(evt);
            }
        });

        team1NameButton.setText("Name");
        team1NameButton.setToolTipText("Change Team 1's Name");
        team1NameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team1NameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout team1PanelLayout = new javax.swing.GroupLayout(team1Panel);
        team1Panel.setLayout(team1PanelLayout);
        team1PanelLayout.setHorizontalGroup(
            team1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(team1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team1PanelLayout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addComponent(team1ScoreLabel)
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(team1PanelLayout.createSequentialGroup()
                        .addComponent(team1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(team1NameButton))
                    .addGroup(team1PanelLayout.createSequentialGroup()
                        .addComponent(team1SubtractButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(team1AddButton)))
                .addContainerGap())
        );
        team1PanelLayout.setVerticalGroup(
            team1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(team1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(team1Label)
                    .addComponent(team1NameButton))
                .addGap(18, 18, 18)
                .addComponent(team1ScoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(team1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(team1SubtractButton)
                    .addComponent(team1AddButton))
                .addContainerGap())
        );

        team2Panel.setBackground(new java.awt.Color(255, 255, 204));

        team2Label.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        team2Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        team2Label.setText("Team 2");

        team2ScoreLabel.setFont(new java.awt.Font("Tahoma", 0, 96)); // NOI18N
        team2ScoreLabel.setText("jLabel2");

        team2SubtractButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        team2SubtractButton.setText("-");
        team2SubtractButton.setToolTipText("Subtract a Point");
        team2SubtractButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team2SubtractButtonActionPerformed(evt);
            }
        });

        team2AddButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        team2AddButton.setText("+");
        team2AddButton.setToolTipText("Add a Point");
        team2AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team2AddButtonActionPerformed(evt);
            }
        });

        team2NameButton.setText("Name");
        team2NameButton.setToolTipText("Change Team 2's Name");
        team2NameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team2NameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout team2PanelLayout = new javax.swing.GroupLayout(team2Panel);
        team2Panel.setLayout(team2PanelLayout);
        team2PanelLayout.setHorizontalGroup(
            team2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(team2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(team2PanelLayout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(team2ScoreLabel)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(team2PanelLayout.createSequentialGroup()
                        .addComponent(team2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(team2NameButton))
                    .addGroup(team2PanelLayout.createSequentialGroup()
                        .addComponent(team2SubtractButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(team2AddButton)))
                .addContainerGap())
        );
        team2PanelLayout.setVerticalGroup(
            team2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, team2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(team2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(team2Label)
                    .addComponent(team2NameButton))
                .addGap(18, 18, 18)
                .addComponent(team2ScoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(team2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(team2SubtractButton)
                    .addComponent(team2AddButton))
                .addContainerGap())
        );

        timerPanel.setBackground(new java.awt.Color(255, 255, 255));

        setTimeButton.setText("Set Time");
        setTimeButton.setToolTipText("Change the number of seconds on the timer");
        setTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTimeButtonActionPerformed(evt);
            }
        });

        resetTimerButton.setText("Reset Timer");
        resetTimerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTimerButtonActionPerformed(evt);
            }
        });

        startStopPanel.setBackground(new java.awt.Color(255, 255, 255));

        timerLabel.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        timerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timerLabel.setText("60");
        timerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        timerStartButton.setBackground(new java.awt.Color(51, 255, 0));
        timerStartButton.setForeground(new java.awt.Color(255, 255, 255));
        timerStartButton.setText("Start Timer");
        timerStartButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        timerStartButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timerStartButton.setPreferredSize(new java.awt.Dimension(100, 50));
        timerStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    timerStartButtonActionPerformed(evt);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ScoreTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        timerStopButton.setBackground(new java.awt.Color(255, 0, 51));
        timerStopButton.setForeground(new java.awt.Color(255, 255, 255));
        timerStopButton.setText("Stop Timer");
        timerStopButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timerStopButton.setPreferredSize(new java.awt.Dimension(100, 50));
        timerStopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timerStopButtonActionPerformed(evt);
            }
        });
        
        soundCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        soundCheckBox.setSelected(true);
        soundCheckBox.setText("Sound On/Off");
        soundCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout startStopPanelLayout = new javax.swing.GroupLayout(startStopPanel);
        startStopPanel.setLayout(startStopPanelLayout);
        startStopPanelLayout.setHorizontalGroup(
            startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startStopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(startStopPanelLayout.createSequentialGroup()
                        .addComponent(timerStartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timerStopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        startStopPanelLayout.setVerticalGroup(
            startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startStopPanelLayout.createSequentialGroup()
                .addComponent(timerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timerStartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timerStopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout timerPanelLayout = new javax.swing.GroupLayout(timerPanel);
        timerPanel.setLayout(timerPanelLayout);
        timerPanelLayout.setHorizontalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timerPanelLayout.createSequentialGroup()
                        .addComponent(setTimeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(soundCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetTimerButton))
                    .addGroup(timerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startStopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        timerPanelLayout.setVerticalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setTimeButton)
                    .addComponent(resetTimerButton)
                    .addComponent(soundCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startStopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(timerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(team1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(team2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(team1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(team2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void soundCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        if (soundCheckBox.isSelected()) {
            soundOn = true;
        }
        else {
            soundOn = false;
        }
    }
    private void team1SubtractButtonActionPerformed(java.awt.event.ActionEvent evt) {
        team1Score -= 1;
        blip();
        displayScores();
    }

    private void team1AddButtonActionPerformed(java.awt.event.ActionEvent evt) {
        team1Score += 1;
        beep();
        displayScores();
    }

    private void team2SubtractButtonActionPerformed(java.awt.event.ActionEvent evt) {
        team2Score -= 1;
        blip();
        displayScores();
    }

    private void team2AddButtonActionPerformed(java.awt.event.ActionEvent evt) {
        team2Score += 1;
        beep();
        displayScores();
    }

    private void timerStartButtonActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException {
        disableButtons();
        if (!timerMade) {
            makeTimer();
        }
        displayTimer();
    }
    
    private void makeTimer() {
        sTimer.restart();
        timerMade = true;
    }
    
    private void displayTimer() throws InterruptedException {
        if (timer > 0) {
            timerLabel.setText(Integer.toString(timer));
            timer--;
        }
        else {
            sTimer.stop();
            timerMade = false;
            timerLabel.setText("0");
            timerLabel.paintImmediately(timerLabel.getVisibleRect());
            if (soundOn) {
                try {
                    //load the buzzer sound as a clip
                    AudioInputStream buzzerAudioIn = AudioSystem.getAudioInputStream(buzzerSoundFile);
                    Clip buzzerClip = AudioSystem.getClip();
                    buzzerClip.open(buzzerAudioIn);   
                    buzzerClip.start();
                    Thread.sleep(1500);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                    Logger.getLogger(ScoreTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            enableButtons();
            timer = userTime;
            timerLabel.setText(Integer.toString(timer));
        }
    }

    private void team1NameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String team1Name = JOptionPane.showInputDialog("Enter Team 1's Name:", JOptionPane.OK_OPTION);
        team1Label.setText(team1Name);
    }

    private void team2NameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String team2Name = JOptionPane.showInputDialog("Enter Team 2's Name:", JOptionPane.OK_OPTION);
        team2Label.setText(team2Name);
    }

    private void setTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean ok = false;
        int newTime = 0;
        while (!ok) {
            String input = (String)JOptionPane.showInputDialog(this, 
                    "Enter the desired time (in seconds):", 
                    "Set a New Time",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    "60");
            
            if (input == null) {
                ok = true;
            }
            else {
                try { 
                    newTime = Integer.valueOf(input);
                }
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Try again. No Letters Allowed.", "Invalid Input", JOptionPane.OK_OPTION);
                    newTime = -1;
                    timerLabel.setText("--");
                }

                if (newTime != 0 && newTime != -1) {
                    ok = true;
                    userTime = newTime;
                    timer = userTime;
                    timerLabel.setText(Integer.toString(timer));
                }
                else if (newTime == 0) {
                    JOptionPane.showMessageDialog(this, "Try again. Your number must be larger than 0.", "Invalid Input", JOptionPane.OK_OPTION);
                    newTime = 0;
                    timerLabel.setText("--");
                }
            }
        }
    }

    private void timerStopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        sTimer.stop();
        timerMade = false;
        enableButtons();
    }

    private void resetTimerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        timer = userTime;
        timerLabel.setText(Integer.toString(timer));
        timerInterrupted = false;
        timerRunning = false;
    }
    
    public static void displayScores() {
        team1ScoreLabel.setText(Integer.toString(team1Score));
        team2ScoreLabel.setText(Integer.toString(team2Score));
    }
    
    public void disableButtons() {
        timerStartButton.setEnabled(false);
        resetTimerButton.setEnabled(false);
        setTimeButton.setEnabled(false);
    }
    
    public void enableButtons() {
        timerStartButton.setEnabled(true);
        resetTimerButton.setEnabled(true);
        setTimeButton.setEnabled(true);
    }
    
    public void beep() {
        if (soundOn) {
            try {
                //load the beep sound as a clip
                AudioInputStream beepAudioIn = AudioSystem.getAudioInputStream(beepSoundFile);
                Clip beepClip = AudioSystem.getClip();
                beepClip.open(beepAudioIn);  
                beepClip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(ScoreTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void blip() {
        if (soundOn) {
            try {
                //load the blip sound as a clip
                AudioInputStream blipAudioIn = AudioSystem.getAudioInputStream(blipSoundFile);
                Clip blipClip = AudioSystem.getClip();
                blipClip.open(blipAudioIn);
                blipClip.start();
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(ScoreTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton resetTimerButton;
    private javax.swing.JButton setTimeButton;
    private javax.swing.JPanel startStopPanel;
    private javax.swing.JButton team1AddButton;
    private static javax.swing.JLabel team1Label;
    private javax.swing.JButton team1NameButton;
    private javax.swing.JPanel team1Panel;
    private static javax.swing.JLabel team1ScoreLabel;
    private javax.swing.JButton team1SubtractButton;
    private javax.swing.JButton team2AddButton;
    private static javax.swing.JLabel team2Label;
    private javax.swing.JButton team2NameButton;
    private javax.swing.JPanel team2Panel;
    private static javax.swing.JLabel team2ScoreLabel;
    private javax.swing.JButton team2SubtractButton;
    private javax.swing.JLabel timerLabel;
    private javax.swing.JPanel timerPanel;
    private javax.swing.JButton timerStartButton;
    private javax.swing.JButton timerStopButton;
    private javax.swing.JCheckBox soundCheckBox;
}
