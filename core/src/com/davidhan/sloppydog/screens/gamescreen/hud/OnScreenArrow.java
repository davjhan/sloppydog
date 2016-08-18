package com.davidhan.sloppydog.screens.gamescreen.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.uireusables.SolidDrawable;
import com.davidhan.sloppydog.screens.gamescreen.entities.BaseEntity;

/**
 * name: OnScreenArrow
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class OnScreenArrow extends BaseEntity {
    com.davidhan.sloppydog.screens.gamescreen.Controller controller;
    int dire;
    boolean isDown = false;
    public OnScreenArrow(IApp iApp, com.davidhan.sloppydog.screens.gamescreen.Controller controller, int dire) {
        super(iApp);
        this.dire = dire;
        this.controller = controller;
        setSprite(new Sprite(SolidDrawable.createTexture(80,60, Colors.get(ColorNames.OFF_WHITE))));
        addListener(clickListener);
    }
    private InputListener clickListener = new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            isDown = true;
            Gdx.input.vibrate(GameConst.UX.HAPTIC_BUZZ_DUR);
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            isDown = false;
            Gdx.input.vibrate(GameConst.UX.HAPTIC_BUZZ_DUR);
            super.touchUp(event, x, y, pointer, button);
        }
    };

    @Override
    public void act(float delta) {
        if(isDown){
            onDown();
        }
        super.act(delta);
    }

    private void onDown() {
        controller.onArrowPressed(dire);
    }

    @Override
    public boolean remove() {
        controller = null;
        return super.remove();
    }
}
