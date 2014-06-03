package com.aateam.spaceminer.bonus;

import com.aateam.spaceminer.tiles.AnimationCollection;
import com.aateam.spaceminer.tiles.BonusMaterials;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Bonus {

    private Animation animation;
    private BonusTypes type;

    private Bonus(){
    }

    public static Bonus createBonus(BonusTypes bonusType){
        Bonus bonus = new Bonus();
        bonus.type = bonusType;
        Animation anim = null;
        switch (bonusType) {
            case BONUS_1:
                anim = AnimationCollection.getInstance().getBonusAnimation(BonusMaterials.TEMP_BONUS_1_MATERIAL);
                break;
            case BONUS_2:
                anim = AnimationCollection.getInstance().getBonusAnimation(BonusMaterials.TEMP_BONUS_2_MATERIAL);
                break;
            case BONUS_3:
                anim = AnimationCollection.getInstance().getBonusAnimation(BonusMaterials.TEMP_BONUS_3_MATERIAL);
                break;
        }
        bonus.animation = anim;
        return  bonus;
    }

    public Animation getAnimation() {
        return animation;
    }

    public BonusTypes getType() {
        return type;
    }

}
