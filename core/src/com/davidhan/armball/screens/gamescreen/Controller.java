package com.davidhan.armball.screens.gamescreen;

/**
 * name: Controlller
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface Controller {
    public void onArrowPressed(int dire);
    public void onPlayerZeroTouchedDown();
    public void onPlayerZeroTouchedUp();
    public void onPlayerOneTouchedDown();
    public void onPlayerOneTouchedUp();
}
