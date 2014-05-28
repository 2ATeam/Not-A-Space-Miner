package com.aateam.spaceminer.desktop;

import com.aateam.spaceminer.preferences.Config;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class ToolsLauncher {
    public static void main(String[] args) {
        ///TODO: Here should do some atlas packing
        TexturePacker.process(Config.tilesetPath, "tilesets\\packed", "tilesets\\tilepack");
    }
}
