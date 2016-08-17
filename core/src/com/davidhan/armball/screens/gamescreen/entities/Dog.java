package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.davidhan.armball.app.IApp;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.constants.GameConst;
import com.davidhan.armball.screens.gamescreen.box2d.BodyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * name: Arm
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Dog extends CompoundPhysicalEntity implements Grabber{
    public static final String TAG = "arm";
    public static final String SHAFT = "arm_shaft";
    public static final String HAND = "arm_hand";


    Body hand;
    Body tail;
    Sprite handSprite;
    List<Body> links;
    List<Sprite> shaftSprites;
    List<Sprite> shaftOutlineSprites;
    Grabbable grabRequestedBody;
    Grabbable grabbed;
    Joint grabbedJoint;
    int playerNum;

    public Dog(IApp iApp, World world, int playerNum) {
        super(iApp);
        makeBodies(world);
        this.playerNum = playerNum;
        initSprites();

    }

    private void initSprites() {
        shaftSprites = new ArrayList<Sprite>();

        shaftOutlineSprites = new ArrayList<Sprite>();
        for (int i = 0; i < links.size(); i++) {
            Sprite shaftSprite = new Sprite(iApp.res().textures.link[playerNum][1]);
            Sprite shaftOutlineSprite = new Sprite(iApp.res().textures.link[playerNum][0]);
            shaftSprite.setOriginCenter();
            shaftOutlineSprite.setOriginCenter();
            shaftSprites.add(shaftSprite);
            shaftOutlineSprites.add(shaftOutlineSprite);
        }
        handSprite = new Sprite(iApp.res().textures.hand[playerNum][0]);
      //  handSprite.play();
        handSprite.setOriginCenter();
        handSprite.setOrigin(handSprite.getOriginX(), handSprite.getOriginY() - 5);
    }


    @Override
    protected void makeBodies(World world) {
        links = BodyFactory.createArm(world);
        hand = BodyFactory.createHand(world, links.get(0));

        addBody(hand);
        addBody(links);

        for (Body l : links) {

            l.setUserData(this);
            l.setLinearDamping(1);

        }
        hand.setLinearDamping(1);
        hand.setUserData(this);
        tail = links.get(links.size() - 1);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        drawLink(batch, parentAlpha, shaftOutlineSprites);
        drawLink(batch, parentAlpha, shaftSprites);
        drawSpriteToBody(batch, handSprite, hand);
    }


    private void drawLink(Batch batch, float parentAlpha, List<Sprite> sprites) {
        for (int i = 0; i < links.size(); i++) {
            Body linkBody = links.get(i);
            Sprite shaftSprite = sprites.get(i);

            shaftSprite.setCenter(
                    linkBody.getPosition().x * Display.WORLDSCALE,
                    linkBody.getPosition().y * Display.WORLDSCALE
            );
            shaftSprite.setRotation((float) Math.toDegrees(linkBody.getAngle()));
            shaftSprite.draw(batch, parentAlpha);
        }
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public boolean isGrabbing() {
        return grabbed != null;
    }

    @Override
    public Grabbable getGrabbing() {
        return grabbed;
    }

    @Override
    public void releaseGrabbed() {
        if(grabbed != null) {
            temp1.set(0,GameConst.Arm.HAND_RADIUS+grabbed.getRadius()+0.5f);
            temp2.set(hand.getWorldPoint(temp1));
            grabbed.body().setTransform(temp2,grabbed.body().getAngle());
            grabbed.beReleased();
            grabbed = null;
            hand.getWorld().destroyJoint(grabbedJoint);
        }
    }


    public List<Body> getLinks() {
        return links;
    }

    @Override
    public void act(float delta) {
        if(grabRequestedBody != null){
            grab(grabRequestedBody);
        }
        if(isGrabbing()){
            handSprite.setRegion(iApp.res().textures.hand[playerNum][1]);
        }else{
            handSprite.setRegion(iApp.res().textures.hand[playerNum][0]);
        }
    }


    public void move(int dire) {
        Vector2 vec = new Vector2(0, GameConst.Arm.IMPULSE_POWER);
        vec.setAngle(90 - 30 * dire);
        hand.applyLinearImpulse(vec, hand.getWorldCenter(), true);
//        hand.applyAngularImpulse(20*-dire,true);
//        tunnelUp();
    }

    public void face(Vector2 location) {
        face(hand,location);
    }
    public void face(Body body,Vector2 location) {
        Vector2 vec = new Vector2(location);
        vec.sub(body.getPosition());
        float deltaRad = vec.angleRad()-(90*MathUtils.degRad)-body.getAngle();
        float intensity = Math.min(Math.abs(deltaRad)/ (90*MathUtils.degRad),1);

        Gdx.app.log("tttt Arm", "angle: " +vec.angleRad()*MathUtils.radDeg);
        Gdx.app.log("tttt Arm", "deltaRad: " +deltaRad*MathUtils.radDeg);
        Gdx.app.log("tttt Arm", "intensity: " +intensity);
        Gdx.app.log("tttt Arm", "-------------------");
        body.applyAngularImpulse(  Math.signum(deltaRad)*intensity*30,true);
    }
    public void goToLocation(Vector2 location,float power) {
        if (getLinkBottomY(links.size() / 3) < 0.1f) {
            tail.applyLinearImpulse(new Vector2(0, 30), tail.getWorldCenter(), true);
        }else if (getLinkBottomY(links.size() / 3) > Display.WORLD_HEIGHT+0.1f) {
            tail.applyLinearImpulse(new Vector2(0, -30), tail.getWorldCenter(), true);
        }else {
            goToLocation(hand, location,power);
        }
    }
    public void goToLocation(Body body,Vector2 location,float power) {
            Vector2 vec = new Vector2(location);
            vec.sub(body.getPosition());
            vec.setLength(power);
            body.applyLinearImpulse(vec, body.getWorldCenter(), true);

    }

    public void rotateBodiesBy(float degrees) {
        super.rotateBodiesBy(hand, degrees);
    }

    public void translateBodiesTo(float x, float y) {
        super.translateBodiesTo(hand, x, y);
    }

    public void goForward(){
        Vector2 vec = new Vector2(0, GameConst.Arm.IMPULSE_POWER);
        vec.setAngleRad(hand.getAngle()+90*MathUtils.degRad);
        hand.applyLinearImpulse(vec, hand.getWorldCenter(), true);
    }
    public Body getHand() {
        return hand;
    }

    public float getLinkBottomY(int linkNum) {
        Body link = links.get(linkNum);
        return link.getWorldPoint(temp1.set(0, -GameConst.Arm.ARM_RADIUS - GameConst.Arm.LINK_HALF_LENGTH)).y;
    }

    public boolean isFullyInArena() {
        return tail.getWorldPoint(temp1.set(0, -GameConst.Arm.ARM_RADIUS - GameConst.Arm.LINK_HALF_LENGTH)).y - 0.1f > 0;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public Body getTail() {
        return tail;
    }
    public void requestGrab(Grabbable grabbable) {
        grabRequestedBody = grabbable;
    }
    private void grab(Grabbable grabbable) {
        WeldJointDef jointDef = new WeldJointDef ();


        jointDef.initialize(hand, grabbable.body(), grabbable.body().getPosition());
        jointDef.localAnchorA.set(0,GameConst.Arm.HAND_RADIUS);
        grabbable.beGrabbedBy(this);
        grabbed = grabbable;
        grabbable.body().setTransform(hand.getWorldPoint(jointDef.localAnchorA),grabbable.body().getAngle());
        grabbedJoint = hand.getWorld().createJoint(jointDef);

        grabRequestedBody = null;
    }
}
