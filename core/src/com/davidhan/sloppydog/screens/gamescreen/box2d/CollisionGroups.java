package com.davidhan.sloppydog.screens.gamescreen.box2d;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 * name: CollisionGroups
 * desc:
 * date: 2016-08-14
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class CollisionGroups {
    public static short DEFAULT = 1<<1;
    public static short DOG_TORSO = 1<<2;
    public static short DOG_EXTRAS = 1<<3;
    public static short ARM_LINK_1 = 0x0100;
    public static short HAND = 0x1000;

    public static Filter FILTER_DOG_TORSO(){
        Filter filter = new Filter();
        filter.categoryBits = DOG_TORSO;
        return filter;
    }
    public static Filter FILTER_DOG_EXTRA(){
        Filter filter = new Filter();
        filter.categoryBits = DOG_EXTRAS;
        filter.maskBits = ~0;
        filter.maskBits &= ~DOG_TORSO;
        return filter;
    }
}
