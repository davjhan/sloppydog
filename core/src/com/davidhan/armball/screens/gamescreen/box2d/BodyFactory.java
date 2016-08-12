package com.davidhan.armball.screens.gamescreen.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
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
    private static float pi = 3.14159f;

    protected BodyFactory(World world, long addr) {
        super(world, addr);
    }

    public static Body createWalls(World world) {
        Body body = world.createBody(new BodyDef());
        PolygonShape groundBox = new PolygonShape();
        //Bottom
        groundBox.setAsBox(
                Display.WORLD_HALF_WIDTH,
                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS,
                new Vector2(Display.WORLD_HALF_WIDTH,
                        com.davidhan.armball.constants.GameConst.Physics.GROUND_TOP -
                                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS), 0);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef).setUserData(Wall.GROUND);
        // body.createFixture(groundBox, 0.0f);

        //top
        groundBox.setAsBox(
                Display.WORLD_HALF_WIDTH,
                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS,
                new Vector2(Display.WORLD_HALF_WIDTH,
                        Display.WORLD_HEIGHT +
                                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS), 0);
        body.createFixture(groundBox, 0.0f);

        //left
        groundBox.setAsBox(
                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS,
                Display.WORLD_HEIGHT,
                new Vector2(-com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS, 0), 0);
        body.createFixture(groundBox, 0.0f);

        //right
        groundBox.setAsBox(
                com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS,
                Display.WORLD_HEIGHT,
                new Vector2(Display.WORLD_WIDTH + com.davidhan.armball.constants.GameConst.Physics.BOUNDS_HALF_THICKNESS, 0), 0);
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
                com.davidhan.armball.constants.GameConst.Player.HEAD_RADIUS,
                com.davidhan.armball.constants.GameConst.Player.FEET_HEIGHT,
                new Vector2(
                        0, 0)
                , 0);

        fixtureDef.shape = torso;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Player.FEET);


        torso.setAsBox(
                com.davidhan.armball.constants.GameConst.Player.HEAD_RADIUS,
                com.davidhan.armball.constants.GameConst.Player.TORSO_HEIGHT,
                new Vector2(
                        0,
                        com.davidhan.armball.constants.GameConst.Player.TORSO_HEIGHT)
                , 0);
        fixtureDef.isSensor = false;
        fixtureDef.shape = torso;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Player.TORSO);

        //head
        head.setRadius(com.davidhan.armball.constants.GameConst.Player.HEAD_RADIUS);
        head.setPosition(
                new Vector2(0, com.davidhan.armball.constants.GameConst.Player.TORSO_HEIGHT * 2)
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

        net.setAsBox(com.davidhan.armball.constants.GameConst.Hoop.HOOP_DISTANCE,
                com.davidhan.armball.constants.GameConst.Hoop.NET_HEIGHT);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = net;
        fixtureDef.density = 0;
        body.createFixture(fixtureDef).setUserData(com.davidhan.armball.screens.gamescreen.entities.Hoop.NET);


        edge.setRadius(com.davidhan.armball.constants.GameConst.Hoop.RADIUS);
        edge.setPosition(new Vector2(-com.davidhan.armball.constants.GameConst.Hoop.HOOP_DISTANCE, 0));
        body.createFixture(edge, 0);

        edge.setPosition(new Vector2(com.davidhan.armball.constants.GameConst.Hoop.HOOP_DISTANCE, 0));
        body.createFixture(edge, 0);
        edge.dispose();

        return body;
    }

    public static Body creatBall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();

        fixtureDef.density = 0.5f;
        fixtureDef.friction = 2;
        fixtureDef.restitution = 0.6f;

        CircleShape ball = new CircleShape();
        ball.setRadius(com.davidhan.armball.constants.GameConst.Ball.RADIUS);
        fixtureDef.shape = ball;

        body.createFixture(fixtureDef);
        ball.dispose();

        return body;
    }

    public static Body createShoulder(World world) {
        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        CircleShape shoulderShape = new CircleShape();
        shoulderShape.setRadius(GameConst.Arm.SHOULDER_RADIUS);
        body.createFixture(shoulderShape, 10).setUserData(Arm.SHOULDER);
        shoulderShape.dispose();

        return body;
    }

    public static List<Body> createArm(World world, Body shoulder) {
        List<Body> links = new ArrayList<Body>();
        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();

        BodyDef linkBodyDef = new BodyDef();
        linkBodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape linkShape = new PolygonShape();
        linkShape.setAsBox(
                GameConst.Arm.ARM_THICKNESS,
                GameConst.Arm.LINK_HALF_LENGTH,
                tmp.set(
                        0,
                        0
                ),
                0
        );
        fixtureDef.shape = linkShape;


        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
        revoluteJointDef.lowerAngle = -(float)Math.toRadians(360);
        revoluteJointDef.upperAngle = 0;
        //---
        Body prevBody = shoulder;
        Vector2 joinSpot = shoulder.getPosition();
        for (int i = 0; i < GameConst.Arm.NUM_LINKS; i++) {
            Body linkBody = world.createBody(linkBodyDef);
            if(i == 1){
                revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
                revoluteJointDef.localAnchorA.x = 0;
                revoluteJointDef.localAnchorA.y = GameConst.Arm.LINK_HALF_LENGTH;

            }
            linkBody.setTransform(
                    Display.WORLD_HALF_WIDTH,
                    GameConst.Arm.SHOULDER_Y
                            +GameConst.Arm.LINK_HALF_LENGTH
                            + (i * GameConst.Arm.LINK_HALF_LENGTH * 2), 0);
            linkBody.createFixture(fixtureDef);


            revoluteJointDef.initialize(prevBody, linkBody, joinSpot);
            revoluteJointDef.localAnchorB.x = 0;
            revoluteJointDef.localAnchorB.y = -GameConst.Arm.LINK_HALF_LENGTH;
            world.createJoint(revoluteJointDef).setUserData(Arm.SHAFT);

            links.add(linkBody);
            prevBody = linkBody;
            joinSpot.set(
                    linkBody.getPosition().x,
                    linkBody.getPosition().y + GameConst.Arm.LINK_HALF_LENGTH);
        }

        return links;
    }

    public static Body createHand(World world, Body lastLink) {


        BodyDef handBodyDef = new BodyDef();
        handBodyDef.type = BodyDef.BodyType.DynamicBody;

        Body handBody = world.createBody(handBodyDef);
        handBody.setTransform(
                lastLink.getPosition().x,
                lastLink.getPosition().y+ GameConst.Arm.LINK_HALF_LENGTH,0);


        CircleShape handShape = new CircleShape();
        handShape.setRadius(GameConst.Arm.HAND_RADIUS);

        FixtureDef fixtureDef = BodyFactoryUtils.getArmFixtureDef();
        fixtureDef.density=5;
        fixtureDef.shape = handShape;
        handBody.createFixture(fixtureDef).setUserData(Arm.HAND);



        RevoluteJointDef revoluteJointDef = BodyFactoryUtils.getLinkRevoluteJointDef();
        revoluteJointDef.initialize(lastLink, handBody, lastLink.getPosition().cpy().add(0,GameConst.Arm.LINK_HALF_LENGTH));

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
}
