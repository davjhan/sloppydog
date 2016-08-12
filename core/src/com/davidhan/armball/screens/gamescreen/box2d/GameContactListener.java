package com.davidhan.armball.screens.gamescreen.box2d;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.davidhan.armball.screens.gamescreen.GameScreen;
import com.davidhan.armball.screens.gamescreen.entities.Arm;
import com.davidhan.armball.screens.gamescreen.entities.BodyEntity;
import com.davidhan.armball.screens.gamescreen.entities.PhysicalEntity;
import com.davidhan.armball.screens.gamescreen.entities.Target;

/**
 * name: GameContactListener
 * desc:
 * date: 2016-08-10
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameContactListener implements ContactListener {
    GameScreen gameScreen;
    BodyEntity beATemp;
    BodyEntity beBTemp;
    public GameContactListener(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }
    @Override
    public void beginContact(Contact contact) {
      //  PhysicalEntity a = (PhysicalEntity)contact.getFixtureA().getBody().getUserData();
      //  PhysicalEntity b = (PhysicalEntity)contact.getFixtureB().getBody().getUserData();
        if(between(contact, Arm.TAG, Target.TAG)){
            if(oneFixtureIsTag(contact,Arm.HAND)){
                Target target = (Target) getEntityByTag(contact,Target.TAG);
                target.flagForReposition();
                gameScreen.incrementScore();
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
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
