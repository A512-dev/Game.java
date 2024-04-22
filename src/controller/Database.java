package controller;

import model.charactersModel.EpsilonModel;
import model.charactersModel.PanelModel;
import view.GlassFrame;

import java.awt.geom.Point2D;

public class Database {
    public static Sensitivity sensitivity;
    public static Difficulty difficulty;
    public static SoundLevel soundLevel;
    public static PanelModel panelModelData;
    public static boolean gameOver;
    public static boolean pause;
    public static boolean moveRightEpsilon;
    public static boolean moveLeftEpsilon;
    public static boolean moveUpEpsilon;
    public static boolean moveDownEpsilon;
    public static boolean moveRightReleasedEpsilon;
    public static boolean moveLeftReleasedEpsilon;
    public static boolean moveUpReleasedEpsilon;
    public static boolean moveDownReleasedEpsilon;
    public static int timeWave;
    public static int time;
    public static boolean wave;
    public static int waveNumber;
    public static int numberOfLaserBalls;
    public static boolean threeLaserBalls;
    public static int timerThreeLaserBalls;
    public static boolean shopOpen;
    public static boolean gameStarted;
    public static int keyEpMove=1;

    public static int firstTime;



    public static int widthLeft;
    public static int widthRight;
    public static int heightDown;
    public static int heightUp;
    public static Point2D laserDestination = null;
    public static int timeLaserBall;

    public Database () {
        firstTime = 0;
        gameOver = false;
        pause = false;
        moveRightEpsilon = false;
        moveLeftEpsilon = false;
        moveUpEpsilon = false;
        moveDownEpsilon = false;
        moveRightReleasedEpsilon = true;
        moveLeftReleasedEpsilon=true;
        moveUpReleasedEpsilon=true;
        moveDownReleasedEpsilon=true;
        wave = false;
        shopOpen = false;
        gameStarted = false;
        widthLeft = GlassFrame.getINSTANCE().getWidth()/4;
        widthRight = GlassFrame.getINSTANCE().getWidth()/4 ;
        heightDown = GlassFrame.getINSTANCE().getHeight()/4;
        heightUp = GlassFrame.getINSTANCE().getHeight()/4;


        keyEpMove=1;
        timeWave = 0;
        time = 0;
        numberOfLaserBalls =1 ;
        threeLaserBalls = false;
        timerThreeLaserBalls = 0;

        timeLaserBall++;

        waveNumber = 0;
        if (Database.difficulty==null)
            Database.difficulty = Difficulty.Easy;
    }

    public static PanelModel getPanelModelData() {
        return panelModelData;
    }

    public static void setPanelModelData(PanelModel panelModelData) {
        Database.panelModelData = panelModelData;
    }
}

