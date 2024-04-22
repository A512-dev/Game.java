package view;

import game.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static controller.Constants.GLASS_FRAME_DIMENSION;

public final class GlassFrame extends JFrame implements KeyListener {
    private static GlassFrame INSTANCE;
    SoundManager soundManager;
    public GlassFrame() throws HeadlessException {
        setUndecorated(true);
        setName("glass frame");
        setTitle("frame1");
        //setBackground(new Color(0,0,0,0));
        setSize(GLASS_FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        setLayout(null);
        System.out.println("GlassFrameWidth:"+getWidth());
    }

    public static GlassFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new GlassFrame();
        return INSTANCE;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key");

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}