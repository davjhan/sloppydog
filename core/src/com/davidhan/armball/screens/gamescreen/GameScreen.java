package com.davidhan.armball.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.davidhan.armball.screens.ScreenBase;
import com.davidhan.armball.screens.gamescreen.box2d.BodyFactory;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.Ball;
import com.davidhan.armball.screens.gamescreen.entities.Target;

/**
 * name: GameScreen
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameScreen extends ScreenBase implements Controlller {
    GameGui gui;
    World world;
    com.davidhan.armball.screens.gamescreen.entities.Player player;
    Ball ball;
    Arm arm;
    Target target;
    com.davidhan.armball.screens.gamescreen.entities.Wall walls;
    com.davidhan.armball.screens.gamescreen.entities.Hoop hoop;
    com.davidhan.armball.uireusables.GameGroup entitiesGroup;
    Box2DDebugRenderer debugRenderer;
    ContactListener contactListener;
    boolean gameStarted = false;
    boolean gameEnded = false;
    boolean paused = false;
    ClickListener startGameClickListener;
    ClickListener restartGameClickListener;
    private int score = 0;


    public GameScreen(com.davidhan.armball.app.IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        Box2D.init();
        gui = new GameGui(iApp,this);
        initGroups();
        world = new World(new Vector2(0, com.davidhan.armball.constants.GameConst.Physics.GRAVITY), true);


        debugRenderer = new Box2DDebugRenderer();

        contactListener = new com.davidhan.armball.screens.gamescreen.box2d.GameContactListener(this);
        world.setContactListener(contactListener);

        setupNewGame();
    }

    private void initGroups() {
        entitiesGroup = new com.davidhan.armball.uireusables.GameGroup();
        stage.addActor(entitiesGroup);
        stage.addActor(gui);
    }


    private void createBounds() {
        // Create our body definition
        walls = new com.davidhan.armball.screens.gamescreen.entities.Wall(iApp, com.davidhan.armball.screens.gamescreen.box2d.BodyFactory.createWalls(world));
    }

    private void spawnBall() {
        ball = new Ball(iApp, com.davidhan.armball.screens.gamescreen.box2d.BodyFactory.creatBall(world));
        ball.setBodyPos(com.davidhan.armball.constants.GameConst.Ball.STARTING_X, com.davidhan.armball.constants.GameConst.Ball.STARTING_Y);
        entitiesGroup.spawn(ball);
    }

    @Override
    protected void update(float delta) {
        if(!paused && gameStarted ) {
            world.step(1f / 60f, 6, 6);
            checkKeys();
        }
        debugRenderer.render(world, stage.getCamera().combined.scl(com.davidhan.armball.constants.Display.WORLDSCALE));

        super.update(delta);
    }

    private void checkKeys() {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            onArrowPressed(1);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            onArrowPressed(-1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            onJumpPressed();
        }

    }


    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.SPACE){
            if(gameStarted || gameEnded) {
                restart();
            }else{
                startGame();
            }
        }
        return super.keyDown(keycode);
    }

    public void restart() {
        reset();
        setupNewGame();
    }

    private void setupNewGame() {
        createBounds();
        stage.addListener(startGameClickListener = new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                startGame();
                stage.removeListener(this);
                super.touchUp(event, x, y, pointer, button);
            }
        });
        gui.newGame();
        setGameStarted(false);
       // spawnBall();
        spawnArm();
        target = new Target(iApp,BodyFactory.createTarget(world));
        entitiesGroup.spawn(target);

    }

    private void spawnArm() {
        arm = new Arm(iApp, world);
        //arm.setShoulderPos(body().setTransform(Display.WORLD_HALF_WIDTH, GameConst.Arm.SHOULDER_Y,0);
        entitiesGroup.spawn(arm);
    }

    private void startGame() {
        setGameStarted(true);
       // ball.body().applyLinearImpulse(new Vector2(0,50),ball.body().getWorldCenter(),true);
        gui.startGame();
        stage.removeListener(startGameClickListener);
    }
    public void endGame(){
        gameEnded = true;
        gui.endGame();
        stage.addListener(restartGameClickListener = new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                restart();
                stage.removeListener(this);
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    private void reset() {
        gameEnded = false;
        gameStarted = false;
        world.dispose();
        world = new World(new Vector2(0, com.davidhan.armball.constants.GameConst.Physics.GRAVITY), true);
        entitiesGroup.clear();
        setScore(0);
        if(startGameClickListener != null)stage.removeListener(startGameClickListener);
        if(restartGameClickListener != null) stage.removeListener(restartGameClickListener);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void render(float delta) {

        super.render(delta);
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    @Override
    public void onArrowPressed(int dire) {
        if(gameEnded) return;
        arm.move(dire);
    }

    @Override
    public void onJumpPressed() {
        if(gameEnded) return;
        arm.gotoTarget(target.body().getPosition());
    }
    public void setScore(int score){
        this.score = score;
        gui.setScore(score);
    }
    public void incrementScore(){
        score++;
        gui.setScore(score);
    }
}
