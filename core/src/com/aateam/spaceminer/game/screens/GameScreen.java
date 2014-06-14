package com.aateam.spaceminer.game.screens;

import com.aateam.spaceminer.core.STGame;
import com.aateam.spaceminer.game.*;
import com.aateam.spaceminer.game.input.STGameFieldGestureListener;
import com.aateam.spaceminer.preferences.GameConfig;
import com.aateam.spaceminer.tiles.TileTypes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Observable;

public class GameScreen extends Observable implements Screen {

    private final STGame game;
    
    private OrthographicCamera camera;
    private Rectangle viewport;
    private TileMap map;
    private TileMap nextFigureMap;
    private STController gameController;
    private Figure currentFigure;
    private Figure nextFigure;
    private long lastDropTime;
    private boolean isStuck = false;
    private boolean isLoosed = false;

    public GameScreen(final STGame tetris) {
        this.game = tetris;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, STGame.WIDTH, STGame.HEIGHT);
        viewport = new Rectangle(0, 0, STGame.WIDTH, STGame.HEIGHT);
        map = new TileMap(GameConfig.getInstance().mapHeight, GameConfig.getInstance().mapWidth);
        nextFigureMap = new TileMap(4, 5);
        gameController = new STController(map);
        Gdx.input.setInputProcessor(new GestureDetector(new STGameFieldGestureListener(gameController, camera)));
        nextFigure = Figure.createFigure(FigureTypes.getRandom());
        lastDropTime = System.currentTimeMillis();
        spawnFigure();
    }

    ///TODO: move this game over indication to the game over screen
    private void gameOver() {
        isLoosed = true;
        Gdx.app.log("GAME_INFO", "GAME OVER!\n" + game.playerStats.toString());
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

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        update();
        drawField();
    }

    public void update() {
        if (isLoosed)
            return;

        if (System.currentTimeMillis() - lastDropTime > game.playerStats.getSpeed()) {
            lastDropTime = System.currentTimeMillis();
            tick();
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

    private void drawField(){
        game.batch.begin();
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                game.batch.draw(map.getTile(i, j).getTexture(),
                        j * GameConfig.getInstance().blockSize,
                        i * GameConfig.getInstance().blockSize,
                        GameConfig.getInstance().blockSize,
                        GameConfig.getInstance().blockSize);
            }
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float phisW = (float) width;
        float phisH = (float) height;
        float virtW = (float) STGame.WIDTH;
        float virtH = (float) STGame.HEIGHT;
        float ratio = phisW / phisH;
        float scale;
        Vector2 crop = new Vector2(0.0f, 0.0f);
        if (ratio > STGame.ASPECT_RATIO) {
            scale = phisH / virtH;
            crop.x = (phisW - virtW * scale) / 2f;
        } else if (ratio < STGame.ASPECT_RATIO){
            scale = phisW / virtW;
            crop.y = (phisH - virtH * scale) / 2f;
        } else {
            scale = phisW / virtW;
        }
        viewport.set(crop.x, crop.y, virtW * scale, virtH * scale);
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
}