package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.armball.app.IApp;

/**
 * name: Door
 * desc:
 * date: 2016-08-12
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Door extends PhysicalEntity {
    public static final String TAG = "door";
    boolean isOpen = true;
    private Arm arm;

    public Door(IApp iApp, Body body) {
        super(iApp, body);
        body.setUserData(this);
        body.getFixtureList().get(0).setSensor(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isOpen) {
            if (arm.isFullyInArena()) {
                closeDoor();
            }
        }
    }

    private void closeDoor() {
        isOpen = false;
        arm = null;
        body.getFixtureList().get(0).setSensor(false);
        Gdx.app.log("tttt Door", "close: ");
    }

    @Override
    public String getTag() {
        return TAG;
    }


    public void setArm(Arm arm) {
        this.arm = arm;
    }
}
