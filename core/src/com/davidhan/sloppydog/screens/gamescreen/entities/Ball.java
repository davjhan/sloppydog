package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.sloppydog.app.IApp;

/**
 * name: Ball
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Ball extends PhysicalEntity implements Grabbable {
    public static final String TAG = "ball";
    com.davidhan.sloppydog.screens.gamescreen.entities.Grabber grabber;
    boolean asleep;


    public Ball(IApp iApp, Body body) {
        super(iApp, body);
        setSprite(new Sprite(iApp.res().textures.ball));
        body.setLinearDamping(1);
        body.setUserData(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public boolean isGrabbed() {
        return grabber != null;
    }

    @Override
    public com.davidhan.sloppydog.screens.gamescreen.entities.Grabber getGrabber() {
        return grabber;
    }

    @Override
    public void beGrabbedBy(com.davidhan.sloppydog.screens.gamescreen.entities.Grabber grabber) {
        this.grabber = grabber;
    }

    @Override
    public void beReleased() {
        this.grabber = null;
    }

    @Override
    public float getRadius() {
        return com.davidhan.sloppydog.constants.GameConst.Ball.RADIUS;
    }


}
