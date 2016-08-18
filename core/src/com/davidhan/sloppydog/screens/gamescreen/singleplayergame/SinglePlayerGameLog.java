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
    private double countDownInMillis;
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
    public String getCounDownString(){
        return String.valueOf(countDownInMillis/1000);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update(float delta) {
        changeHunger(SPGameRules.Hunger.DIMINISH_RATE_PER_FRAME);

    }

    public void changeHunger(float delta) {
        setHunger(hunger- delta);
    }

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = Math.min(0,Math.max(1,hunger));
        if(hunger == 0){
            flagGameOver();
        }
    }

    private void flagGameOver() {
        game.endGame();
    }
}
