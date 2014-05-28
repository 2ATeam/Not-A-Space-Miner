package com.aateam.spaceminer.tiles;

import com.badlogic.gdx.graphics.Texture;


public class Tile {

    Texture image;
    TileTypes mapBlockType;

    public Tile(Texture image, TileTypes type) {
        this.image = image;
        this.mapBlockType = type;
    }

    public Tile(TileTypes type) {
        this(null, type);
    }

    public Texture getTexture() {
        return image;
    }

    public TileTypes getType() {
        return mapBlockType;
    }
}
