package com.aateam.spaceminer.game.input;

import com.aateam.spaceminer.game.STController;
import com.badlogic.gdx.graphics.Camera;

public class STInputListener {

    protected STController controller;
    protected Camera camera;

    public STInputListener(STController controller) {
        this.controller = controller;
    }

    public STInputListener(STController controller, Camera camera) {
        this.controller = controller;
        this.camera = camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setController(STController controller) {
        this.controller = controller;
    }
}
