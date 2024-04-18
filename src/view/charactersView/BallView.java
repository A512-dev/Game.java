package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BallView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius;
    public static ArrayList<BallView> ballViews=new ArrayList<>();
    public BallView(String id) {
        this.id = id;
        ballViews.add(this);
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
}
