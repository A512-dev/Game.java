package view;

import controller.Update;
import model.charactersModel.BallModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import view.charactersView.TriangleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLocationToCenter(GlassFrame.getINSTANCE());
        setLayout(null);
        GlassFrame.getINSTANCE().getContentPane().add(this);
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
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                MotionPanel.getINSTANCE();
//                revalidate();
//                repaint();

                for(int i = 0; i< NUMBER_OF_TRIANGLES; i++){
                    double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth());
                    double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY(),MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight());
                    double randomRadius=rng.nextDouble(MIN_RADIUS, MAX_RADIUS);
                    int degree = rng.nextInt(0,360);
                    new TriangleModel(new Point2D.Double(randomX,randomY),degree,randomRadius);
                }
                for(int i = 0; i< NUMBER_OF_SQUARES; i++){
                    System.out.println("mPanelX:"+MotionPanel.getINSTANCE().getX()+" mPanelY:"+MotionPanel.getINSTANCE().getY());
                    double randomRadius=rng.nextDouble(MIN_RADIUS, MAX_RADIUS);
                    double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX()+randomRadius+2,MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth()-randomRadius-2);
                    double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY()+randomRadius+2,MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight()-randomRadius-2);
                    int degree = rng.nextInt(0,360);
                    System.out.println("squareAnchor:"+randomX+" "+randomY+"radius:"+randomRadius);
                    new SquareModel(new Point2D.Double(randomX,randomY),randomRadius,degree);
                }
                for(int i = 0; i< NUMBER_OF_BALLS; i++){
                    double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth());
                    double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY(),MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight());
                    double randomRadius=rng.nextDouble(MIN_RADIUS, MAX_RADIUS);
                    new BallModel(new Point2D.Double(randomX,randomY),randomRadius);
                }
                new Update();
                repaint();
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
