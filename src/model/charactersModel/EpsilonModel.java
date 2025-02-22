package model.charactersModel;

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
    public static Point2D anchorEpsilon;
    double radius;
    String id;
    public static Direction direction;
    public static int numHp = 100;
    ArrayList<Point2D> epsilonVertices = new ArrayList<>();
    public static double speedEpsilon = 3*60D/UPS;
    public static double velocityEpsilon;
    public static int degree;
    public static int numVertices;
    boolean isAlive;
    int degreeDirection;

    public static int numXP = 100;


    public static ArrayList<EpsilonModel> epsilonModels =new ArrayList<>();

    public EpsilonModel(Point2D anchor, double radius, int degree,int numVertices) {
        this.anchorEpsilon = anchor;
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
        this.epsilonVertices.clear();


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
        return anchorEpsilon;
    }

    @Override
    public ArrayList<Point2D> getVertices() {
        return this.epsilonVertices;
    }

    @Override
    public void move(Direction direction, double speed, double velocity) {
        if (speed>10000)
            speed = SPEED;
        Point2D movement=multiplyVector(direction.getDirectionVector(),speed);
        anchorEpsilon =addVectors(anchorEpsilon,movement);
        for (int i=0; i<EpsilonModel.numVertices; i++)
            this.epsilonVertices.set(i,addVectors(this.epsilonVertices.get(i),movement));
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
    public boolean isCollectibleSq() {
        return false;
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
        direction = new Direction(180);
    }
    public void goRightEp(){
        direction = new Direction(0);
    }
    public void goUpEp(){
        direction = new Direction(270);
    }
    public void goDownEp(){
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
