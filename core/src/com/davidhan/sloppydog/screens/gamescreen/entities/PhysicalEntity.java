package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Display;

/**
 * name: PhysicalEntity
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class PhysicalEntity extends BodyEntity {
    protected Body body;
    public PhysicalEntity(IApp iApp, Body body) {
        super(iApp);
        this.body = body;
        body.setUserData(this);
    }

    @Override
    public void act(float delta) {

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(sprite != null){
            attachSpriteToBody(batch,parentAlpha,sprite,body);
        }
    }

    private void attachSpriteToBody(Batch batch,float parentAlpha,Sprite sprite, Body body) {
        sprite.setCenter(body.getPosition().x* Display.WORLDSCALE,body.getPosition().y* Display.WORLDSCALE);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
        sprite.draw(batch,parentAlpha);
    }

    public void setBodyPos(float x, float y){
        body.setTransform(x,y,0);
    }
    @Override
    public boolean remove() {
        body.getWorld().destroyBody(body);
        body = null;
        return super.remove();
    }

    public Body body() {
        return body;
    }




}
