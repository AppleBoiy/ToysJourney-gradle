package org.toysjourney.entites;

import org.toysjourney.gamestates.Playing;
import org.toysjourney.utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.toysjourney.utilz.Constants.EnemyConstants.*;

public class EnemyManager {

    private final Playing playing;
    private final ArrayList<Crabby> crabbies = new ArrayList<>();
    private BufferedImage[][] crabbyArr;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        try {
            loadEnemyImgs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        for (Crabby c : crabbies)
            c.update();

    }

    public void draw(Graphics g) {
        drawCrabs(g);

    }

    private void drawCrabs(Graphics g) {
        for (Crabby c : crabbies)
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x, (int) c.getHitbox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);

    }

    private void loadEnemyImgs() throws IOException {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int j = 0; j < crabbyArr.length; j++)
            for (int i = 0; i < crabbyArr[j].length; i++)
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);

    }

}
