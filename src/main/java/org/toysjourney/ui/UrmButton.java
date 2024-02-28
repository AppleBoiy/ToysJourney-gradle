package org.toysjourney.ui;

import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.toysjourney.utilz.Constants.UI.URMButtons.URM_DEFAULT_SIZE;
import static org.toysjourney.utilz.Constants.UI.URMButtons.URM_SIZE;

public class UrmButton extends PauseButton {
    private final int rowIndex;
    private BufferedImage[] images;
    private int index;
    private boolean mouseOver, mousePressed;

    public UrmButton(int x, int y, int width, int height, int rowIndex) throws IOException {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImg();
    }

    private void loadImg() throws IOException {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++)
            images[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);

    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    public void draw(Graphics g) {
        g.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);

    }

    public void resetBool() {
        mouseOver = false;
        mousePressed = false;
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


}
