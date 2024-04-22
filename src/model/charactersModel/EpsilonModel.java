package model.charactersModel;

import controller.Database;
import model.collision.Collidable;
import model.movement.Direction;
import model.movement.Movable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static controller.Constants.*;
import static controller.Controller.createEpsilonView;
import static controller.Utils.addVectors;
import static controller.Utils.multiplyVector;

public class EpsilonModel implements Movable, Collidable{
    Point2D anchor;
    double radius;
    String id;
    public static Direction direction;
    static int numHp = 100;
    static ArrayList<Point2D> epsilonVertices = new ArrayList<>();
    public static double speedEpsilon = 3*60D/UPS;
    public static double velocityEpsilon;
    static int degree;
    static int numVertices;
    boolean isAlive;
    int degreeDirection;


    public static ArrayList<EpsilonModel> epsilonModels =new ArrayList<>();

    public EpsilonModel(Point2D anchor, double radius, int degree,int numVertices) {
        this.anchor = anchor;
        this.isAlive = true;
        this.radius = radius;
        EpsilonModel.numVertices = numVertices;
        this.id= UUID.randomUUID().toString();
        degreeDirection = new Random().nextInt();
        this.direction=new Direction(degreeDirection);
        EpsilonModel.setNumHp(100);
        createEpsilonView(id);
        EpsilonModel.degree = degree;
        epsilonModels.add(this);
        Collidable.collidables.add(this);

        EpsilonModel.velocityEpsilon = 0;

        for (int i=0; i<EpsilonModel.numVertices; i++) {
            epsilonVertices.add(new Point2D.Double((int) (anchor.getX()+radius*Math.cos(Math.toRadians((degree+((double) 360/numVertices)*i)))),
                    (int) (anchor.getY()+radius*Math.sin(Math.toRadians(degree+((double) 360/numVertices)*i)))));
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setSpeed(double speed) {
        EpsilonModel.speedEpsilon = speed;
    }

    @Override
    public void setVelocity(double velocity) {
        EpsilonModel.velocityEpsilon = velocity;
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
        return EpsilonModel.epsilonVertices;
    }

    @Override
    public void move(Direction direction, double speed, double velocity) {
        if (speed>10000)
            speed = SPEED;
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        this.anchor=addVectors(anchor,movement);
        for (int i=0; i<EpsilonModel.numVertices; i++)
            EpsilonModel.epsilonVertices.set(i,addVectors(EpsilonModel.epsilonVertices.get(i),movement));
        speedEpsilon += velocityEpsilon;
        if (speedEpsilon<0.5)
            velocityEpsilon = 0;
    }

    @Override
    public void move() {
        move(direction,speedEpsilon,velocityEpsilon);
    }

    @Override
    public boolean isCircular() {
        if (EpsilonModel.numVertices==0)
            return true;
        return false;
    }

    @Override
    public boolean isEpsilon() {
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

    public static int getNumVertices() {
        return numVertices;
    }
    public static void setNumVertices(int numVertices) {
        EpsilonModel.numVertices = numVertices;
    }

    public static int getNumHp() {
        return numHp;
    }

    public static void setNumHp(int numHp) {
        EpsilonModel.numHp = numHp;
    }

    public void goLeftEp(){
        Point2D directionVector = direction.getDirectionVector();
//        if ((degreeDirection>=0 && degreeDirection<=90)) {
//            degreeDirection = 180-degreeDirection;
//        }
//        else if (degreeDirection>=270 && degreeDirection<=360) {
//            degreeDirection = 900-2*degreeDirection;
//        }
//        else {
//            degreeDirection = 180;
//        }
        direction = new Direction(180);
    }
    public void goRightEp(){
//        if ((degreeDirection>=90 && degreeDirection<=180)) {
//            degreeDirection = 180-degreeDirection;
//        }
//        else if (degreeDirection>=180 && degreeDirection<=270) {
//            degreeDirection = 540-degreeDirection;
//        }
//        else {
//            degreeDirection = 0;
//        }
        direction = new Direction(0);
    }
    public void goUpEp(){
//        if ((degreeDirection>=270 && degreeDirection<=360)) {
//            degreeDirection = 360-degreeDirection;
//        }
//        else if (degreeDirection>=180 && degreeDirection<=270) {
//            degreeDirection = 360-degreeDirection;
//        }
//        else {
//            degreeDirection = 90;
//        }
        direction = new Direction(270);
    }
    public void goDownEp(){
//        if ((degreeDirection>=0 && degreeDirection<=45)) {
//            degreeDirection = 360-45;
//        }
//        else if (degreeDirection>=45 && degreeDirection<=90){
//            degreeDirection = 360-degreeDirection
//        }
//        else if (degreeDirection>=90 && degreeDirection<=135) {
//            degreeDirection = 360-degreeDirection;
//        }
//        else if (degreeDirection>=135 && degreeDirection<=180)
//            degreeDirection = 50+degreeDirection;
//        else if (degreeDirection>=180 && degreeDirection<=180+45){
//            degreeDirection = 45 + degreeDirection;
//        }
//        else if (degreeDirection>=180+45 && degreeDirection<=270)
//            degreeDirection = 270;
//        else if ()
        direction = new Direction(90);
    }

    public void goLeftDownEp() {
        direction = new Direction(90+45+180);
    }
    public void goLeftUpEp() {
        direction = new Direction(+45+180);
    }
    public void goRightDownEp() {
        direction = new Direction(45);
    }
    public void goRightUpEp() {
        direction = new Direction(45+90);
    }
}
