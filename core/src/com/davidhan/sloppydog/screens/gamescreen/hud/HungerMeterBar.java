package com.davidhan.sloppydog.screens.gamescreen.hud;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.AnimConst;
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
    Image flash;
    HanLabel percentLabel;
    Action flashAction;
    public HungerMeterBar(IApp iApp,int type,float initialFillPercentage,float width) {
        super(iApp);
        this.type = type;
        this.fillPercentage = initialFillPercentage;
        setSize(
                width,
                GameConst.Hud.HungerMeter.HEIGHT - GameConst.Hud.HungerMeter.PAD * 2);

        makeUI();

    }



    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
      //  calcWidth();
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
        flash = new Image(new SolidDrawable(
                getWidth(),
                getHeight(),
                Colors.get(ColorNames.OFF_WHITE)
        ));
        percentLabel = new HanLabel(iApp,"100%", FontAssets.Font.SGK,
                type == FULL?
                        Colors.get(ColorNames.HUNGER_METER_EMPTY_TEXT):
                        Colors.get(ColorNames.OFF_WHITE));
        setPercenLevelText();
        HanLabel hungerMeterLabel  = new HanLabel(iApp,"HUNGER METER", FontAssets.Font.SPORTY,
                type == FULL?Colors.get(ColorNames.HUNGER_METER_FILLED_TEXT):
                        Colors.get(ColorNames.HUNGER_METER_EMPTY_TEXT));
        spawn(bg);

        spawn(hungerMeterLabel,GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.left);
        spawn(percentLabel,getRight()-GameConst.Hud.HungerMeter.PAD,getHeight()/2, Align.right);
        spawn(flash);
        flash.setVisible(false);
}

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        bg.setWidth(width);
        flash.setWidth(width);
        percentLabel.setX(getRight()-GameConst.Hud.HungerMeter.PAD*2-percentLabel.getPrefWidth());
    }

    public void setFillPercentage(float fillPercentage) {
        if(type == FULL && fillPercentage > this.fillPercentage){
            flash();
        }
        this.fillPercentage = fillPercentage;
    }

    private void flash() {
        if(flashAction != null){
            flash.removeAction(flashAction);
        }
        flash.setVisible(true);
        flash.addAction(flashAction = Actions.sequence(
                Actions.alpha(1),
                Actions.fadeOut(AnimConst.DUR_MED, Interpolation.pow2In)
        ));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPercenLevelText();
        if(fillPercentage < 0.01 && type == FULL){
            return;
        }
        if(fillPercentage < 1 && type == FULL){
            clipBegin(getX(),getY(),getWidth()*fillPercentage,getHeight());
            super.draw(batch, parentAlpha);
            clipEnd();
        }else{
            super.draw(batch, parentAlpha);
        }
    }

    private void setPercenLevelText() {
        percentLabel.setText(String.valueOf((int)(fillPercentage*100))+"%");
    }
}
