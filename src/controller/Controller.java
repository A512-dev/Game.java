package controller;

import model.charactersModel.BallModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import view.MotionPanel;
import view.charactersView.BallView;
import view.charactersView.PanelView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller.Utils.relativeLocation;

public abstract class Controller {
    public static void createBallView(String id){
        new BallView(id);
    }
    public static void createSquareView(String id){
        new SquareView(id);
    }
    public static void createTriangleView(String id){
        new TriangleView(id);
    }
    public static void createPanelView(){
        new PanelView();
    }




    public static Point2D calculateViewLocationBall(Component component, String id){
        BallModel ballModel=findModelBall(id);
        Point corner=new Point(component.getX(),component.getY());
        assert ballModel != null;
        return relativeLocation(ballModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationSquareAnchor(Component component, String id){
        SquareModel squareModel=findModelSquare(id);
        Point corner=new Point(component.getX(),component.getY());
        assert squareModel != null;
        return relativeLocation(squareModel.getAnchor(),corner);
    }
    public static Point2D calculateViewLocationTriangleAnchor(Component component, String id){
        TriangleModel triangleModel=findModelTriangle(id);
        Point corner=new Point(component.getX(),component.getY());
        assert triangleModel != null;
        return relativeLocation(triangleModel.getAnchor(),corner);
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



    public static double getViewRadiusBall(String id){
        return Objects.requireNonNull(findModelBall(id)).getRadius();
    }
    public static double getViewRadiusSquare(String id){
        return Objects.requireNonNull(findModelSquare(id)).getRadius();
    }
    public static double getViewRadiusTriangle(String id){
        return Objects.requireNonNull(findModelTriangle(id)).getRadius();
    }

    private static TriangleModel findModelTriangle(String id) {
        for (TriangleModel triangleModel: TriangleModel.triangleModels){
            if (triangleModel.getId().equals(id)) return triangleModel;
        }
        return null;
    }
    public static BallModel findModelBall(String id){
        for (BallModel ballModel: BallModel.ballModels){
            if (ballModel.getId().equals(id)) return ballModel;
        }
        return null;
    }
    public static SquareModel findModelSquare(String id){
        for (SquareModel squareModel: SquareModel.squareModels){
            if (squareModel.getId().equals(id)) return squareModel;
        }
        return null;
    }
}
