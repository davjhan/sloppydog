package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * name: Wall
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Wall extends PhysicalEntity {
    public static final String TAG = "wall";
    public static final String GROUND = "ground";


    public Wall(com.davidhan.armball.app.IApp iApp, Body body) {
        super(iApp, body);
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
