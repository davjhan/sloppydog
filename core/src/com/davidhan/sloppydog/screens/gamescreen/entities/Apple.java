package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.AnimConst;
import com.davidhan.sloppydog.constants.GameConst;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.screens.gamescreen.GameRules;
import com.davidhan.sloppydog.screens.gamescreen.box2d.CollisionGroups;
import com.davidhan.sloppydog.utils.GameUtils;
import com.davidhan.sloppydog.utils.TextureColorer;

import java.util.Random;

/**
 * name: Target
 * desc:
 * date: 2016-08-11
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Apple extends PhysicalEntity {
    public static final String TAG = "apple";
    public static final float MIN_DIST_FROM_ROOT = 5;
    public Random random;
    Sprite shadow;
    Sprite whiteSprite;
    Action flashAction;
    boolean flash = false;
    float verticalRange;
    float horizontalRange;
    int appleNum;
    boolean isEaten;
    boolean flagRemoval = false;
    private boolean wallHit = false;

    public Apple(IApp iApp, Body body, int appleNum, Vector2 lastSpot) {
        super(iApp, body);
        initVars();
        random = new Random();
        if (appleNum == 0) {
            body.setTransform(lastSpot, 0);
        } else {
            body.setTransform(getValidSpot(lastSpot), 0);
        }
        body.setUserData(this);
        setSprite(new Sprite(iApp.res().textures.apple[0]));
        sprite.setOriginCenter();
        whiteSprite = new Sprite(TextureColorer.tintTexture(iApp.res().textures.apple[0], Colors.get(ColorNames.OFF_WHITE)));
        shadow = new Sprite(iApp.res().textures.shadow[0]);
        shadow.setOriginCenter();
        startFlash();
        startImpulse(appleNum);

    }

    private void startImpulse(int appleNum) {
        temp1.set(0,appleNum* Math.max(GameRules.MAX_APPLE_STARTING_VEL,GameRules.APPLE_VELOCITY_INC*appleNum));
        temp1.setAngle(360*random.nextFloat());
        body().applyLinearImpulse(temp1,body.getWorldCenter(),true);
        body().applyAngularImpulse(appleNum* Math.max(GameRules.MAX_APPLE_STARTING_SPIN,GameRules.APPLE_SPIN_INC*appleNum),true);
    }

    private void initVars() {
        verticalRange = (GameConst.World.HEIGHT - GameConst.World.BOTTOM_PAD - GameConst.World.TOP_PAD - GameConst.Apple.RADIUS * 2);
        horizontalRange = (GameConst.World.WIDTH - GameConst.Apple.RADIUS * 2);
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void act(float delta) {
        if(flagRemoval){
            remove();
            return;
        }
        if (isEaten) {
            if(wallHit) {
                body().getFixtureList().get(0).setFilterData(CollisionGroups.FILTER_NON_WALL_CLIP());
                if (GameUtils.isOutOfBounds(body().getPosition(), GameConst.Apple.RADIUS)) {
                    flagRemoval = true;
                }
            }
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        drawSpriteToBodyNoRot(batch, shadow, body);

        super.draw(batch, parentAlpha);
        if (flash) {
            drawSpriteToBodyNoRot(batch, whiteSprite, body);
        }
    }

    private void startFlash() {
        if (flashAction != null) {
            removeAction(flashAction);
        }
        addAction(flashAction = Actions.sequence(Actions.run(flashOnRunnable), Actions.delay(AnimConst.DUR_SUPER_SHOT), Actions.run(flashOffRunnable), Actions.delay(AnimConst.DUR_SUPER_SHOT), Actions.run(flashOnRunnable), Actions.delay(AnimConst.DUR_SUPER_SHOT), Actions.run(flashOffRunnable)));
    }
    public void getEaten(){
        isEaten = true;
        sprite.setRegion(iApp.res().textures.apple[1]);
    }

    public boolean isEaten() {
        return isEaten;

    }
    public void hitWall(){
        wallHit = true;
    }

    private Runnable flashOnRunnable = new Runnable() {
        @Override
        public void run() {
            flash = true;
        }
    };
    private Runnable flashOffRunnable = new Runnable() {
        @Override
        public void run() {
            flash = false;
        }
    };

    public Vector2 getValidSpot(Vector2 root) {
        Vector2 pos = new Vector2(
                GameConst.Apple.RADIUS + (random.nextFloat() * horizontalRange), GameConst.World.BOTTOM_PAD + GameConst.Apple.RADIUS + (random.nextFloat() * verticalRange));
        if (Vector2.dst(pos.x, pos.y, root.x, root.y) < MIN_DIST_FROM_ROOT) {
            return getValidSpot(root);
        } else {
            return pos;
        }
    }
}
