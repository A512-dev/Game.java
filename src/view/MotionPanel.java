package view;

import controller.Database;
import model.charactersModel.EpsilonModel;
import model.charactersModel.PanelModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.collision.Collidable;
import view.charactersView.EpsilonView;
import view.charactersView.PanelView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.PANEL_SIZE;
import static controller.Constants.center;

public final class MotionPanel extends JPanel implements MouseListener,KeyListener {

    private static MotionPanel INSTANCE;
    private final Random rng=new Random();
    private MotionPanel() {
        this.setBackground(Color.black);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setVisible(true);
        setFocusable(true);
        System.out.println("color:"+this.getColorModel());

        setSize(PANEL_SIZE);
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().getContentPane().add(this);
        this.setFocusable(true);
        requestFocus();
        this.setVisible(true);
        setBackground(Color.red);
//        this.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
//                    Database.pause = true;
//                    System.out.println("esc");
//                    pauseGame();
//                }
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//
        JButton homeButton = new JButton("home");
        homeButton.setBounds(10,370,100,30);
        homeButton.setBackground(new Color(0,0,0,0));
        add(homeButton);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //removeAll();
                GlassFrame.getINSTANCE().getContentPane().removeAll();
                MotionPanel.setINSTANCE(null);
                EpsilonView.epsilonViews.clear();
                EpsilonModel.epsilonModels.clear();
                SquareView.squareViews.clear();
                SquareModel.squareModels.clear();
                TriangleView.triangleViews.clear();
                TriangleModel.triangleModels.clear();
                Collidable.collidables.clear();
                new HomePanel();
                revalidate();
                repaint();
            }
        });
//        System.out.println(Collidable.collidables.get(0).getVertices().get(0));
        PanelView panelView = new PanelView();
        new PanelModel();
//        if (PanelModel.getINSTANCE().equals(null))
//            ;
        //Collidable.collidables.add(this);

        //System.out.println(getX()+" "+getY()+" "+getWidth()+" "+getHeight());
    }
    public void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            System.out.println("Right");
//        }
//        if (keyCode==KeyEvent.VK_ESCAPE || keyCode==KeyEvent.VK_ENTER) {
//            System.out.println(615251252);
//            Database.pause = true;
//            pauseGame();
//        }
//
//    }
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        if (keyCode==KeyEvent.VK_ESCAPE) {
//            System.out.println(615251252);
//            Database.pause = true;
//            pauseGame();
//        }
//    }
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
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
            Point2D location=epsilonView.getCurrentLocation();
            double radius=epsilonView.getCurrentRadius();
            Point2D[] points = epsilonView.getCurrentVertices();
            if (points.length==0)
            {
                g.fillOval((int) location.getX(),(int) location.getY(),10,10);
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
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
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
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
            Point2D location=triangleView.getCurrentLocation();
            double radius=triangleView.getCurrentRadius();
            Point2D[] points = triangleView.getCurrentVertices();
            int[] xPoints = {(int) points[0].getX(),(int) points[1].getX(),(int) points[2].getX()};
            int[] yPoints = {(int) points[0].getY(),(int) points[1].getY(),(int) points[2].getY()};
            g.fillPolygon(xPoints,yPoints,3);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Serif",Font.ITALIC,28));
        g.drawString(Integer.toString(EpsilonModel.getNumHp()),15,25);
    }
    public void pauseGame() {
        System.out.println(66666);
        JPanel panelPause = new JPanel();
        panelPause.setLayout(null);
        panelPause.setBounds((int) center.getX()-MotionPanel.getINSTANCE().getX()/2,(int) center.getY()-MotionPanel.getINSTANCE().getY()/2,
                getX(),getY());
        JButton homePageButton = new JButton("Home");
        homePageButton.setBounds(100,100,100,100);
        homePageButton.setBackground(Color.red);
        GlassFrame.getINSTANCE().add(homePageButton);
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
                Collidable.collidables.clear();
                Database.pause = false;
                new HomePanel();
            }
        });

        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(100,200,100,100);
        continueButton.setBackground(Color.red);
        GlassFrame.getINSTANCE().add(continueButton);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.pause = false;
                panelPause.removeAll();
                remove(homePageButton);
                new MotionPanel();
            }
        });
        GlassFrame.getINSTANCE().add(panelPause);
    }

    public static MotionPanel getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new MotionPanel();
        return INSTANCE;
    }
    public static void setINSTANCE(MotionPanel motionPanel) {
        INSTANCE = motionPanel;
    }





}
