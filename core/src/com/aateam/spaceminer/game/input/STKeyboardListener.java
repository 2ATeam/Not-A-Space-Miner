package com.aateam.spaceminer.game.input;

import com.aateam.spaceminer.game.Directions;
import com.aateam.spaceminer.game.STController;
import com.badlogic.gdx.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lux on 13.06.2014.
 */
public class STKeyboardListener extends STInputListener  implements KeyListener{

    public STKeyboardListener(STController controller) {
        super(controller);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Input.Keys.A) {
            if (!controller.willOverlap(Directions.LEFT))
                controller.moveFigure(Directions.LEFT);
        }
        else if (e.getKeyCode() == Input.Keys.D) {
            if (!controller.willOverlap(Directions.RIGHT)) {
                controller.moveFigure(Directions.RIGHT);
            }
        }
        else if (e.getKeyCode() == Input.Keys.S) {
            if (!controller.willOverlap(Directions.DOWN)) {
                controller.moveFigure(Directions.DOWN);
            }
        }
        else if (e.getKeyCode() == Input.Keys.Q) {
            if(controller.willRotate(false)) {
                controller.rotate(false);
            }
        }
        else if (e.getKeyCode() == Input.Keys.E) {
            if (controller.willRotate(true)) {
                controller.rotate(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
