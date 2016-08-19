package com.davidhan.sloppydog.resources;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

/**
 * name: Colours
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ColorNames {
    public static final String NEAR_BLACK = "312825";
    public static final String GAME_BG = "F5E8DA";
    public static final String OFF_WHITE = "eeeeee";
    public static final String TEXT_REG = NEAR_BLACK;
    public static final String TEXT_DISABLED = "6f6565";
    public static final String BTN_PRIMARY_HIGHLIGHT = "fb79a5";
    public static final String BTN_PRIMARY_TEXT = "eeeeee";
    public static final String BTN_REGULAR_HIGHLIGHT = "cbb8b3";
    public static final String BTN_REGULAR_TEXT = "432a23";
    public static final String BTN_DISABLED_HIGHLIGHT = "675a5f";
    public static final String BTN_DISABLED_TEXT = "0f0d0e";
    public static final String MODAL_DIM = "00000064";
    public static final String SKIN_COLOR = "f79a6b";
    public static final String GRASS_BG = "adc078";
    public static final String GRASS_BG_DARK = "70905b";


    public static final String HUNGER_METER_BG = "3c213c";
    public static final String HUNGER_METER_FILLED = "d54c4c";
    public static final String HUNGER_METER_FILLED_TEXT = "952828";
    public static final String HUNGER_METER_EMPTY = "60465f";
    public static final String HUNGER_METER_EMPTY_TEXT = HUNGER_METER_BG;

    public ColorNames() {

    }

    public static void init() {
        for (Field f : ClassReflection.getFields(ColorNames.class)) {
            try {
                putColor((String) f.get(f));
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }
    }

    private static void putColor(String colorName) {
        Colors.put(colorName, Color.valueOf(colorName));
    }
}
