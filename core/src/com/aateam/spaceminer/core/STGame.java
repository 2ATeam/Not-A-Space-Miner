package com.aateam.spaceminer.core;

import com.aateam.spaceminer.game.Stats;
import com.aateam.spaceminer.game.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class STGame extends Game {

	public SpriteBatch batch;
    private GameScreen tetris;
    public Stats playerStats;

    public final int W = 1280;
    public final int H = 960;

    @Override
	public void create () {
		batch = new SpriteBatch();
        tetris = new GameScreen(this);
        setScreen(tetris);
	}

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
