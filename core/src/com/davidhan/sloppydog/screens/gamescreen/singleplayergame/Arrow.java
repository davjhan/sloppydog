package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.screens.gamescreen.entities.BaseEntity;

/**
 * name: Arrow
 * desc:
 * date: 2016-08-22
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Arrow extends BaseEntity{
    public Arrow(IApp iApp) {
        super(iApp);
        setSprite(new Sprite(iApp.res().textures.arrow[0]));
        setOrigin(getWidth()/2,15);

    }
}
