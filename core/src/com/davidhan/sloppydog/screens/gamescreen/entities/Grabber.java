package com.davidhan.sloppydog.screens.gamescreen.entities;

/**
 * name: Grabber
 * desc:
 * date: 2016-08-15
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Grabber {
    public String getTag();
    public boolean isGrabbing();
    public Grabbable getGrabbing();
    public void releaseGrabbed();
}
