package com.aateam.spaceminer.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Config {

    private static final String prefsName = "config";

    // modifiable params with default values:
    public static float fallingSpeed = 500F;
    public static float speedIncrement = 100F;
    public static long levelScoreLimit = 500L;
    public static long lineCost = 100L;
    public static int blockSize = 32;
    public static int mapWidth = 10;
    public static int mapHeight = 20;
    public static String tilesetPath = "data/tilesets/tileset.png";

    // available param indicators:
    private static final String PARAM_FS = "falling_speed";
    private static final String PARAM_MW = "map_width";
    private static final String PARAM_MH = "map_height";
    private static final String PARAM_LC = "line_cost";
    private static final String PARAM_TP = "tileset_path";
    private static final String PARAM_LS = "level_score_limit";
    private static final String PARAM_SI = "speed_increment";
    private static final String PARAM_BS = "block_size";

    public static void save(){
        Preferences prefs = Gdx.app.getPreferences(prefsName);
        prefs.putFloat(PARAM_FS,fallingSpeed);
        prefs.putFloat(PARAM_SI,speedIncrement);
        prefs.putLong(PARAM_LS, levelScoreLimit);
        prefs.putLong(PARAM_LC,lineCost);
        prefs.putInteger(PARAM_BS, blockSize);
        prefs.putInteger(PARAM_MW, mapWidth);
        prefs.putInteger(PARAM_MH, mapHeight);
        prefs.putString(PARAM_TP, tilesetPath);
        prefs.flush();
    }

    public static void load() {
        Preferences prefs = Gdx.app.getPreferences(prefsName);
        fallingSpeed = prefs.getFloat(PARAM_FS,fallingSpeed);
        speedIncrement = prefs.getFloat(PARAM_SI,speedIncrement);
        levelScoreLimit = prefs.getLong(PARAM_LS, levelScoreLimit);
        lineCost = prefs.getLong(PARAM_LC,lineCost);
        blockSize = prefs.getInteger(PARAM_BS, blockSize);
        mapWidth = prefs.getInteger(PARAM_MW, mapWidth);
        mapHeight = prefs.getInteger(PARAM_MH, mapHeight);
        tilesetPath = prefs.getString(PARAM_TP, tilesetPath);
    }
}