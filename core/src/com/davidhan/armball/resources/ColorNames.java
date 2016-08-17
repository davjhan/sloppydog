package com.davidhan.armball.resources;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import net.dermetfan.gdx.physics.box2d.ContactAdapter;

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
    public static final String BTN_REGULAR_HIGHLIGHT = "f9f5f6";
    public static final String BTN_REGULAR_TEXT = "371622";
    public static final String BTN_DISABLED_HIGHLIGHT = "675a5f";
    public static final String BTN_DISABLED_TEXT = "0f0d0e";
    public static final String MODAL_DIM = "00000064";
    public static final String SKIN_COLOR = "f79a6b";

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
