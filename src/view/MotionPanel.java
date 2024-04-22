package view;

import controller.Database;
import controller.Utils;
import model.charactersModel.*;
import model.charactersModel.collectibles.SquareCollectibleModel;
import model.charactersModel.collectibles.TriangleCollectibleModel;
import model.collision.Collidable;
import view.charactersView.*;
import view.charactersView.collectiblesView.SquareCollectibleView;
import view.charactersView.collectiblesView.TriangleCollectibleView;

import javax.swing.*;
import javax.xml.crypto.Data;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;

import static controller.Constants.*;

public final class MotionPanel extends JPanel implements MouseListener,KeyListener {

    private static MotionPanel INSTANCE;
    private final Random rng=new Random();
    private MotionPanel() {
        this.setBackground(Color.black);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setVisible(true);
        setFocusable(true);
        System.out.println("color:"+this.getColorModel());

        setSize(new Dimension(Database.widthLeft+Database.widthRight,Database.heightUp+Database.heightDown));
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().add(this);
        this.setFocusable(true);
        requestFocus();
        this.setVisible(true);
        setBackground(Color.red);

        PanelModel.getINSTANCE();

        JButton homeButton = new JButton("home");
        homeButton.setForeground(Color.white);
        homeButton.setBounds((int) PanelModel.point1Panel.getX()-GlassFrame.getINSTANCE().getWidth()/4 + 10
                ,(int) PanelModel.point3Panel.getY()-GlassFrame.getINSTANCE().getHeight()/4 -40,100,30);
        homeButton.setBackground(new Color(0,0,0,0));
        add(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //removeAll();
                setVisible(false);
                Database.gameStarted = false;
                Database.shopOpen = false;
                Database.threeLaserBalls = false;
                Database.pause = false;
                MotionPanel.setINSTANCE(null);
                INSTANCE = null;
                PanelModel.setINSTANCE(null);

                GlassFrame.getINSTANCE().getContentPane().removeAll();

                EpsilonView.epsilonViews.clear();
                EpsilonModel.epsilonModels.clear();
                EpsilonModel.numXP = 0;
                EpsilonModel.numHp = 100;
                SquareView.squareViews.clear();
                SquareModel.squareModels.clear();
                TriangleView.triangleViews.clear();
                TriangleModel.triangleModels.clear();
                LaserBallModel.laserBallModels.clear();
                LaserBallView.laserBallViews.clear();
                SquareCollectibleModel.squareCollectibleModels.clear();
                SquareCollectibleView.squareCollectibleViews.clear();
                TriangleCollectibleView.triangleCollectibleViews.clear();
                TriangleCollectibleModel.triangleCollectibleModels.clear();

                for (int i=0; i<Collidable.collidables.size(); i++) {
                    if (!Collidable.collidables.get(i).isPanel())
                        Collidable.collidables.remove(Collidable.collidables.get(i));
                }
                System.out.println("epsilon "+EpsilonModel.epsilonModels+" "+EpsilonView.epsilonViews);
                System.out.println("square "+SquareModel.squareModels+" "+SquareView.squareViews);
                System.out.println("triangle "+TriangleModel.triangleModels+" "+TriangleView.triangleViews);
                System.out.println("collidable "+Collidable.collidables.size());
                System.out.println("homePPPPPPPP");
                new HomePanel();
//                revalidate();
//                repaint();
            }
        });
