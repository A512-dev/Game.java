package view;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.PANEL_SIZE;

public class SettingsPanel extends JPanel {
    public SettingsPanel(){
        setBorder(BorderFactory.createLineBorder(Color.yellow,5));
        setSize(PANEL_SIZE);
        setBackground(Color.red);
        setLocationToCenter(GlassFrame.getINSTANCE());
        setLayout(null);
        GlassFrame.getINSTANCE().add(this);
    }
    private void setLocationToCenter(GlassFrame glassFrame){
        setLocation(glassFrame.getWidth()/2-getWidth()/2,glassFrame.getHeight()/2-getHeight()/2);
    }
}
