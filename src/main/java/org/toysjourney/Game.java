package org.toysjourney;

import org.toysjourney.gamestates.GameState;
import org.toysjourney.gamestates.Menu;
import org.toysjourney.gamestates.Playing;

import java.awt.*;
import java.io.IOException;

interface GameConstants {
    int TILES_DEFAULT_SIZE = 32;
    float SCALE = 2f;
    int TILES_IN_WIDTH = 26;
    int TILES_IN_HEIGHT = 14;
    int MAP_WIDTH = 40;
    int MAP_HEIGHT = 40;
    int PLAYER_SIZE = (int) (50 * 0.76);
    int PLAYER_WIDTH = (int) (31 * 0.76);
    int PLAYER_HEIGHT = (int) (42 * 0.76);

    int FPS_SET = 120;
    int UPS_SET = 200;
}

public class Game implements Runnable, GameConstants {

    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int SCREEN_WIDTH = TILES_SIZE * TILES_IN_WIDTH; //1280
    public final static int SCREEN_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; //720

    private final GamePanel gamePanel;
    private Playing playing;
    private Menu menu;


    public Game() throws IOException {
        initClasses();

        gamePanel = new GamePanel(this);
        GameWindow gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() throws IOException {

        menu = new Menu(this);
        playing = new Playing(this);

    }

    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch (GameState.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;

        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (GameState.state == GameState.PLAYING)
            playing.getPlayer().resetDirBooleans();
        // add pause later
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}