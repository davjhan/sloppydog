package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.resources.FontAssets;
import com.davidhan.sloppydog.screens.gamescreen.Controller;
import com.davidhan.sloppydog.screens.gamescreen.GameScreen;
import com.davidhan.sloppydog.screens.gamescreen.hud.HungerMeter;
import com.davidhan.sloppydog.screens.gamescreen.hud.OnScreenArrow;
import com.davidhan.sloppydog.uireusables.GameGroup;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton;




/**
 * name: GameGui
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerGui extends GameGroup {
    GameGroup preGame;
    GameGroup inGame;
    GameGroup postGame;
    IApp iApp;
    OnScreenArrow leftArrow;
    OnScreenArrow rightArrow;
    GameScreen gameScreen;
    Controller controller;
    HanLabel scoreCounter;
    HungerMeter hungerMeter;
    SinglePlayerGameLog gameLog;
    private boolean screenTouchDown;

    public SinglePlayerGui(IApp iApp, GameScreen gameScreen,SinglePlayerGameLog gameLog) {
        this.iApp = iApp;
        this.gameScreen = gameScreen;
        this.gameLog = gameLog;
        initGroups();
        this.controller =  gameScreen;
        makeController(gameScreen);
        makeHud();
    }

    private void makeHud() {
        HanTextButton resetButton = new HanTextButton(iApp,"");
        resetButton.setSize(20,20);

        resetButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.restartGame();
            }
        });

        hungerMeter = new HungerMeter(iApp,gameLog.getHunger());
        inGame.spawn(hungerMeter,0,Display.HEIGHT,Align.topLeft);

        scoreCounter = new HanLabel(iApp,"0", FontAssets.Font.SGK, Colors.get(ColorNames.NEAR_BLACK));
        inGame.spawn(scoreCounter,10, Display.HEIGHT-30,Align.topLeft);

        inGame.spawn(resetButton, 0, 0,Align.bottomLeft);
    }

    private void makeController(final Controller controller) {
        Actor bgCatcher = new Actor();
        bgCatcher.setSize(Display.WIDTH, Display.HEIGHT);

        bgCatcher.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //screenTouchDown = true;
                controller.onTouchedDown();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                controller.onTouchedUp();
                //screenTouchDown = false;
            }
        });

        //leftArrow = new OnScreenArrow(iApp,controlller,-1);
       // rightArrow = new OnScreenArrow(iApp,controlller,1);
        inGame.spawn(bgCatcher);
        //inGame.spawn(leftArrow,0,0,Align.bottomLeft);
       // inGame.spawn(rightArrow,Display.WIDTH,0,Align.bottomRight);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        if(screenTouchDown){
//            controller.onPlayerOneTouchedDown();
//        }
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

    public void endGame() {
        preGame.clear();
        postGame.clear();
        HanLabel tapToResetLabel = new HanLabel(
                iApp,"Gameover. Tap to reset.",
                FontAssets.Font.SGK,
                Colors.get(ColorNames.NEAR_BLACK)
        );
        postGame.spawn(tapToResetLabel, Display.HALF_WIDTH,60, Align.bottom);
    }
    public void newGame() {
        preGame.clear();
        postGame.clear();
        HanLabel tapToResetLabel = new HanLabel(
                iApp,"Tap to Start.",
                FontAssets.Font.SGK,
                Colors.get(ColorNames.NEAR_BLACK)
        );
        postGame.spawn(tapToResetLabel, Display.HALF_WIDTH,60,Align.bottom);
    }
   public void startGame(){
        preGame.clear();
        postGame.clear();
    }

    public void setScore(int score) {
        scoreCounter.setText(String.valueOf(score));
    }
}
