package com.aateam.spaceminer.game.screens;

import com.aateam.spaceminer.core.STGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

    private final STGame game;
    private OrthographicCamera camera;
    private Stage stage;

    private Button btnStartGame;
    private Button btnExit;

    public MainMenuScreen(final STGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, STGame.WIDTH, STGame.HEIGHT);
        setupUI();
        addActionListeners();
    }

    private void setupUI() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("ui/fonts/font.fnt"), false);
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/buttons/buttons.pack"));
        Skin skin = new Skin(atlas);
        Table table = new Table(skin);
        stage = new Stage();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = skin.getDrawable("btnup");
        style.down = skin.getDrawable("btndown");
        style.pressedOffsetX = 1;
        style.pressedOffsetY = -1;
        style.font = font;

        btnStartGame = new TextButton("Start", style);
        btnExit = new TextButton("Exit", style);

        btnStartGame.pad(10);
        btnExit.pad(10);

        table.add(btnStartGame);
        table.row();
        table.add(btnExit);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    private void addActionListeners() {
        btnStartGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {    }

    @Override
    public void show() {    }

    @Override
    public void hide() {    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void dispose() {    }
}

