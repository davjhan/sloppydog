package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.AnimConst;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.utils.TextureColorer;

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
    Sprite shadow;
    Sprite whiteSprite;
    Action flashAction;
    boolean flash = false;


    public Target(IApp iApp, Body body) {
        super(iApp, body);
        reposition();
        body.setUserData(this);
        setSprite(new Sprite(iApp.res().textures.apple[0]));
        sprite.setOriginCenter();
        whiteSprite = new Sprite(TextureColorer.tintTexture(iApp.res().textures.apple[0], Colors.get(ColorNames.OFF_WHITE)));
        shadow = new Sprite(iApp.res().textures.shadow[0]);
        shadow.setOriginCenter();

    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void act(float delta) {
        if (repositionFlag) {
            reposition();
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        drawSpriteToBodyNoRot(batch, shadow, body);

        super.draw(batch, parentAlpha);
        if (flash) {
            drawSpriteToBodyNoRot(batch, whiteSprite, body);
        }
    }


    public void reposition() {
        Random random = new Random();
        float x = random.nextFloat() * Display.WORLD_WIDTH;
        float y = (random.nextFloat() * (Display.WORLD_HEIGHT - GameConst.Physics.GROUND_TOP)) + GameConst.Physics.GROUND_TOP;
        repositionFlag = false;
        setBodyPos(x, y);
        startFlash();
    }

    private void startFlash() {
        if(flashAction != null){
            removeAction(flashAction);
        }
        addAction(flashAction = Actions.sequence(
                Actions.run(flashOnRunnable),
                Actions.delay(AnimConst.DUR_SUPER_SHOT),
                Actions.run(flashOffRunnable),
                Actions.delay(AnimConst.DUR_SUPER_SHOT),
                Actions.run(flashOnRunnable),
                Actions.delay(AnimConst.DUR_SUPER_SHOT),
                Actions.run(flashOffRunnable)
        ));
    }

    private Runnable flashOnRunnable = new Runnable() {
        @Override
        public void run() {
            flash = true;
        }
    };
    private Runnable flashOffRunnable = new Runnable() {
        @Override
        public void run() {
            flash = false;
        }
    };

    public void flagForReposition() {
        repositionFlag = true;
    }
}
