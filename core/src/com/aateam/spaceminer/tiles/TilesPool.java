package com.aateam.spaceminer.tiles;

import com.aateam.spaceminer.game.Materials;
import com.aateam.spaceminer.utils.Config;
import com.badlogic.gdx.graphics.Texture;

public final class TilesPool {

    private static TilesPool instance;
    public static TilesPool getInstance() {
        if (instance == null)
            instance = new TilesPool();

        return instance;
    }

    private Tile[] tilePool;

    public TilesPool() {
        fillTilePool();
    }

    private void fillTilePool() {


        tilePool = new Tile[Materials.values().length];
        tilePool[Materials.L_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.L_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.J_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.J_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.Z_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.Z_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.S_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.S_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.T_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.T_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.O_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.O_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.I_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.I_MATERIAL), TileTypes.BLOCK);
        tilePool[Materials.TRANSPARENT_MATERIAL.ordinal()] = new Tile(getChunkAt(Materials.TRANSPARENT_MATERIAL), TileTypes.FREE);
    }

    private Texture getChunkAt(Materials material) {
        /// TODO: rewrite this using tilesets and libGDX
        return new Texture(Config.tilesetPath);
    }

    public Tile getTile(Materials materialType) {
        return tilePool[materialType.ordinal()];
    }
}