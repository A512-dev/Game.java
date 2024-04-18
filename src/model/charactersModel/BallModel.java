package model.charactersModel;

import model.collision.Collidable;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.SPEED;
import static controller.Controller.createBallView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class BallModel implements Movable, Collidable{
    Point2D anchor;
    double radius;
    String id;
    Direction direction;
    int numHp;
    boolean isAlive;
    public static ArrayList<BallModel> ballModels=new ArrayList<>();

    public BallModel(Point2D anchor, double radius) {
        this.anchor = anchor;
        this.isAlive = true;
        this.radius = radius;
        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        this.numHp = 100;
        ballModels.add(this);
        Collidable.collidables.add(this);
        createBallView(id);
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
        return null;
    }

    @Override
    public void move(Direction direction, double speed) {
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        this.anchor=addVectors(anchor,movement);
    }

    @Override
    public void move() {
        move(direction,SPEED);
    }

    @Override
    public boolean isCircular() {
        return true;
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
        return false;
    }

    @Override
    public double getRadius() {
        return radius;
    }
}
