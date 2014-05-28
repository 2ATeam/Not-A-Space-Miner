package com.aateam.spaceminer.game;


import com.aateam.spaceminer.tiles.Tile;
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
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.L_MATERIAL)},
                                          { TilesPool.getInstance().getTile(Materials.L_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL)}};
                break;
            }
            case J_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(Materials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.J_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.J_MATERIAL)}};
                break;
            }
            case Z_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.Z_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.Z_MATERIAL)}};
                break;
            }
            case S_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL),
                                           TilesPool.getInstance().getTile(Materials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(Materials.S_MATERIAL)},
                                         { TilesPool.getInstance().getTile(Materials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(Materials.S_MATERIAL),
                                           TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL)}};
                break;
            }
            case T_SHAPE: {
                figure = new Figure(3, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.TRANSPARENT_MATERIAL)},
                                          { TilesPool.getInstance().getTile(Materials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.T_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.T_MATERIAL)}};
                break;
            }
            case O_SHAPE: {
                figure = new Figure(2, 2);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.O_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.O_MATERIAL)},
                                          { TilesPool.getInstance().getTile(Materials.O_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.O_MATERIAL)}};
                break;
            }
            case I_SHAPE: {
                figure = new Figure(4, 1);
                figure.mask = new Tile[][]{{TilesPool.getInstance().getTile(Materials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.I_MATERIAL),
                                            TilesPool.getInstance().getTile(Materials.I_MATERIAL)}};
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

    @Override
    protected Object clone(){
        Figure f = new Figure(columns, rows);
        f.mask = this.mask.clone();
        return f;
    }
}