package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * name: BaseEntity
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BaseEntity extends Actor {
    Sprite sprite;
    com.davidhan.armball.app.IApp iApp;

    public BaseEntity(com.davidhan.armball.app.IApp iApp) {
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
        batch.draw(sprite.getTexture(),
                (getX() + sprite.getX()),
                (getY() + sprite.getY()),
                (sprite.getWidth()),
                (sprite.getHeight()));
    }

    @Override
    public boolean remove() {
        iApp = null;
        return super.remove();
    }
}
