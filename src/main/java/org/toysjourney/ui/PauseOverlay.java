package org.toysjourney.ui;

import org.toysjourney.Game;
import org.toysjourney.gamestates.GameState;
import org.toysjourney.gamestates.Playing;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.toysjourney.utilz.Constants.UI.PauseButtons.SOUND_SIZE;
import static org.toysjourney.utilz.Constants.UI.URMButtons.URM_SIZE;
import static org.toysjourney.utilz.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static org.toysjourney.utilz.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

public class PauseOverlay {

    private final Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;
    private VolumeButton volumeButton;


    public PauseOverlay(Playing playing) {
        this.playing = playing;
        try {
            loadBackground();
            createSoundButtons();
            createUrmButtons();
            createVolumeButton();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createVolumeButton() throws IOException {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() throws IOException {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int bY = (int) (325 * Game.SCALE);


        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);


    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);

    }

    private void loadBackground() throws IOException {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.SCREEN_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);

    }

    public void update() {

        musicButton.update();
        sfxButton.update();

        menuB.update();
        replayB.update();
        unpauseB.update();

        volumeButton.update();

    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        //UrmButtons
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        //Volume Button
        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }

    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (isIn(e, menuB))
            menuB.setMousePressed(true);
        else if (isIn(e, replayB))
            replayB.setMousePressed(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMousePressed(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMousePressed(true);

    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());

        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        } else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.state = GameState.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed())
                System.out.println("replay the game");
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed())
                playing.unpauseGame();
        }

        musicButton.resetBool();
        sfxButton.resetBool();
        menuB.resetBool();
        replayB.resetBool();
        unpauseB.resetBool();
        volumeButton.resetBool();

    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);


        if (isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (isIn(e, menuB))
            menuB.setMouseOver(true);
        else if (isIn(e, replayB))
            replayB.setMouseOver(true);
        else if (isIn(e, unpauseB))
            unpauseB.setMouseOver(true);
        else if (isIn(e, volumeButton))
            volumeButton.setMouseOver(true);

    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());

    }

}
