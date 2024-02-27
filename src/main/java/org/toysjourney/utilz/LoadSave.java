package org.toysjourney.utilz;

import org.toysjourney.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);


        try {
            assert is != null;
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                assert is != null;
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return img;
    }

//	public static ArrayList<Crabby> GetCrabs() {
//		BufferedImage img = GetSpriteAtlas(TILE_DATA);
//		ArrayList<Crabby> list = new ArrayList<>();
//	}


    public static int[][] GetTileData() {
//		BufferedImage img = GetSpriteAtlas(TILE_DATA);
        int[][] tileData = new int[Game.MAP_HEIGHT][Game.MAP_WIDTH];
        try {

            InputStream is = LoadSave.class.getResourceAsStream(TILE_DATA);
            File file = new File(TILE_DATA);

            Scanner scanner = new Scanner(file);
            int col = 0, row = 0;
            // Loop through each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] numbers = line.split(" ");

                for (String number : numbers) {
                    // Convert the string to an integer
                    int index = Integer.parseInt(number);
                    tileData[row][col] = index;
                    col++;
                }
                col = 0;
                row++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tileData;
    }

}
