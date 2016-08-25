package com.davidhan.sloppydog.backend;

/**
 * name: SavedataFields
 * desc:
 * date: 2016-08-21
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SavedData {
    public int versionNumber = 1;
    public String versionName = "0.1";
    public int highscore = 0;
    public int totalApples = 0;
    public int numGamesStarted = 0;
    public int numGamesCompleted = 0;
    public int numAdImpressions = 0;
    public boolean showTutorial = true;
    public boolean[] perkLevelUnlocked;
    public boolean[] perkLevelAcknowledged;
}
