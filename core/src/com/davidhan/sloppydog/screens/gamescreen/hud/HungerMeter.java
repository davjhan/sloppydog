package com.davidhan.sloppydog.screens.gamescreen.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.uireusables.AppGameGroup;

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

        makeHungerBar(initialHunger);
    }

    private void makeHungerBar(float initialHunger) {
        float width = calcWidth();
        fullMeterBar = new HungerMeterBar(iApp,HungerMeterBar.FULL, initialHunger,width);
        emptyMeterBar = new HungerMeterBar(iApp,HungerMeterBar.EMPTY,initialHunger,width);

        spawn(emptyMeterBar,GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.left);
        spawn(fullMeterBar,GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.left);
    }

    private float calcWidth() {

        return  Display.WIDTH-GameConst.Hud.PAUSE_BUTTON_WIDTH- (GameConst.Hud.HungerMeter.PAD)-getX();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        Gdx.app.log("tttt HungerMeter", "width: " +calcWidth());
        fullMeterBar.setWidth(calcWidth());
        emptyMeterBar.setWidth(calcWidth());
    }




    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public void setFullMeterPercentage(float fillPercent){
        fullMeterBar.setFillPercentage(fillPercent);
        emptyMeterBar.setFillPercentage(fillPercent);
    }



}
