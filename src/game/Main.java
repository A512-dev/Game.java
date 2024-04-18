package game;

import controller.Constants;
import controller.Update;
import model.charactersModel.BallModel;
import model.charactersModel.SquareModel;
import view.GlassFrame;
import view.HomePanel;
import view.MotionPanel;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static controller.Constants.*;

public class Main {
    public static Random rng=new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GlassFrame.getINSTANCE();
            new HomePanel();
            //MotionPanel.getINSTANCE();
            new Update();
            SoundManager soundManager = new SoundManager();
            soundManager.play();
        });
    }
}
