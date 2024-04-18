package model.charactersModel;

import controller.Constants;
import model.collision.Collidable;
import model.movement.Direction;
import view.MotionPanel;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PanelModel implements Collidable{
    int degree;
    int directionX, directionY;
    Line2D line1, line2, line3, line4;
    ArrayList<Line2D> lines = new ArrayList<>();
    double radius;
    String id;
    Direction direction;
    public static Point2D center = Constants.center;
    public static int widthLeft = MotionPanel.getINSTANCE().getWidth() / 2;
    public static int widthRight = MotionPanel.getINSTANCE().getWidth() / 2;
    public static int heightDown = MotionPanel.getINSTANCE().getHeight() / 2;
    public static int heightUp = MotionPanel.getINSTANCE().getHeight() / 2;
    public static Point2D point1Panel;
    public static Point2D point2Panel;
    public static Point2D point3Panel;
    public static Point2D point4Panel;

    public PanelModel(){
        point1Panel = new Point2D.Double(center.getX() - widthLeft, center.getY() - heightUp);
        point2Panel = new Point2D.Double(center.getX() + widthRight, center.getY() - heightUp);
        point3Panel = new Point2D.Double(center.getX() + widthRight, center.getY() + heightDown);
        point4Panel = new Point2D.Double(center.getX() - widthLeft, center.getY() + heightUp);
        System.out.println("xPanell:"+point1Panel.getX()+" "+ point2Panel.getX()+" "+ point3Panel.getX()+" "+ point4Panel.getX());
        System.out.println("yPanell:"+point1Panel.getY()+" "+ point2Panel.getY()+" "+ point3Panel.getY()+" "+ point4Panel.getY());
        Collidable.collidables.add(this);
    }
    @Override
    public boolean isCircular() {
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
    public boolean isTriangular() {
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
//    public static PanelModel getINSTANCE() {
//        if (INSTANCE==null) INSTANCE=new PanelModel();
//        return INSTANCE;
//   }
}


//    public PanelModel(Point2D center, int widthLeft, int widthRight, int heightDown, int heightUp) {
//        this.center = center;
//        this.widthLeft = widthLeft;
//        this.widthRight = widthRight;
//        this.heightDown = heightDown;
//        this.heightUp = heightUp;
//        this.degree = 45;
//        this.id= UUID.randomUUID().toString();
//        this.direction=new Direction(new Random().nextInt());
//        Collidable.collidables.add(this);
//        createPanelView();
//        this.point1.setLocation(center.getX()-widthLeft,center.getY()-heightUp);
//        this.point2.setLocation(center.getX()+ widthRight,center.getY()-heightUp);
//        this.point3.setLocation(center.getX()+widthRight,center.getY()+heightDown);
//        this.point4.setLocation(center.getX()-widthLeft,center.getY()-heightUp);
//        System.out.println("pointsX:"+point1.getX()+" "+point2.getX()+" "+point3.getX()+" "+point4.getX());
//        System.out.println("pointsY:"+point1.getY()+" "+point2.getY()+" "+point3.getY()+" "+point4.getY());
//        Collidable.collidables.add(this);
//        Database.panelModelData = this;
//    }
//
//    @Override
//    public void setDirection(Direction direction){
//            this.direction = direction;
//    }
//    @Override
//    public Point2D getAnchor() {
//        return center;
//    }
//    @Override
//    public ArrayList<Point2D> getVertices() {
//        ArrayList<Point2D> vertices = new ArrayList<>();
//        vertices.add(point4);
//        vertices.add(point3);
//        vertices.add(point2);
//        vertices.add(point1);
//        //System.out.println(point1.getX()+" "+point1.getY());
//        return vertices;
//    }
//
//        @Override
//        public CollisionState collides(Collidable collidable) {
//            return Collidable.super.collides(collidable);
//        }
//    public int getWidthRight() {
//        return widthRight;
//    }
//
//    public void setWidthRight(int widthRight) {
//        this.widthRight = widthRight;
//        Database.panelModelData.setWidthRight(widthRight);
//    }
//
//    public int getWidthLeft() {
//        return widthLeft;
//    }
//
//    public void setWidthLeft(int widthLeft) {
//        this.widthLeft = widthLeft;
//        Database.panelModelData.setWidthLeft(widthLeft);
//    }
//
//    public int getHeightDown() {
//        return heightDown;
//    }
//
//    public void setHeightDown(int heightDown) {
//        this.heightDown = heightDown;
//        Database.panelModelData.setHeightDown(heightDown);
//    }
//
//    public int getHeightUp() {
//        return heightUp;
//    }
//
//    public void setHeightUp(int heightUp) {
//        this.heightUp = heightUp;
//        Database.panelModelData.setHeightUp(heightUp);
//    }
//
//
//
//    public Point2D getCenter() {
//        return center;
//    }
//
//    public Point2D getPoint1() {
//        return point1;
//    }
//
//    public Point2D getPoint2() {
//        return point2;
//    }
//
//    public Point2D getPoint3() {
//        return point3;
//    }
//
//    public Point2D getPoint4() {
//        return point4;
//    }
//
////        @Override
////        public void move(Direction direction, double speed) {
////            Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
////            Point corner=new Point(GlassFrame.getINSTANCE().getX(),GlassFrame.getINSTANCE().getY());
////            this.center=addVectors(center,movement);
////            this.point1=addVectors(point1, movement);
////            this.point2=addVectors(point2, movement);
////            this.point3=addVectors(point3, movement);
////            this.point4=addVectors(point4, movement);
////        }
//
//        @Override
//        public boolean isCircular() {
//            return false;
//        }
//
//        @Override
//        public boolean isPanel() {
//            return true;
//        }
//
//        @Override
//        public boolean isRectangular() {
//            return false;
//        }
//
//        @Override
//        public boolean isTriangular() {
//            return false;
//        }
//
//        @Override
//        public double getRadius() {
//            return 0;
//        }
//    }
