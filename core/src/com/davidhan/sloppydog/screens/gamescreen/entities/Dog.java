package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.screens.gamescreen.box2d.DogBodyFactory;

import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

import java.util.ArrayList;
import java.util.List;

/**
 * name: Arm
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Dog extends CompoundPhysicalEntity {
    public static final String TAG = "dog";
    public static final String SHAFT = "dog_shaft";
    public static final String HEAD = "dog_head";
    public static final String ARM = "dog_arm";
    public static final Object TAIL = "dog_tail";


    Body head;
    Body tail;
    Sprite tailSprite;
    Sprite armSprite;
    Sprite pawSprite;
    Sprite torsoLinkSprite;
    AnimatedSprite headIdle;
    AnimatedSprite headChomping;
    AnimatedSprite headSprite;
    List<Body> links;
    List<Sprite> shaftSprites;
    List<Sprite> shaftOutlineSprites;
    List<Body> frontTopArm;
    List<Body> frontBotArm;
    List<Body> backTopArm;
    List<Body> backBotArm;
    Vector2  headOrigin;
    private Body target;
    boolean running;
    int flagGrow = 0;
    boolean dead = false;
    int playerNum;

    public Dog(IApp iApp, World world, int playerNum) {
        super(iApp);
        makeBodies(world);
        this.playerNum = playerNum;
        initAnimations();
        initSprites();

    }

    private void initAnimations() {
        headIdle = new AnimatedSprite(iApp.res().textures.dogHeadIdle);
        headChomping = new AnimatedSprite(iApp.res().textures.dogHeadChomp);

        headOrigin = new Vector2(
                iApp.res().textures.dogHeadFrames[0][0].getRegionWidth() / 2 + 14,
                iApp.res().textures.dogHeadFrames[0][0].getRegionHeight() / 2);
        headIdle.setOrigin(headOrigin.x, headOrigin.y);
        headChomping.setOrigin(headOrigin.x, headOrigin.y);

    }

    private void initSprites() {
        shaftSprites = new ArrayList<Sprite>();


        shaftOutlineSprites = new ArrayList<Sprite>();
//        for (int i = 0; i < links.size(); i++) {
//            Sprite shaftSprite = new Sprite(iApp.res().textures.link[playerNum][1]);
//            Sprite shaftOutlineSprite = new Sprite(iApp.res().textures.link[playerNum][0]);
//            shaftSprite.setOriginCenter();
//            shaftOutlineSprite.setOriginCenter();
//            shaftSprites.add(shaftSprite);
//            shaftOutlineSprites.add(shaftOutlineSprite);
//        }
        torsoLinkSprite = new Sprite(iApp.res().textures.dogLink[0]);
        pawSprite= new Sprite(iApp.res().textures.dogArm[0][1]);

        armSprite = new Sprite(iApp.res().textures.dogArm[0][0]);
        headSprite = headIdle;
        tailSprite = new Sprite(iApp.res().textures.dogTail[0]);
        tailSprite.setOriginCenter();
    }


    @Override
    protected void makeBodies(World world) {
        links = DogBodyFactory.createDogBody(world, this);
        head = DogBodyFactory.createHead(world, links.get(0));
        tail = DogBodyFactory.createTail(world, links.get(links.size()-1));

        frontTopArm = DogBodyFactory.createArms(world, links.get(1), this);
        rotateBodiesBy(frontTopArm,frontTopArm.get(0),260);
        translateBodiesTo(frontTopArm, frontTopArm.get(0), links.get(1).getPosition());


        backTopArm = DogBodyFactory.createArms(world, links.get(1), this);
        rotateBodiesBy(backTopArm,backTopArm.get(0),280);
        translateBodiesTo(backTopArm, backTopArm.get(0), links.get(1).getPosition());


        frontBotArm = DogBodyFactory.createArms(world, tail, this);
        rotateBodiesBy(frontBotArm, frontBotArm.get(0),260);
        translateBodiesTo(frontBotArm, frontBotArm.get(0), tail.getPosition());

        backBotArm = DogBodyFactory.createArms(world, tail, this);
        rotateBodiesBy(backBotArm, backBotArm.get(0),280);
        translateBodiesTo(backBotArm, backBotArm.get(0), tail.getPosition());

        // frontTopArm =
        addBody(head);
        addBody(links);
        addBody(frontTopArm);
        addBody(frontBotArm);
        addBody(backBotArm);
        addBody(backTopArm);
        addBody(tail);
        head.setLinearDamping(0.1f);
        head.setUserData(this);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        drawChain(batch, backTopArm, armSprite,pawSprite);
        drawChain(batch, backBotArm, armSprite,pawSprite);
        drawChain(batch, links, torsoLinkSprite);

        // drawLink(batch, parentAlpha, shaftSprites);
        drawSpriteToBody(batch, headSprite, head,-90);
        drawSpriteToBody(batch, tailSprite,tail);
        drawChain(batch, frontTopArm, armSprite,pawSprite);
        drawChain(batch, frontBotArm, armSprite,pawSprite);
    }

    private void drawChain(Batch batch, List<Body> bodies, Sprite normal,Sprite last) {
        for (int i = 0; i < bodies.size(); i++) {
            if(i == bodies.size()-1){

                drawSpriteToBody(batch, last, bodies.get(i));
            }else{
                drawSpriteToBody(batch, normal, bodies.get(i));
            }
        }
    }
    private void drawChain(Batch batch, List<Body> bodies, Sprite normal) {
        drawChain(batch,bodies,normal,normal);
    }

    private void drawLink(Batch batch, float parentAlpha, List<Sprite> sprites) {
        for (int i = 0; i < links.size(); i++) {
            Body linkBody = links.get(i);
            Sprite shaftSprite = sprites.get(i);

            shaftSprite.setCenter(
                    linkBody.getPosition().x * GameConst.World.SCALE,
                    linkBody.getPosition().y * GameConst.World.SCALE
            );
            shaftSprite.setRotation((float) Math.toDegrees(linkBody.getAngle()));
            shaftSprite.draw(batch, parentAlpha);
        }
    }

    @Override
    public String getTag() {
        return TAG;
    }


    public List<Body> getLinks() {
        return links;
    }

    @Override
    public void act(float delta) {
        if(!dead) {
            if (target == null) {
                setRunning(false);
            }
            if (running) {
                goToLocation(target.getPosition(), GameConst.Arm.IMPULSE_POWER);
                setHeadSprite(headChomping);
            } else {
                setHeadSprite(headIdle);
            }
            if(flagGrow > 0){
                grow();
            }
        }
    }

    public void setTarget(Body target) {
        this.target = target;
    }


    public void move(int dire) {
        Vector2 vec = new Vector2(0, GameConst.Dog.IMPULSE_POWER);
        vec.setAngle(90 - 30 * dire);
        head.applyLinearImpulse(vec, head.getWorldCenter(), true);
//        hand.applyAngularImpulse(20*-dire,true);
//        tunnelUp();
    }

    public void face(Vector2 location) {
        face(head, location);
    }

    public void face(Body body, Vector2 location) {
        Vector2 vec = new Vector2(location);
        vec.sub(body.getPosition());
        float deltaRad = vec.angleRad() - (90 * MathUtils.degRad) - body.getAngle();
        float intensity = Math.min(Math.abs(deltaRad) / (90 * MathUtils.degRad), 1);

        Gdx.app.log("tttt Arm", "angle: " + vec.angleRad() * MathUtils.radDeg);
        Gdx.app.log("tttt Arm", "deltaRad: " + deltaRad * MathUtils.radDeg);
        Gdx.app.log("tttt Arm", "intensity: " + intensity);
        Gdx.app.log("tttt Arm", "-------------------");
        body.applyAngularImpulse(Math.signum(deltaRad) * intensity * 30, true);
    }

    public void goToLocation(Vector2 location, float power) {
        if (getLinkBottomY(links.size() / 3) < 0.1f) {
            tail.applyLinearImpulse(new Vector2(0, 30), tail.getWorldCenter(), true);
        } else if (getLinkBottomY(links.size() / 3) > com.davidhan.sloppydog.constants.Display.WORLD_HEIGHT + 0.1f) {
            tail.applyLinearImpulse(new Vector2(0, -30), tail.getWorldCenter(), true);
        } else {
            goToLocation(head, location, power);
        }
    }

    public void goToLocation(Body body, Vector2 location, float power) {
        Vector2 vec = new Vector2(location);
        vec.sub(body.getPosition());
        vec.setLength(power);
        body.applyLinearImpulse(vec, body.getWorldCenter(), true);

    }

    public void rotateBodiesBy(float degrees) {
        super.rotateBodiesBy(head, degrees);
    }

    public void translateBodiesTo(float x, float y) {
        super.translateBodiesTo(head, x, y);
    }

    public Body getHead() {
        return head;
    }

    public float getLinkBottomY(int linkNum) {
        Body link = links.get(linkNum);
        return link.getWorldPoint(temp1.set(0, -GameConst.Dog.Torso.RADIUS - GameConst.Dog.Torso.LINK_HALF_LENGTH)).y;
    }

    public boolean isFullyInArena() {
        return tail.getWorldPoint(temp1.set(0, -GameConst.Dog.Torso.RADIUS - GameConst.Dog.Torso.LINK_HALF_LENGTH)).y - 0.1f > 0;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public Body getTail() {
        return tail;
    }

    public void die(){
        dead = true;
        headSprite = new AnimatedSprite(iApp.res().textures.dogHeadDead);
        headSprite.setOrigin(headOrigin.x,headOrigin.y);
        torsoLinkSprite = new Sprite(iApp.res().textures.dogLink[1]);
        pawSprite= new Sprite(iApp.res().textures.dogArm[1][1]);
        armSprite = new Sprite(iApp.res().textures.dogArm[1][0]);
        tailSprite = new Sprite(iApp.res().textures.dogTail[1]);
    }

    public void flagGrow(int amount){
        flagGrow += amount;
    }
    public void grow(){
        //  tail.getWorld().destroyJoint(tail.getJointList().get(0));
        Gdx.app.log("tttt Dog", "at grow()");
        for(JointEdge jointEdge:tail.getJointList()){
            if(jointEdge.other == links.get(links.size()-1)){
                Gdx.app.log("tttt Dog", "destroy");
                tail.getWorld().destroyJoint(jointEdge.joint);
                break;
            }
        }

        links.add(DogBodyFactory.extendTorso(tail.getWorld(),links.get(links.size()-1),tail,this));
        flagGrow --;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setHeadSprite(AnimatedSprite headSprite) {
        if (headSprite == this.headSprite) {
            return;
        }
        this.headSprite = headSprite;
        // this.headSprite.setTime(0);
        this.headSprite.play();
    }
}
