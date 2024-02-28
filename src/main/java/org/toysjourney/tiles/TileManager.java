package org.toysjourney.tiles;

import org.toysjourney.Game;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {

    private final Tile tileOne;
    private BufferedImage[] tileSprite;
    private float cameraX, cameraY;

    public TileManager(Game game) throws IOException {
        importTiles();
        tileOne = new Tile(LoadSave.GetTileData());
    }

    private void importTiles() throws IOException {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TILE_ATLAS);
        tileSprite = new BufferedImage[132];
        for (int j = 0; j < 11; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                tileSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }

    }

    public void setCameraValues(float x, float y) {
        this.cameraX = x;
        this.cameraY = y;
    }

    public void draw(Graphics g) {
        int mapWidth = tileOne.tileData()[0].length;
        int mapHeight = tileOne.tileData().length;

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int index = tileOne.getSpriteIndex(x, y);
                int worldX = x * Game.TILES_SIZE;
                int worldY = y * Game.TILES_SIZE;

                if (isInView(worldX, worldY)) {
                    int screenX = toScreenX(worldX);
                    int screenY = toScreenY(worldY);
                    g.drawImage(
                            tileSprite[index],
                            screenX,
                            screenY,
                            Game.TILES_SIZE,
                            Game.TILES_SIZE,
                            null
                    );
                }
            }
        }
    }

    private boolean isInView(int worldX, int worldY) {
        int viewWidth = Game.SCREEN_WIDTH / 2 + Game.TILES_SIZE;
        int viewHeight = Game.SCREEN_HEIGHT / 2 + Game.TILES_SIZE;
        return worldX >= cameraX - viewWidth && worldX < cameraX + viewWidth &&
                worldY >= cameraY - viewHeight && worldY < cameraY + viewHeight;
    }

    private int toScreenX(int worldX) {
        return (int) (worldX - cameraX + Game.SCREEN_WIDTH / 2);
    }

    private int toScreenY(int worldY) {
        return (int) (worldY - cameraY + Game.SCREEN_HEIGHT / 2);
    }


    public void update() {

    }

    public Tile getCurrentTile() {
        return tileOne;
    }

}
