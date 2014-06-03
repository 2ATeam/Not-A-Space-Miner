package com.aateam.spaceminer.tiles;

import com.aateam.spaceminer.preferences.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class AnimationCollection {

    private static AnimationCollection instance;
    private Animation[] animations;

    public static AnimationCollection getInstance() {
        if (instance == null)
            instance = new AnimationCollection();

        return instance;
    }

    private AnimationCollection() {
        fillAnimation();
    }

    private void fillAnimation() {
        animations = new Animation[BonusMaterials.values().length];
        animations[BonusMaterials.TEMP_BONUS_1_MATERIAL.ordinal()] = new Animation(30f, getAtlas(BonusMaterials.TEMP_BONUS_1_MATERIAL));
        animations[BonusMaterials.TEMP_BONUS_2_MATERIAL.ordinal()] = new Animation(30f, getAtlas(BonusMaterials.TEMP_BONUS_2_MATERIAL));
        animations[BonusMaterials.TEMP_BONUS_3_MATERIAL.ordinal()] = new Animation(30f, getAtlas(BonusMaterials.TEMP_BONUS_3_MATERIAL));
    }

    private TextureRegion getAtlas(BonusMaterials material) {
        /// TODO: switch on tilesetPath depending on material
        return new TextureRegion(new Texture(Gdx.files.internal(GameConfig.getInstance().tilesetPath)));
    }

    public Animation getBonusAnimation(BonusMaterials materialType) {
        return animations[materialType.ordinal()];
    }
}