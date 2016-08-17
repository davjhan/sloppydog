package com.davidhan.armball.screens.gamescreen.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.constants.GameConst;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.Wall;

import java.util.ArrayList;
import java.util.List;

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
                (p0x-GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        (p0x-GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                        - GameConst.BOUNDS.WALL_THICKNESS
                ),
                0
        );
        //Bottom right
        float botRightWidth = (Display.WORLD_WIDTH-GameConst.BOUNDS.DOOR_HALF_WIDTH-p0x) /2;
        body.createFixture(groundBox,0).setUserData(Wall.GROUND);
        groundBox.setAsBox(
                botRightWidth,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        Display.WORLD_WIDTH-botRightWidth,
                        - GameConst.BOUNDS.WALL_THICKNESS
                ),
                0
        );
        body.createFixture(groundBox,0).setUserData(Wall.GROUND);
        // body.createFixture(groundBox, 0.0f);

        //Top Left
        groundBox.setAsBox(
                (p1x-GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        (p1x-GameConst.BOUNDS.DOOR_HALF_WIDTH)/2,
                        Display.WORLD_HEIGHT+GameConst.BOUNDS.WALL_THICKNESS
                ),
                0
        );
        //Top Top Right
        float topRightWidth = (Display.WORLD_WIDTH-GameConst.BOUNDS.DOOR_HALF_WIDTH-p1x) /2;
        body.createFixture(groundBox,0).setUserData(Wall.GROUND);
        groundBox.setAsBox(
                topRightWidth,
                GameConst.BOUNDS.WALL_THICKNESS,
                new Vector2(
                        Display.WORLD_WIDTH-topRightWidth,
                        Display.WORLD_HEIGHT+GameConst.BOUNDS.WALL_THICKNESS
                ),
                0
        );

        body.createFixture(groundBox,0).setUserData(Wall.GROUND);
    }
    public static Body createSideWalls(World world) {
        Body body = world.createBody(new BodyDef());
        PolygonShape groundBox = new PolygonShape();

        // body.createFixture(groundBox, 0.0f);

        //left
        groundBox.setAsBox(
                GameConst.BOUNDS.WALL_THICKNESS,
                Display.WORLD_HEIGHT,
                new Vector2(-GameConst.BOUNDS.WALL_THICKNESS, 0), 0);
        body.createFixture(groundBox, 0.0f);

        //right
        groundBox.setAsBox(
                GameConst.BOUNDS.WALL_THICKNESS,
                Display.WORLD_HEIGHT,
                new Vector2(Display.WORLD_WIDTH + GameConst.BOUNDS.WALL_THICKNESS, 0), 0);
        body.createFixture(groundBox, 0.0f);
        // Clean up after ourselves
        groundBox.dispose();
        return body;
    }


    public static Body createPlayer(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.density = 5f;
        fixtureDef.friction = 3f;
        fixtureDef.restitution = 0f;


        CircleShape head = new CircleShape();
        PolygonShape torso = new PolygonShape();


        torso.setAsBox(
                GameConst.Player.HEAD_RADIUS,
                GameConst.Player.FEET_HEIGHT,
                new Vector2(
                        0, 0)
                , 0);

        fixtureDef.shape = torso;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Player.FEET);


        torso.setAsBox(
                GameConst.Player.HEAD_RADIUS,
                GameConst.Player.TORSO_HEIGHT,
                new Vector2(
                        0,
                        GameConst.Player.TORSO_HEIGHT)
                , 0);
        fixtureDef.isSensor = false;
        fixtureDef.shape = torso;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Player.TORSO);

        //head
        head.setRadius(GameConst.Player.HEAD_RADIUS);
        head.setPosition(
                new Vector2(0, GameConst.Player.TORSO_HEIGHT * 2)
        );
        fixtureDef.restitution = 1f;
        fixtureDef.shape = head;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Player.HEAD);


        torso.dispose();
        head.dispose();

        return body;
    }

    public static Body createHoop(World world) {
        Body body = world.createBody(new BodyDef());

        CircleShape edge = new CircleShape();
        PolygonShape net = new PolygonShape();

        net.setAsBox(GameConst.Hoop.HOOP_DISTANCE,
                GameConst.Hoop.NET_HEIGHT);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = net;
        fixtureDef.density = 0;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Hoop.NET);


        edge.setRadius(GameConst.Hoop.RADIUS);
        edge.setPosition(new Vector2(-GameConst.Hoop.HOOP_DISTANCE, 0));
        body.createFixture(edge, 0);

        edge.setPosition(new Vector2(GameConst.Hoop.HOOP_DISTANCE, 0));
        body.createFixture(edge, 0);
        edge.dispose();

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
        filterData.maskBits &= ~CollisionGroups.HAND;

        CircleShape ball = new CircleShape();
        ball.setRadius(GameConst.Ball.RADIUS);
        fixtureDef.shape = ball;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setFilterData(filterData);
        ball.dispose();

        return body;
    }


    public static List<Body> createArm(World world) {
        List<Body> links = new ArrayList<Body>();
        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();

        BodyDef linkBodyDef = new BodyDef();
        linkBodyDef.type = BodyDef.BodyType.DynamicBody;

        CircleShape knob = new CircleShape();
        knob.setRadius(GameConst.Arm.ARM_RADIUS);
        PolygonShape linkShape = new PolygonShape();
        linkShape.setAsBox(
                GameConst.Arm.ARM_RADIUS,
                GameConst.Arm.LINK_HALF_LENGTH
        );
        fixtureDef.shape = linkShape;


        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
        revoluteJointDef.lowerAngle = -(float)Math.toRadians(360);
        revoluteJointDef.upperAngle = 0;
        //---
        Body prevBody = null;
        Vector2 joinSpot = new Vector2();

        Filter filterData = new Filter();
        filterData.categoryBits= CollisionGroups.ARM_LINK_0;
        filterData.maskBits=1;

       // filterData0.groupIndex = -1;
        for (int i = 0; i < GameConst.Arm.NUM_LINKS; i++) {
            Body linkBody = world.createBody(linkBodyDef);
            if(i == 1){
                revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
                revoluteJointDef.localAnchorA.x = 0;
                revoluteJointDef.localAnchorA.y = GameConst.Arm.LINK_HALF_LENGTH;

            }

            fixtureDef.shape = linkShape;
            linkBody.setTransform(
                    GameConst.Arm.P0_STARTING_X,
                    GameConst.Arm.P0_STARTING_Y
                            -GameConst.Arm.LINK_HALF_LENGTH
                            - (i * GameConst.Arm.LINK_HALF_LENGTH * 2), 0);
            Fixture shaft= linkBody.createFixture(fixtureDef);
            shaft.setFilterData(filterData);
            shaft.setUserData(Arm.SHAFT);

            knob.setPosition(new Vector2(0,-GameConst.Arm.LINK_HALF_LENGTH));
            fixtureDef.shape = knob;
            Fixture topKnob= linkBody.createFixture(fixtureDef);
            topKnob.setFilterData(filterData);
            topKnob.setUserData(Arm.SHAFT);

            knob.setPosition(new Vector2(0,GameConst.Arm.LINK_HALF_LENGTH));
            Fixture botKnob= linkBody.createFixture(fixtureDef);
            botKnob.setFilterData(filterData);
            botKnob.setUserData(Arm.SHAFT);

            if(i > 0) {
                revoluteJointDef.initialize(prevBody, linkBody, joinSpot);
                world.createJoint(revoluteJointDef);
            }

            links.add(linkBody);
            prevBody = linkBody;
            joinSpot.set(
                    linkBody.getPosition().x,
                    linkBody.getPosition().y - GameConst.Arm.LINK_HALF_LENGTH);
            if(i % 2 == 0){
                filterData.categoryBits= CollisionGroups.ARM_LINK_0;
                filterData.maskBits=31;
                filterData.maskBits&= ~CollisionGroups.ARM_LINK_0;
            }else{
                filterData.categoryBits= CollisionGroups.ARM_LINK_1;
                filterData.maskBits=31;
                filterData.maskBits&= ~CollisionGroups.ARM_LINK_1;
            }
        }

        return links;
    }

    public static Body createHand(World world, Body firstLink) {


        BodyDef handBodyDef = new BodyDef();
        handBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body handBody = world.createBody(handBodyDef);
        handBody.setTransform(
                firstLink.getPosition().x,
                firstLink.getPosition().y+ GameConst.Arm.LINK_HALF_LENGTH,0);

        Filter filterData  =new Filter();
        filterData.categoryBits = CollisionGroups.HAND;
       // filterData.maskBits = 31;

        CircleShape handShape = new CircleShape();
        handShape.setRadius(GameConst.Arm.HAND_RADIUS);

        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        fixtureDef.density=5;
        fixtureDef.shape = handShape;
        Fixture hand = handBody.createFixture(fixtureDef);
        Fixture handSensor = handBody.createFixture(fixtureDef);
        handSensor.setUserData(Arm.HAND);
        handSensor.setSensor(true);
        hand.setFilterData(filterData);



        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
        revoluteJointDef.initialize(firstLink, handBody, firstLink.getPosition().cpy().add(0,GameConst.Arm.LINK_HALF_LENGTH));

        revoluteJointDef.localAnchorA.x = 0;
        revoluteJointDef.localAnchorA.y = GameConst.Arm.LINK_HALF_LENGTH;

        revoluteJointDef.localAnchorB.x = 0;
        revoluteJointDef.localAnchorB.y = -GameConst.Arm.HAND_RADIUS;
        world.createJoint(revoluteJointDef);
        return handBody;
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
                new Vector2(   Display.WORLD_HALF_WIDTH, -GameConst.BOUNDS.DOOR_HALF_WIDTH),
                0
        );
        body.createFixture(shoulderShape, 10);
        shoulderShape.dispose();

        return body;
    }


}
