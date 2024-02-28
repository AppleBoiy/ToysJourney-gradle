package org.toysjourney.inputs;

import org.toysjourney.GamePanel;
import org.toysjourney.gamestates.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private final GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Objects.requireNonNull(GameState.state) == GameState.PLAYING) {
            gamePanel.getGame().getPlaying().mouseDragged(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Objects.requireNonNull(GameState.state) == GameState.PLAYING) {
            gamePanel.getGame().getPlaying().mouseClicked(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}