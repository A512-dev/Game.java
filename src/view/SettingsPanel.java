package view;

import controller.Database;
import controller.Difficulty;
import controller.Sensitivity;
import controller.SoundLevel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import static controller.Constants.PANEL_SIZE;

public class SettingsPanel extends JPanel implements ActionListener, ChangeListener {
    String sensitivityLevelString = "LOW";
    String difficultyLevelString = "EASY";
    String soundLevelString = "LOW";
    public SettingsPanel(){
        setBorder(BorderFactory.createLineBorder(Color.yellow,5));
        setSize(PANEL_SIZE);
        //setBackground(Color.red);
        this.setBackground(Color.green);
        setLocationToCenter(GlassFrame.getINSTANCE());
        setLayout(null);
        GlassFrame.getINSTANCE().add(this);
        JLabel sensitivityMovementLabel = new JLabel("SENSITIVITY:");
        JLabel difficulty = new JLabel("DIFFICULTY:");
        JLabel soundLevel = new JLabel("SOUND:");
        sensitivityMovementLabel.setFont(new Font("Serif",Font.ITALIC,30));
        sensitivityMovementLabel.setBounds(50,50,280,40);
        sensitivityMovementLabel.setPreferredSize(new Dimension(300,100));
        add(sensitivityMovementLabel);

        difficulty.setFont(new Font("Serif",Font.ITALIC,30));
        difficulty.setBounds(50,150,280,40);
        difficulty.setPreferredSize(new Dimension(300,100));
        add(difficulty);

        soundLevel.setFont(new Font("Serif",Font.ITALIC,30));
        soundLevel.setBounds(50,250,280,40);
        soundLevel.setPreferredSize(new Dimension(300,100));
        add(soundLevel);

        JSlider sensitivitySlider = new JSlider(1,3);
        sensitivitySlider.setBounds(300,40,350,50);
        sensitivitySlider.setBackground(new Color(0,0,0,11));
        add(sensitivitySlider);
        JTextField textFieldSensitivity = new JTextField("MEDIUM");
        textFieldSensitivity.setBackground(new Color(0,0,0,0));
        textFieldSensitivity.setBounds(700,40,100,50);
        textFieldSensitivity.setBorder(null);
        add(textFieldSensitivity);
        sensitivitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (sensitivitySlider.getValue()) {
                    case 1 -> {
                        textFieldSensitivity.setText("LOW");
                        Database.sensitivity = Sensitivity.Low;
                    }
                    case 2 -> {
                        textFieldSensitivity.setText("MEDIUM");
                        Database.sensitivity = Sensitivity.Medium;
                    }
                    case 3 -> {
                        textFieldSensitivity.setText("HIGH");
                        Database.sensitivity = Sensitivity.High;
                    }
                }
            }
        });



        JSlider difficultySlider = new JSlider(1,3);
        difficultySlider.setBounds(300,140,350,50);
        difficultySlider.setBackground(new Color(0,0,0,11));
        add(difficultySlider);
        JTextField textFieldDifficulty = new JTextField("MEDIUM");
        textFieldDifficulty.setBackground(new Color(0,0,0,0));
        textFieldDifficulty.setBounds(700,140,100,50);
        textFieldDifficulty.setBorder(null);
        add(textFieldDifficulty);
        difficultySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (difficultySlider.getValue()) {
                    case 1 -> {
                        textFieldDifficulty.setText("EASY");
                        Database.difficulty = Difficulty.Easy;
                    }
                    case 2 -> {
                        textFieldDifficulty.setText("MEDIUM");
                        Database.difficulty = Difficulty.Medium;
                    }
                    case 3 -> {
                        textFieldDifficulty.setText("HARD");
                        Database.difficulty = Difficulty.Hard;
                    }
                }
            }
        });

        JSlider soundLevelSlider = new JSlider(1,3);
        soundLevelSlider.setBounds(300,240,350,50);
        soundLevelSlider.setBackground(new Color(0,0,0,11));
        add(soundLevelSlider);
        JTextField textFieldSound = new JTextField("MEDIUM");
        textFieldSound.setBackground(new Color(0,0,0,0));
        textFieldSound.setBounds(700,240,100,50);
        textFieldSound.setBorder(null);
        add(textFieldSound);
        soundLevelSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (soundLevelSlider.getValue()) {
                    case 1 -> {
                        textFieldSound.setText("LOW");
                        Database.soundLevel = SoundLevel.Low;
                    }
                    case 2 -> {
                        textFieldSound.setText("MEDIUM");
                        Database.soundLevel = SoundLevel.Low;
                    }
                    case 3 -> {
                        textFieldSound.setText("HIGH");
                        Database.soundLevel = SoundLevel.Low;
                    }
                }
            }
        });

        JButton homePageButton = new JButton("Home Page");
        homePageButton.setBackground(Color.cyan);
        homePageButton.setBounds(50,350,150,50);
        add(homePageButton);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                new HomePanel();
                revalidate();
            }
        });

//        sensitivitySlider.setPaintTicks(true);
//        sensitivitySlider.setPaintTrack(true);
//        sensitivitySlider.setPaintLabels(true);
//        sensitivitySlider.setMajorTickSpacing(50);
//        sensitivitySlider.setMinorTickSpacing(15)

//        JTextField textFieldSensitivity = new JTextField(sensitivityLevelString);


//        JSlider slider = new JSlider(0,255,100);
//
//        JLabel label = new JLabel();
//        slider.setPaintTicks(true);
//        slider.setMinorTickSpacing(15);
//        slider.setPaintTrack(true);
//        slider.setMajorTickSpacing(50);
//        slider.setPaintLabels(true);
//        slider.setOrientation(SwingConstants.VERTICAL);
//        label.setText("R: ");
//        textField.setText(String.valueOf(slider.getValue()));
//        slider.setValue(Integer.parseInt(textField.getText()));
//        textField.addActionListener( this);
//        slider.addChangeListener(this);

    }
    private void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {



    }
}
