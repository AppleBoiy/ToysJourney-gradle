package org.toysjourney;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    public GameWindow(GamePanel gamePanel) {

        JFrame jframe = new JFrame("ToysJourney");

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(true);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // TODO Auto-generated method stub

            }
        });

    }

}