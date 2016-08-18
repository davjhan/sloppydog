package com.davidhan.sloppydog.screens.gamescreen.hud;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.resources.FontAssets;
import com.davidhan.sloppydog.uireusables.AppGameGroup;
import com.davidhan.sloppydog.uireusables.SolidDrawable;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;

/**
 * name: HungerMeterBar
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HungerMeterBar extends AppGameGroup {
    public static final int FULL = 0;
    public static final int EMPTY = 1;
    int type ;
    float fillPercentage;
    float fullWidth;
    Image bg;
    public HungerMeterBar(IApp iApp,int type,float initialFillPercentage) {
        super(iApp);
        this.type = type;
        this.fillPercentage = initialFillPercentage;
        this.fullWidth = GameConst.Hud.HungerMeter.WIDTH - GameConst.Hud.HungerMeter.PAD * 2;
        setSize(
                fullWidth,
                GameConst.Hud.HungerMeter.HEIGHT - GameConst.Hud.HungerMeter.PAD * 2);

        makeUI();

    }

    private void makeUI() {
        bg = new Image(new SolidDrawable(
                getWidth(),
                getHeight(),
                Colors.get(
                        type == FULL?
                                ColorNames.HUNGER_METER_FILLED:
                                ColorNames.HUNGER_METER_EMPTY)
        ));
        HanLabel hungryLabel = new HanLabel(iApp,"HUNGRY", FontAssets.Font.SGK,
                type == FULL?Colors.get(ColorNames.HUNGER_METER_FILLED_TEXT):
                        Colors.get(ColorNames.HUNGER_METER_EMPTY_TEXT));
        HanLabel fullLabel  = new HanLabel(iApp,"FULL", FontAssets.Font.SGK,
                type == FULL?Colors.get(ColorNames.HUNGER_METER_FILLED_TEXT):
                        Colors.get(ColorNames.HUNGER_METER_EMPTY_TEXT));
        spawn(bg);
        spawn(hungryLabel,GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.left);
        spawn(fullLabel,getWidth()-GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.right);
    }

    public void setFillPercentage(float fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(fillPercentage < 1){
            clipBegin(getX(),getY(),fullWidth*fillPercentage,getHeight());
            super.draw(batch, parentAlpha);
            clipEnd();
        }else{
            super.draw(batch, parentAlpha);
        }
    }
}
