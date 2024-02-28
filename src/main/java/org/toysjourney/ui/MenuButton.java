package org.toysjourney.ui;

import org.toysjourney.gamestates.GameState;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.toysjourney.utilz.Constants.UI.Buttons.*;

public class MenuButton {

    private final int xPos;
    private final int yPos;
    private final int rowIndex;
    private final int xOffsetCenter = B_WIDTH / 2;
    private final GameState state;
    private int index;
    private BufferedImage[] imgs;
    private Boolean mouseOver = false, mousePressed = false;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState state) throws IOException {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImg();
        initBound();
    }

    private void initBound() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);

    }

    private void loadImg() throws IOException {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
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


    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = state;
    }

    public void resetBool() {
        mouseOver = false;
        mousePressed = false;
    }

}
