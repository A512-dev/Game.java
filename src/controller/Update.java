package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.SquareModel;
import model.charactersModel.TriangleModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GlassFrame;
import view.HomePanel;
import view.MotionPanel;
import view.charactersView.EpsilonView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;

import javax.swing.*;


import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.relativeLocation;

public class Update {

    public Update() {
        new Timer(1000, e -> updateTime()) {{
            setCoalesce(true);
        }}.start();
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()) {{
            setCoalesce(true);
        }}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()) {{
            setCoalesce(true);
        }}.start();
    }

    private void updateTime() {
        Database.time++;
    }

    public void updateView() {
        if (!Database.gameOver) {
            for (TriangleView triangleView : TriangleView.triangleViews) {
                triangleView.setCurrentLocation(calculateViewLocationTriangleAnchor(MotionPanel.getINSTANCE(), triangleView.getId()));
                triangleView.setCurrentRadius(getViewRadiusTriangle(triangleView.getId()));
                triangleView.setCurrentVertices(calculateViewLocationTrianglePoints(GlassFrame.getINSTANCE(), triangleView.getId()));
            }
            for (EpsilonView epsilonView : EpsilonView.epsilonViews) {
                epsilonView.setCurrentLocation(calculateViewLocationEpsilon(MotionPanel.getINSTANCE(), epsilonView.getId()));
                epsilonView.setCurrentRadius(getViewRadiusEpsilon(epsilonView.getId()));
                epsilonView.setCurrentVertices(calculateViewLocationEpsilonPoints(GlassFrame.getINSTANCE(), epsilonView.getId()));

            }
            for (SquareView squareView : SquareView.squareViews) {
                squareView.setCurrentLocation(calculateViewLocationSquareAnchor(MotionPanel.getINSTANCE(), squareView.getId()));
                squareView.setCurrentRadius(getViewRadiusSquare(squareView.getId()));
                squareView.setCurrentVertices(calculateViewLocationSquarePoints(GlassFrame.getINSTANCE(), squareView.getId()));
                //System.out.println(squareView.getCurrentLocation());
                //System.out.println(squareView.getCurrentVertices()[0]+" "+squareView.getCurrentVertices()[1]+" "+squareView.getCurrentVertices()[2]+
                //" "+squareView.getCurrentVertices()[3]);
            }
            //System.out.println("numHp:"+BallModel.getNumHp());
        }
        GlassFrame.getINSTANCE().repaint();
    }


    public void updateModel() {

        //System.out.println(Database.gameOver+" "+BallModel.getNumHp());
        if (EpsilonModel.getNumHp() < 1 && !Database.gameOver) {
            //System.out.println(12);
            Database.gameOver = true;
            GlassFrame.getINSTANCE().getContentPane().removeAll();
            TriangleModel.triangleModels.clear();
            SquareModel.squareModels.clear();
            EpsilonModel.epsilonModels.clear();
            TriangleView.triangleViews.clear();
            SquareView.squareViews.clear();
            EpsilonView.epsilonViews.clear();

            Collidable.collidables.clear();
            MotionPanel.setINSTANCE(null);

            EpsilonModel.setNumHp(100);

            //System.out.println("BallHP<1");

            new HomePanel();
        }
        if (!Database.gameOver && !Database.pause) {
            for (EpsilonModel epsilonModel : EpsilonModel.epsilonModels) {
                System.out.println("1:locationL" + epsilonModel.getAnchor() + " direction:" + EpsilonModel.direction.state + " speed:" + EpsilonModel.speedEpsilon);
                if (Database.moveLeftEpsilon && Database.moveRightEpsilon) {
                    Database.moveRightEpsilon = false;
                    Database.moveLeftEpsilon = false;
                }
                if (!Database.moveLeftReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
                    Database.moveLeftReleasedEpsilon = true;
                    Database.moveRightReleasedEpsilon =true;
                    Database.moveLeftEpsilon = false;
                    Database.moveRightEpsilon = false;
                }
                if (!Database.moveDownReleasedEpsilon && !Database.moveUpReleasedEpsilon) {
                    Database.moveDownReleasedEpsilon = true;
                    Database.moveUpReleasedEpsilon =true;
                    Database.moveDownEpsilon = false;
                    Database.moveUpEpsilon = false;
                }
                if (Database.moveUpEpsilon && Database.moveDownEpsilon) {
                    Database.moveDownEpsilon = false;
                    Database.moveUpEpsilon = false;
                }
                if (Database.moveLeftEpsilon) {
                    if (Database.moveDownEpsilon) {
                        if (!Database.moveDownReleasedEpsilon && !Database.moveLeftReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goLeftDownEp();
                            System.out.println("LeftDownConsistent");
                        }
                        else {
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goLeftUpEp();
                                System.out.println("LeftDown");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                if (Database.moveLeftReleasedEpsilon)
                                    Database.moveLeftEpsilon = false;
                                if (Database.moveDownReleasedEpsilon)
                                    Database.moveDownEpsilon = false;
                            }
                        }
                    }
                    if (Database.moveUpEpsilon) {
                        if (!Database.moveUpReleasedEpsilon && !Database.moveLeftReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goLeftUpEp();
                            System.out.println("LeftUpConsistent");
                        }
                        else {
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goLeftUpEp();
                                System.out.println("LeftUp");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                if (Database.moveLeftReleasedEpsilon)
                                    Database.moveLeftEpsilon = false;
                                if (Database.moveUpReleasedEpsilon)
                                    Database.moveUpEpsilon = false;
                            }
                        }
                    } if (!Database.moveUpEpsilon && !Database.moveDownEpsilon && Database.moveUpReleasedEpsilon && Database.moveDownReleasedEpsilon){
                        if (!Database.moveLeftReleasedEpsilon){
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goLeftEp();
                            System.out.println("left");
                        }
                        else {
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goLeftEp();
                                System.out.println("left");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                Database.moveLeftEpsilon = false;
                            }
                        }
                    }
                }
                else if (Database.moveRightEpsilon) {
                    if (Database.moveDownEpsilon) {
                        if (!Database.moveDownReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goRightDownEp();
                            System.out.println("RightDownConsistent");
                        }
                        else {
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goRightDownEp();
                                System.out.println("RightDown");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                if (Database.moveRightReleasedEpsilon)
                                    Database.moveRightEpsilon = false;
                                if (Database.moveDownReleasedEpsilon)
                                    Database.moveDownEpsilon = false;
                            }
                        }

                    } else if (Database.moveUpEpsilon) {
                        if (!Database.moveUpReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goRightUpEp();
                        }
                        else {
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goRightUpEp();
                                System.out.println("RightUp");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                if (Database.moveRightReleasedEpsilon)
                                    Database.moveRightEpsilon = false;
                                if (Database.moveUpReleasedEpsilon)
                                    Database.moveUpEpsilon = false;
                            }
                        }
                    } else {
                        if (!Database.moveRightReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goRightEp();
                        }
                        else{
                            if (Database.keyEpMove <= 25) {
                                Database.keyEpMove++;
                                epsilonModel.goRightEp();
                                System.out.println("right");
                                EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                                System.out.println(EpsilonModel.speedEpsilon);
                            } else {
                                Database.keyEpMove = 1;
                                Database.moveRightEpsilon = false;
                            }
                        }
                    }
                }
                else if (Database.moveDownEpsilon) {
                    if (!Database.moveDownReleasedEpsilon) {
                        EpsilonModel.speedEpsilon = SPEED;
                        epsilonModel.goDownEp();
                        System.out.println("downConsistent");
                    }
                    else {
                        if (Database.keyEpMove <= 25) {
                            Database.keyEpMove++;
                            epsilonModel.goDownEp();
                            System.out.println("down");
                            EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                            System.out.println(EpsilonModel.speedEpsilon);
                        } else {
                            Database.keyEpMove = 1;
                            Database.moveDownEpsilon = false;
                        }
                    }
                }
                else if (Database.moveUpEpsilon) {
                    if (Database.moveUpReleasedEpsilon) {
                        if (Database.keyEpMove <= 25) {
                            Database.keyEpMove++;
                            epsilonModel.goUpEp();
                            System.out.println("up");
                            EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                            System.out.println(EpsilonModel.speedEpsilon);
                        } else {
                            Database.keyEpMove = 1;
                            Database.moveUpEpsilon = false;
                        }
                    }
                    else {
                        EpsilonModel.speedEpsilon = SPEED;
                        epsilonModel.goUpEp();
                    }
                }
