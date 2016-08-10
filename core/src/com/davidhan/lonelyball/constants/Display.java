package com.davidhan.lonelyball.constants;

import com.badlogic.gdx.Gdx;

/**
 * name: Display
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Display {
    public static final int WIDTH = 270;
    public static final int HEIGHT = 480;
    public static final float HALF_WIDTH = 135;
    public static final float HALF_HEIGHT = 240;
    private static float sc = -1;
    public static float scale(){
        if(sc == -1){
            sc = ((float) Gdx.graphics.getWidth())/((float)WIDTH);
        }
        return sc;
    }
}
