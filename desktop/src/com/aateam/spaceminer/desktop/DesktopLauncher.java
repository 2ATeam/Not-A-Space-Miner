package com.aateam.spaceminer.desktop;

import com.aateam.spaceminer.core.STGame;
import com.aateam.spaceminer.preferences.GameConfig;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Miner";
        config.width = GameConfig.getInstance().mapWidth *  GameConfig.getInstance().blockSize;
        config.height = GameConfig.getInstance().mapHeight *  GameConfig.getInstance().blockSize;
		new LwjglApplication(new STGame(), config);
	}
}
