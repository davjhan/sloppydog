package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.armball.app.IApp;

/**
 * name: Hoop
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Hoop extends PhysicalEntity {
    public static final String TAG = "hoop";
    public static final String NET = "net";

    public Hoop(IApp iApp, Body body) {
        super(iApp, body);
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
