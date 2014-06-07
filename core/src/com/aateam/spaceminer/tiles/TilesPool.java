package com.aateam.spaceminer.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public final class TilesPool implements Disposable {

    private static TilesPool instance;
    private Texture texture;
    private TextureRegion[] sprites;
    private Tile[] tilePool;

    public static TilesPool getInstance() {
        if (instance == null)
            instance = new TilesPool();

        return instance;
    }

    private TilesPool() {
        texture = new Texture(Gdx.files.internal("tilesets/tileset.png"));
        refresh();
    }

    private void fillTilePool() {
        tilePool = new Tile[BlockMaterials.values().length + BonusMaterials.values().length];
        tilePool[BlockMaterials.L_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.L_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.J_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.J_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.Z_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.Z_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.S_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.S_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.T_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.T_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.O_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.O_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.I_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.I_MATERIAL.ordinal()], TileTypes.BLOCK);
        tilePool[BlockMaterials.TRANSPARENT_MATERIAL.ordinal()] = new Tile(sprites[BlockMaterials.TRANSPARENT_MATERIAL.ordinal()], TileTypes.FREE);
    }

    private void splitIntoChunks() {
        /// TODO: magic numbers here. needfix

        if (texture == null)
            return;

        final int rows = 4;
        final int columns = 6;
        final int blockSize = 64;
        int index = 0;
        sprites = new TextureRegion[rows * columns];
        for( int i = 0; i < rows; i++){
            for( int j = 0; j < columns; j++) {
                sprites[index++] = new TextureRegion(texture, i * blockSize, j * blockSize, blockSize, blockSize);
            }
        }
    }

    public void refresh(){
        splitIntoChunks();
        fillTilePool();
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

    @Override
    public void dispose(){
        texture.dispose();
    }
}