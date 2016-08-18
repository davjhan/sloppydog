package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * name: BodyEntity
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class BodyEntity extends BaseEntity {
   protected Vector2 temp1 = new Vector2();
   protected Vector2 temp2 = new Vector2();
    public BodyEntity(com.davidhan.sloppydog.app.IApp iApp) {
        super(iApp);
    }

    public void drawSpriteToBody(Batch batch, Sprite sprite, Body body){
        drawSpriteToBody(batch,sprite,body,0);
    }
    public void drawSpriteToBody(Batch batch, Sprite sprite, Body body,float rotationOffsetDeg){
        sprite.setPosition(
                body.getPosition().x * com.davidhan.sloppydog.constants.Display.WORLDSCALE-sprite.getOriginX(),
                body.getPosition().y * com.davidhan.sloppydog.constants.Display.WORLDSCALE -sprite.getOriginY()
        );
        sprite.setRotation(rotationOffsetDeg+body.getAngle()* MathUtils.radDeg);
        sprite.draw(batch);
    }
    public abstract String getTag();

}
