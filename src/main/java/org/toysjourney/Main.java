package org.toysjourney;

import org.toysjourney.utilz.DisplayImage;
import org.toysjourney.utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

    static void TestImg() {
        System.out.println("Test import images from LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS) in MenuButton.java");

        final String MENU_BUTTONS = "buttons/button_atlas.png";

        BufferedImage temp = LoadSave.GetSpriteAtlas(MENU_BUTTONS);

        // If image exist show the image
        if (temp != null) {
            // Show the image in window
            try {
                DisplayImage.showImage(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Image does not exist");
        }


    }

    static void TestText() {
        System.out.println("Test import text from LoadSave.GetSpriteAtlas(LoadSave.TILE_DATA) in Map.java");

        // File path: src/main/resources/map.txt
        final String TILE_DATA_FILE = "src/main/resources/map.txt";

        try {
            // Read the file
            File file = new File(TILE_DATA_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Game();

    }

}