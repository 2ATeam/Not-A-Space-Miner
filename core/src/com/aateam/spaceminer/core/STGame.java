package com.aateam.spaceminer.core;

import com.aateam.spaceminer.game.Stats;
import com.aateam.spaceminer.game.screens.GameScreen;
import com.aateam.spaceminer.tiles.TilesPool;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class STGame extends Game {

	public SpriteBatch batch;
    private GameScreen gameScreen;
    public Stats playerStats;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final float ASPECT_RATIO = (float)WIDTH / (float)HEIGHT;

    @Override
	public void create () {
		batch = new SpriteBatch();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
	}

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        TilesPool.getInstance().dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }
}
