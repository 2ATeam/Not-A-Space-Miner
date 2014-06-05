package com.aateam.spaceminer.game.screens;

import com.aateam.spaceminer.core.STGame;
import com.aateam.spaceminer.game.*;
import com.aateam.spaceminer.preferences.GameConfig;
import com.aateam.spaceminer.tiles.TileTypes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Observable;

public class GameScreen extends Observable implements Screen {

    private final STGame game;
    
    private OrthographicCamera camera;
    private TileMap map;
    private TileMap nextFigureMap;
    private STController gameController;
    private Figure currentFigure;
    private Figure nextFigure;
    private boolean isStuck = false;
    private boolean isLoosed = false;

    public GameScreen(final STGame tetris) {
        this.game = tetris;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.W, game.H);
        map = new TileMap(GameConfig.getInstance().mapHeight, GameConfig.getInstance().mapWidth);
        nextFigureMap = new TileMap(4, 5);
        gameController = new STController(map);
        nextFigure = Figure.createFigure(FigureTypes.getRandom());
    }

    @Override
    public void render(float delta) {
        camera.update();
        processKeyboardInput();
        drawField();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void processKeyboardInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!gameController.willOverlap(Directions.LEFT))
                 gameController.moveFigure(Directions.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!gameController.willOverlap(Directions.RIGHT)) {
                 gameController.moveFigure(Directions.RIGHT);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (!gameController.willOverlap(Directions.DOWN)) {
                 gameController.moveFigure(Directions.DOWN);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if(gameController.willRotate(false)) {
                gameController.rotate(false);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if(gameController.willRotate(true)) {
                gameController.rotate(true);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
             spawnFigure(); /// TODO: test. remove later.
        }
    }

    private void drawField(){
        game.batch.begin();
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                game.batch.draw(map.getTile(i, j).getTexture(),
                        j * GameConfig.getInstance().blockSize,
                        i * GameConfig.getInstance().blockSize);
            }
        }

//        game.batch.draw(map.getTile(0, 0).getTexture(), 100, 100);
        game.batch.end();
    }

    public void mainLoop() {
        long lastDropTime = System.currentTimeMillis();
        spawnFigure();
        gameController.projectFigure(nextFigureMap, nextFigure, 1, 2);

        while (!isLoosed) {
            if (System.currentTimeMillis() - lastDropTime > game.playerStats.getSpeed()) {
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

    ///TODO: move this game over indication to the game over screen
    private void gameOver() {
        isLoosed = true;
        System.out.println("GAME OVER!\n" + game.playerStats.toString());
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
            game.playerStats.increaseScore(clearedLines);
            System.out.println(game.playerStats.toString());
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
}
