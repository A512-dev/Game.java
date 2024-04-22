package view.charactersView;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LaserBallView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius;
    public static ArrayList<LaserBallView> laserBallViews =new ArrayList<>();
    public LaserBallView(String id) {
        this.id = id;
        laserBallViews.add(this);
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

    public Point2D[] getCurrentVertices() {return null;}
    public String getId() {
        return id;
    }
}
