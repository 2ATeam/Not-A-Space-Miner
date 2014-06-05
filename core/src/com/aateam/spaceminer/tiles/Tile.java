package com.aateam.spaceminer.tiles;

import com.aateam.spaceminer.bonus.Bonus;
import com.aateam.spaceminer.bonus.BonusTypes;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Tile {

    private TextureRegion image;
    private Bonus bonus;
    TileTypes tileType;

    public Tile(TextureRegion image, TileTypes type) {
        this.image = image;
        this.tileType = type;
    }

    public Tile(TileTypes type) {
        this(null, type);
    }

    public TextureRegion getTexture() {
        return image;
    }

    public Animation getAnimation(){
        if (!isAnimated()) return null;
        return bonus.getAnimation();
    }

    public TileTypes getType() {
        return tileType;
    }

    public boolean isAnimated(){
        return tileType == TileTypes.BONUS && bonus != null;
    }

    public boolean isFree(){
        return tileType == TileTypes.FREE;
    }

    public void setBonus(BonusTypes bonus) {
        this.bonus = Bonus.createBonus(bonus);
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
}
