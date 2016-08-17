package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * name: Grabbable
 * desc:
 * date: 2016-08-15
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Grabbable {
    public boolean isGrabbed();
    public Grabber getGrabber();
    public void beGrabbedBy(Grabber physicalEntity);
    public void beReleased();
    public Body body();
    public float getRadius();
}
