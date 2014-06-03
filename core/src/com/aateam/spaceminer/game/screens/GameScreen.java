package com.aateam.spaceminer.game.screens;

import com.aateam.spaceminer.game.STetris;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Lux on 02.06.2014.
 */
public class GameScreen implements Screen {

    private final STetris tetris;
    private OrthographicCamera camera;

    public GameScreen(final STetris tetris) {
        this.tetris = tetris;

    }


    @Override
    public void render(float delta) {

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
}
