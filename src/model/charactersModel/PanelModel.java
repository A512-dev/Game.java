package model.charactersModel;

import controller.Constants;
import controller.Database;
import model.collision.Collidable;
import model.movement.Direction;
import view.GlassFrame;
import view.MotionPanel;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Database.*;

public class PanelModel implements Collidable{



    public static Point2D point1Panel;
    public static Point2D point2Panel;
    public static Point2D point3Panel;
    public Point2D point4Panel;


    public static Point2D center = Constants.center;
    private static PanelModel INSTANCE;

    public PanelModel(){
        if (Database.firstTime==0) {
            widthLeft = GlassFrame.getINSTANCE().getWidth() / 4;
            widthRight = GlassFrame.getINSTANCE().getWidth() / 4;
            heightDown = GlassFrame.getINSTANCE().getHeight() / 4;
            heightUp = GlassFrame.getINSTANCE().getHeight() / 4;
        }
        Database.firstTime++;
        point1Panel = new Point2D.Double(center.getX() - widthLeft, center.getY() - heightUp);
        point2Panel = new Point2D.Double(center.getX() + widthRight, center.getY() - heightUp);
        point3Panel = new Point2D.Double(center.getX() + widthRight, center.getY() + heightDown);
        point4Panel = new Point2D.Double(center.getX() - widthLeft, center.getY() + heightUp);
        System.out.println("xPanell:"+point1Panel.getX()+" "+ point2Panel.getX()+" "+ point3Panel.getX()+" "+ point4Panel.getX());
        System.out.println("yPanell:"+point1Panel.getY()+" "+ point2Panel.getY()+" "+ point3Panel.getY()+" "+ point4Panel.getY());
        System.out.println();
        System.out.println(center);
        if (Collidable.collidables.size()==0)
            Collidable.collidables.add(this);
    }

    public static PanelModel getINSTANCE(){
        if (INSTANCE==null) INSTANCE = new PanelModel();
        return INSTANCE;
    }
    public static void setINSTANCE(PanelModel panelModel) {
        INSTANCE = panelModel;
    }

    @Override
    public String getId() {
        return "panel";
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public boolean isEpsilon() {
        return false;
    }

    @Override
    public boolean isPanel() {
        return true;
    }

    @Override
    public boolean isRectangular() {
        return false;
    }
    @Override
    public boolean isLaserBall() {
        return false;
    }
    @Override
    public boolean isTriangular() {
        return false;
    }

    @Override
    public boolean isCollectibleSq() {
        return false;
    }

    @Override
    public boolean isCollectibleTr() {
        return false;
    }

    @Override
    public double getRadius() {
        return Math.max(widthLeft,heightUp);
    }

    @Override
    public Point2D getAnchor() {
        return center;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        ArrayList<Point2D> vertices = new ArrayList<>();
        vertices.add(point4Panel);
        vertices.add(point3Panel);
        vertices.add(point2Panel);
        vertices.add(point1Panel);
        return vertices;
    }


//    public static int getWidthLeft() {
//        return PanelModel.getINSTANCE().widthLeft;
//    }
//    public static void setWidthLeft(int widthLeft) {
//        PanelModel.getINSTANCE().widthLeft = widthLeft;
//    }
//    public static int getWidthRight() {
//        return PanelModel.getINSTANCE().widthRight;
//    }
//    public static void setWidthRight(int widthRight) {
//        PanelModel.getINSTANCE().widthRight = widthRight;
//    }
//    public static int getHeightDown() {
//        return PanelModel.getINSTANCE().heightDown;
//    }
//    public static void setHeightDown(int heightDown) {
//        PanelModel.getINSTANCE().heightDown = heightDown;
//    }
//    public static int getHeightUp() {
//        return PanelModel.getINSTANCE().heightUp;
//    }
//    public static void setHeightUp(int heightUp) {
//        PanelModel.getINSTANCE().heightUp = heightUp;
//    }
    public static Point2D getPoint1Panel() {
        return new Point2D.Double(center.getX() - widthLeft, center.getY() - heightUp);
    }
    public static void setPoint1Panel(Point2D point1Panel) {
        PanelModel.getINSTANCE().point1Panel = point1Panel;
    }
    public static Point2D getPoint2Panel() {
        return new Point2D.Double(center.getX() + widthRight, center.getY() - heightUp);
    }
    public static void setPoint2Panel(Point2D point2Panel) {
        PanelModel.getINSTANCE().point2Panel = point2Panel;
    }
    public static Point2D getPoint3Panel() {
        return new Point2D.Double(center.getX() + widthRight, center.getY() + heightDown);
    }
    public static void setPoint3Panel(Point2D point3Panel) {
        PanelModel.getINSTANCE().point3Panel = point3Panel;
    }
    public static Point2D getPoint4Panel() {
        return new Point2D.Double(center.getX() - widthLeft, center.getY() + heightUp);
    }
    public static void setPoint4Panel(Point2D point4Panel) {
        PanelModel.getINSTANCE().point4Panel = point4Panel;
    }
}
