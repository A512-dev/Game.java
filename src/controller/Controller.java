package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.LaserBallModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.charactersModel.collectibles.SquareCollectibleModel;
import model.charactersModel.collectibles.TriangleCollectibleModel;
import model.collision.Collidable;
import model.movement.Direction;
import view.MotionPanel;
import view.charactersView.*;
import view.charactersView.collectiblesView.SquareCollectibleView;
import view.charactersView.collectiblesView.TriangleCollectibleView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller.Constants.*;
import static controller.Utils.relativeLocation;
import static game.Main.rng;

public abstract class Controller {
    public static void createEpsilonView(String id){
        new EpsilonView(id);
    }
    public static void createSquareView(String id){
        new SquareView(id);
    }
    public static void createTriangleView(String id){
        new TriangleView(id);
    }
    public static void createLaserBallView(String id){
        new LaserBallView(id);
    }
    public static void createSquareCollectiblesView(String id){
        new SquareCollectibleView(id);
    }
    public static void createTriangleCollectiblesView(String id){
        new TriangleCollectibleView(id);
    }




    public static Point2D calculateViewLocationEpsilon(Component component, String id){
        EpsilonModel ballModel= findModelEpsilon(id);
        Point corner=new Point(component.getX(),component.getY());
        assert ballModel != null;
        return relativeLocation(ballModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationSquareAnchor(Component component, String id){
        SquareModel squareModel=findModelSquare(id);
        Point corner=new Point(component.getX(),component.getY());
//        assert squareModel != null;
        if (squareModel==null)
            return null;
        return relativeLocation(squareModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationTriangleAnchor(Component component, String id){
        TriangleModel triangleModel=findModelTriangle(id);
        Point corner=new Point(component.getX(),component.getY());
//        assert triangleModel != null;
        if (triangleModel==null)
            return null;
        return relativeLocation(triangleModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationLaserBallAnchor(Component component, String id){
        LaserBallModel laserBallModel=findModelLaserBall(id);
        Point corner=new Point(component.getX(),component.getY());
//        assert triangleModel != null;
        if (laserBallModel==null)
            return null;
        return relativeLocation(laserBallModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationSqCollectibleAnchor(Component component, String id){
        SquareCollectibleModel squareCollectibleModel=findModelSquareCollectible(id);
        Point corner=new Point(component.getX(),component.getY());
//        assert triangleModel != null;
        if (squareCollectibleModel==null)
            return null;
        return relativeLocation(squareCollectibleModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationTrCollectibleAnchor(Component component, String id){
        TriangleCollectibleModel triangleCollectibleModel=findModelTriangleCollectible(id);
        Point corner=new Point(component.getX(),component.getY());
//        assert triangleModel != null;
        if (triangleCollectibleModel==null)
            return null;
        return relativeLocation(triangleCollectibleModel.getAnchor(),corner);
    }







    public static Point2D[] calculateViewLocationSquarePoints(Component component, String id){
        SquareModel squareModel=findModelSquare(id);
//        Point corner=new Point(component.getX(),component.getY());
        assert squareModel != null;
        Point2D[] currentViewLocationSqPoints = new Point2D[4];
        Point corner=new Point(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getY());
        currentViewLocationSqPoints[0] = relativeLocation(squareModel.getVertices().get(0), corner);
        currentViewLocationSqPoints[1] = relativeLocation(squareModel.getVertices().get(1), corner);
        currentViewLocationSqPoints[2] = relativeLocation(squareModel.getVertices().get(2), corner);
        currentViewLocationSqPoints[3] = relativeLocation(squareModel.getVertices().get(3), corner);

        return currentViewLocationSqPoints;
    }
    public static Point2D[] calculateViewLocationTrianglePoints(Component component, String id){
        TriangleModel triangleModel=findModelTriangle(id);
        Point corner=new Point(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getY());
        assert triangleModel != null;
        Point2D[] currentViewLocationTrPoints = new Point2D[3];
        currentViewLocationTrPoints[0] = relativeLocation(triangleModel.getVertices().get(0), corner);
        currentViewLocationTrPoints[1] = relativeLocation(triangleModel.getVertices().get(1), corner);
        currentViewLocationTrPoints[2] = relativeLocation(triangleModel.getVertices().get(2), corner);
        return currentViewLocationTrPoints;
    }
    public static Point2D[] calculateViewLocationEpsilonPoints(Component component, String id){
        EpsilonModel epsilonModel=findModelEpsilon(id);
//        Point corner=new Point(component.getX(),component.getY());
        assert epsilonModel != null;
        Point2D[] currentViewLocationEpPoints = new Point2D[EpsilonModel.getNumVertices()];
        Point corner=new Point(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getY());
        for (int i=0; i<EpsilonModel.getNumVertices(); i++)
        {
            currentViewLocationEpPoints[i] = relativeLocation(epsilonModel.getVertices().get(i), corner);
        }
        return currentViewLocationEpPoints;
    }





    public static double getViewRadiusEpsilon(String id){
        return Objects.requireNonNull(findModelEpsilon(id)).getRadius();
    }
    public static double getViewRadiusSquare(String id){
        return Objects.requireNonNull(findModelSquare(id)).getRadius();
    }
    public static double getViewRadiusTriangle(String id){
        return Objects.requireNonNull(findModelTriangle(id)).getRadius();
    }
    public static double getViewRadiusLaserBall(String id) {
        return Objects.requireNonNull(findModelLaserBall(id)).getRadius();
    }

    public static TriangleModel findModelTriangle(String id) {
        for (TriangleModel triangleModel: TriangleModel.triangleModels){
            if (triangleModel.getId().equals(id)) return triangleModel;
        }
        return null;
    }
    public static EpsilonModel findModelEpsilon(String id){
        for (EpsilonModel epsilonModel : EpsilonModel.epsilonModels){
            if (epsilonModel.getId().equals(id)) return epsilonModel;
        }
        return null;
    }
    public static SquareModel findModelSquare(String id){
        for (SquareModel squareModel: SquareModel.squareModels){
            if (squareModel.getId().equals(id)) return squareModel;
        }
        return null;
    }
    public static LaserBallModel findModelLaserBall(String id){
        for (LaserBallModel laserBallModel: LaserBallModel.laserBallModels){
            if (laserBallModel.getId().equals(id)) return laserBallModel;
        }
        return null;
    }
    public static SquareCollectibleModel findModelSquareCollectible(String id){
        for (int i=0;  i<SquareCollectibleModel.squareCollectibleModels.size(); i++){
            if (SquareCollectibleModel.squareCollectibleModels.get(i).getId().equals(id))
                return SquareCollectibleModel.squareCollectibleModels.get(i);
        }
        return null;
    }
    public static TriangleCollectibleModel findModelTriangleCollectible(String id){
        for (int i=0;  i<TriangleCollectibleModel.triangleCollectibleModels.size(); i++){
            if (TriangleCollectibleModel.triangleCollectibleModels.get(i).getId().equals(id))
                return TriangleCollectibleModel.triangleCollectibleModels.get(i);
        }
        return null;
    }



    public static Collidable findCollidable(String id) {
        for (int i=0; i< Collidable.collidables.size(); i++){
            if (Collidable.collidables.get(i).getId().equals(id)) return Collidable.collidables.get(i);
        }
        return null;
    }

    public static double findDistancePoints(Point2D point1, Point2D point2) {
        return Math.sqrt((point1.getX()-point2.getX())*(point1.getX()-point2.getX())+
                (point1.getY()-point2.getY())*(point1.getY()-point2.getY()));
    }



    public static void createModelsChars(){
        int numTriangles = 0;
        int numSquares = 0;
        switch (Database.waveNumber) {
            case 1:
                switch (Database.difficulty) {
                    case Easy -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE1_EASY;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE1_EASY;
                    }
                    case Medium -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE1_MEDIUM;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE1_MEDIUM;
                    }
                    case Hard -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE1_HARD;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE1_HARD;
                    }
                }
                break;
            case 2:
                switch (Database.difficulty) {
                    case Easy -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE2_EASY;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE2_EASY;
                    }
                    case Medium -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE2_MEDIUM;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE2_MEDIUM;
                    }
                    case Hard -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE2_HARD;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE2_HARD;
                    }
                }
                break;
            case 3:
                switch (Database.difficulty) {
                    case Easy -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE3_EASY;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE3_EASY;
                    }
                    case Medium -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE3_MEDIUM;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE3_MEDIUM;
                    }
                    case Hard -> {
                        numSquares = NUMBER_OF_SQUARES_WAVE3_HARD;
                        numTriangles = NUMBER_OF_TRIANGLES_WAVE3_HARD;
                    }
                }
                break;
        }
        for(int i = 0; i< numTriangles; i++){
            double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX(),MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth());
            double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY(),MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight());
            double randomRadius=rng.nextDouble(MIN_RADIUS, MAX_RADIUS);
            int degree = rng.nextInt(0,360);
            new TriangleModel(new Point2D.Double(randomX,randomY),degree,randomRadius);
        }
        for(int i = 0; i< numSquares; i++){
            double randomRadius=rng.nextDouble(MIN_RADIUS, MAX_RADIUS);
            double randomX=rng.nextDouble(MotionPanel.getINSTANCE().getX()+randomRadius+2,MotionPanel.getINSTANCE().getX()+MotionPanel.getINSTANCE().getWidth()-randomRadius-2);
            double randomY=rng.nextDouble(MotionPanel.getINSTANCE().getY()+randomRadius+2,MotionPanel.getINSTANCE().getY()+MotionPanel.getINSTANCE().getHeight()-randomRadius-2);
            int degree = rng.nextInt(0,360);
            new SquareModel(new Point2D.Double(randomX,randomY),randomRadius,degree);
        }
    }
//    public static void createModelLaserBall(Direction direction) {
//        for(int i = 0; i< Database.numberOfLaserBalls; i++){
//            double posX = EpsilonModel.anchorEpsilon.getX()+direction.getDirectionVector().getX();
//            double posY = EpsilonModel.anchorEpsilon.getY()+direction.getDirectionVector().getY();
//            new LaserBallModel();
//        }
//    }
    public static void generateCollectiblesTriangle(Point2D anchor) {
        double randomX1 = rng.nextDouble( anchor.getX()-20,anchor.getX()+20);
        double randomY1 = rng.nextDouble( anchor.getY()-20,anchor.getY()+20);
        double randomX2 = rng.nextDouble( anchor.getX()-20,anchor.getX()+20);
        double randomY2 = rng.nextDouble( anchor.getY()-20,anchor.getY()+20);
        new TriangleCollectibleModel(new Point2D.Double(randomX1,randomY1));
        new TriangleCollectibleModel(new Point2D.Double(randomX2,randomY2));
    }
    public static void generateCollectiblesSquare(Point2D anchor) {
        double randomX1 = rng.nextDouble( anchor.getX()-20,anchor.getX()+20);
        double randomY1 = rng.nextDouble( anchor.getY()-20,anchor.getY()+20);
        new SquareCollectibleModel(new Point2D.Double(randomX1,randomY1));
    }
}

//    public static void impact(Point2D pointImpact) {
//
//    }
