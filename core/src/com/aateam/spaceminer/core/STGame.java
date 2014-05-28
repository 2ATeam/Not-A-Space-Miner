package com.aateam.spaceminer.core;

import com.aateam.spaceminer.game.Directions;
import com.aateam.spaceminer.game.STController;
import com.aateam.spaceminer.game.STetris;
import com.aateam.spaceminer.game.TileMap;
import com.aateam.spaceminer.preferences.Config;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class STGame extends ApplicationAdapter {
	SpriteBatch batch;

    private int mapOffset = 10;
    private STetris tetris;
    private STController controller;


	@Override
	public void create () {
		batch = new SpriteBatch();
        tetris = new STetris();
        controller = tetris.getGameController();
        Config.load();
	}

	@Override
	public void render () {
        proccessKeyboardInput();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		drawField();
	}

    private void proccessKeyboardInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!controller.willOverlap(Directions.LEFT))
                controller.moveFigure(Directions.LEFT);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (!controller.willOverlap(Directions.RIGHT)) {
                controller.moveFigure(Directions.RIGHT);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (!controller.willOverlap(Directions.DOWN)) {
                controller.moveFigure(Directions.DOWN);
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if(controller.willRotate(false))
                controller.rotate(false);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if(controller.willRotate(true))
                controller.rotate(true);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            tetris.spawnFigure();
        }
    }

    private void drawField(){
        TileMap map = tetris.getMap();
        /// TODO: Main painting goes here
        batch.begin();
        for (int i = 0; i < map.getRowsAmount(); i++) {
            for (int j = 0; j < map.getCollsAmount(); j++) {
                batch.draw(map.getTile(i, j).getTexture(),
                        j * Config.blockSize + mapOffset,
                        i * Config.blockSize + mapOffset);
            }
        }
        batch.end();
    }
}
