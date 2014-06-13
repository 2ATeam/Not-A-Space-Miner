package com.aateam.spaceminer.game.input;

import com.aateam.spaceminer.game.Directions;
import com.aateam.spaceminer.game.STController;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Lux on 13.06.2014.
 */
public class STGestureListener extends STInputListener implements GestureDetector.GestureListener {

    public STGestureListener(STController controller) {
        super(controller);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        final int threshold = 900;
        if (velocityY >= threshold) {
            while (!controller.willOverlap(Directions.DOWN))
                controller.moveFigure(Directions.DOWN);
            return true;
        } else if (velocityY <= threshold) {
            if (controller.willRotate(false))
                controller.rotate(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
