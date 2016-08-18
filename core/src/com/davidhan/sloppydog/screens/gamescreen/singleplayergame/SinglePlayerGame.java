package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.GameScreen;
import com.davidhan.sloppydog.screens.gamescreen.box2d.BodyFactory;
import com.davidhan.sloppydog.screens.gamescreen.entities.BgGrass;
import com.davidhan.sloppydog.screens.gamescreen.entities.Dog;
import com.davidhan.sloppydog.screens.gamescreen.entities.Door;
import com.davidhan.sloppydog.screens.gamescreen.entities.Target;


/**
 * name: GameScreen
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SinglePlayerGame extends GameScreen {
    SinglePlayerGui gui;

      Target ball;
    Dog dog;
    Door door;
    Target target;
    BgGrass bgGrass;
    SinglePlayerGameLog gameLog;
    SinglePlayerPauseMenu pauseMenu;

    public SinglePlayerGame(IApp iApp) {
        super(iApp);
    }

    @Override
    public void firstLaunchSetup() {
        super.firstLaunchSetup();

    }

    private void initBG() {
        bgGrass = new BgGrass(iApp);
        bgGroup.spawn(bgGrass);
    }

    private void createBounds() {
        // Create our body definition
        BodyFactory.createSideWalls(world);
        BodyFactory.createTopAndBottomWalls(world, GameConst.Arm.P0_STARTING_X, GameConst.Arm.P1_STARTING_X);
        door = new Door(BodyFactory.createDoor(world));
    }

    private void spawnBall() {
          ball = new Target(iApp, BodyFactory.createBall(world));
          ball.setBodyPos(GameConst.Ball.STARTING_X, GameConst.Ball.STARTING_Y);
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
            onTouchedDown();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dog.face(target.body().getPosition());
        }

    }

    @Override
    protected void setupNewGame() {
        super.setupNewGame();
        initBG();
        paused = false;
        createBounds();
        gui.newGame();
        // spawnBall();

        target = new Target(iApp, BodyFactory.createTarget(world));
        target.body().setTransform(3,GameConst.World.HEIGHT-7,0);
        entitiesGroup.spawn(target);
        spawnDog();
    }

    @Override
    protected void initContactListener(World world) {
        contactListener = new SinglePlayerContactListener(this);
        world.setContactListener(contactListener);

    }

    @Override
    protected void initGameLog() {
        gameLog = new SinglePlayerGameLog(this);
    }

    private void spawnDog() {
        dog = new Dog(iApp, world, 0);
        // door.setArm(dog);
        entitiesGroup.spawn(dog);
        dog.setTarget(target.body());
        dog.translateBodiesTo(GameConst.Dog.STARTING_X, GameConst.Dog.STARTING_Y);
    }

    @Override
    public void startGame() {
        super.startGame();
        gui.startGame();
    }

    @Override
    public void endGame() {
        Gdx.app.log("tttt SinglePlayerGame", "game ended"+gameEnded);
        gameEnded = true;
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
    public void startPauseMenu() {
        paused = true;
        pauseMenu = new SinglePlayerPauseMenu(iApp,new SinglePlayerPauseMenu.PauseMenuListener(){
            @Override
            public void resume() {
                SinglePlayerGame.this.resumeGame();
            }

            @Override
            public void restartGame() {
                SinglePlayerGame.this.restartGame();
            }
        });
        iApp.modalStage().addActor(pauseMenu);
    }

    @Override
    protected void resumeGame() {
        paused = false;
    }

    //Controller
    @Override
    public void onArrowPressed(int dire) {
        if (gameEnded) return;
        dog.move(dire);
    }

    @Override
    public void onTouchedDown() {
        if (!gameRunning()) return;
        // dog.tunnelUp();
        dog.setRunning(true);
    }

    @Override
    public void onTouchedUp() {
        if (!gameRunning()) return;
        dog.setRunning(false);
    }

    private boolean gameRunning() {
        return !gameEnded && gameStarted;
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
        door.checkDoor(world);
    }

    public SinglePlayerGameLog getGameLog() {
        return gameLog;
    }


}
