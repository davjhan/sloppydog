package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Random;

/**
 * name: Target
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Target extends PhysicalEntity {
    public static final String TAG = "target";
    boolean repositionFlag = false;

    public Target(com.davidhan.sloppydog.app.IApp iApp, Body body) {
        super(iApp, body);
        reposition();
        body.setUserData(this);
        setSprite(new Sprite(iApp.res().textures.ball));
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void act(float delta) {
        if(repositionFlag){
            reposition();
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
    }

    public void reposition(){
        Random random = new Random();
        float x = random.nextFloat()* com.davidhan.sloppydog.constants.Display.WORLD_WIDTH;
        float y = (random.nextFloat()* (com.davidhan.sloppydog.constants.Display.WORLD_HEIGHT- com.davidhan.sloppydog.constants.GameConst.Physics.GROUND_TOP))+ com.davidhan.sloppydog.constants.GameConst.Physics.GROUND_TOP;
        Gdx.app.log("tttt Target", "x y: " +x+"  "+y);
        repositionFlag = false;
        setBodyPos(x,y);
    }

    public void flagForReposition() {
        repositionFlag = true;
    }
}
