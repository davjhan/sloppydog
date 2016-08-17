package com.davidhan.armball.screens.gamescreen;

import com.davidhan.armball.screens.gamescreen.entities.Arm;

/**
 * name: TwoPlayerGameScreen
 * desc:
 * date: 2016-08-15
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface TwoPlayerGameScreen {
    Arm getPlayerOne();

    Arm getPlayerTwo();
}
