package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.davidhan.sloppydog.constants.SPGameRules;
import com.davidhan.sloppydog.screens.gamescreen.GameLog;

/**
 * name: GameLog
 * desc:
 * date: 2016-08-12
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerGameLog extends GameLog {
    private int score;
    private float hunger;
    private SinglePlayerGame game;
    public SinglePlayerGameLog(SinglePlayerGame game){
        this.game = game;
        this.hunger = SPGameRules.Hunger.INITIAL;
    }
    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update(float delta) {
        changeHunger(-SPGameRules.Hunger.DIMINISH_RATE_PER_FRAME);
    }

    public void changeHunger(float delta) {
        setHunger(hunger+ delta);
    }

    public float getHunger() {
        return hunger;
    }
    public float getHungerPercentage(){
        return hunger/SPGameRules.Hunger.MAX;
    }
    public void setHunger(float hunger) {
        this.hunger = Math.max(0,Math.min(SPGameRules.Hunger.MAX,hunger));

        if( this.hunger == 0){
            flagGameOver();
        }
    }

    private void flagGameOver() {
        game.endGame();
    }
}
