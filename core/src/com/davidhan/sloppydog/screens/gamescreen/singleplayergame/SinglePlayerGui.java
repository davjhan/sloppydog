package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.resources.FontAssets;
import com.davidhan.sloppydog.screens.gamescreen.Controller;
import com.davidhan.sloppydog.screens.gamescreen.hud.HungerMeter;
import com.davidhan.sloppydog.screens.gamescreen.hud.OnScreenArrow;
import com.davidhan.sloppydog.uireusables.GameGroup;
import com.davidhan.sloppydog.uireusables.SolidDrawable;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanImageButton;
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
    SinglePlayerGame game;
    Controller controller;
    HanLabel scoreCounter;
    HungerMeter hungerMeter;
    HanImageButton pauseButton;
    ParticleEmitter particleEmitter;
    private boolean screenTouchDown;

    public SinglePlayerGui(IApp iApp, SinglePlayerGame game) {
        this.iApp = iApp;
        this.game = game;
        initGroups();
        this.controller = game;
    }

    private void makeHud() {
        Image bg = new Image(new SolidDrawable(Display.WIDTH, GameConst.Hud.HEIGHT, Colors.get(ColorNames.HUNGER_METER_BG)));
        inGame.spawn(bg,0,Display.HEIGHT,Align.topLeft);

        HanTextButton resetButton = new HanTextButton(iApp,"");
        resetButton.setSize(20,20);

        resetButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                game.restartGame();
            }
        });

        hungerMeter = new HungerMeter(iApp, game.getGameLog().getHungerPercentage());
        pauseButton = new HanImageButton(iApp,
                new TextureRegionDrawable(iApp.res().textures.pauseButton[0]),
                new TextureRegionDrawable(iApp.res().textures.pauseButton[1])
        );

        pauseButton.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                game.startPauseMenu();
            }
        });


        Image appleIcon = new Image(iApp.res().textures.appleIcon);
        scoreCounter = new HanLabel(iApp,"0", FontAssets.Font.SPORTY, Colors.get(ColorNames.OFF_WHITE));

        inGame.spawn(appleIcon, Spacing.SMALL_PLUS, Display.HEIGHT- GameConst.Hud.HEIGHT/2,Align.left);
        inGame.spawn(scoreCounter,appleIcon.getRight()+ Spacing.X_SMALL, Display.HEIGHT- GameConst.Hud.HEIGHT/2,Align.left);
        inGame.spawn(hungerMeter,scoreCounter.getRight()+Spacing.SMALL,Display.HEIGHT- GameConst.Hud.HEIGHT/2,Align.left);
        hungerMeter.setX(scoreCounter.getRight()+Spacing.SMALL);
        inGame.spawn(pauseButton,Display.WIDTH,Display.HEIGHT,Align.topRight);
      //  inGame.addActor(resetButton);


       // inGame.spawn(resetButton);
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
        hungerMeter.setFullMeterPercentage(game.getGameLog().getHungerPercentage());
//        if(screenTouchDown){
//            controller.onPlayerOneTouchedDown();
//        }
    }

    private void initGroups() {
        preGame = new GameGroup();
        inGame = new GameGroup();
        inGame.setSize(Display.WIDTH,Display.HEIGHT);
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
//        HanLabel tapToResetLabel = new HanLabel(
//                iApp,"Tap to Start.",
//                FontAssets.Font.SGK,
//                Colors.get(ColorNames.NEAR_BLACK)
//        );
        inGame.clear();
        makeController(game);
        makeHud();

        //postGame.spawn(tapToResetLabel, Display.HALF_WIDTH,60,Align.bottom);
    }
   public void startGame(){
        preGame.clear();
        postGame.clear();
    }

    public void setScore(int score) {
        scoreCounter.setText(String.valueOf(score));
        scoreCounter.layout();
        hungerMeter.setX(scoreCounter.getRight()+Spacing.SMALL *((int)Math.log10(score)+1));
    }
}
