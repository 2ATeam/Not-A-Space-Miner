package com.aateam.spaceminer.desktop;

import com.aateam.spaceminer.core.STGame;
import com.aateam.spaceminer.preferences.Config;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Miners";
        config.width = Config.mapWidth * Config.blockSize;
        config.height = Config.mapHeight * Config.blockSize;
		new LwjglApplication(new STGame(),config);
	}
}
