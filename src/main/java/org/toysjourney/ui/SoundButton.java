package org.toysjourney.ui;

import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.toysjourney.utilz.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;

public class SoundButton extends PauseButton {

    private BufferedImage[][] soundImg;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        try {
            loadSoundImg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSoundImg() throws IOException {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImg = new BufferedImage[2][3];
        for (int j = 0; j < soundImg.length; j++)
            for (int i = 0; i < soundImg[j].length; i++)
                soundImg[j][i] = temp.getSubimage(
                        i * SOUND_SIZE_DEFAULT,
                        j * SOUND_SIZE_DEFAULT,
                        SOUND_SIZE_DEFAULT,
                        SOUND_SIZE_DEFAULT
                );
    }

    public void update() {
        rowIndex = muted ? 1 : 0;

        colIndex = 0;
        if (mouseOver)
            colIndex = 1;
        if (mousePressed)
            colIndex = 2;

    }

    public void resetBool() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        g.drawImage(soundImg[rowIndex][colIndex], x, y, width, height, null);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

}