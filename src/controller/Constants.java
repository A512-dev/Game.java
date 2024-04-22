package controller;

import view.GlassFrame;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

public class Constants {
    public static final Dimension GLASS_FRAME_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension PANEL_SIZE = new Dimension((int) (GLASS_FRAME_DIMENSION.getWidth()/2), (int) (GLASS_FRAME_DIMENSION.getHeight()/2));
    public static final int FPS = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getRefreshRate();
    public static final double FRAME_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/FPS;
    public static final int UPS = 50;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/UPS;
    public static final double SPEED = 3*60D/UPS;
    public static final double MIN_RADIUS = 25;
    public static final double MAX_RADIUS = 35;
    public static final double EPSILON_RADIUS = 30;
    public static final int NUMBER_OF_SQUARES_WAVE1_EASY =1;
    public static final int NUMBER_OF_TRIANGLES_WAVE1_EASY = 1;
    public static final int NUMBER_OF_SQUARES_WAVE1_MEDIUM = 2;
    public static final int NUMBER_OF_TRIANGLES_WAVE1_MEDIUM = 2;
    public static final int NUMBER_OF_SQUARES_WAVE1_HARD = 3;
    public static final int NUMBER_OF_TRIANGLES_WAVE1_HARD = 3;


    public static final int NUMBER_OF_SQUARES_WAVE2_EASY = 2;
    public static final int NUMBER_OF_TRIANGLES_WAVE2_EASY = 2;
    public static final int NUMBER_OF_SQUARES_WAVE2_MEDIUM = 4;
    public static final int NUMBER_OF_TRIANGLES_WAVE2_MEDIUM = 4;
    public static final int NUMBER_OF_SQUARES_WAVE2_HARD = 5;
    public static final int NUMBER_OF_TRIANGLES_WAVE2_HARD = 5;


    public static final int NUMBER_OF_SQUARES_WAVE3_EASY = 4;
    public static final int NUMBER_OF_TRIANGLES_WAVE3_EASY = 4;
    public static final int NUMBER_OF_SQUARES_WAVE3_MEDIUM = 6;
    public static final int NUMBER_OF_TRIANGLES_WAVE3_MEDIUM = 6;
    public static final int NUMBER_OF_SQUARES_WAVE3_HARD = 8;
    public static final int NUMBER_OF_TRIANGLES_WAVE3_HARD = 8;

    public static final double RADIUS_COLLECTIBLES = 8;
    public static final double LASER_GUN_RADIUS_ORIGINAL = 4;
    public static double LASER_GUN_RADIUS = 4;
    public static final Point2D center = new Point2D.Double(GlassFrame.getINSTANCE().getWidth()/2,GlassFrame.getINSTANCE().getHeight()/2);

    public static final double MAX_RADIUS_EPSILON = 30;
    public static final double MIN_RADIUS_EPSILON = 20;
    public static final double ACCELERATION = SPEED*1.1;
}
