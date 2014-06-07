package com.aateam.spaceminer.desktop;

import com.aateam.spaceminer.core.STGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Miner";
        config.useGL20 = true;
        config.width = STGame.WIDTH;
        config.height = STGame.HEIGHT;
        new LwjglApplication(new STGame(), config);
    }
}
