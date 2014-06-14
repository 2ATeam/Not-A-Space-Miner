package com.aateam.spaceminer.game.input;

import com.aateam.spaceminer.game.Directions;
import com.aateam.spaceminer.game.STController;
import com.aateam.spaceminer.preferences.GameConfig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class STGameFieldGestureListener extends STInputListener implements GestureDetector.GestureListener {

    private Vector3 touchPosition;

    public STGameFieldGestureListener(STController controller, OrthographicCamera camera) {
        super(controller, camera);
        touchPosition = new Vector3();
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
        touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPosition);
        final int scaledX = (int) (touchPosition.x / GameConfig.getInstance().blockSize);
        if (scaledX > controller.getCurFigurePos().x) {
            if (!controller.willOverlap(Directions.RIGHT))
                controller.moveFigure(Directions.RIGHT);
        }
        else if (scaledX < controller.getCurFigurePos().x) {
            if (!controller.willOverlap(Directions.LEFT))
                controller.moveFigure(Directions.LEFT);
        }
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
