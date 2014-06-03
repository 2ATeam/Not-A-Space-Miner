package com.aateam.spaceminer.tiles;

import com.badlogic.gdx.graphics.Texture;


public class Tile {

    Texture texture;
    TileTypes mapBlockType;

    public Tile(Texture image, TileTypes type) {
        this.texture = image;
        this.mapBlockType = type;
    }

    public Tile(TileTypes type) {
        this(null, type);
    }

    public Texture getTexture() {
        return texture;
    }

    public TileTypes getType() {
        return mapBlockType;
    }
}
