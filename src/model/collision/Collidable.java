package model.collision;

import model.charactersModel.EpsilonModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Utils.weightedAddVectors;

public interface Collidable {
    ArrayList<Collidable> collidables=new ArrayList<>();
    String getId();
    boolean isCircular();
    boolean isEpsilon();
    boolean isPanel();
    boolean isRectangular();
    boolean isTriangular();
    double getRadius();
    Point2D getAnchor();
    ArrayList<Point2D> getVertices();
    default CollisionState collides(Collidable collidable){
        if (isEpsilon())
        {
            if (isCircular() && collidable.isCircular()){
                double distance=getAnchor().distance(collidable.getAnchor());
                if (distance<=getRadius()+collidable.getRadius()){
                    return new CollisionState(weightedAddVectors(getAnchor(),collidable.getAnchor(),getRadius() ,collidable.getRadius()));
                }
                return null;
            }
            else if (isCircular() && !collidable.isCircular()){
                Point2D closest=closestPointOnPolygon(getAnchor(),collidable.getVertices());
                if (closest.distance(getAnchor())<=getRadius()) {
                    if (collidable.getVertices().contains(closest) && collidable.getVertices().size()==4 && !collidable.isPanel())
                        EpsilonModel.setNumHp(EpsilonModel.getNumHp()-6);
                    else if(collidable.getVertices().contains(closest) && collidable.getVertices().size()==3 && !collidable.isPanel())
                    {
                        EpsilonModel.setNumHp(EpsilonModel.getNumHp()-10);
                        System.out.println("collusion");
                    }

                    return new CollisionState(closest);
                }
            }
            else if (!isCircular() && collidable.isCircular()) {
                Point2D closest = closestPointOnPolygon(collidable.getAnchor(), getVertices());
                if (closest.distance(collidable.getAnchor()) <= collidable.getRadius()) {
                    if (getVertices().contains(closest) && getVertices().size() == 4 && !isPanel())
                        EpsilonModel.setNumHp(EpsilonModel.getNumHp() - 6);
                    else if (getVertices().contains(closest) && getVertices().size() == 3 && !isPanel()) {
                        System.out.println("collusion");
                        EpsilonModel.setNumHp(EpsilonModel.getNumHp() - 10);
                    }

                    return new CollisionState(closest);
                }
            }
            if (!isCircular() && !collidable.isCircular() && !collidable.isPanel()) {
                Point2D closest = closestPointOnPolygon(getAnchor(), collidable.getVertices());

                Point2D[] pointsEp = new Point2D[getVertices().size()];
                Point2D[] pointsShape2 = new Point2D[collidable.getVertices().size()];

                int[] xPolyEp = new int[getVertices().size()];
                int[] yPolyEp = new int[getVertices().size()];
                for (int i=0; i<getVertices().size(); i++)
                {
                    xPolyEp[i] = (int) getVertices().get(i).getX();
                    yPolyEp[i] = (int) getVertices().get(i).getY();
                }
                Polygon epPoly = new Polygon(xPolyEp,yPolyEp,getVertices().size());

                int[] xPolyShape2 = new int[collidable.getVertices().size()];
                int[] yPolyShape2 = new int[collidable.getVertices().size()];
                for (int i=0; i<collidable.getVertices().size(); i++)
                {
                    xPolyEp[i] = (int) collidable.getVertices().get(i).getX();
                    yPolyEp[i] = (int) collidable.getVertices().get(i).getY();
                }
                Polygon shape2Poly = new Polygon(xPolyShape2,yPolyShape2,collidable.getVertices().size());
                for (int i=0; i<getVertices().size(); i++)
                {
                    if (shape2Poly.contains(getVertices().get(i)))
                        return new CollisionState(getVertices().get(i));
                }
                for (int i=0; i<collidable.getVertices().size(); i++)
                {
                    if (epPoly.contains(collidable.getVertices().get(i)))
                        return new CollisionState(collidable.getVertices().get(i));
                }
                if (closest.distance(getAnchor())<=getRadius()) {
                    //System.out.println("last");
                    return new CollisionState(closest);
                }
            }
        }
        else if (!isCircular() && !collidable.isCircular())
        {
            if (isPanel() && !collidable.isPanel()) {
                Point2D closest=closestPointOnPolygon(collidable.getAnchor(),getVertices());

                if (closest!=null && closest.distance(collidable.getAnchor())<=collidable.getRadius()) {
//                    System.out.println("kjh");
//                    System.out.println("Anchor:"+collidable.getAnchor().getX()+" "+collidable.getAnchor().getY());
//                    System.out.println("closest: "+closest.getX()+" "+closest.getY());
//
//                    System.out.println("qqqq");
                    if ((closest.getX()> getVertices().get(3).getX() -5)
                        && (closest.getX()<getVertices().get(1).getX()+5)
                        && (closest.getY()>getVertices().get(3).getY()-5)
                        && (closest.getY()<getVertices().get(1).getY()+5))
                    {
                        //System.out.println(getVertices().get(0)+" "+getVertices().get(1)+" "+getVertices().get(2)+" "+getVertices().get(3));
                        //System.out.println("Anchor:"+collidable.getAnchor().getX()+" "+collidable.getAnchor().getY());
//                        System.out.println((closest.getX()> getVertices().get(3).getX() -5)+" "+(closest.getX()<getVertices().get(1).getX()+5)
//                        +" "+(closest.getY()>getVertices().get(3).getY()-5)+" "+(closest.getY()<getVertices().get(1).getY()+5));
//                        System.out.println("closest: "+closest.getX()+" "+closest.getY());
//                        System.out.println(closest.distance(collidable.getAnchor())+" radius:"+collidable.getRadius());
//                        System.out.println(",kerflerf;");
//                        System.out.println("closest: "+closest.getX()+" "+closest.getY());
//                        System.out.println(closest.distance(collidable.getAnchor())+" radius:"+collidable.getRadius());
//                        System.out.println("kwkekwkkekekekekekekeek");
                        return new CollisionState(closest);
                    }
                }
            }
            if (isRectangular() && collidable.isRectangular()) {
                Point2D pointImpact = null;
                Point2D closest = closestPointOnPolygon(getAnchor(), collidable.getVertices());

                Point2D point1Shape1 = getVertices().get(0);
                Point2D point2Shape1 = getVertices().get(1);
                Point2D point3Shape1 = getVertices().get(2);
                Point2D point4Shape1 = getVertices().get(3);

                Point2D point1Shape2 = collidable.getVertices().get(0);
                Point2D point2Shape2 = collidable.getVertices().get(1);
                Point2D point3Shape2 = collidable.getVertices().get(2);
                Point2D point4Shape2 = collidable.getVertices().get(3);

                int[] xPoly1 = {(int) getVertices().get(0).getX(),(int) getVertices().get(1).getX()
                        ,(int) getVertices().get(2).getX(),(int) getVertices().get(3).getX()};
                int[] yPoly1 = {(int) getVertices().get(0).getY(),(int) getVertices().get(1).getY()
                        ,(int) getVertices().get(2).getY(),(int) getVertices().get(3).getY()};
                Polygon squarePoly1 = new Polygon(xPoly1,yPoly1,4);
                int[] xPoly2 = {(int) collidable.getVertices().get(0).getX(),(int) collidable.getVertices().get(1).getX()
                        ,(int) collidable.getVertices().get(2).getX(),(int) collidable.getVertices().get(3).getX()};
                int[] yPoly2 = {(int) collidable.getVertices().get(0).getY(),(int) collidable.getVertices().get(1).getY()
                        ,(int) collidable.getVertices().get(2).getY(),(int) collidable.getVertices().get(3).getY()};
                Polygon squarePoly2 = new Polygon(xPoly2,yPoly2,4);
                if (squarePoly2.contains(point1Shape1)) {
                    pointImpact = point1Shape1;
                    //System.out.println("1st");
                }
                else if (squarePoly2.contains(point2Shape1)){
                    pointImpact = point2Shape1;
                    //System.out.println("2nd");
                }
                else if (squarePoly2.contains(point3Shape1)){
                    pointImpact = point3Shape1;
                    //System.out.println("3rd");
                }
                else if (squarePoly2.contains(point4Shape1)){
                    pointImpact = point4Shape1;
                    //System.out.println("4th");
                }
                else if (squarePoly1.contains(point1Shape2)){
                    pointImpact = point1Shape2;
                    //System.out.println("5th");
                }
                else if (squarePoly1.contains(point2Shape2)){
                    pointImpact = point2Shape2;
                    //System.out.println("6th");
                }
                else if (squarePoly1.contains(point3Shape2)){
                    pointImpact = point3Shape2;
                    //System.out.println("7th");
                }
                else if (squarePoly1.contains(point4Shape2)) {
                    pointImpact = point4Shape2;
                    //System.out.println("8th");
                }
                else if (closest.distance(getAnchor())<=getRadius()) {
                    pointImpact = closest;
                    //System.out.println("last");
                }
                CollisionState collisionPoint = new CollisionState(pointImpact);
                if (pointImpact!=null) {
                    collisionPoint.impact();
                    return collisionPoint;
                }
            }
            if (isTriangular() && collidable.isTriangular()){
                Point2D closest = closestPointOnPolygon(getAnchor(), collidable.getVertices());
                Point2D pointImpact = null;

                Point2D point1Triangle1 = getVertices().get(0);
                Point2D point2Triangle1 = getVertices().get(1);
                Point2D point3Triangle1 = getVertices().get(2);

                Point2D point1Triangle2 = collidable.getVertices().get(0);
                Point2D point2Triangle2 = collidable.getVertices().get(1);
                Point2D point3Triangle2 = collidable.getVertices().get(2);

                int[] xPoly1 = {(int) getVertices().get(0).getX(),(int) getVertices().get(1).getX(),(int) getVertices().get(2).getX()};
                int[] yPoly1 = {(int) getVertices().get(0).getY(),(int) getVertices().get(1).getY(),(int) getVertices().get(2).getY()};
                Polygon trianglePoly1 = new Polygon(xPoly1,yPoly1,3);
                int[] xPoly2 = {(int) collidable.getVertices().get(0).getX(),(int) collidable.getVertices().get(1).getX()
                        ,(int) collidable.getVertices().get(2).getX()};
                int[] yPoly2 = {(int) collidable.getVertices().get(0).getY(),(int) collidable.getVertices().get(1).getY()
                        ,(int) collidable.getVertices().get(2).getY()};
                Polygon trianglePoly2 = new Polygon(xPoly2,yPoly2,3);
                if (trianglePoly2.contains(point1Triangle1)) {
                    //System.out.println("1st");
                    pointImpact = point1Triangle1;
                }
                else if (trianglePoly2.contains(point2Triangle1)){
                    pointImpact = point2Triangle1;
                    //System.out.println("2nd");
                }
                else if (trianglePoly2.contains(point3Triangle1)){
                    pointImpact = point3Triangle1;
                    //System.out.println("3rd");
                }
                else if (trianglePoly1.contains(point1Triangle2)){
                    pointImpact = point1Triangle2;
                    //System.out.println("4th");
                }
                else if (trianglePoly1.contains(point2Triangle2)){
                    pointImpact = point2Triangle2;
                    //System.out.println("5th");
                }
                else if (trianglePoly1.contains(point3Triangle2)){
                    pointImpact = point3Triangle2;
                    //System.out.println("6th");
                }

                else if (closest.distance(getAnchor())==getRadius()) {
                    pointImpact = closest;
                    //System.out.println("last");
                }
                CollisionState collisionPoint = new CollisionState(pointImpact);
                if (pointImpact!=null) {
                    collisionPoint.impact();
                    return collisionPoint;
                }
            }
            if (isTriangular() && collidable.isRectangular()) {
                Point2D pointImpact = null;
                Point2D closest = closestPointOnPolygon(getAnchor(), collidable.getVertices());

                Point2D point1Triangle = getVertices().get(0);
                Point2D point2Triangle = getVertices().get(1);
                Point2D point3Triangle = getVertices().get(2);

                Point2D point1Square = collidable.getVertices().get(0);
                Point2D point2Square = collidable.getVertices().get(1);
                Point2D point3Square = collidable.getVertices().get(2);
                Point2D point4Square = collidable.getVertices().get(3);

                int[] xPolyT = {(int) getVertices().get(0).getX(),(int) getVertices().get(1).getX(),(int) getVertices().get(2).getX()};
                int[] yPolyT = {(int) getVertices().get(0).getY(),(int) getVertices().get(1).getY(),(int) getVertices().get(2).getY()};
                Polygon trianglePoly = new Polygon(xPolyT,yPolyT,3);
                int[] xPolyS = {(int) collidable.getVertices().get(0).getX(),(int) collidable.getVertices().get(1).getX()
                        ,(int) collidable.getVertices().get(2).getX(),(int) collidable.getVertices().get(3).getX()};
                int[] yPolyS = {(int) collidable.getVertices().get(0).getY(),(int) collidable.getVertices().get(1).getY()
                        ,(int) collidable.getVertices().get(2).getY(),(int) collidable.getVertices().get(3).getY()};
                Polygon squarePoly = new Polygon(xPolyS,yPolyS,4);
                if (squarePoly.contains(point1Triangle)) {
                    pointImpact = point1Triangle;
                    //System.out.println("1st");
                }
                else if (squarePoly.contains(point2Triangle)){
                    pointImpact = point2Triangle;
                    //System.out.println("2nd");
                }
                else if (squarePoly.contains(point3Triangle)){
                    pointImpact = point3Triangle;
                    //System.out.println("3rd");
                }

                else if (trianglePoly.contains(point1Square)){
                    pointImpact = point1Square;
                    //System.out.println("4th");
                }
                else if (trianglePoly.contains(point2Square)){
                    pointImpact = point2Square;
                    //System.out.println("5th");
                }
                else if (trianglePoly.contains(point3Square)){
                    pointImpact = point3Square;
                    //System.out.println("6th");
                }
                else if (trianglePoly.contains(point4Square)){
                    pointImpact = point4Square;
                    //System.out.println("7th");
                }
                else if (closest.distance(getAnchor())==getRadius()) {
                    pointImpact = closest;
                    //System.out.println("last");
                }
                CollisionState collisionPoint = new CollisionState(pointImpact);
                if (pointImpact!=null) {
                    collisionPoint.impact();
                    return collisionPoint;
                }
            }
        }
        //TODO neither this or collidable are circular
        return null;
    }
    default Point2D closestPointOnPolygon(Point2D point,ArrayList<Point2D> vertices){
        double minDistance=Double.MAX_VALUE;
        Point2D closest=null;
        for (int i=0;i<vertices.size();i++){
            Point2D temp=getClosestPointOnSegment(vertices.get(i),vertices.get((i+1)%vertices.size()),point);
            double distance=temp.distance(point);
            if (distance<minDistance) {
                minDistance=distance;
                closest=temp;
            }
        }
        return closest;
    }
    default Point2D getClosestPointOnSegment(Point2D head1, Point2D head2, Point2D point) {
        double u =((point.getX()-head1.getX())*(head2.getX()-head1.getX())+(point.getY()-head1.getY())*(head2.getY()-head1.getY()))/head2.distanceSq(head1);
        if (u > 1.0) return (Point2D) head2.clone();
        else if (u <= 0.0) return (Point2D) head1.clone();
        else return new Point2D.Double(head2.getX()* u + head1.getX() * (1.0 - u) + 0.5,head2.getY() * u + head1.getY() * (1.0 - u) + 0.5);
    }
}
