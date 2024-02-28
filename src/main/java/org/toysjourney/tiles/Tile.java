package org.toysjourney.tiles;

public record Tile(int[][] tileData) {

    public int getSpriteIndex(int x, int y) {
        return tileData[y][x];
    }


}
