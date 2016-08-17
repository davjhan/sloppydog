package com.davidhan.armball.screens.gamescreen.singleplayergame;

import com.davidhan.armball.screens.gamescreen.GameLog;

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
    public SinglePlayerGameLog(){
        countDownInMillis = 20*1000;
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
        countDownInMillis -= delta;
    }
}
