package controller;

import model.charactersModel.PanelModel;

public class Database {
    public static Sensitivity sensitivity;
    public static Difficulty difficulty;
    public static SoundLevel soundLevel;
    public static PanelModel panelModelData;
    public static boolean gameOver = false;
    public static boolean pause = false;
    public static boolean moveRightEpsilon = false;
    public static boolean moveLeftEpsilon = false;
    public static boolean moveUpEpsilon = false;
    public static boolean moveDownEpsilon = false;

    public static int keyEpMove = 1;
    public static boolean moveRightReleasedEpsilon = true;
    public static boolean moveLeftReleasedEpsilon=true;
    public static boolean moveUpReleasedEpsilon=true;
    public static boolean moveDownReleasedEpsilon=true;
    public static int time = 0;

    public static PanelModel getPanelModelData() {
        return panelModelData;
    }

    public static void setPanelModelData(PanelModel panelModelData) {
        Database.panelModelData = panelModelData;
    }
}

