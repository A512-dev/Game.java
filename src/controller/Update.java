package controller;

import model.charactersModel.*;
import model.charactersModel.collectibles.SquareCollectibleModel;
import model.charactersModel.collectibles.TriangleCollectibleModel;
import model.collision.Collidable;
import model.collision.CollisionState;
import model.movement.Direction;
import model.movement.Movable;
import view.GameOverPanel;
import view.GlassFrame;
import view.HomePanel;
import view.MotionPanel;
import view.charactersView.EpsilonView;
import view.charactersView.LaserBallView;
import view.charactersView.SquareView;
import view.charactersView.TriangleView;
import view.charactersView.collectiblesView.SquareCollectibleView;
import view.charactersView.collectiblesView.TriangleCollectibleView;

import javax.swing.*;


import java.awt.geom.Point2D;
import java.util.ArrayList;

import static controller.Constants.*;
import static controller.Controller.*;
import static controller.Utils.relativeLocation;
import static game.Main.rng;

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
        if (!Database.pause && !Database.gameOver && Database.gameStarted) {
            if (Database.timeWave>= 10) {
                if (!Database.wave) {
                    createModelsChars();
                    Database.waveNumber++;
                }
                Database.wave = true;
            }
            if (Database.timeLaserBall>2)
                Database.timeLaserBall = Database.timeLaserBall%3;
            if (Database.threeLaserBalls)
                Database.timerThreeLaserBalls++;
            if (Database.timerThreeLaserBalls>=10){
                Database.timerThreeLaserBalls = 0;
                Database.threeLaserBalls = false;
            }
            if (Database.gameStarted)
                Database.timeWave++;
            Database.time++;
            for (int i = 0; i < SquareCollectibleModel.squareCollectibleModels.size(); i++) {
                SquareCollectibleModel.squareCollectibleModels.get(i).time++;
                if (SquareCollectibleModel.squareCollectibleModels.get(i).time >= 10) {
                    String id = SquareCollectibleModel.squareCollectibleModels.get(i).getId();
                    Collidable.collidables.remove(findCollidable(id));
                    SquareCollectibleModel.squareCollectibleModels.remove(findModelSquareCollectible(id));
                }
            }
            for (int i = 0; i < TriangleCollectibleModel.triangleCollectibleModels.size(); i++) {
                TriangleCollectibleModel.triangleCollectibleModels.get(i).time++;
                if (TriangleCollectibleModel.triangleCollectibleModels.get(i).time >= 10) {
                    String id = TriangleCollectibleModel.triangleCollectibleModels.get(i).getId();
                    Collidable.collidables.remove(findCollidable(id));
                    TriangleCollectibleModel.triangleCollectibleModels.remove(findModelTriangleCollectible(id));
                }
            }

            if (Database.wave) {
                Database.widthRight -= 2;
                Database.widthLeft -= 2;
                Database.heightUp -= 2;
                Database.heightDown -= 2;
                new PanelModel();
                MotionPanel.getINSTANCE();
            }
        }

    }

    public void updateView() {
        if (!Database.gameOver) {
            if (Database.wave) {
                for (int i = 0; i < TriangleView.triangleViews.size(); i++) {
                    if (findModelTriangle(TriangleView.triangleViews.get(i).getId()) == null)
                        TriangleView.triangleViews.remove(TriangleView.triangleViews.get(i));
                    else {
                        TriangleView.triangleViews.get(i).setCurrentLocation(calculateViewLocationTriangleAnchor(MotionPanel.getINSTANCE(), TriangleView.triangleViews.get(i).getId()));
                        TriangleView.triangleViews.get(i).setCurrentRadius(getViewRadiusTriangle(TriangleView.triangleViews.get(i).getId()));
                        TriangleView.triangleViews.get(i).setCurrentVertices(calculateViewLocationTrianglePoints(GlassFrame.getINSTANCE(), TriangleView.triangleViews.get(i).getId()));
                    }
                }
                for (int i = 0; i < SquareView.squareViews.size(); i++) {
                    if (findModelSquare(SquareView.squareViews.get(i).getId()) == null)
                        SquareView.squareViews.remove(SquareView.squareViews.get(i));
                    else {
                        SquareView.squareViews.get(i).setCurrentLocation(calculateViewLocationSquareAnchor(MotionPanel.getINSTANCE(), SquareView.squareViews.get(i).getId()));
                        SquareView.squareViews.get(i).setCurrentRadius(getViewRadiusSquare(SquareView.squareViews.get(i).getId()));
                        SquareView.squareViews.get(i).setCurrentVertices(calculateViewLocationSquarePoints(GlassFrame.getINSTANCE(), SquareView.squareViews.get(i).getId()));
                    }
                }
                for (int i = 0; i < LaserBallView.laserBallViews.size(); i++) {
                    if (findModelLaserBall(LaserBallView.laserBallViews.get(i).getId()) == null)
                        LaserBallView.laserBallViews.remove(LaserBallView.laserBallViews.get(i));
                    else {
                        LaserBallView.laserBallViews.get(i).setCurrentLocation(calculateViewLocationLaserBallAnchor(MotionPanel.getINSTANCE(), LaserBallView.laserBallViews.get(i).getId()));
                        LaserBallView.laserBallViews.get(i).setCurrentRadius(LASER_GUN_RADIUS);
                    }
                    //System.out.println(LaserBallView.laserBallViews);
                }
                for (int i = 0; i < SquareCollectibleView.squareCollectibleViews.size(); i++) {
                    if (findModelSquareCollectible(SquareCollectibleView.squareCollectibleViews.get(i).getId()) == null)
                        SquareCollectibleView.squareCollectibleViews.remove(SquareCollectibleView.squareCollectibleViews.get(i));
                    else {
                        SquareCollectibleView.squareCollectibleViews.get(i).setCurrentLocation(
                                calculateViewLocationSqCollectibleAnchor(MotionPanel.getINSTANCE(), SquareCollectibleView.squareCollectibleViews.get(i).getId()));
                        SquareCollectibleView.squareCollectibleViews.get(i).setCurrentRadius(RADIUS_COLLECTIBLES);
                    }

                }
                for (int i = 0; i < TriangleCollectibleView.triangleCollectibleViews.size(); i++) {
                    if (findModelTriangleCollectible(TriangleCollectibleView.triangleCollectibleViews.get(i).getId()) == null)
                        TriangleCollectibleView.triangleCollectibleViews.remove(TriangleCollectibleView.triangleCollectibleViews.get(i));
                    else {
                        TriangleCollectibleView.triangleCollectibleViews.get(i).setCurrentLocation(calculateViewLocationTrCollectibleAnchor(MotionPanel.getINSTANCE()
                                , TriangleCollectibleView.triangleCollectibleViews.get(i).getId()));
                        TriangleCollectibleView.triangleCollectibleViews.get(i).setCurrentRadius(RADIUS_COLLECTIBLES);
                    }
                    //System.out.println(TriangleCollectibleView.triangleCollectibleViews.get(i).getCurrentLocation());
                }
            }
            for (EpsilonView epsilonView : EpsilonView.epsilonViews) {
                epsilonView.setCurrentLocation(calculateViewLocationEpsilon(MotionPanel.getINSTANCE(), epsilonView.getId()));
                epsilonView.setCurrentRadius(EPSILON_RADIUS);
                epsilonView.setCurrentVertices(calculateViewLocationEpsilonPoints(GlassFrame.getINSTANCE(), epsilonView.getId()));
                //System.out.println(EpsilonModel.anchorEpsilon);
            }
        }
        GlassFrame.getINSTANCE().repaint();
    }


    public void updateModel() {
//            System.out.println("epsilon " + EpsilonModel.epsilonModels + " " + EpsilonView.epsilonViews);
//            System.out.println("square " + SquareModel.squareModels + " " + SquareView.squareViews);
//            System.out.println("triangle " + TriangleModel.triangleModels + " " + TriangleView.triangleViews);
//            System.out.println("collidable " + Collidable.collidables);
//            System.out.println("panel:"+PanelModel.getPoint1Panel()+" "+PanelModel.getPoint2Panel()+" "+PanelModel.getPoint3Panel()+" "+PanelModel.getPoint4Panel());
//        if (EpsilonModel.epsilonModels.size()>0) {
//            System.out.println("epsilonL: "+EpsilonModel.anchorEpsilon+" "+EpsilonModel.direction.getDirectionVector());
//        }
        if (Database.waveNumber==5  || (EpsilonModel.getNumHp() < 1 && !Database.gameOver && Database.gameStarted)) {
            //System.out.println(12);
            Database.gameOver = true;
            Database.gameStarted = false;
            GlassFrame.getINSTANCE().getContentPane().removeAll();
            TriangleModel.triangleModels.clear();
            SquareModel.squareModels.clear();
            EpsilonModel.epsilonModels.clear();
            TriangleView.triangleViews.clear();
            SquareView.squareViews.clear();
            EpsilonView.epsilonViews.clear();
            LaserBallModel.laserBallModels.clear();
            LaserBallView.laserBallViews.clear();
            Collidable.collidables.clear();
            new PanelModel();

            MotionPanel.setINSTANCE(null);
//            PanelModel.setINSTANCE(null);

            EpsilonModel.setNumHp(100);

            //System.out.println("BallHP<1");
            //new HomePanel();
            new GameOverPanel();
        }
        if (!Database.gameStarted){
            if (Collidable.collidables.size()>1) {
                Collidable.collidables.clear();
                new PanelModel();
            }
        }
        if (!Database.gameOver && !Database.pause && Database.gameStarted) {
//            System.out.println("ccccccccccccccccccccccccccccccccc");
            if (Database.wave) {
                for (int i = 0; i < SquareModel.squareModels.size(); i++) {
                    if (SquareModel.squareModels.get(i).numHpSquare < 1) {
                        generateCollectiblesSquare(SquareModel.squareModels.get(i).getAnchor());
                        //System.out.println("squareDead");
                        String id = SquareModel.squareModels.get(i).getId();
                        Collidable.collidables.remove(findCollidable(id));
                        SquareModel.squareModels.remove(findModelSquare(id));
                    } else {
                        int dashChance = rng.nextInt(0, 100);
                        if (dashChance % 100 == 0) {
                            SquareModel.squareModels.get(i).dash = true;
                            SquareModel.squareModels.get(i).dashSquare(EpsilonModel.anchorEpsilon);
                        }
                        SquareModel.squareModels.get(i).move();
//                        if (SquareModel.squareModels.get(i).getAnchor().getX()>PanelModel.getINSTANCE().point2Panel.getX())
//                            SquareModel.squareModels.get(i).setAnchor(new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()-2
//                                    ,SquareModel.squareModels.get(i).getAnchor().getY()));
//                        if (SquareModel.squareModels.get(i).getAnchor().getX()<PanelModel.getINSTANCE().point2Panel.getX())
//                            SquareModel.squareModels.get(i).setAnchor(new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()+2
//                                    ,SquareModel.squareModels.get(i).getAnchor().getY()));
//                        if (SquareModel.squareModels.get(i).getAnchor().getY()<PanelModel.getINSTANCE().point1Panel.getY())
//                            SquareModel.squareModels.get(i).setAnchor(new Point2D.Double(SquareModel.squareModels.get(i).getAnchor().getX()
//                                    ,PanelModel.getINSTANCE().point1Panel.getY()+2));
//                        if (SquareModel.squareModels.get(i).getAnchor().getY()>PanelModel.getINSTANCE().point3Panel.getY())
//                            SquareModel.squareModels.get(i).setAnchor(new Point2D.Double(SquareModel.squareModels.get(i).getAnchor().getY()
//                                    ,PanelModel.getINSTANCE().point3Panel.getY()-2));
                    }
                }
                for (int i = 0; i < TriangleModel.triangleModels.size(); i++) {
                    if (TriangleModel.triangleModels.get(i).numHpTriangle < 1) {
                        generateCollectiblesTriangle(TriangleModel.triangleModels.get(i).getAnchor());
                        String id = TriangleModel.triangleModels.get(i).getId();
                        TriangleModel.triangleModels.remove(findModelTriangle(id));
                        Collidable.collidables.remove(findCollidable(id));
                    } else {
                        int dashChance = rng.nextInt(0, 100);
                        if (dashChance % 100 == 0)
                            TriangleModel.triangleModels.get(i).dash = true;
                        TriangleModel.triangleModels.get(i).move();
//                        TriangleModel.triangleModels.get(i).move();
//                        if (TriangleModel.triangleModels.get(i).getAnchor().getX()>PanelModel.getINSTANCE().point2Panel.getX())
//                            TriangleModel.triangleModels.get(i).setAnchor(new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()-2
//                                    ,SquareModel.squareModels.get(i).getAnchor().getY()));
//                        if (TriangleModel.triangleModels.get(i).getAnchor().getX()<PanelModel.getINSTANCE().point2Panel.getX())
//                            TriangleModel.triangleModels.get(i).setAnchor(new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()+2
//                                    ,TriangleModel.triangleModels.get(i).getAnchor().getY()));
//                        if (TriangleModel.triangleModels.get(i).getAnchor().getY()<PanelModel.getINSTANCE().point1Panel.getY())
//                            TriangleModel.triangleModels.get(i).setAnchor(new Point2D.Double(SquareModel.squareModels.get(i).getAnchor().getX()
//                                    ,PanelModel.getINSTANCE().point1Panel.getY()+2));
//                        if (TriangleModel.triangleModels.get(i).getAnchor().getY()>PanelModel.getINSTANCE().point3Panel.getY())
//                            TriangleModel.triangleModels.get(i).setAnchor(new Point2D.Double(TriangleModel.triangleModels.get(i).getAnchor().getY()
//                                    ,PanelModel.getINSTANCE().point3Panel.getY()-2));
                    }
                }
                for (int i = 0; i < SquareCollectibleModel.squareCollectibleModels.size(); i++) {
                    if (SquareCollectibleModel.squareCollectibleModels.get(i).numHpCollectible < 1) {
                        String id = SquareCollectibleModel.squareCollectibleModels.get(i).getId();
                        SquareCollectibleModel.squareCollectibleModels.remove(findModelSquareCollectible(id));
                        Collidable.collidables.remove(findCollidable(id));
                    }
                }
                for (int i = 0; i < TriangleCollectibleModel.triangleCollectibleModels.size(); i++) {
                    if (TriangleCollectibleModel.triangleCollectibleModels.get(i).numHpCollectible < 1) {
                        String id = TriangleCollectibleModel.triangleCollectibleModels.get(i).getId();
                        TriangleCollectibleModel.triangleCollectibleModels.remove(findModelTriangleCollectible(id));
                        Collidable.collidables.remove(findCollidable(id));
                    }
                }
                if (SquareModel.squareModels.size() == 0 && TriangleModel.triangleModels.size() == 0
                        && SquareCollectibleModel.squareCollectibleModels.size() == 0 && TriangleCollectibleModel.triangleCollectibleModels.size() == 0) {
                    SquareView.squareViews.clear();
                    TriangleView.triangleViews.clear();
                    SquareCollectibleView.squareCollectibleViews.clear();
                    TriangleCollectibleView.triangleCollectibleViews.clear();
                    Database.wave = false;
                    Database.timeWave = 0;
                }
            }
            for (int i = 0; i < LaserBallModel.laserBallModels.size(); i++) {
                if (LaserBallModel.laserBallModels.get(i).numHpLaseBall < 1) {
                    String id = LaserBallModel.laserBallModels.get(i).getId();
                    LaserBallModel.laserBallModels.remove(findModelLaserBall(id));
                    Collidable.collidables.remove(findCollidable(id));
                } else {
                    LaserBallModel.laserBallModels.get(i).move();
                }
            }
            for (EpsilonModel epsilonModel : EpsilonModel.epsilonModels) {
//                if (EpsilonModel.anchorEpsilon.getX()>PanelModel.getINSTANCE().point2Panel.getX())
//                    EpsilonModel.anchorEpsilon = new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()-2
//                            ,EpsilonModel.anchorEpsilon.getY());
//                if (EpsilonModel.anchorEpsilon.getX()<PanelModel.getINSTANCE().point2Panel.getX())
//                    EpsilonModel.anchorEpsilon = new Point2D.Double(PanelModel.getINSTANCE().point2Panel.getX()+2
//                            ,EpsilonModel.anchorEpsilon.getY());
//                if (EpsilonModel.anchorEpsilon.getY()<PanelModel.getINSTANCE().point1Panel.getY())
//                    EpsilonModel.anchorEpsilon = new Point2D.Double(EpsilonModel.anchorEpsilon.getX()
//                            ,PanelModel.getINSTANCE().point1Panel.getY()+2);
//                if (EpsilonModel.anchorEpsilon.getY()>PanelModel.getINSTANCE().point3Panel.getY())
//                    EpsilonModel.anchorEpsilon = new Point2D.Double(TriangleModel.triangleModels.get(i).getAnchor().getY()
//                            ,PanelModel.getINSTANCE().point3Panel.getY()-2);
                //System.out.println("1:locationL" + epsilonModel.getAnchor() + " direction:" + EpsilonModel.direction.state + " speed:" + EpsilonModel.speedEpsilon);
                if (Database.moveLeftEpsilon && Database.moveRightEpsilon) {
                    Database.moveRightEpsilon = false;
                    Database.moveLeftEpsilon = false;
                }
                if (!Database.moveLeftReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
//                    Database.moveLeftReleasedEpsilon = true;
//                    Database.moveRightReleasedEpsilon = true;
                    Database.moveLeftEpsilon = false;
                    Database.moveRightEpsilon = false;
                }
                if (!Database.moveDownReleasedEpsilon && !Database.moveUpReleasedEpsilon) {
//                    Database.moveDownReleasedEpsilon = true;
//                    Database.moveUpReleasedEpsilon = true;
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
                            //System.out.println("LeftDownConsistent");
                        } else {
                            {
                                Database.keyEpMove = 1;
                                if (Database.moveLeftReleasedEpsilon)
                                    Database.moveLeftEpsilon = false;
                                if (Database.moveDownReleasedEpsilon)
                                    Database.moveDownEpsilon = false;
                            }
                        }
                    } else if (Database.moveUpEpsilon) {
                        if (!Database.moveUpReleasedEpsilon && !Database.moveLeftReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goLeftUpEp();
                            //System.out.println("LeftUpConsistent");
                        } else {
                            {
                                Database.keyEpMove = 1;
                                if (Database.moveLeftReleasedEpsilon)
                                    Database.moveLeftEpsilon = false;
                                if (Database.moveUpReleasedEpsilon)
                                    Database.moveUpEpsilon = false;
                            }
                        }
                    }
                    if (!Database.moveUpEpsilon && !Database.moveDownEpsilon && Database.moveUpReleasedEpsilon && Database.moveDownReleasedEpsilon) {
                        if (!Database.moveLeftReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goLeftEp();
                            // System.out.println("left");
                        } else {
                            {
                                Database.keyEpMove = 1;
                                EpsilonModel.velocityEpsilon = -SPEED / 25;
                                Database.moveLeftEpsilon = false;
                            }
                        }
                    }
                } else if (Database.moveRightEpsilon) {
                    if (Database.moveDownEpsilon) {
                        if (!Database.moveDownReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goRightDownEp();
                            // System.out.println("RightDownConsistent");
                        } else {
                                Database.keyEpMove = 1;
                                if (Database.moveRightReleasedEpsilon)
                                    Database.moveRightEpsilon = false;
                                if (Database.moveDownReleasedEpsilon)
                                    Database.moveDownEpsilon = false;
                        }
                    } else if (Database.moveUpEpsilon) {
                        if (!Database.moveUpReleasedEpsilon && !Database.moveRightReleasedEpsilon) {
                            EpsilonModel.speedEpsilon = SPEED;
                            EpsilonModel.velocityEpsilon = 0;
                            epsilonModel.goRightUpEp();
                        } else {
                            {
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
                        else {
                            Database.keyEpMove = 1;
                            Database.moveRightEpsilon = false;
                            EpsilonModel.velocityEpsilon = -SPEED / 25;
                        }
                    }
                } else if (Database.moveDownEpsilon) {
                    if (!Database.moveDownReleasedEpsilon) {
                        EpsilonModel.speedEpsilon = SPEED;
                        epsilonModel.goDownEp();
                        //System.out.println("downConsistent");
                    } else {
                        if (Database.keyEpMove <= 25) {
                            Database.keyEpMove++;
                            epsilonModel.goDownEp();
                            //System.out.println("down");
                            EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                            //System.out.println(EpsilonModel.speedEpsilon);
                        } else {
                            Database.keyEpMove = 1;
                            Database.moveDownEpsilon = false;
                        }
                    }
                } else if (Database.moveUpEpsilon) {
                    if (Database.moveUpReleasedEpsilon) {
                        if (Database.keyEpMove <= 25) {
                            Database.keyEpMove++;
                            epsilonModel.goUpEp();
                            //System.out.println("up");
                            EpsilonModel.speedEpsilon = SPEED * 4 / Database.keyEpMove;
                            //System.out.println(EpsilonModel.speedEpsilon);
                        } else {
                            Database.keyEpMove = 1;
                            Database.moveUpEpsilon = false;
                        }
                    } else {
                        EpsilonModel.speedEpsilon = SPEED;
                        epsilonModel.goUpEp();
                    }
                }
//                else {
//                    EpsilonModel.speedEpsilon = SPEED;
//                }
                epsilonModel.move();
                // System.out.println("2:locationL" + epsilonModel.getAnchor() + " direction:" + EpsilonModel.direction.state + " speed:" + EpsilonModel.speedEpsilon);
            }
        }
        ArrayList<Collidable> collidables = new ArrayList<>(Collidable.collidables);
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {

                CollisionState collisionState = collidables.get(i).collides(collidables.get(j));
                if (collisionState != null) {
                    //System.out.println("collission: "+collisionState.collisionPoint);
                    //System.out.println("anchor: "+collidables.get(j).getAnchor());
                    if (collidables.get(i) instanceof Movable && !collidables.get(i).isLaserBall()) {
                        ((Movable) collidables.get(i)).setDirection(new Direction(relativeLocation(collidables.get(i).getAnchor(), collisionState.collisionPoint)));
                        ((Movable) collidables.get(i)).setSpeed(1.5*SPEED);
                        ((Movable) collidables.get(i)).setVelocity(-SPEED / 25);
                    }
                    if (collidables.get(j) instanceof Movable && !collidables.get(j).isLaserBall()) {
                        ((Movable) collidables.get(j)).setDirection(new Direction(relativeLocation(collidables.get(j).getAnchor(), collisionState.collisionPoint)));
                        ((Movable) collidables.get(j)).setSpeed(1.5*SPEED);
                        ((Movable) collidables.get(j)).setVelocity(-SPEED / 25);
                        //System.out.println(EpsilonModel.anchorEpsilon);
                        //System.out.println(EpsilonModel.direction.getDirectionVector());
                    }}
            }
        }
        //System.out.println(Database.gameOver+" "+BallModel.getNumHp())
    }
}


