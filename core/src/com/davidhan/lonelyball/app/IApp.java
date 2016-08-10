package com.davidhan.lonelyball.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.davidhan.lonelyball.resources.Assets;

/**
 * name: IApp
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public interface IApp {
    public void setScreen(Screen screen);
    public Assets res();
    public Stage modalStage();
}
