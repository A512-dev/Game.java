package model.charactersModel.collectibles;



import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class SquareCollectibleModel implements  Collidable{
    Point2D anchor;
    int directionX, directionY;
    double radius = RADIUS_COLLECTIBLES;
    public int time;
    String id;

    public int numHpCollectible = 1;

    double speedBall = 0;
    double velocityBall = 0;


    public static ArrayList<SquareCollectibleModel> squareCollectibleModels = new ArrayList<>();

    public SquareCollectibleModel(Point2D anchor) {

        this.anchor = anchor;
        this.radius = RADIUS_COLLECTIBLES;

        this.time = 0;

        this.id= UUID.randomUUID().toString();
        squareCollectibleModels.add(this);
        Collidable.collidables.add(this);
        createSquareCollectiblesView(id);

        this.speedBall = 0;
        this.velocityBall = 0;
    }
    @Override
    public String getId() {
        return id;
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
        return true;
    }

    @Override
    public boolean isCollectibleTr() {
        return false;
    }

    @Override
    public boolean isLaserBall() {
        return false;
    }

    @Override
    public double getRadius() {
        return radius;
    }

}
