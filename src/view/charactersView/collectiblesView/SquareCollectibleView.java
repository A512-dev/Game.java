package view.charactersView.collectiblesView;


import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SquareCollectibleView {
    String id;
    Point2D currentLocation=new Point2D.Double(0,0);
    double currentRadius;
    public static ArrayList<SquareCollectibleView> squareCollectibleViews =new ArrayList<>();
    public SquareCollectibleView(String id) {
        this.id = id;
        squareCollectibleViews.add(this);
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
