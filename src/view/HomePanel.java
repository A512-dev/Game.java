package view;

import controller.Database;
import model.charactersModel.EpsilonModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.collision.Collidable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import static controller.Constants.*;
import static controller.Constants.MAX_RADIUS;
import static game.Main.rng;

public class HomePanel extends JPanel {
    JButton buttonStart, buttonSettings, buttonSkillTree, buttonExit, buttonTutorial;
    int cornerX, cornerY;
    public HomePanel() {
        setBorder(BorderFactory.createLineBorder(Color.yellow,5));
        setSize(PANEL_SIZE);
        setBackground(Color.red);
        setFocusable(true);
        requestFocus(true);

        setLocationToCenter(GlassFrame.getINSTANCE());
        setLayout(null);
        GlassFrame.getINSTANCE().add(this);
        setFocusable(true);
        requestFocus(true);
//        cornerX = GlassFrame.getINSTANCE().getWidth()/2-this.getWidth()/2;
//        cornerY = GlassFrame.getINSTANCE().getHeight()/2-this.getHeight()/2;
        this.buttonStart = new JButton("START");
        this.buttonSettings = new JButton("SETTINGS");
        this.buttonSkillTree = new JButton("SKILL TREE");
        this.buttonExit = new JButton("EXIT");
        this.buttonTutorial = new JButton("TUTORIAL");
//        buttonStart.setPreferredSize(new Dimension(200, 120));
//        buttonSettings.setPreferredSize(new Dimension(200,120));
//        buttonSkillTree.setPreferredSize(new Dimension(200, 120));
//        buttonExit.setPreferredSize(new Dimension(200, 120));
        buttonStart.setBackground(Color.cyan);
        buttonSettings.setBackground(Color.cyan);
        buttonSkillTree.setBackground(Color.cyan);
        buttonExit.setBackground(Color.cyan);
        buttonTutorial.setBackground(Color.cyan);

//        System.out.println(getX()+" "+getY());
//        System.out.println(GlassFrame.getINSTANCE().getX()+" "+GlassFrame.getINSTANCE().getWidth()/2);
//
        //buttonStart.setLocation(cornerX+10,cornerY+10);
        buttonStart.setBounds(70, 10, 200,150);
        buttonSettings.setBounds(320,10,200,150);
        buttonSkillTree.setBounds(70,200,200,150);
        buttonExit.setBounds(320,200,100,50);
        buttonTutorial.setBounds(520,200,200,150);

        //buttonStart.setBounds();
        this.add(buttonStart);
        this.add(buttonSettings);
        this.add(buttonExit);
        this.add(buttonSkillTree);
        this.add(buttonTutorial);

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Database();
                Database.gameStarted = true;


                //creating Epsilon
                double randomX1=rng.nextDouble(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth());
                double randomY1=rng.nextDouble(MotionPanel.getINSTANCE().getY(),MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight());
                //double randomRadius1=rng.nextDouble(MIN_RADIUS_EPSILON, MAX_RADIUS_EPSILON);
                int degree1 = rng.nextInt(0,180);
                new EpsilonModel(new Point2D.Double(randomX1,randomY1),EPSILON_RADIUS,degree1,5);
                EpsilonModel.setNumHp(100);

                GlassFrame.getINSTANCE().getContentPane().removeAll();
//                GlassFrame.setINSTANCE(null);
                MotionPanel.setINSTANCE(null);
//                GlassFrame.getINSTANCE();
                MotionPanel.getINSTANCE();
//                revalidate();
//                repaint();





                //new Update();
//                revalidate();
//                repaint();
                System.out.println(1);
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                new SettingsPanel();
                revalidate();
                repaint();
            }
        });
        buttonSkillTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                new SkillTreePanel();
                revalidate();
                repaint();
            }
        });
        buttonTutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                new TutorialPanel();
                revalidate();
                repaint();
            }
        });
    }
    private void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }

}
