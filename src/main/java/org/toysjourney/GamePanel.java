package org.toysjourney;

import org.toysjourney.inputs.KeyboardInputs;
import org.toysjourney.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


// class of game's display
public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {
        MouseInputs mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
//		this.setBackground(new Color(78,129,99));
        this.setBackground(Color.black);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        setPreferredSize(size);

    }

    public void paintComponent(Graphics g) { // keep calling themselves to make game's screen change.
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

}