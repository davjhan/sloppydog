package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.davidhan.sloppydog.app.IApp;

/**
 * name: BaseEntity
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BaseEntity extends Actor {
    Sprite sprite;
    IApp iApp;

    public BaseEntity(IApp iApp) {
        this.iApp = iApp;
    }
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        setSize(sprite.getWidth(),sprite.getHeight());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {
           drawSprite(batch,parentAlpha,sprite);
        }
        super.draw(batch, parentAlpha);
    }
    public void drawSprite(Batch batch,float parentAlpha,Sprite sprite){
        sprite.setPosition(getX()-getOriginX(),getY()-getOriginY());
        sprite.draw(batch,parentAlpha);
    }

    @Override
    public boolean remove() {
        iApp = null;
        return super.remove();
    }
}
