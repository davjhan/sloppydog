package com.davidhan.sloppydog.screens.gamescreen.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.sloppydog.constants.GameConst;

/**
 * name: Boundaries
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BodyFactory extends Body {
    /**
     * Constructs a new body with the given address
     *
     * @param world the world
     * @param addr  the address
     */
    public static Vector2 tmp = new Vector2();

    protected BodyFactory(World world, long addr) {
        super(world, addr);
    }

    public static void createTopAndBottomWalls(World world,float p0x,float p1x) {
        Body body = world.createBody(new BodyDef());
        PolygonShape groundBox = new PolygonShape();
        //Bottomleft
        groundBox.setAsBox(
                (p0x- GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        (p0x- GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                        - GameConst.BOUNDS.WALL_THICKNESS+GameConst.Hud.BOTTOM
                ),
                0
        );
        //Bottom right
        float botRightWidth = (GameConst.World.WIDTH- GameConst.BOUNDS.DOOR_HALF_WIDTH-p0x) /2;
        body.createFixture(groundBox,0).setUserData(GameConst.BOUNDS.WALL_TAG);
        groundBox.setAsBox(
                botRightWidth,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        GameConst.World.WIDTH-botRightWidth,
                        - GameConst.BOUNDS.WALL_THICKNESS+GameConst.Hud.BOTTOM
                ),
                0
        );
        body.createFixture(groundBox,0).setUserData(GameConst.BOUNDS.WALL_TAG);
        // body.createFixture(groundBox, 0.0f);
        //Top
        groundBox.setAsBox(
                GameConst.World.HALF_WIDTH,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        GameConst.World.HALF_WIDTH,
                        GameConst.World.HEIGHT+ GameConst.BOUNDS.WALL_THICKNESS-GameConst.Hud.HungerMeter.HEIGHT/GameConst.World.SCALE
                ),
                0
        );
        body.createFixture(groundBox,0).setUserData(GameConst.BOUNDS.WALL_TAG);
//        //Top Left
//        groundBox.setAsBox(
//                (p1x- GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
//                GameConst.BOUNDS.WALL_THICKNESS,
//                new Vector2(
//                        (p1x- GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
//                        GameConst.World.HEIGHT+ GameConst.BOUNDS.WALL_THICKNESS
//                ),
//                0
//        );
//        //Top Top Right
//        float topRightWidth = (GameConst.World.WIDTH- GameConst.BOUNDS.DOOR_HALF_WIDTH-p1x) /2;
//        body.createFixture(groundBox,0).setUserData(Wall.GROUND);
//        groundBox.setAsBox(
//                topRightWidth,
//                GameConst.BOUNDS.WALL_THICKNESS,
//                new Vector2(
//                        GameConst.World.WIDTH-topRightWidth,
//                        GameConst.World.HEIGHT+ GameConst.BOUNDS.WALL_THICKNESS
//                ),
//                0
//        );


    }
    public static Body createSideWalls(World world) {
        Body body = world.createBody(new BodyDef());
        PolygonShape groundBox = new PolygonShape();

        // body.createFixture(groundBox, 0.0f);

        //left
        groundBox.setAsBox(
                GameConst.BOUNDS.WALL_THICKNESS,
                GameConst.World.HEIGHT,
                new Vector2(-GameConst.BOUNDS.WALL_THICKNESS, 0), 0);
        body.createFixture(groundBox, 0.0f).setUserData(GameConst.BOUNDS.WALL_TAG);

        //right
        groundBox.setAsBox(
                GameConst.BOUNDS.WALL_THICKNESS,
                GameConst.World.HEIGHT,
                new Vector2(GameConst.World.WIDTH + GameConst.BOUNDS.WALL_THICKNESS, 0), 0);
        body.createFixture(groundBox, 0.0f).setUserData(GameConst.BOUNDS.WALL_TAG);
        // Clean up after ourselves
        groundBox.dispose();
        return body;
    }

    public static Body createBall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.density = 20;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0.7f;

        Filter filterData  =new Filter();
        filterData.maskBits = 31;


        CircleShape ball = new CircleShape();
        ball.setRadius(GameConst.Ball.RADIUS);
        fixtureDef.shape = ball;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setFilterData(filterData);
        ball.dispose();

        return body;
    }




    public static Body createTarget(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        CircleShape shoulderShape = new CircleShape();
        shoulderShape.setRadius(GameConst.TARGET.RADIUS);
        FixtureDef fixtureDef = new FixtureDef();
      //  fixtureDef.isSensor = true;
        fixtureDef.shape = shoulderShape;
        fixtureDef.density = 2;
        Fixture sensorFixture = body.createFixture(fixtureDef);
        shoulderShape.dispose();
        body.setGravityScale(0);
        return body;
    }

    public static Body createDoor(World world) {
        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        PolygonShape shoulderShape = new PolygonShape();
        shoulderShape.setAsBox(GameConst.BOUNDS.DOOR_HALF_WIDTH,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(   GameConst.Dog.STARTING_X, -GameConst.BOUNDS.WALL_THICKNESS+GameConst.Hud.BOTTOM),
                0
        );
        Fixture fixture = body.createFixture(shoulderShape, 10);
        fixture.setSensor(true);
        fixture.setUserData(GameConst.BOUNDS.DOOR_TAG);
        shoulderShape.dispose();

        return body;
    }


}
