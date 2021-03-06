package com.davidhan.sloppydog.app;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.davidhan.sloppydog.backend.User;
import com.davidhan.sloppydog.resources.Assets;

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
    public User user();
    public Stage modalStage();

    void initUser();
}
