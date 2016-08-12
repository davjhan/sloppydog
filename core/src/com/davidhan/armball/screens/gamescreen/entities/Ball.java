package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * name: Ball
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Ball extends PhysicalEntity {
    public static final String TAG = "ball";
    boolean asleep;
    public Ball(com.davidhan.armball.app.IApp iApp, Body body) {
        super(iApp, body);
        body.setGravityScale(1f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