//                else {
//                    EpsilonModel.speedEpsilon = SPEED;
//                }
                epsilonModel.move();
                System.out.println("2:locationL" + epsilonModel.getAnchor() + " direction:" + EpsilonModel.direction.state + " speed:" + EpsilonModel.speedEpsilon);
            }
            for (SquareModel squareModel : SquareModel.squareModels) {
                squareModel.move();
            }
            for (TriangleModel triangleModel : TriangleModel.triangleModels) {
                triangleModel.move();
            }
            ArrayList<Collidable> collidables = new ArrayList<>(Collidable.collidables);
            for (int i = 0; i < collidables.size(); i++) {
                for (int j = i + 1; j < collidables.size(); j++) {
                    CollisionState collisionState = collidables.get(i).collides(collidables.get(j));
                    if (collisionState != null) {
                        if (collidables.get(i) instanceof Movable) {
//                        if (collidables.get(i).isRectangular())
//                            System.out.println("1:"+collidables.get(j).getVertices().get(0)+" "+collidables.get(j).getVertices().get(1)
//                            +" "+collidables.get(j).getVertices().get(2)+" "+collidables.get(j).getVertices().get(3))
//                            System.out.println(collidables.get(i).getVertices());
//                            System.out.println(collidables.get(i).isTriangular()+" "+collidables.get(i).getAnchor());
                            ((Movable) collidables.get(i)).setDirection(new Direction(relativeLocation(collidables.get(i).getAnchor(), collisionState.collisionPoint)));
                            ((Movable) collidables.get(i)).setSpeed(SPEED);
                            ((Movable) collidables.get(i)).setVelocity(-SPEED/25);
                        }
                        if (collidables.get(j) instanceof Movable) {
//                        if (collidables.get(j).isTriangular())
//                            System.out.println("2:"+collidables.get(i).getVertices().get(0)+" "+collidables.get(i).getVertices().get(1)
//                                    +" "+collidables.get(i).getVertices().get(2)+" "+collidables.get(i).getVertices().get(3))
                            ((Movable) collidables.get(j)).setDirection(new Direction(relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint)));
                            ((Movable) collidables.get(j)).setSpeed(SPEED);
                            ((Movable) collidables.get(j)).setVelocity(-SPEED/25);
                        }
                    }
                }
            }
        }
    }
}
