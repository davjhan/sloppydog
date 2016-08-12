package com.davidhan.armball.app;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * name: IScreen
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IScreen {
    public Stage getStage();
    public InputProcessor getInputProcessor();
}
