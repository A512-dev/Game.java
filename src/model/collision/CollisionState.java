package model.collision;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;

import java.awt.geom.Point2D;

public class CollisionState {
    public Point2D collisionPoint;

    public CollisionState(Point2D collisionPoint) {
        this.collisionPoint = collisionPoint;
    }
    public void impact(){
        for (int i=0; i< SquareModel.squareModels.size(); i++) {
            Point2D collisionVector = new Point2D.Double(SquareModel.squareModels.get(i).getAnchor().getX()-collisionPoint.getX(),
                    SquareModel.squareModels.get(i).getAnchor().getY()-collisionPoint.getY());
            double distance = Math.sqrt(collisionVector.getX()*collisionVector.getX()+collisionVector.getY()*collisionVector.getY());
            collisionVector.setLocation(((int) collisionVector.getX()/distance),((int) collisionVector.getY()/distance));
            SquareModel.squareModels.get(i).direction.getNewDirectionVectors(collisionVector);
        }
        for (int i = 0; i< TriangleModel.triangleModels.size(); i++) {
            Point2D collisionVector = new Point2D.Double(TriangleModel.triangleModels.get(i).getAnchor().getX()-collisionPoint.getX(),
                    TriangleModel.triangleModels.get(i).getAnchor().getY()-collisionPoint.getY());
            double distance = Math.sqrt(collisionVector.getX()*collisionVector.getX()+collisionVector.getY()*collisionVector.getY());
            collisionVector.setLocation(((int) collisionVector.getX()/distance),((int) collisionVector.getY()/distance));
            TriangleModel.triangleModels.get(i).direction.getNewDirectionVectors(collisionVector);
        }
        for (int i = 0; i< EpsilonModel.epsilonModels.size(); i++) {
            Point2D collisionVector = new Point2D.Double(EpsilonModel.epsilonModels.get(i).getAnchor().getX()-collisionPoint.getX(),
                    EpsilonModel.epsilonModels.get(i).getAnchor().getY()-collisionPoint.getY());
            double distance = Math.sqrt(collisionVector.getX()*collisionVector.getX()+collisionVector.getY()*collisionVector.getY());
            collisionVector.setLocation(((int) collisionVector.getX()/distance),((int) collisionVector.getY()/distance));
            EpsilonModel.direction.getNewDirectionVectors(collisionVector);
        }
    }
}
