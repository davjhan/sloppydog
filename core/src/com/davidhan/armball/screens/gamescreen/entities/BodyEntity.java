package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;

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
    public BodyEntity(IApp iApp) {
        super(iApp);
    }

    public void drawSpriteToBody(Batch batch, Sprite sprite, Body body){
          sprite.setCenter(
           body.getPosition().x * Display.WORLDSCALE,
            body.getPosition().y * Display.WORLDSCALE + 5
          );
          sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        sprite.draw(batch);
    }

    public abstract String getTag();

}
