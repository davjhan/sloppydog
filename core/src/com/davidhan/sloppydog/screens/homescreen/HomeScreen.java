package com.davidhan.sloppydog.screens.homescreen;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.modals.SettingsModal;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;

/**
 * name: HomeScreen
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HomeScreen extends com.davidhan.sloppydog.screens.ScreenBase {

    com.davidhan.sloppydog.uireusables.GameGroup bgGroup;
    com.davidhan.sloppydog.uireusables.GameGroup textGroup;
    public HomeScreen(com.davidhan.sloppydog.app.IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        initGroups();
        Image bg = new Image(new com.davidhan.sloppydog.uireusables.SolidDrawable(com.davidhan.sloppydog.constants.Display.WIDTH, com.davidhan.sloppydog.constants.Display.HEIGHT,Colors.get(com.davidhan.sloppydog.resources.ColorNames.OFF_WHITE)));
        bgGroup.spawn(bg);
        HanLabel text = new HanLabel(iApp,"Game 2");

        com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton startGame =new com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton(iApp,"New Game", com.davidhan.sloppydog.resources.HanSkin.ButtonStyles.PRIMARY);
        com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton settings =new com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton(iApp,"settings");
        settings.setClickListener(new com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener() {
            @Override
            public void onClick() {
                com.davidhan.sloppydog.modals.ModalBase modal = new SettingsModal(iApp);
                iApp.modalStage().addActor(modal);
            }
        });
        com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton removeAds =new com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton(iApp,"Remove Ads");
        removeAds.setClickEnabled(false);
        textGroup.spawn(text, com.davidhan.sloppydog.constants.Display.HALF_WIDTH, com.davidhan.sloppydog.constants.Display.HALF_HEIGHT, Align.center);
        textGroup.spawn(startGame, com.davidhan.sloppydog.constants.Display.HALF_WIDTH, com.davidhan.sloppydog.constants.Display.HALF_HEIGHT- 40, Align.center);
        textGroup.spawn(settings, com.davidhan.sloppydog.constants.Display.HALF_WIDTH, com.davidhan.sloppydog.constants.Display.HALF_HEIGHT-80, Align.center);
        textGroup.spawn(removeAds, com.davidhan.sloppydog.constants.Display.HALF_WIDTH, com.davidhan.sloppydog.constants.Display.HALF_HEIGHT-120, Align.center);

    }

    @Override
    public void show() {
        super.show();
        iApp.setScreen(new com.davidhan.sloppydog.screens.gamescreen.singleplayergame.SinglePlayerGame(iApp));
    }

    @Override
    protected void update(float delta) {
        super.update(delta);

    }

    private void initGroups() {
        textGroup = new com.davidhan.sloppydog.uireusables.GameGroup();
        bgGroup = new com.davidhan.sloppydog.uireusables.GameGroup();
        stage.addActor(bgGroup);
        stage.addActor(textGroup);
    }
}
