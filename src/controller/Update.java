package controller;

import model.charactersModel.BallModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;
import view.MotionPanel;
import view.charactersView.BallView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import javax.swing.*;


import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.relativeLocation;

public class Update {

    public Update() {
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()){{setCoalesce(true);}}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()){{setCoalesce(true);}}.start();
    }

    public void updateView(){
        for (TriangleView triangleView: TriangleView.triangleViews){
            triangleView.setCurrentLocation(calculateViewLocationTriangleAnchor(MotionPanel.getINSTANCE(),triangleView.getId()));
            triangleView.setCurrentRadius(getViewRadiusTriangle(triangleView.getId()));
            triangleView.setCurrentVertices(calculateViewLocationTrianglePoints(GlassFrame.getINSTANCE(),triangleView.getId()));
        }
        for (BallView ballView: BallView.ballViews){
            ballView.setCurrentLocation(calculateViewLocationBall(MotionPanel.getINSTANCE(),ballView.getId()));
            ballView.setCurrentRadius(getViewRadiusBall(ballView.getId()));
        }
        for (SquareView squareView: SquareView.squareViews){
            squareView.setCurrentLocation(calculateViewLocationSquareAnchor(MotionPanel.getINSTANCE(),squareView.getId()));
            squareView.setCurrentRadius(getViewRadiusSquare(squareView.getId()));
            squareView.setCurrentVertices(calculateViewLocationSquarePoints(GlassFrame.getINSTANCE(),squareView.getId()));
            //System.out.println(squareView.getCurrentLocation());
            //System.out.println(squareView.getCurrentVertices()[0]+" "+squareView.getCurrentVertices()[1]+" "+squareView.getCurrentVertices()[2]+
                    //" "+squareView.getCurrentVertices()[3]);
        }
        GlassFrame.getINSTANCE().repaint();


    }

    public void updateModel(){
        for (BallModel ballModel: BallModel.ballModels){
            ballModel.move();
        }
        for (SquareModel squareModel: SquareModel.squareModels){
            squareModel.move();
        }
        for (TriangleModel triangleModel: TriangleModel.triangleModels){
            triangleModel.move();
        }
        ArrayList<Collidable> collidables=new ArrayList<>(Collidable.collidables);
        for (int i=0;i<collidables.size();i++){
            for (int j=i+1;j<collidables.size();j++){
                CollisionState collisionState=collidables.get(i).collides(collidables.get(j));
                if (collisionState!=null){
                    if (collidables.get(i) instanceof Movable){
//                        if (collidables.get(i).isRectangular())
//                            System.out.println("1:"+collidables.get(j).getVertices().get(0)+" "+collidables.get(j).getVertices().get(1)
//                            +" "+collidables.get(j).getVertices().get(2)+" "+collidables.get(j).getVertices().get(3));
                        ((Movable) collidables.get(i)).setDirection(new Direction(relativeLocation(collidables.get(i).getAnchor(),collisionState.collisionPoint)));
                    }
                    if (collidables.get(j) instanceof Movable){
//                        if (collidables.get(j).isTriangular())
//                            System.out.println("2:"+collidables.get(i).getVertices().get(0)+" "+collidables.get(i).getVertices().get(1)
//                                    +" "+collidables.get(i).getVertices().get(2)+" "+collidables.get(i).getVertices().get(3));
                        ((Movable) collidables.get(j)).setDirection(new Direction(relativeLocation(collidables.get(j).getAnchor(),collisionState.collisionPoint)));
                        System.out.println(1);
                    }
                }
            }
        }
    }
}
