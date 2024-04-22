package game;

import controller.Update;
import model.charactersModel.EpsilonModel;
import view.GlassFrame;
import view.HomePanel;

import javax.swing.*;
import java.util.Random;

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
