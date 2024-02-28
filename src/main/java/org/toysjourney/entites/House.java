package org.toysjourney.entites;

import org.toysjourney.Game;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class House {

    private final Point pos;
    private float cameraX, cameraY;
    private BufferedImage img;

    public House(Point pos) {
        this.pos = pos;
        try {
            loadHouseImg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHouseImg() throws IOException {
        img = LoadSave.GetSpriteAtlas(LoadSave.HOUSE);
    }

    public Point getPos() {
        return pos;
    }

    public void setCameraValues(float cameraX, float cameraY) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) (pos.x - cameraX), (int) (pos.y - cameraY), (int) (168 * Game.SCALE), (int) (224 * Game.SCALE), null);
    }
}