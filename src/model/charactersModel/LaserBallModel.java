package model.charactersModel;

import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.createEpsilonView;
import static controller.Controller.createLaserBallView;
import static controller.Utils.*;

public class LaserBallModel implements Movable,Collidable{
    Point2D anchor;
    int directionX, directionY;
    double radius;
    String id;

    public boolean isLarge() {
        return isLarge;
    }

    boolean isLarge;

    public int numHpLaseBall = 1;

    double speedBall;
    double velocityBall;



    public Direction direction;


    public static ArrayList<LaserBallModel> laserBallModels = new ArrayList<>();

    public LaserBallModel(Point2D destination, Point2D anchor, Boolean isLarge) {

        this.anchor = anchor;
        this.radius = LASER_GUN_RADIUS;

        this.isLarge = isLarge;

        this.id= UUID.randomUUID().toString();
        this.direction=new Direction(new Random().nextInt());
        laserBallModels.add(this);
        Collidable.collidables.add(this);
        createLaserBallView(id);

        this.speedBall = SPEED;
        this.velocityBall = 0;

        this.direction = new Direction(relativeLocation(destination,relativeLocation(anchor,PanelModel.getPoint1Panel())));
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setSpeed(double speed) {
        this.speedBall = speed;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocityBall = velocity;
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
    public CollisionState collides(Collidable collidable) {
        return Collidable.super.collides(collidable);
    }

    @Override
    public void move(Direction direction, double speed, double velocity) {
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        Point corner=new Point(GlassFrame.getINSTANCE().getX(), GlassFrame.getINSTANCE().getY());
        this.anchor=addVectors(anchor,movement);
        speedBall += velocityBall;
        if (speedBall<0.5)
            velocityBall = 0;
    }

    @Override
    public void move() {
        move(direction,speedBall,velocityBall);
    }

    @Override
    public boolean isCircular() {
        return true;
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
    public boolean isLaserBall() {
        return true;
    }

    @Override
    public boolean isCollectibleTr() {
        return false;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    public Direction getDirection() {
        return direction;
    }
}
