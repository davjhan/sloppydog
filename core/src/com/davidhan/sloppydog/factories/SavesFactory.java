package com.davidhan.sloppydog.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.davidhan.sloppydog.backend.SavedData;

/**
 * name: SavesFactory
 * desc:
 * date: 2016-08-20
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SavesFactory {
    public static SavedData parseSavedData(Preferences preferences) {
        SavedData savedData = new SavedData();
        for (Field f : ClassReflection.getFields(SavedData.class)) {
            try {
                if (preferences.contains(f.getName())) {
                    if (f.getType() == String.class) {
                        f.set(savedData, preferences.getString(f.getName()));
                    } else if (f.getType() == boolean.class) {
                        f.set(savedData, preferences.getBoolean(f.getName()));
                    } else if (f.getType() == int.class) {
                        f.set(savedData, preferences.getInteger(f.getName()));
                    } else if (f.getType() == float.class) {
                        f.set(savedData, preferences.getFloat(f.getName()));
                    } else if (f.getType() == long.class) {
                        f.set(savedData, preferences.getLong(f.getName()));
                    }
                }
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        return savedData;
    }

    public static void saveSavedData(SavedData savedData, Preferences preferences) {
        for (Field f : ClassReflection.getFields(SavedData.class)) {
            try {

                if (f.getType() == String.class) {
                    preferences.putString(f.getName(), (String) f.get(savedData));
                } else if (f.getType() == boolean.class) {
                    preferences.putBoolean(f.getName(), (Boolean) f.get(savedData));
                } else if (f.getType() == int.class) {
                    preferences.putInteger(f.getName(), (Integer) f.get(savedData));
                } else if (f.getType() == float.class) {
                    preferences.putFloat(f.getName(), (Float) f.get(savedData));
                } else if (f.getType() == long.class) {
                    preferences.putLong(f.getName(), (Long) f.get(savedData));
                }
            } catch (ReflectionException e) {
                e.printStackTrace();
            }
        }

        preferences.flush();
    }
}
