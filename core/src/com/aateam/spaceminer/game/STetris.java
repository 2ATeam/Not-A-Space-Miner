package com.aateam.spaceminer.game;

import com.aateam.spaceminer.preferences.GameConfig;
import com.aateam.spaceminer.tiles.TileTypes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Observable;


public class STetris extends Observable {
    // deprecated
        private TileMap map;
        private TileMap nextFigureMap;
        private STController gameController;
        //private GameField gameField;
        //private Stats playerStats;
        private Figure currentFigure;
        private Figure nextFigure;
        private boolean isStuck = false;
        private boolean isLoosed = false;
    //

    public SpriteBatch batch;
    public static final int W = 1280;
    public static final int H = 960;
    public Stats playerStats;

    public STetris() {
        map = new TileMap(GameConfig.getInstance().mapHeight, GameConfig.getInstance().mapWidth);
        nextFigureMap = new TileMap(4, 5);
        gameController = new STController(map);
        playerStats = new Stats(); // default.
        nextFigure = Figure.createFigure(FigureTypes.getRandom());
    }

    public void mainLoop() {
        long lastDropTime = System.currentTimeMillis();
        spawnFigure();
        gameController.projectFigure(nextFigureMap, nextFigure, 1, 2);

        while (!isLoosed) {
            if (System.currentTimeMillis() - lastDropTime > playerStats.getSpeed()) {
                lastDropTime = System.currentTimeMillis();
                tick();
            }
        }
    }

    private void tick() {
        if(!gameController.willOverlap(Directions.DOWN)) {
            gameController.moveFigure(Directions.DOWN);
            isStuck = false;
        }
        else if(isStuck) {
            gameOver();
        }
        else{
            checkLines();
            spawnFigure();
            gameController.clearMap(nextFigureMap);
            gameController.projectFigure(nextFigureMap, nextFigure, 1, 2);
            isStuck = true;
            setChanged();
            notifyObservers();
        }
    }

    private void gameOver() {
        isLoosed = true;
        System.out.println("GAME OVER!\n" + playerStats.toString());
    }

    private void checkLines() {
        int blocksInLine;
        int clearedLines = 0;
        int i = map.getRowsAmount() - 1;
        do {
            blocksInLine = 0;
            for (int j = 0; j < map.getCollsAmount(); j++) {
                if (map.getTile(i, j).getType() == TileTypes.BLOCK) ++blocksInLine;
            }
            if (blocksInLine == map.getCollsAmount()) {
                gameController.clearLine(i);
                ++clearedLines;
            }
            else --i;
        } while (i >= 0 && blocksInLine > 0);
        if (clearedLines > 0) {
            playerStats.increaseScore(clearedLines);
            System.out.println(playerStats.toString());
        }
    }

    public void spawnFigure() {
        currentFigure = nextFigure;
        nextFigure = Figure.createFigure(FigureTypes.getRandom());
        gameController.addFigure(currentFigure);
    }

    public STController getGameController() {
        return gameController;
    }

    public TileMap getMap() {
        return map;
    }

    public Stats getPlayerStats() {
        return playerStats;
    }

    public TileMap getNextFigureMap() {
        return nextFigureMap;
    }
}