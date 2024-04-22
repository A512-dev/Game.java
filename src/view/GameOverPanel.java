package view;

import controller.Database;
import model.charactersModel.EpsilonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    public GameOverPanel() {
        GlassFrame.getINSTANCE().add(this);
        setVisible(true);
        setLayout(null);
        setFocusable(true);
        setLayout(null);
        GlassFrame.getINSTANCE().add(this);
        setBounds(GlassFrame.getINSTANCE().getWidth()/8,GlassFrame.getINSTANCE().getHeight()/8
                ,GlassFrame.getINSTANCE().getWidth()*6/8,GlassFrame.getINSTANCE().getHeight()*6/8);
        setBackground(Color.yellow);
        JTextField textField = new JTextField();
        textField.setText("Game is Over");
        textField.setBounds(this.getWidth()/4,50,getWidth()/2,150);
        textField.setFont(new Font("Serif",Font.BOLD, 90));
        textField.setBackground(new Color(0,0,0,0));
        textField.setBorder(null);
        textField.setVisible(true);
        add(textField);


        JTextField textFieldXp = new JTextField();
        textFieldXp.setText("XP: "+Integer.toString(EpsilonModel.numXP));
        textFieldXp.setBounds(this.getWidth()/4+80,250,getWidth()/2,150);
        textFieldXp.setFont(new Font("Serif",Font.BOLD, 90));
        textFieldXp.setBackground(new Color(0,0,0,0));
        textFieldXp.setBorder(null);
        textFieldXp.setVisible(true);
        add(textFieldXp);

        JButton exit  = new JButton("EXIT");
        exit.setBounds(140,400,200,100);
        exit.setBackground(Color.red);
        exit.setVisible(true);
        this.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                MotionPanel.setINSTANCE(null);
                GlassFrame.setINSTANCE(null);
                new HomePanel();
                revalidate();
                repaint();
            }
        });

        JButton playAgain  = new JButton("PLAY AGAIN");
        playAgain.setBounds(840,400,200,100);
        playAgain.setBackground(Color.red);
        playAgain.setVisible(true);

        add(playAgain);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                new Database();
                MotionPanel.setINSTANCE(null);
                GlassFrame.setINSTANCE(null);
                MotionPanel.getINSTANCE();
                revalidate();
                repaint();
            }
        });
    }
}
