package view;

import game.SoundManager;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.GLASS_FRAME_DIMENSION;

public final class GlassFrame extends JFrame {
    private static GlassFrame INSTANCE;
    SoundManager soundManager;
    public GlassFrame() throws HeadlessException {
        setUndecorated(true);
        setBackground(Color.white);
        setSize(GLASS_FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        System.out.println(getWidth());
    }

    public static GlassFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new GlassFrame();
        return INSTANCE;
    }
}