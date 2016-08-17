package com.davidhan.armball.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.resources.ColorNames;
import com.davidhan.armball.resources.FontAssets;
import com.davidhan.armball.screens.gamescreen.Controller;
import com.davidhan.armball.screens.gamescreen.aigame.AIGame;
import com.davidhan.armball.screens.gamescreen.hud.OnScreenArrow;
import com.davidhan.armball.uireusables.GameGroup;
import com.davidhan.armball.uireusables.scene2dhan.ClickListener;
import com.davidhan.armball.uireusables.scene2dhan.HanLabel;
import com.davidhan.armball.uireusables.scene2dhan.HanTextButton;

/**
 * name: GameGui
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class AiGameGui extends GameGroup {
    GameGroup preGame;
    GameGroup inGame;
    GameGroup postGame;
    IApp iApp;
    OnScreenArrow leftArrow;
    OnScreenArrow rightArrow;
    AIGame gameScreen;
    Controller controller;
    HanLabel scoreCounter;
    HanTextButton p0GrabReleaseButton;
    private boolean screenTouchDown;

    public AiGameGui(IApp iApp, AIGame gameScreen) {
        this.iApp = iApp;
        this.gameScreen = gameScreen;
        initGroups();
        this.controller = (Controller) gameScreen;
        makeController(gameScreen);
        makeHud();
    }

    private void makeHud() {
        HanTextButton resetButton = new HanTextButton(iApp,"");
        resetButton.setSize(20,20);
        inGame.spawn(resetButton,Display.WIDTH,Display.HEIGHT,Align.topRight);
        resetButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.restartGame();
            }
        });


        scoreCounter = new HanLabel(iApp,"0",FontAssets.Font.SGK, Colors.get(ColorNames.NEAR_BLACK));
        inGame.spawn(scoreCounter,10,Display.HEIGHT-10,Align.topLeft);

        p0GrabReleaseButton = new HanTextButton(iApp,"RELEASE");
        p0GrabReleaseButton.setVisible(false);
        p0GrabReleaseButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.getPlayerZero().releaseGrabbed();
            }
        });
        inGame.spawn(p0GrabReleaseButton,Display.HALF_WIDTH,100,Align.center);
    }

    private void makeController(final Controller controller) {
        Actor bgCatcher = new Actor();
        bgCatcher.setSize(Display.WIDTH,Display.HEIGHT);

        bgCatcher.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //screenTouchDown = true;
                controller.onPlayerZeroTouchedDown();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                controller.onPlayerZeroTouchedUp();
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

    public void update(float delta) {
        if(gameScreen.getPlayerZero().isGrabbing()){
            p0GrabReleaseButton.setVisible(true);
        }else{
            p0GrabReleaseButton.setVisible(false);
        }
    }
}
