package com.davidhan.armball.screens.gamescreen.entities;

import com.davidhan.armball.app.IApp;

/**
 * name: BodyEntity
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class BodyEntity extends BaseEntity {

    public BodyEntity(IApp iApp) {
        super(iApp);
    }

    public abstract String getTag();
}
