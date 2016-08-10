package com.davidhan.lonelyball.screens.gamescreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.constants.Display;
import com.davidhan.lonelyball.screens.ScreenBase;

/**
 * name: GameScreen
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameScreen extends ScreenBase {
    GameGui gui;
    World world;
    Box2DDebugRenderer debugRenderer;

    public GameScreen(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        Box2D.init();
        world = new World(new Vector2(0, GameRules.Physics.GRAVITY), true);
        debugRenderer = new Box2DDebugRenderer();

        initialSetup();
    }

    private void initialSetup() {
        createBounds();
    }

    private void createBounds() {
        // Create our body definition
        BodyDef groundBodyDef = new BodyDef();
// Set its world position
// Create a body from the defintion and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

// Create a polygon shape
        PolygonShape groundBox = new PolygonShape();
// Set the polygon shape as a box which is twice the size of our view port and 20 high
// (setAsBox takes half-width and half-height as arguments)
// Create a fixture from our polygon shape and add it to our ground body

        //Bottom
        groundBox.setAsBox(
                Display.HALF_WIDTH,
                GameRules.Physics.BOUNDS_HALF_THICKNESS,
                new Vector2(Display.HALF_WIDTH,
                GameRules.Physics.GROUND_TOP -
                        GameRules.Physics.BOUNDS_HALF_THICKNESS),0);
        groundBody.createFixture(groundBox, 0.0f);

        //top
        groundBox.setAsBox(
                Display.HALF_WIDTH,
                GameRules.Physics.BOUNDS_HALF_THICKNESS,
                new Vector2(Display.HALF_WIDTH,
                        Display.HEIGHT +
                                GameRules.Physics.BOUNDS_HALF_THICKNESS),0);
        groundBody.createFixture(groundBox, 0.0f);

        //left
        groundBox.setAsBox(
                GameRules.Physics.BOUNDS_HALF_THICKNESS,
                Display.HEIGHT,
                new Vector2(-GameRules.Physics.BOUNDS_HALF_THICKNESS,0),0);
        groundBody.createFixture(groundBox, 0.0f);

        //right
        groundBox.setAsBox(
                GameRules.Physics.BOUNDS_HALF_THICKNESS,
                Display.HEIGHT,
                new Vector2(Display.WIDTH+GameRules.Physics.BOUNDS_HALF_THICKNESS,0),0);
        groundBody.createFixture(groundBox, 0.0f);
// Clean up after ourselves
        groundBox.dispose();


    }

    @Override
    protected void update(float delta) {
        world.step(1 / 60f, 6, 2);
        debugRenderer.render(world, stage.getCamera().combined);
        super.update(delta);
    }

    @Override
    public void render(float delta) {

        super.render(delta);
    }
}
