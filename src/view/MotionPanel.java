package view;

import controller.Database;
import model.charactersModel.PanelModel;
import model.collision.Collidable;
import view.charactersView.BallView;
import view.charactersView.PanelView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static controller.Constants.PANEL_SIZE;

public final class MotionPanel extends JPanel{

    private static MotionPanel INSTANCE;
    private final Random rng=new Random();
    private MotionPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black,5));
        setBackground(new Color(0,0,0,0));
        setSize(PANEL_SIZE);
        setLocationToCenter(GlassFrame.getINSTANCE());
        GlassFrame.getINSTANCE().add(this);
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

    @Override
    protected void paintComponent(Graphics g) {
        for (BallView ballView: BallView.ballViews){
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
            Point2D location=ballView.getCurrentLocation();
            double radius=ballView.getCurrentRadius();
            g.fillOval((int) (location.getX()-radius), (int) (location.getY()-radius), (int) (2 *radius), (int) (2*radius));
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
//            System.out.println("squareX:"+(int) point1.getX()+" "+(int) point2.getX()+" "+(int) point3.getX()+" "+(int) point4.getX());
//            System.out.println("squareY:"+(int) point1.getY()+" "+(int) point2.getY()+" "+(int) point3.getY()+" "+(int) point4.getY());
            g.fillPolygon(xPoints,yPoints,4);
//            g.drawLine((int) point1.getX(),(int) point1.getY(),(int) point2.getX(),(int) point2.getY());
//            g.drawLine((int) point2.getX(),(int) point2.getY(),(int) point3.getX(),(int) point3.getY());
//            g.drawLine((int) point3.getX(),(int) point3.getY(),(int) point4.getX(),(int) point4.getY());
//            g.drawLine((int) point4.getX(),(int) point4.getY(),(int) point4.getX(),(int) point4.getY());
        }
        for (TriangleView triangleView: TriangleView.triangleViews){
            g.setColor(new Color(rng.nextInt(100,200),rng.nextInt(100,200),rng.nextInt(100,200)));
            Point2D location=triangleView.getCurrentLocation();
            double radius=triangleView.getCurrentRadius();
            Point2D[] points = triangleView.getCurrentVertices();
//            g.drawLine((int) points[0].getX(),(int) points[0].getY(),(int) points[1].getX(),(int) points[1].getY());
//            g.drawLine((int) points[1].getX(),(int) points[1].getY(),(int) points[2].getX(),(int) points[2].getY());
//            g.drawLine((int) points[2].getX(),(int) points[2].getY(),(int) points[0].getX(),(int) points[0].getY());
            int[] xPoints = {(int) points[0].getX(),(int) points[1].getX(),(int) points[2].getX()};
            int[] yPoints = {(int) points[0].getY(),(int) points[1].getY(),(int) points[2].getY()};
            g.fillPolygon(xPoints,yPoints,3);
        }
    }

    public static MotionPanel getINSTANCE() {

        if (INSTANCE==null) INSTANCE=new MotionPanel();
        return INSTANCE;
    }
}
