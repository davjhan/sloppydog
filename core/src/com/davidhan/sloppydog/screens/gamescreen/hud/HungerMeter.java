package com.davidhan.sloppydog.screens.gamescreen.hud;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.uireusables.AppGameGroup;
import com.davidhan.sloppydog.uireusables.SolidDrawable;

/**
 * name: HungerMeter
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HungerMeter extends AppGameGroup {
    Image bg;
    HungerMeterBar fullMeterBar;
    HungerMeterBar emptyMeterBar;

    public HungerMeter(IApp iApp,float initialHunger) {
        super(iApp);
        setSize(GameConst.Hud.HungerMeter.WIDTH, GameConst.Hud.HungerMeter.HEIGHT);

        makeBG();

        makeHungerBar(initialHunger);
    }

    private void makeHungerBar(float initialHunger) {
        fullMeterBar = new HungerMeterBar(iApp,HungerMeterBar.FULL, initialHunger);
        emptyMeterBar = new HungerMeterBar(iApp,HungerMeterBar.EMPTY,1);

        spawn(emptyMeterBar,GameConst.Hud.HungerMeter.PAD, GameConst.Hud.HungerMeter.PAD);
        spawn(fullMeterBar,GameConst.Hud.HungerMeter.PAD, GameConst.Hud.HungerMeter.PAD);
    }



    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void makeBG() {
        bg = new Image(new SolidDrawable(getWidth(), getHeight(), Colors.get(ColorNames.HUNGER_METER_BG)));
        spawn(bg);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isTransform()) applyTransform(batch, computeTransform());
        drawChildren(batch, parentAlpha);
    }

}
