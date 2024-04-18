package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SquareView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    Point2D[] currentVertices = new Point2D[4];
    double currentRadius;
    public static ArrayList<SquareView> squareViews =new ArrayList<>();
    public SquareView(String id) {
        this.id = id;
        for (int i=0; i<4; i++)
            currentVertices[i] = new Point2D.Double(0,0);
        squareViews.add(this);
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
    public String getId() {
        return id;
    }
}
