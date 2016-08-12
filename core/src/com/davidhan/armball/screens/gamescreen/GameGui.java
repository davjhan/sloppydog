package com.davidhan.armball.screens.gamescreen;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;
import com.davidhan.armball.uireusables.scene2dhan.ClickListener;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.resources.ColorNames;
import com.davidhan.armball.resources.FontAssets;
import com.davidhan.armball.screens.gamescreen.hud.OnScreenArrow;
import com.davidhan.armball.uireusables.GameGroup;
import com.davidhan.armball.uireusables.scene2dhan.HanLabel;
import com.davidhan.armball.uireusables.scene2dhan.HanTextButton;

/**
 * name: GameGui
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameGui extends GameGroup {
    GameGroup preGame;
    GameGroup inGame;
    GameGroup postGame;
    IApp iApp;
    OnScreenArrow leftArrow;
    OnScreenArrow rightArrow;
    GameScreen gameScreen;
    HanLabel scoreCounter;
    public GameGui(IApp iApp,GameScreen gameScreen) {
        this.iApp = iApp;
        this.gameScreen = gameScreen;
        initGroups();
        makeController(gameScreen);
        makeHud();
    }

    private void makeHud() {
        HanTextButton resetButton = new HanTextButton(iApp,"");
        resetButton.setSize(20,10);
        inGame.spawn(resetButton,Display.WIDTH,Display.HEIGHT,Align.topRight);
        resetButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.restart();
            }
        });


        scoreCounter = new HanLabel(iApp,"0",FontAssets.Font.SGK, Colors.get(ColorNames.OFF_WHITE));
        inGame.spawn(scoreCounter,10,Display.HEIGHT-10,Align.topLeft);
    }

    private void makeController(final com.davidhan.armball.screens.gamescreen.Controlller controlller) {
        Actor bgCatcher = new Actor();
        bgCatcher.setSize(Display.WIDTH,Display.HEIGHT);

        bgCatcher.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //controlller.setThrust(true);
                return true;
            }

        });

        //leftArrow = new OnScreenArrow(iApp,controlller,-1);
       // rightArrow = new OnScreenArrow(iApp,controlller,1);
        //inGame.spawn(bgCatcher);
        //inGame.spawn(leftArrow,0,0,Align.bottomLeft);
       // inGame.spawn(rightArrow,Display.WIDTH,0,Align.bottomRight);

    }

    private void initGroups() {
        preGame = new GameGroup();
        inGame = new GameGroup();
        postGame = new GameGroup();
        debugAll();
        spawn(preGame);
        spawn(inGame);
        spawn(postGame);
    }

    void endGame() {
        preGame.clear();
        postGame.clear();
        HanLabel tapToResetLabel = new HanLabel(
                iApp,"Gameover. Tap to reset.",
                FontAssets.Font.SGK,
                Colors.get(ColorNames.OFF_WHITE)
        );
        postGame.spawn(tapToResetLabel, Display.HALF_WIDTH,60, Align.bottom);
    }
    void newGame() {
        preGame.clear();
        postGame.clear();
        HanLabel tapToResetLabel = new HanLabel(
                iApp,"Tap to Start.",
                FontAssets.Font.SGK,
                Colors.get(ColorNames.OFF_WHITE)
        );
        postGame.spawn(tapToResetLabel, Display.HALF_WIDTH,60,Align.bottom);
    }
    void startGame(){
        preGame.clear();
        postGame.clear();
    }

    public void setScore(int score) {
        scoreCounter.setText(String.valueOf(score));
    }
}
