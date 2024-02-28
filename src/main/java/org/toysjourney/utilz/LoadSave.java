package org.toysjourney.utilz;

import org.toysjourney.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

interface LoadSaveInterface {

    String FRIEREN = "Frieren_Animation.png";
    String CRABBY_SPRITE = "crabby_sprite.png";

    String TILE_ATLAS = "map.png";
    String TILE_DATA = "src/main/resources/map.txt";

    String MENU_BACKGROUND = "background/menu_background.png";
    String PAUSE_BACKGROUND = "background/pause_menu.png";
    String MENU_BACKGROUND_IMG = "background/masterpiece_background.png";

    String MENU_BUTTONS = "buttons/button_atlas.png";
    String SOUND_BUTTONS = "buttons/sound_button.png";
    String URM_BUTTONS = "buttons/urm_buttons.png";
    String VOLUME_BUTTONS = "buttons/volume_buttons.png";

    String HOUSE = "house.png";

    String POTION_ATLAS = "objects/potions_sprites.png";
    String KEY_1_ATLAS = "objects/Key_1.png";

}

public class LoadSave implements LoadSaveInterface {

    public static BufferedImage GetSpriteAtlas(String fileName) throws IOException {
        try (InputStream is = LoadSave.class.getResourceAsStream("/" + fileName)) {
            Objects.requireNonNull(is, "Failed to load sprite atlas: " + fileName);
            return ImageIO.read(is);
        }
    }


//	public static ArrayList<Crabby> GetCrabs() {
//		BufferedImage img = GetSpriteAtlas(TILE_DATA);
//		ArrayList<Crabby> list = new ArrayList<>();
//	}


    public static int[][] GetTileData() throws FileNotFoundException {
        int[][] tileData = new int[Game.MAP_HEIGHT][Game.MAP_WIDTH];

        try (Scanner scanner = new Scanner(new File(TILE_DATA))) {
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split(" ");

                for (int col = 0; col < numbers.length; col++) {
                    tileData[row][col] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
        }

        return tileData;
    }


}
