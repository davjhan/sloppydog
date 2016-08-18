package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.sloppydog.constants.GameConst;

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
                body.getPosition().x * GameConst.World.SCALE-sprite.getOriginX(),
                body.getPosition().y * GameConst.World.SCALE -sprite.getOriginY()
        );
        sprite.setRotation(rotationOffsetDeg+body.getAngle()* MathUtils.radDeg);
        sprite.draw(batch);
    }
    protected void drawSpriteToBodyNoRot(Batch batch, Sprite sprite, Body body) {
        sprite.setPosition(
                body.getPosition().x * GameConst.World.SCALE-sprite.getOriginX(),
                body.getPosition().y * GameConst.World.SCALE -sprite.getOriginY()
        );

        sprite.draw(batch);
    }

    public abstract String getTag();

}
