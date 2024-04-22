package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import view.MotionPanel;
import view.charactersView.EpsilonView;
import view.charactersView.PanelView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Objects;

import static controller.Utils.relativeLocation;

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
    public static void createPanelView(){
        new PanelView();
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

    private static TriangleModel findModelTriangle(String id) {
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

//    public static void impact(Point2D pointImpact) {
//
//    }

}
