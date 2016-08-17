package com.davidhan.armball.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.constants.GameConst;
import com.davidhan.armball.resources.ColorNames;
import com.davidhan.armball.screens.gamescreen.GameScreen;
import com.davidhan.armball.screens.gamescreen.box2d.BodyFactory;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.Ball;
import com.davidhan.armball.screens.gamescreen.entities.Door;
import com.davidhan.armball.screens.gamescreen.entities.Target;
import com.davidhan.armball.screens.gamescreen.entities.Wall;
import com.davidhan.armball.uireusables.SolidDrawable;


/**
 * name: GameScreen
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerGame extends GameScreen {
    SinglePlayerGui gui;

    Ball ball;
    Arm arm;
    Door door;
    Target target;
    Wall walls;
    SinglePlayerGameLog gameLog;

    public SinglePlayerGame(IApp iApp) {
        super(iApp);
    }

    @Override
    public void firstLaunchSetup() {
        super.firstLaunchSetup();
        initBG();
    }

    private void initBG() {
        Image bg = new Image(new SolidDrawable(Display.WIDTH, Display.HEIGHT, Colors.get(ColorNames.GAME_BG)));
        bgGroup.spawn(bg);
    }

    private void createBounds() {
        // Create our body definition
        walls = new Wall(iApp, BodyFactory.createSideWalls(world));
        BodyFactory.createTopAndBottomWalls(world, GameConst.Arm.P0_STARTING_X,GameConst.Arm.P1_STARTING_X);
        door = new Door(iApp, BodyFactory.createDoor(world));
        entitiesGroup.spawn(door);
    }

    private void spawnBall() {
        ball = new Ball(iApp, com.davidhan.armball.screens.gamescreen.box2d.BodyFactory.createBall(world));
        ball.setBodyPos(com.davidhan.armball.constants.GameConst.Ball.STARTING_X, com.davidhan.armball.constants.GameConst.Ball.STARTING_Y);
        entitiesGroup.spawn(ball);
    }

    @Override
    protected void checkKeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            onArrowPressed(1);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            onArrowPressed(-1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            onPlayerZeroTouchedDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            arm.face(target.body().getPosition());
        }

    }

    @Override
    protected void setupNewGame() {
        super.setupNewGame();

        gameLog = new SinglePlayerGameLog();
        createBounds();
        gui.newGame();
        // spawnBall();
        spawnArm();
        target = new Target(iApp, BodyFactory.createTarget(world));
        entitiesGroup.spawn(target);

        contactListener = new com.davidhan.armball.screens.gamescreen.box2d.GameContactListener(this);
        world.setContactListener(contactListener);

    }

    @Override
    protected void initContactListener(World world) {

    }

    private void spawnArm() {
        arm = new Arm(iApp, world, 0);
        door.setArm(arm);
        //arm.setShoulderPos(body().setTransform(Display.WORLD_HALF_WIDTH, GameConst.Arm.SHOULDER_Y,0);
        entitiesGroup.spawn(arm);
        arm.moveBodiesBy(0, 8);
        //  arm.rotateBodiesBy(arm.getHand(),30);
    }

    @Override
    public void startGame() {
        super.startGame();
        gui.startGame();
    }

    @Override
    public void endGame() {
        super.endGame();
        gui.endGame();

    }

    @Override
    protected void reset() {
        super.reset();
        setScore(0);
    }

    @Override
    protected void initGui() {
        gui = new SinglePlayerGui(iApp, this);
        stage.addActor(gui);
    }

    @Override
    protected void update(float delta) {
        if (!paused && gameStarted) {
            gameLog.update(delta);
            world.step(1f / 60f, 6, 6);
            checkKeys();
        }


        super.update(delta);
    }




    @Override
    public void onArrowPressed(int dire) {
        if (gameEnded) return;
        arm.move(dire);
    }

    @Override
    public void onPlayerZeroTouchedDown() {
        if (gameEnded) return;
        // arm.tunnelUp();
        arm.goToLocation(target.body().getPosition(),GameConst.Arm.IMPULSE_POWER);
    }

    @Override
    public void onPlayerZeroTouchedUp() {

    }

    @Override
    public void onPlayerOneTouchedDown() {

    }

    @Override
    public void onPlayerOneTouchedUp() {

    }

    public void setScore(int score) {
        gameLog.setScore(score);
        gui.setScore(score);
    }

    public void incrementScore() {
        gameLog.incrementScore();
        gui.setScore(gameLog.getScore());
    }

    @Override
    protected void onUpdateRunning(float delta) {
        gameLog.update(delta);
    }

    public SinglePlayerGameLog getGameLog() {
        return gameLog;
    }


}
