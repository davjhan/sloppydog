package com.davidhan.armball.screens.homescreen;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.modals.ModalBase;
import com.davidhan.armball.modals.SettingsModal;
import com.davidhan.armball.resources.ColorNames;
import com.davidhan.armball.resources.HanSkin;
import com.davidhan.armball.screens.ScreenBase;
import com.davidhan.armball.screens.gamescreen.singleplayergame.SinglePlayerGame;
import com.davidhan.armball.uireusables.GameGroup;
import com.davidhan.armball.uireusables.SolidDrawable;
import com.davidhan.armball.uireusables.scene2dhan.ClickListener;
import com.davidhan.armball.uireusables.scene2dhan.HanLabel;
import com.davidhan.armball.uireusables.scene2dhan.HanTextButton;

/**
 * name: HomeScreen
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HomeScreen extends ScreenBase {

    GameGroup bgGroup;
    GameGroup textGroup;
    public HomeScreen(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        initGroups();
        Image bg = new Image(new SolidDrawable(Display.WIDTH,Display.HEIGHT,Colors.get(ColorNames.OFF_WHITE)));
        bgGroup.spawn(bg);
        HanLabel text = new HanLabel(iApp,"Game 2");

        HanTextButton startGame =new HanTextButton(iApp,"New Game", HanSkin.ButtonStyles.PRIMARY);
        HanTextButton settings =new HanTextButton(iApp,"settings");
        settings.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                ModalBase modal = new SettingsModal(iApp);
                iApp.modalStage().addActor(modal);
            }
        });
        HanTextButton removeAds =new HanTextButton(iApp,"Remove Ads");
        removeAds.setClickEnabled(false);
        textGroup.spawn(text,Display.HALF_WIDTH,Display.HALF_HEIGHT, Align.center);
        textGroup.spawn(startGame,Display.HALF_WIDTH,Display.HALF_HEIGHT- 40, Align.center);
        textGroup.spawn(settings,Display.HALF_WIDTH,Display.HALF_HEIGHT-80, Align.center);
        textGroup.spawn(removeAds,Display.HALF_WIDTH,Display.HALF_HEIGHT-120, Align.center);

    }

    @Override
    public void show() {
        super.show();
        iApp.setScreen(new SinglePlayerGame(iApp));
    }

    @Override
    protected void update(float delta) {
        super.update(delta);

    }

    private void initGroups() {
        textGroup = new GameGroup();
        bgGroup = new GameGroup();
        stage.addActor(bgGroup);
        stage.addActor(textGroup);
    }
}
