package org.toysjourney.object;

import org.toysjourney.Game;
import org.toysjourney.gamestates.Playing;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.toysjourney.utilz.Constants.ObjectConstants.*;


interface ObjectInterface {
    int HALF_SCREEN_WIDTH = Game.SCREEN_WIDTH / 2;
    int HALF_SCREEN_HEIGHT = Game.SCREEN_HEIGHT / 2;
    float HALF_PLAYER_SIZE = Game.PLAYER_SIZE * Game.SCALE / 2;
}

public class ObjectManager implements ObjectInterface {

    private final Playing playing;
    private ArrayList<Potion> potions;
    private ArrayList<Key> keys;
    private BufferedImage[][] potionImgs;
    private BufferedImage[][] key1_Imgs;
    private float cameraX, cameraY;

    public ObjectManager(Playing playing) throws IOException {
        this.playing = playing;
        loadImages();

        initializePotions();
        initializeKeys();
    }

    private void initializePotions() {
        potions = new ArrayList<>();
        // Add commented potion if needed
//     potions.add(new Potion(createPotionPosition(RED_POTION), RED_POTION));
        potions.add(new Potion(createPotionPosition(BLUE_POTION), BLUE_POTION));
    }

    private void initializeKeys() {
        keys = new ArrayList<>();
        keys.add(new Key(createKeyPosition(), KEY_1));
    }

    private Point createPotionPosition(int potionType) {
        // Adjust coordinates and scaling based on potion type
        int offsetX = 31 * Game.TILES_SIZE;
        int offsetY = 17 * Game.TILES_SIZE;
        if (potionType == RED_POTION) {
            // Adjust for red potion position
            offsetX += Game.TILES_SIZE;
        }
        return new Point(offsetX + POTION_WIDTH / 2 + (int) (3 * Game.SCALE),
                offsetY + POTION_HEIGHT / 2 - (int) (2 * Game.SCALE));
    }

    private Point createKeyPosition() {
        return new Point(25 * Game.TILES_SIZE + KEY_WIDTH / 2, 25 * Game.TILES_SIZE + KEY_HEIGHT / 2);
    }


    public void checkObjectTouched(Rectangle2D.Float hitBox) {
        for (Potion p : potions) {
            if (!p.isActive()) {
                continue; // Skip to the next potion if already inactive
            }
            if (hitBox.intersects(p.getHitBox())) {
                p.setActive(false);
                applyPotionToPlayer(p);
                break; // Exit the loop after applying the first valid potion effect
            }
        }
    }


    public void applyPotionToPlayer(Potion p) {
        if (p.getObjType() == RED_POTION)
            playing.getPlayer().changHealth(RED_POTION_VALUE);
        else
            playing.getPlayer().changPower(BLUE_POTION_VALUE);

    }


    public void applyKeyToPlayer(Key k) {

        playing.getPlayer().pickKey();


    }

    private void loadImages() throws IOException {
        BufferedImage potionSprite = LoadSave.GetSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++) {
            for (int i = 0; i < potionImgs[j].length; i++) {
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);
            }
        }

        BufferedImage key1_Sprite = LoadSave.GetSpriteAtlas(LoadSave.KEY_1_ATLAS);
        key1_Imgs = new BufferedImage[1][8];

        for (int j = 0; j < key1_Imgs.length; j++) {
            for (int i = 0; i < key1_Imgs[j].length; i++) {
                key1_Imgs[j][i] = key1_Sprite.getSubimage(50 * i, 50 * j, 50, 50);
            }
        }

    }

    public void update() {
        for (Potion p : potions)
            if (p.isActive())
                p.update();

        for (Key k : keys)
            if (k.isActive())
                k.update();


    }

    public void setCameraValues(float x, float y) {
        this.cameraX = x;
        this.cameraY = y;
    }

    public void draw(Graphics g) {
        drawPotions(g);
        drawKeys(g);

    }

    private void drawKeys(Graphics g) {
        for (Key k : keys) {
            if (k.isActive() && isInView(k.worldX, k.worldY)) {
                int type = 0;
                int screenX = (int) (k.worldX - cameraX + (Game.SCREEN_WIDTH / 2) - (Game.PLAYER_SIZE * Game.SCALE / 2));
                int screenY = (int) (k.worldY - cameraY + (Game.SCREEN_HEIGHT / 2) - (Game.PLAYER_SIZE * Game.SCALE / 2));
                g.drawImage(key1_Imgs[type][k.getAniIndex()], screenX, screenY, KEY_WIDTH, KEY_HEIGHT, null);
                // Only draw the first active key (optional optimization)
                break;
            }
        }
    }

    private boolean isInView(int worldX, int worldY) {
        int viewWidth = HALF_SCREEN_WIDTH + Game.TILES_SIZE;
        int viewHeight = HALF_SCREEN_HEIGHT + Game.TILES_SIZE;
        return worldX >= cameraX - viewWidth && worldX < cameraX + viewWidth &&
                worldY >= cameraY - viewHeight && worldY < cameraY + viewHeight;
    }

    private void drawPotions(Graphics g) {
        for (Potion p : potions)
            if (p.isActive()) {
                int type = 0;
                if (p.getObjType() == RED_POTION)
                    type = 1;

                int screenX = (int) (p.worldX - cameraX + (int) ((Game.SCREEN_WIDTH / 2) - (Game.PLAYER_SIZE * Game.SCALE / 2)));
                int screenY = (int) (p.worldY - cameraY + (int) ((Game.SCREEN_HEIGHT / 2) - (Game.PLAYER_SIZE * Game.SCALE / 2)));
//				System.out.println(cameraX);

                if (p.worldX + Game.TILES_SIZE > cameraX - ((Game.SCREEN_WIDTH / 2) - (Game.TILES_SIZE / 2)) &&
                        p.worldX - Game.TILES_SIZE < cameraX + ((Game.SCREEN_WIDTH / 2) - (Game.TILES_SIZE / 2)) &&
                        p.worldY + Game.TILES_SIZE > cameraY - ((Game.SCREEN_HEIGHT / 2) - (Game.TILES_SIZE / 2)) &&
                        p.worldY - Game.TILES_SIZE < cameraY + ((Game.SCREEN_HEIGHT / 2) - (Game.TILES_SIZE / 2))) {
                    g.drawImage(potionImgs[type][p.getAniIndex()], screenX, screenY, POTION_WIDTH, POTION_HEIGHT, null);
                    p.hitBox.x = screenX;
                    p.hitBox.y = screenY;
                    g.setColor(Color.pink);
                    g.drawRect(screenX, screenY, (int) p.hitBox.width, (int) p.hitBox.height);
//					System.out.println(cameraX + " : " + cameraY);
                }
            }
    }
}
