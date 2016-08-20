package com.davidhan.sloppydog.utils;

import com.badlogic.gdx.math.Vector2;
import com.davidhan.sloppydog.constants.GameConst;

/**
 * name: GameUtils
 * desc:
 * date: 2016-08-19
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameUtils {
    public static boolean isOutOfBounds(Vector2 position, float radius) {
        return(position.x > radius+ GameConst.World.WIDTH ||
                position.x < -radius||
                position.y > radius+ GameConst.World.HEIGHT-GameConst.World.TOP_PAD||
                position.y < GameConst.World.BOTTOM_PAD-radius
                );
    }
}
