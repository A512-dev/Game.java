package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.SPEED;
import static controller.Controller.createSquareView;
import static controller.Utils.*;

public class SquareModel implements Movable, Collidable {
    Point2D anchor;
    Point2D point1 = new Point2D.Double(0,0);
    Point2D point2 = new Point2D.Double(0,0);
    Point2D point3 = new Point2D.Double(0,0);
    Point2D point4 = new Point2D.Double(0,0);
    int degree;
    int directionX, directionY;
    Line2D line1, line2, line3, line4;
    ArrayList<Line2D> lines = new ArrayList<>();
    double radius;
    public static double length;
    String id;

    double speedSquare;
    double velocitySquare;



    public Direction direction;


    public static ArrayList<SquareModel> squareModels = new ArrayList<>();

    public SquareModel(Point2D anchor, double radius,int degree) {

        this.anchor = anchor;
        this.radius = radius;
        this.degree = degree;
        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        squareModels.add(this);
        Collidable.collidables.add(this);
        createSquareView(id);

        this.speedSquare = SPEED;
        this.velocitySquare = 0;

        this.point1.setLocation(anchor.getX()+radius*Math.cos(Math.toRadians(degree)),anchor.getY()+radius*Math.sin(Math.toRadians(degree)));
        this.point2.setLocation(anchor.getX()+radius*Math.cos(Math.toRadians(degree+90)),anchor.getY()+radius*Math.sin(Math.toRadians(degree+90)));
        this.point3.setLocation(anchor.getX()+radius*Math.cos(Math.toRadians(degree+180)),anchor.getY()+radius*Math.sin(Math.toRadians(degree+180)));
        this.point4.setLocation(anchor.getX()+radius*Math.cos(Math.toRadians(degree+270)),anchor.getY()+radius*Math.sin(Math.toRadians(degree+270)));
    }
//    public Polygon getSquarePoly() {
//        int[] xPoly = {(int) point1.getX(),(int) point2.getX(),(int) point3.getX(),(int) point4.getX()};
//        int[] yPoly = {(int) point1.getY(),(int) point2.getY(),(int) point3.getY(),(int) point4.getY()};
//        Polygon squarePoly = new Polygon(xPoly,yPoly,4);
//        return squarePoly;
//    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setSpeed(double speed) {
        this.speedSquare = speed;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocitySquare = velocity;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Point2D getAnchor() {
        return anchor;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        ArrayList<Point2D> vertices = new ArrayList<>();
        vertices.add(point4);
        vertices.add(point3);
        vertices.add(point2);
        vertices.add(point1);
        //System.out.println(point1.getX()+" "+point1.getY());
        return vertices;
    }

    @Override
    public CollisionState collides(Collidable collidable) {
        return Collidable.super.collides(collidable);
    }

    @Override
    public void move(Direction direction, double speed, double velocity) {
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        Point corner=new Point(GlassFrame.getINSTANCE().getX(),GlassFrame.getINSTANCE().getY());
        this.anchor=addVectors(anchor,movement);
        this.point1=addVectors(point1, movement);
        this.point2=addVectors(point2, movement);
        this.point3=addVectors(point3, movement);
        this.point4=addVectors(point4, movement);
        speedSquare += velocitySquare;
        if (speedSquare<0.5)
            velocitySquare = 0;
    }

    @Override
    public void move() {
        move(direction,speedSquare,velocitySquare);
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
        return false;
    }

    @Override
    public boolean isRectangular() {
        return true;
    }

    @Override
    public boolean isTriangular() {
        return false;
    }

    @Override
    public double getRadius() {
        return radius*Math.cos(Math.toRadians(45));
    }

    public Direction getDirection() {
        return direction;
    }
}
