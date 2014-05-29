package com.aateam.spaceminer.desktop;

import com.aateam.spaceminer.preferences.GameConfig;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class ToolsLauncher {
    public static void main(String[] args) {
        TexturePacker.process(GameConfig.getInstance().tilesetPath, "tilesets\\packed", "tilesets\\tilepack");
    }
}
