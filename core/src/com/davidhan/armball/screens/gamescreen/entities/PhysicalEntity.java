package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * name: PhysicalEntity
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class PhysicalEntity extends BodyEntity {
    protected Body body;
    public PhysicalEntity(com.davidhan.armball.app.IApp iApp, Body body) {
        super(iApp);
        this.body = body;
        body.setUserData(this);
    }

    @Override
    public void act(float delta) {
        if(sprite != null){
            sprite.setPosition(body.getPosition().x,body.getPosition().y);
        }
        super.act(delta);
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
