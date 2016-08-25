package com.davidhan.sloppydog.backend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.davidhan.sloppydog.factories.SavesFactory;

/**
 * name: User
 * desc:
 * date: 2016-08-20
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class User {
    private Preferences preferences;
    private SavedData savedData;
    public static final String PREFS_NAME = "saved_game";
    public void User(){

    }
    public void init(){
        preferences = Gdx.app.getPreferences(PREFS_NAME);
        savedData = SavesFactory.parseSavedData(preferences);
    }

    public void flush(){
        preferences.flush();
    }

    public void updateNumAppOpened() {
        savedData.numAppOpens ++;
        save();
    }

    private void save() {
        SavesFactory.saveSavedData(savedData,preferences);
    }

    public int getNumOpened() {
        return savedData.numAppOpens;
    }

    public int getHighscore(){
        return savedData.highscore;
    }
    public void incrementLifetimeEaten(int amount){
        savedData.lifetimeEaten += amount;
        save();
    }
    public int getLifetimeEaten(){
        return savedData.lifetimeEaten;
    }
    public boolean setHighscore(int score){
        if(score > savedData.highscore){
            savedData.highscore = score;
            save();
            return true;
        }
        return false;
    }
    public void deleteSavedData(){
        preferences.clear();
    }
}
