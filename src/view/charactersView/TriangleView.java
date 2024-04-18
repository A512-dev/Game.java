package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius,currPoint1,currPoint2,currPoint3;
    Point2D[] currentVertices = new Point2D[3];
    public static ArrayList<TriangleView> triangleViews =new ArrayList<>();
    public TriangleView(String id) {
        this.id = id;
        triangleViews.add(this);
        currentVertices[0] = new Point2D.Double(0,0);
        currentVertices[1] = new Point2D.Double(0,0);
        currentVertices[2] = new Point2D.Double(0,0);
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

    public Point2D[] getCurrentVertices() {return this.currentVertices;}
    public void setCurrentVertices(Point2D[] currentVertices) {
        this.currentVertices = currentVertices;
    }

    public String getId() {
        return id;
    }
}
