package com.aateam.spaceminer.visualization;


import com.aateam.spaceminer.game.TileMap;

public class GameField {

    private TileMap map;
    private int blockSize;



    public void setMap(TileMap map) {
        this.map = map;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}
