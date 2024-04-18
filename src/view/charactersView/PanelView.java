package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PanelView {
    Point2D corner1, corner2, corner3, corner4;
    int width, height;
    Point2D center;
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    Point2D[] currentVertices = new Point2D[4];
    double currentRadius;
    public PanelView(){
        for (int i=0; i<4; i++)
            currentVertices[i] = new Point2D.Double(0,0);
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

    public Point2D[] getCurrentVertices() {return currentVertices;}
    public void setCurrentVertices(Point2D[] currentVertices) {
        this.currentVertices = currentVertices;
    }
}
