package com.aateam.spaceminer.game;

import com.aateam.spaceminer.tiles.BlockMaterials;
import com.aateam.spaceminer.tiles.Tile;
import com.aateam.spaceminer.tiles.TileTypes;
import com.aateam.spaceminer.tiles.TilesPool;
import com.aateam.spaceminer.utils._2APoint;

public class STController {

    private TileMap map;
    private Figure currentFigure;
    private _2APoint curFigurePos;

    public STController(TileMap map) {
        this.map = map;
    }

    public void addFigure(Figure figure) {
        currentFigure = figure;
        curFigurePos = new _2APoint(map.getCollsAmount() / 2, map.getRowsAmount());
        projectFigure();
    }

    private void fillMaskRegion(Tile target){
        assert currentFigure != null;
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows()- 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                    isWithin(0, projectedY, map.getRowsAmount() - 1, true) &&
                    currentFigure.getMask()[i][j].getType() != TileTypes.FREE)
                map.setTile(projectedY, projectedX, target);
            }
        }
    }

    public void projectFigure(){
        assert currentFigure != null;
        Tile[][] figureMask = currentFigure.getMask();
        int projectedX, projectedY;
        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows() - 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                    isWithin(0, projectedY, map.getRowsAmount() - 1, true)&&
                    currentFigure.getMask()[i][j].getType() != TileTypes.FREE)
                map.setTile(projectedY, projectedX, figureMask[i][j]);
            }
        }
    }

    /**
     * Projects passed figure to specified map
     * @param map target map to project figure on.
     * @param figure figure to project
     * @param x x-pos of projected figure
     * @param y y-pos of projected figure
     */
    public void projectFigure(TileMap map, Figure figure, int x, int y){
        assert figure != null;
        Tile[][] figureMask = figure.getMask();
        int projectedX, projectedY;
        for (int i = 0; i < figure.getRows(); i++) {
            for (int j = 0; j < figure.getColumns(); j++) {
                projectedX = x + (figure.getColumns() - 1 - j);
                projectedY = y - (figure.getRows() - 1 - i);
                if (isWithin(0, projectedX, map.getCollsAmount() - 1, true) &&
                        isWithin(0, projectedY, map.getRowsAmount() - 1, true)&&
                        figure.getMask()[i][j].getType() != TileTypes.FREE)
                    map.setTile(projectedY, projectedX, figureMask[i][j]);
            }
        }
    }

    /**
     * Fills specified map with transparent material tiles
     * @param map map to clear
     *
     * NOTE: may be changed to "fillMap(TileMap map, Tile tile)" in future.
     */
    public void clearMap(TileMap map) {
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                map.setTile(i, j, TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
            }
        }
    }

    private void translateFigurePos(Directions direction) {
        if (currentFigure == null) return;
        int curX = curFigurePos.x;
        int curY = curFigurePos.y;

        switch (direction) {
            case UP: {
                curFigurePos.set(curX, curY + 1);
                break;
            }
            case DOWN: {
                curFigurePos.set(curX, curY - 1);
                break;
            }
            case LEFT: {
                curFigurePos.set(curX - 1, curY);
                break;
            }
            case RIGHT: {
                curFigurePos.set(curX + 1, curY);
                break;
            }
        }
    }

    public void moveFigure(Directions direction) {
        if (currentFigure == null) return;
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        translateFigurePos(direction);
        projectFigure();
    }

    public void moveFigureTo(int x, int y) {
        if (currentFigure == null) return;
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        curFigurePos.set(x, y);
        projectFigure();
    }

    public boolean isOverlapping() {
        assert currentFigure != null : "curFigure is null";
        int projectedX;
        int projectedY;

        for (int i = 0; i < currentFigure.getRows(); i++) {
            for (int j = 0; j < currentFigure.getColumns(); j++) {
                projectedX = curFigurePos.x + (currentFigure.getColumns() - 1 - j);
                projectedY = curFigurePos.y - (currentFigure.getRows() - 1 - i);

                if (!isWithin(0, projectedX, map.getCollsAmount() - 1, true) ||
                    !isWithin(0, projectedY, map.getRowsAmount() - 1, true))
                        return true;

                if (currentFigure.getMask()[i][j].getType() != TileTypes.FREE &&
                    map.getTile(projectedY, projectedX).getType() != TileTypes.FREE){
                        return true;
                }
            }
        }
        return false;
    }

    public boolean willOverlap(Directions direction) {
        if (curFigurePos == null) return true;

        _2APoint curPos = new _2APoint(curFigurePos);
        boolean isOverlapping;
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        translateFigurePos(direction);
        isOverlapping = isOverlapping();
        curFigurePos = curPos;
        projectFigure();
        return isOverlapping;
    }

    public boolean willOverlap(int x, int y) {
        if (curFigurePos == null) return true;

        _2APoint curPos = new _2APoint(curFigurePos);
        boolean isOverlapping;
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        moveFigureTo(x, y);
        isOverlapping = isOverlapping();
        curFigurePos = curPos;
        projectFigure();
        return isOverlapping;
    }

    public boolean willRotate(boolean clockwise) {
        assert currentFigure != null;
        int figEndX = curFigurePos.x + currentFigure.getRows() - 1;
        int figEndY = curFigurePos.y;
        Figure tmp = (Figure)currentFigure.clone();
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        rotateFigure(clockwise);
        boolean willOverlap = isOverlapping();
        currentFigure = tmp;
        projectFigure();
        return isWithin(0, figEndX, map.getCollsAmount() - 1, true) &&
               isWithin(0, figEndY, map.getRowsAmount() - 1, true) &&
               !willOverlap;
    }

    private void rotateFigure(boolean clockwise){
        if (clockwise)
            currentFigure.rotateClockwise();
        else
            currentFigure.rotateCClockwise();
    }

    public void rotate(boolean clockwise){
        assert currentFigure != null;
        fillMaskRegion(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
        rotateFigure(clockwise);
        projectFigure();
    }

    public void clearLine(int rowIndex) {
        map.removeRow(rowIndex);
        map.addRow(TilesPool.getInstance().getTile(BlockMaterials.TRANSPARENT_MATERIAL));
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public _2APoint getCurFigurePos() {
        return curFigurePos;
    }

    /** UTILS */
    private boolean isWithin(int min, int value, int max, boolean inclusive){
        if (inclusive)
            return (min <= value && value <= max);
        else
            return (min < value && value < max);
    }
}