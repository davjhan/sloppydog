package com.davidhan.armball.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * name: IApp
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IApp {
    public void setScreen(Screen screen);
    public com.davidhan.armball.resources.Assets res();
    public Stage modalStage();
}
