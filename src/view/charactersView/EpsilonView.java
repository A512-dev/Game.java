package view.charactersView;

import model.charactersModel.EpsilonModel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class EpsilonView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius;

    Point2D[] currentVertices = new Point2D[EpsilonModel.getNumVertices()];
    public static ArrayList<EpsilonView> epsilonViews =new ArrayList<>();
    public EpsilonView(String id) {
        for (int i=0; i<currentVertices.length; i++)
            currentVertices[i] = new Point2D.Double(0,0);
        this.id = id;
        epsilonViews.add(this);
    }

    public Point2D getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point2D currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getCurrentRadius() {
        return currentRadius;
    }

    public void setCurrentRadius(double currentRadius) {
        this.currentRadius = currentRadius;
    }

    public String getId() {
        return id;
    }

    public Point2D[] getCurrentVertices() {
        return currentVertices;
    }
    public void setCurrentVertices(Point2D[] currentVertices) {
        this.currentVertices = currentVertices;
    }

}
