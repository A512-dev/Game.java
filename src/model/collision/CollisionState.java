package model.collision;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.movement.Direction;

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
            collisionVector.setLocation(50*collisionVector.getX()/distance, 50*collisionVector.getY()/distance);
            Point2D squareVector = new Point2D.Double(SquareModel.squareModels.get(i).direction.getDirectionVector().getX()*SquareModel.squareModels.get(i).getSpeedSquare(),
                    SquareModel.squareModels.get(i).direction.getDirectionVector().getY()*SquareModel.squareModels.get(i).getSpeedSquare());
            SquareModel.squareModels.get(i).direction = new Direction(new Point2D.Double(squareVector.getX()+collisionVector.getX(), squareVector.getY()+collisionVector.getY()));
        }
        for (int i = 0; i< TriangleModel.triangleModels.size(); i++) {
            Point2D collisionVector = new Point2D.Double(TriangleModel.triangleModels.get(i).getAnchor().getX()-collisionPoint.getX(),
                    TriangleModel.triangleModels.get(i).getAnchor().getY()-collisionPoint.getY());
            double distance = Math.sqrt(collisionVector.getX()*collisionVector.getX()+collisionVector.getY()*collisionVector.getY());
            collisionVector.setLocation(50*collisionVector.getX()/distance, 50*collisionVector.getY()/distance);
            Point2D triangleVector = new Point2D.Double(TriangleModel.triangleModels.get(i).direction.getDirectionVector().getX()*TriangleModel.triangleModels.get(i).getSpeedTriangle(),
                    TriangleModel.triangleModels.get(i).direction.getDirectionVector().getY()*TriangleModel.triangleModels.get(i).getSpeedTriangle());
            TriangleModel.triangleModels.get(i).direction = new Direction(new Point2D.Double(triangleVector.getX()+collisionVector.getX(), triangleVector.getY()+collisionVector.getY()));
        }
        for (int i = 0; i< EpsilonModel.epsilonModels.size(); i++) {
            Point2D collisionVector = new Point2D.Double(EpsilonModel.epsilonModels.get(i).getAnchor().getX()-collisionPoint.getX(),
                    EpsilonModel.epsilonModels.get(i).getAnchor().getY()-collisionPoint.getY());
            double distance = Math.sqrt(collisionVector.getX()*collisionVector.getX()+collisionVector.getY()*collisionVector.getY());
            collisionVector.setLocation((50*collisionVector.getX()/distance), 50*collisionVector.getY()/distance);
            Point2D epsilonVector = new Point2D.Double(EpsilonModel.direction.getDirectionVector().getX()*EpsilonModel.speedEpsilon,
                    EpsilonModel.direction.getDirectionVector().getY()*EpsilonModel.speedEpsilon);
            EpsilonModel.direction = new Direction(new Point2D.Double(epsilonVector.getX()+collisionVector.getX()
                    , epsilonVector.getY()+collisionVector.getY()));
            if (EpsilonModel.speedEpsilon<4)
                EpsilonModel.speedEpsilon *= 5;

        }
    }
}
