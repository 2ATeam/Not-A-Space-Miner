package com.aateam.spaceminer.tiles;

import com.aateam.spaceminer.preferences.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class TilesPool {

    private static TilesPool instance;
    private Tile[] tilePool;

    public static TilesPool getInstance() {
        if (instance == null)
            instance = new TilesPool();

        return instance;
    }

    private TilesPool() {
        fillTilePool();
    }

    private void fillTilePool() {
        tilePool = new Tile[BlockMaterials.values().length + BonusMaterials.values().length];
        tilePool[BlockMaterials.L_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.L_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.J_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.J_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.Z_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.Z_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.S_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.S_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.T_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.T_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.O_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.O_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.I_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.I_MATERIAL), TileTypes.BLOCK);
        tilePool[BlockMaterials.TRANSPARENT_MATERIAL.ordinal()] = new Tile(getChunkAt(BlockMaterials.TRANSPARENT_MATERIAL), TileTypes.FREE);
    }

    private Texture getChunkAt(Materials material) {
        /// TODO: rewrite this using tilesets and libGDX
        return new Texture(Gdx.files.internal(GameConfig.getInstance().tilesetPath));
    }

    private int getIndex(Materials mat){
        if (mat instanceof BonusMaterials)
            return BlockMaterials.values().length + ((BonusMaterials) mat).ordinal();
        else
            return ((BlockMaterials) mat).ordinal();
    }

    public Tile getTile(Materials materialType) {
        return tilePool[getIndex(materialType)];
    }
}