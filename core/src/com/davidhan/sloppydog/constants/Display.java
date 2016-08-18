package com.davidhan.sloppydog.constants;

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
    public static final float WORLDSCALE = 10;
    public static final int WORLD_WIDTH = 27;
    public static final float WORLD_HALF_WIDTH = 13.5f;
    public static final int WORLD_HEIGHT = 48;
    public static final float WORLD_HALF_HEIGHT = 24;
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
