package com.aateam.spaceminer.game;


import com.aateam.spaceminer.bonus.BonusTypes;
import com.aateam.spaceminer.tiles.BlockMaterials;
import com.aateam.spaceminer.tiles.Tile;
import com.aateam.spaceminer.tiles.TileTypes;
import com.aateam.spaceminer.tiles.TilesPool;

public class Figure {
    private int columns, rows;
    private Tile[][] mask;

    private Figure(int columns, int rows) {
        this.columns = columns;        
        this.rows = rows;
    }

    public static Figure createFigure(FigureTypes type) {
        Figure figure = null;
        switch (type) {
            case L_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.L_MATERIAL)},
                                          { TilesPool.getInstance().getTile(BlockMaterials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL)}};
                break;
            }
            case J_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(BlockMaterials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.J_MATERIAL)}};
                break;
            }
            case Z_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.Z_MATERIAL)}};
                break;
            }
            case S_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL),
                                           TilesPool.getInstance().getTile(BlockMaterials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(BlockMaterials.S_MATERIAL)},
                                         { TilesPool.getInstance().getTile(BlockMaterials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(BlockMaterials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL)}};
                break;
            }
            case T_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(BlockMaterials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.T_MATERIAL)}};
                break;
            }
            case O_SHAPE: {
                figure = new Figure(2, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.O_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.O_MATERIAL)},
                                          { TilesPool.getInstance().getTile(BlockMaterials.O_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.O_MATERIAL)}};
                break;
            }
            case I_SHAPE: {
                figure = new Figure(4, 1);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(BlockMaterials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(BlockMaterials.I_MATERIAL)}};
                break;
            }
        }
        return figure;
    }

    public void rotateCClockwise() {
        Tile[][] newFig = new Tile[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newFig[j][i] = mask[rows - 1 - i][j];
            }
        }
        mask = newFig;
        int x = columns;
        columns = rows;
        rows = x;
    }

    public void rotateClockwise() {

    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile[][] getMask() {
        return mask;
    }

    private void setBonusAt(int x, int y, BonusTypes bonusType){
        if ((x < 0 || x >= rows) || (y < 0 || y >=columns)) throw new IndexOutOfBoundsException("Out of mask's bounds in setBonusAt() method.");
        if (mask[x][y].getType() == TileTypes.FREE) throw new IllegalStateException("Could not set bonus on free tile.");
        mask[x][y].setBonus(bonusType);
    }

    @Override
    protected Object clone(){
        Figure f = new Figure(columns, rows);
        f.mask = this.mask.clone();
        return f;
    }
}