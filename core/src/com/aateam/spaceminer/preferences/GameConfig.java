package com.aateam.spaceminer.preferences;

public class GameConfig extends Configuration {

    private static GameConfig instance;
    public static GameConfig getInstance() {
        if (instance == null) instance = new GameConfig();
        return instance;
    }

    // modifiable params with default values:
    public float fallingSpeed;
    public float speedIncrement;
    public long levelScoreLimit;
    public long lineCost;
    public int blockSize;
    public int mapWidth;
    public int mapHeight;
    public String tilesetPath = "tilesets/tileset2.png";

    // available param keys:
    private static final String PARAM_FS = "falling_speed";
    private static final String PARAM_MW = "map_width";
    private static final String PARAM_MH = "map_height";
    private static final String PARAM_LC = "line_cost";
    private static final String PARAM_TP = "tileset_path";
    private static final String PARAM_LS = "level_score_limit";
    private static final String PARAM_SI = "speed_increment";
    private static final String PARAM_BS = "block_size";

    @Override
    public String getPrefsFileName() {
        return "game_config";
    }

    @Override
    public void restoreDefaults() {
        fallingSpeed = 500F;
        speedIncrement = 100F;
        levelScoreLimit = 500L;
        lineCost = 100L;
        blockSize = 64;
        mapWidth = 6;
        mapHeight = 12;
        tilesetPath = "data/tilesets/tileset2.png";
    }

    @Override
    public void save(){
        put(PARAM_FS, fallingSpeed);
        put(PARAM_SI, speedIncrement);
        put(PARAM_LS, levelScoreLimit);
        put(PARAM_LC, lineCost);
        put(PARAM_BS, blockSize);
        put(PARAM_MW, mapWidth);
        put(PARAM_MH, mapHeight);
        put(PARAM_TP, tilesetPath);
        flush();
    }

    @Override
    public void load() {
        fallingSpeed = get(PARAM_FS,fallingSpeed);
        speedIncrement = get(PARAM_SI, speedIncrement);
        levelScoreLimit = get(PARAM_LS, levelScoreLimit);
        lineCost = get(PARAM_LC,lineCost);
        blockSize = get(PARAM_BS, blockSize);
        mapWidth = get(PARAM_MW, mapWidth);
        mapHeight = get(PARAM_MH, mapHeight);
        tilesetPath = get(PARAM_TP, tilesetPath);
    }
}