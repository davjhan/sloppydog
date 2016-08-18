package com.davidhan.sloppydog.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.hud.CameraShaker;
import com.davidhan.sloppydog.uireusables.GameGroup;

/**
 * name: GameScreen
 * desc:
 * date: 2016-08-15
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class GameScreen extends com.davidhan.sloppydog.screens.ScreenBase implements Controller {
    protected GameGroup entitiesGroup;
    protected GameGroup bgGroup;
    protected GameGroup nonHudGroup;

    protected boolean gameStarted = false;
    protected boolean gameEnded = false;
    protected boolean paused = false;
    protected boolean debugOn = false;

    protected ContactListener contactListener;
    protected World world;
    protected Box2DDebugRenderer debugRenderer;
    private Action shakeAction;

    public GameScreen(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        Box2D.init();
        initGroups();
        firstLaunchSetup();
        initGui();
        debugRenderer = new Box2DDebugRenderer();
        setupNewGame();
    }

    protected void firstLaunchSetup() {
        stage.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (gameEnded) {
                    restartGame();
                } else if (!gameStarted) {
                    startGame();
                }
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    protected void setupNewGame() {
        gameStarted = false;
        world = new World(new Vector2(0, GameConst.Physics.GRAVITY), true);
        initContactListener(world);
    }

    protected abstract void initContactListener(World world);

    public void startGame() {
        gameStarted = true;
    }

    public void endGame() {
        gameEnded = true;
    }

    protected void reset() {
        gameEnded = false;
        gameStarted = false;
        world.dispose();
        entitiesGroup.clear();
    }

    protected void initGroups() {
        nonHudGroup = new GameGroup();
        bgGroup = new GameGroup();
        entitiesGroup = new GameGroup();
        stage.addActor(bgGroup);
        stage.addActor(nonHudGroup);
        nonHudGroup.addActor(entitiesGroup);
    }

    protected abstract void initGui();

    public void restartGame() {
        reset();
        setupNewGame();
    }

    protected abstract void checkKeys();

    @Override
    protected void update(float delta) {
        if (!paused && gameStarted) {
            world.step(1f / 60f, 6, 6);
            onUpdateRunning(delta);
            checkKeys();
        }
        super.update(delta);
    }

    protected abstract void onUpdateRunning(float delta);

    public void shakeScreen(int magnitude) {
        if (shakeAction != null) {
            nonHudGroup.removeAction(shakeAction);
        }
        shakeAction = CameraShaker.getShakeAction(magnitude);
        nonHudGroup.addAction(shakeAction);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (gameStarted || gameEnded) {
                restartGame();
            } else {
                startGame();
            }
        }

        if (keycode == Input.Keys.D) {
            debugOn = !debugOn;
        }
        return super.keyUp(keycode);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.act(delta);
        stage.draw();
        if (debugOn) {
            debugRenderer.render(world, stage.getCamera().combined.scl(Display.WORLDSCALE));
        }
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