//        System.out.println(Collidable.collidables.get(0).getVertices().get(0));

    }
    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        super.addMouseListener(this);
        System.out.println("mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("entered");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!Database.gameOver && !Database.pause){
            if (!Database.threeLaserBalls) {
                new LaserBallModel(e.getPoint(),EpsilonModel.anchorEpsilon,true);
                LASER_GUN_RADIUS = LASER_GUN_RADIUS_ORIGINAL;
            }
            else {
                LASER_GUN_RADIUS += 10;
                new LaserBallModel(e.getPoint(),EpsilonModel.anchorEpsilon,true);
                Database.threeLaserBalls = false;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            Database.pause = true;
            System.out.println("esc");
            pauseGame();
        }
        if (e.getKeyCode()==KeyEvent.VK_S) {

            if (!Database.shopOpen) {
                Database.pause = true;
                Database.shopOpen = true;
                shopPanel();
            }
            else {
                Database.shopOpen = false;
                Database.pause = false;
                shopPanel();
            }
        }
        System.out.println(e.getKeyCode());

        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            Database.moveRightEpsilon = true;
            Database.moveRightReleasedEpsilon=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            Database.moveLeftEpsilon = true;
            Database.moveLeftReleasedEpsilon=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP) {
            Database.moveUpEpsilon = true;
            Database.moveUpReleasedEpsilon=false;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            Database.moveDownEpsilon = true;
            Database.moveDownReleasedEpsilon=false;
        }
        System.out.println("l:"+Database.moveLeftEpsilon+" "+"r:"+Database.moveRightEpsilon+" "+"d:"+Database.moveDownEpsilon+" "+"u:"+Database.moveUpEpsilon);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            Database.moveRightReleasedEpsilon = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            Database.moveLeftReleasedEpsilon = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_UP) {
            Database.moveUpReleasedEpsilon = true;
        }
        if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            Database.moveDownReleasedEpsilon = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        for (EpsilonView epsilonView : EpsilonView.epsilonViews){
            g.setColor(Color.white);
            Point2D location=epsilonView.getCurrentLocation();
            double radius=epsilonView.getCurrentRadius();
            Point2D[] points = epsilonView.getCurrentVertices();
            if (points.length==0) {
                g.fillOval((int) (location.getX()-EPSILON_RADIUS),(int) (location.getY()-EPSILON_RADIUS),(int) (2*EPSILON_RADIUS),(int) (2*EPSILON_RADIUS));
            }
            else{
                int[] xPoints = new int[points.length];
                int[] yPoints = new int[points.length];
                for (int i=0; i<points.length; i++)
                {
                    xPoints[i] = (int) points[i].getX();
                    yPoints[i] = (int) points[i].getY();
                }
                g.fillPolygon(xPoints,yPoints,points.length);
                //System.out.println(points.length);
            }

        }
        for (SquareView squareView: SquareView.squareViews){
            g.setColor(Color.green);
            Point2D location=squareView.getCurrentLocation();
            double radius=squareView.getCurrentRadius();
            Point2D point1 = squareView.getCurrentVertices()[0];
            Point2D point2 = squareView.getCurrentVertices()[1];
            Point2D point3 = squareView.getCurrentVertices()[2];
            Point2D point4 = squareView.getCurrentVertices()[3];
            int[] xPoints = {(int) point1.getX(),(int) point2.getX(),(int) point3.getX(),(int) point4.getX()};
            int[] yPoints = {(int) point1.getY(),(int) point2.getY(),(int) point3.getY(),(int) point4.getY()};
            g.drawLine((int) point1.getX(),(int) point1.getY(),(int) point2.getX(),(int) point2.getY());
            g.fillPolygon(xPoints,yPoints,4);
        }
        for (TriangleView triangleView: TriangleView.triangleViews){
            g.setColor(Color.red);
            Point2D location=triangleView.getCurrentLocation();
            double radius=triangleView.getCurrentRadius();
            Point2D[] points = triangleView.getCurrentVertices();
            int[] xPoints = {(int) points[0].getX(),(int) points[1].getX(),(int) points[2].getX()};
            int[] yPoints = {(int) points[0].getY(),(int) points[1].getY(),(int) points[2].getY()};
            g.fillPolygon(xPoints,yPoints,3);
        }
        for (LaserBallView laserBallView: LaserBallView.laserBallViews){
            g.setColor(Color.black);
            Point2D location= laserBallView.getCurrentLocation();
            g.fillOval((int) (location.getX()-LASER_GUN_RADIUS/2),(int) (location.getY()-LASER_GUN_RADIUS/2),
                    2*(int) LASER_GUN_RADIUS,2*(int) LASER_GUN_RADIUS);
        }
        for (SquareCollectibleView squareCollectibleView: SquareCollectibleView.squareCollectibleViews){
            g.setColor(Color.red);
            Point2D location= squareCollectibleView.getCurrentLocation();
            g.fillOval((int) (location.getX()-RADIUS_COLLECTIBLES/2),(int) (location.getY()-RADIUS_COLLECTIBLES/2),
                    (int) RADIUS_COLLECTIBLES,(int) RADIUS_COLLECTIBLES);
        }
        for (TriangleCollectibleView triangleCollectibleView: TriangleCollectibleView.triangleCollectibleViews){
            g.setColor(Color.blue);
            Point2D location= triangleCollectibleView.getCurrentLocation();
            g.fillOval((int) (location.getX()-RADIUS_COLLECTIBLES/2),(int) (location.getY()-RADIUS_COLLECTIBLES/2),
                   2* (int) RADIUS_COLLECTIBLES,2*(int) RADIUS_COLLECTIBLES);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Serif",Font.ITALIC,28));
        g.drawString(Integer.toString(EpsilonModel.getNumHp()),15,25);
        g.drawString("XP:"+Integer.toString(EpsilonModel.numXP),MotionPanel.getINSTANCE().getWidth()-85,25);
    }
    public void pauseGame() {
        JButton homePageButton = new JButton("Home");
        homePageButton.setBounds(100,100,100,100);
        homePageButton.setBackground(Color.red);
        GlassFrame.getINSTANCE().getContentPane().add(homePageButton);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                MotionPanel.setINSTANCE(null);
                EpsilonModel.epsilonModels.clear();
                EpsilonView.epsilonViews.clear();
                SquareModel.squareModels.clear();
                SquareView.squareViews.clear();
                TriangleModel.triangleModels.clear();
                TriangleView.triangleViews.clear();
                LaserBallView.laserBallViews.clear();
                LaserBallModel.laserBallModels.clear();
                int size = Collidable.collidables.size();
                Collidable.collidables.clear();
                new PanelModel();
                Database.pause = false;
                Database.gameStarted = false;
                new HomePanel();
            }
        });

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(100,400,100,100);
        continueButton.setBackground(Color.red);
        GlassFrame.getINSTANCE().getContentPane().add(continueButton);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.pause = false;
                GlassFrame.getINSTANCE().getContentPane().remove(homePageButton);
                GlassFrame.getINSTANCE().getContentPane().remove(continueButton);
                removeAll();
                new MotionPanel();
            }
        });
    }
    private void shopPanel() {
        JFrame frameShop = new JFrame("shop");
        frameShop.setUndecorated(true);
        frameShop.setVisible(true);
        frameShop.setLayout(null);
        frameShop.setBounds(GlassFrame.getINSTANCE().getWidth()/8,GlassFrame.getINSTANCE().getHeight()/8
                ,GlassFrame.getINSTANCE().getWidth()*6/8,GlassFrame.getINSTANCE().getHeight()*6/8);
        frameShop.setBackground(new Color(0,100,100,160));
        JTextField textField = new JTextField();
        textField.setText("YOU DON'T HAVE ENOUGH XP");
        textField.setBounds(10,10,400,100);
        textField.setFont(new Font("Serif",Font.BOLD, 20));
        textField.setBackground(Color.cyan);
        textField.setVisible(false);

        JButton exit  = new JButton("EXIT");
        exit.setBounds(40,200,100,80);
        exit.setBackground(Color.red);
        exit.setVisible(false);

        //O' Hephaestus، Banish
        JButton pushBanish = new JButton("O' Hephaestus، Banish");
        pushBanish.setBackground(Color.PINK);
        pushBanish.setBounds(frameShop.getWidth()/2-125,40,250,140);
        frameShop.add(pushBanish);
        pushBanish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        //O’ Athena، Empower:
        JButton threeBall = new JButton("O’ Athena، Empower");
        threeBall.setBackground(Color.PINK);
        threeBall.setBounds(frameShop.getWidth()/2-125,240,250,140);
        frameShop.add(threeBall);
        threeBall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EpsilonModel.numXP>=100) {
                    EpsilonModel.numXP -= 100;
                    Database.numberOfLaserBalls = 3;
                    Database.threeLaserBalls = true;
                    Database.shopOpen = false;
                    Database.pause = false;
                    frameShop.dispose();
                    new MotionPanel();
                }
                else {
                    textField.setVisible(true);
                    exit.setVisible(true);
                }

            }
        });
        //O' Apollo Heal
        JButton heal = new JButton("O' Apollo Heal");
        heal.setBackground(Color.PINK);
        heal.setBounds(frameShop.getWidth()/2-125,440,250,140);
        frameShop.add(heal);
        heal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (EpsilonModel.numXP>=100) {
                    EpsilonModel.numHp += 30;
                    EpsilonModel.numXP -= 100;
                    Database.shopOpen = false;
                    Database.pause = false;
                    frameShop.dispose();
                    new MotionPanel();
                }
                else {
                    textField.setVisible(true);
                    exit.setVisible(true);
                }
            }
        });
        frameShop.add(textField);
        frameShop.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameShop.dispose();
                System.out.println("visible");
                Database.pause = false;
                Database.shopOpen = false;
                new MotionPanel();
                revalidate();
                repaint();
            }
        });
    }
    public static MotionPanel getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new MotionPanel();
        INSTANCE.setSize(new Dimension(Database.widthLeft+Database.widthRight,Database.heightUp+Database.heightDown));
        INSTANCE.setLocationToCenter(GlassFrame.getINSTANCE());
        INSTANCE.setFocusable(true);
        INSTANCE.setBackground(Color.PINK);
        INSTANCE.setBorder(BorderFactory.createLineBorder(Color.black,5));
        return INSTANCE;
    }
    public static void setINSTANCE(MotionPanel motionPanel) {
        INSTANCE = motionPanel;
    }


}
