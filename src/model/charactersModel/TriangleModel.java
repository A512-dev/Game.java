package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.SPEED;
import static controller.Controller.*;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class TriangleModel implements Movable, Collidable {
    Point2D point1, point2, point3, anchor;
    double radius;
    double degree;
    String id;
    Direction direction;
    public static ArrayList<TriangleModel> triangleModels = new ArrayList<>();

    public TriangleModel(Point2D anchor,double degree, double radius) {
        this.anchor = anchor;
        this.radius = radius;
        this.degree = degree;

        this.point1 = new Point2D.Double(anchor.getX()+radius*Math.cos(Math.toRadians(degree)),anchor.getY()-radius*Math.sin(Math.toRadians(degree)));
        this.point2 = new Point2D.Double(anchor.getX()+radius*Math.cos(Math.toRadians(degree+120)),anchor.getY()-radius*Math.sin(Math.toRadians(degree+120)));
        this.point3 = new Point2D.Double(anchor.getX()+radius*Math.cos(Math.toRadians(degree+240)),anchor.getY()-radius*Math.sin(Math.toRadians(degree+240)));

        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        triangleModels.add(this);
        Collidable.collidables.add(this);
        createTriangleView(id);
    }
    public Polygon getTrianglePoly(){
        int[] xPoly = {(int) point1.getX(),(int) point2.getX(),(int) point3.getX()};
        int[] yPoly = {(int) point1.getY(),(int) point2.getY(),(int) point3.getY()};
        Polygon trianglePoly = new Polygon(xPoly,yPoly,3);
        return trianglePoly;
    }
    public String getId() {
        return id;
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
        vertices.add(point3);
        vertices.add(point2);
        vertices.add(point1);
        return vertices;
    }

    @Override
    public CollisionState collides(Collidable collidable) {
        return Collidable.super.collides(collidable);
    }

    @Override
    public void move(Direction direction, double speed) {
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        this.anchor = addVectors(anchor,movement);
        this.point1 = addVectors(point1,movement);
        this.point2 = addVectors(point2,movement);
        this.point3 = addVectors(point3,movement);
    }

    @Override
    public void move() {
        move(direction,SPEED);
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public boolean isPanel() {
        return false;
    }

    @Override
    public boolean isRectangular() {
        return false;
    }

    @Override
    public boolean isTriangular() {
        return true;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
