package com.davidhan.armball.screens.gamescreen.aigame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.constants.GameConst;
import com.davidhan.armball.resources.ColorNames;
import com.davidhan.armball.screens.gamescreen.GameScreen;
import com.davidhan.armball.screens.gamescreen.box2d.AIGameContactListener;
import com.davidhan.armball.screens.gamescreen.box2d.BodyFactory;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.Ball;
import com.davidhan.armball.screens.gamescreen.singleplayergame.AiGameGui;
import com.davidhan.armball.uireusables.SolidDrawable;

/**
 * name: AIGame
 * desc:
 * date: 2016-08-15
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class AIGame extends GameScreen {
    AiGameGui gui;
    Arm playerZeroArm;
    Arm playerOneArm;
    Ball ball;
    boolean playerZeroTouchedDown;
    boolean playerOneTouchedDown;
    public AIGame(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void setupNewGame() {
        super.setupNewGame();
        gui.newGame();
        createBounds();
        initBG();

        setupBall();
        setupArms();
        playerZeroTouchedDown = false;
        playerOneTouchedDown = false;
    }

    @Override
    protected void initContactListener(World world) {
        contactListener = new AIGameContactListener(this);
        world.setContactListener(contactListener);
    }

    private void setupBall() {
        ball = new Ball(iApp,BodyFactory.createBall(world));
        ball.setBodyPos(Display.WORLD_HALF_WIDTH,Display.WORLD_HALF_HEIGHT);
        entitiesGroup.spawn(ball);
    }

    @Override
    public void startGame() {
        super.startGame();
        gui.startGame();
    }

    private void createBounds() {
        BodyFactory.createSideWalls(world);
        BodyFactory.createTopAndBottomWalls(world,GameConst.Arm.P0_STARTING_X,GameConst.Arm.P1_STARTING_X);
    }

    private void setupArms() {
        playerZeroArm = new Arm(iApp, world, 0);
        entitiesGroup.spawn(playerZeroArm);

        playerOneArm = new Arm(iApp,world,1);
        playerOneArm.translateBodiesTo(GameConst.Arm.P0_STARTING_X,
                GameConst.Arm.P0_STARTING_Y);

        playerOneArm.rotateBodiesBy(180);
        playerOneArm.translateBodiesTo(GameConst.Arm.P1_STARTING_X,
                GameConst.Arm.P1_STARTING_Y);
        entitiesGroup.spawn(playerOneArm);
    }


    @Override
    protected void initGui() {
        gui = new AiGameGui(iApp,this);
        stage.addActor(gui);
    }
    private void initBG() {
        Image bg = new Image(new SolidDrawable(Display.WIDTH, Display.HEIGHT, Colors.get(ColorNames.GAME_BG)));
        bgGroup.spawn(bg);
    }
    @Override
    protected void checkKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            onArrowPressed(1);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            onArrowPressed(-1);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            onPlayerOneTouchedDown();
        }else{
            onPlayerOneTouchedUp();
        }

    }


    @Override
    protected void onUpdateRunning(float delta) {
        gui.update(delta);
        if(playerZeroTouchedDown){
            //playerZeroArm.goToLocation(new Vector2(Display.WORLD_WIDTH,0),GameConst.Arm.IMPULSE_POWER*2);
            playerZeroArm.goToLocation(new Vector2(Gdx.input.getX()/Display.WORLDSCALE,(Gdx.graphics.getHeight()-Gdx.input.getY())/Display.WORLDSCALE),GameConst.Arm.IMPULSE_POWER);
           // playerOneArm.goToLocation(new Vector2(Display.WORLD_HALF_WIDTH,0));
             // playerOneArm.goToLocation(playerOneArm.getTail().getPosition());
        }else{
           // playerZeroArm.goToLocation(playerZeroArm.getTail().getPosition(),GameConst.Arm.IMPULSE_POWER);
            //if(playerZeroArm.isGrabbing()){
           //     playerZeroArm.goToLocation(new Vector2(0,Display.WORLD_HEIGHT),GameConst.Arm.IMPULSE_POWER);
           // }else if(ball.isGrabbed()){
           //     playerZeroArm.goToLocation(playerOneArm.getHand().getPosition(),GameConst.Arm.IMPULSE_POWER);
           // }else{
              //  playerZeroArm.goToLocation(ball.body().getPosition(),GameConst.Arm.IMPULSE_POWER);
            //}
           // playerZeroArm.goToLocation(playerOneArm.getTail().getPosition(),GameConst.Arm.IMPULSE_POWER);
            //playerOneArm.goToLocation(ball.body().getPosition());
        }
        if(playerOneTouchedDown){

          //  playerOneArm.goToLocation(new Vector2(Display.WORLD_HALF_WIDTH,Display.WORLD_HEIGHT));

        }else {
         //   playerOneArm.goToLocation(playerZeroArm.getTail().getPosition(),GameConst.Arm.IMPULSE_POWER);
//            playerTwoArm.goToLocation(ball.body().getPosition());
//        }
        }
       // playerOneArm.face(playerTwoArm.getTail().getPosition());


    }

    @Override
    public void onArrowPressed(int dire) {

    }

    @Override
    public void onPlayerZeroTouchedDown() {
        if (gameEnded) return;
        playerZeroTouchedDown = true;
        // arm.tunnelUp();
        //playerOneArm.goForward();
        //playerOneArm.goToLocation(playerTwoArm.getHand().getPosition());
    }

    @Override
    public void onPlayerZeroTouchedUp() {
        if (gameEnded) return;
        playerZeroTouchedDown = false;

    }

    @Override
    public void onPlayerOneTouchedDown() {
        if (gameEnded) return;
        playerOneTouchedDown = true;
    }

    @Override
    public void onPlayerOneTouchedUp() {
        if (gameEnded) return;
        playerOneTouchedDown = false;
    }

    public Arm getPlayerZero() {
        return playerZeroArm;
    }
}
