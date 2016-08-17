package com.davidhan.armball.screens.gamescreen.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.davidhan.armball.screens.gamescreen.aigame.AIGame;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.Ball;
import com.davidhan.armball.screens.gamescreen.entities.BodyEntity;
import com.davidhan.armball.screens.gamescreen.entities.PhysicalEntity;

/**
 * name: GameContactListener
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class AIGameContactListener implements ContactListener {
    AIGame gameScreen;
    BodyEntity beATemp;
    BodyEntity beBTemp;
    public AIGameContactListener(AIGame gameScreen){
        this.gameScreen = gameScreen;
    }
    @Override
    public void beginContact(Contact contact) {
      //  PhysicalEntity a = (PhysicalEntity)contact.getFixtureA().getBody().getUserData();
      //  PhysicalEntity b = (PhysicalEntity)contact.getFixtureB().getBody().getUserData();
        if(oneFixtureIsTag(contact,Arm.HAND)){
            Gdx.app.log("tttt AIGameContactListener", "one is hand");
        }
        if(between(contact, Arm.TAG, Ball.TAG)){
            if(oneFixtureIsTag(contact,Arm.HAND)){
                Gdx.app.log("tttt AIGameContactListener", "hit BALL AND HAND");
                Arm arm = (Arm) getEntityByTag(contact,Arm.TAG);
                Ball ball = (Ball) getEntityByTag(contact,Ball.TAG);
                if(!ball.isGrabbed()) {
                    arm.requestGrab(ball);
                }
                //gameScreen.incrementScore();
            }
        }
        if(oneFixtureIsTag(contact, Arm.HAND)){
            Arm arm= (Arm) getEntityByTag(contact,Arm.TAG);
            //Gdx.app.log("tttt GameContactListener", "vel len "+arm.getHand().getLinearVelocity().len2());
            if(arm.getHand().getLinearVelocity().len2() > 20*20){
                Gdx.app.log("tttt GameContactListener", "shake: ");
                gameScreen.shakeScreen(4);
            }
        }

    }

    private boolean oneFixtureIsTag(Contact contact, String tag) {
        return (contact.getFixtureA().getUserData() != null
                && contact.getFixtureA().getUserData().equals(tag))
                || (contact.getFixtureB().getUserData() != null
                && contact.getFixtureB().getUserData().equals(tag));
    }
    private boolean oneBodyIsTag(PhysicalEntity a, PhysicalEntity b, String tag) {
        return a.getTag().equals(tag) || b.getTag().equals(tag);
    }
    private BodyEntity getEntityByTag(Contact contact, String tag) {
        beATemp = (BodyEntity)contact.getFixtureB().getBody().getUserData();
        beBTemp = (BodyEntity)contact.getFixtureA().getBody().getUserData();
        return beATemp.getTag().equals(tag)?beATemp: beBTemp;
    }
    private Fixture getFixtureByTag(Contact contact, String tag) {
        return contact.getFixtureA().getUserData().equals(tag)? contact.getFixtureA(): contact.getFixtureB();
    }

    private boolean between(Contact contact,String tag1, String tag2) {
        if(contact.getFixtureA().getBody().getUserData() == null || contact.getFixtureB().getBody().getUserData() == null){
            return false;
        }
        beATemp = (BodyEntity)contact.getFixtureB().getBody().getUserData();
        beBTemp = (BodyEntity)contact.getFixtureA().getBody().getUserData();
        return((beATemp.getTag().equals(tag1) && beBTemp.getTag().equals(tag2)) ||
                (beATemp.getTag().equals(tag2) && beBTemp.getTag().equals(tag1)));
    }

    @Override
    public void endContact(Contact contact) {


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if(between(contact,Arm.TAG,Arm.TAG)){

              Arm armA = (Arm)contact.getFixtureA().getBody().getUserData();
              Arm armB = (Arm)contact.getFixtureB().getBody().getUserData();
            if(armA == armB){
                if(oneFixtureIsTag(contact,Arm.HAND)){
                    contact.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
