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
    public static final int UPS = 100;
    public static final double MODEL_UPDATE_TIME=(double) TimeUnit.SECONDS.toMillis(1)/UPS;
    public static final double SPEED = 3*60D/UPS;
    public static final double MIN_RADIUS = 5;
    public static final double MAX_RADIUS = 50;
    public static final int NUMBER_OF_BALLS = 1;
    public static final int NUMBER_OF_SQUARES =2;
    public static final int NUMBER_OF_TRIANGLES = 2;
    public static final Point2D center = new Point2D.Double(GlassFrame.getINSTANCE().getWidth()/2,GlassFrame.getINSTANCE().getHeight()/2);
}
