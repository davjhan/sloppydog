package com.davidhan.armball.screens.gamescreen.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.davidhan.armball.app.IApp;

import java.util.ArrayList;
import java.util.List;

/**
 * name: CompoundPhysicsalEntity
 * desc:
 * date: 2016-08-14
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class CompoundPhysicalEntity extends BodyEntity {
    List<Body> bodies;
    public CompoundPhysicalEntity(IApp iApp) {
        super(iApp);
        bodies = new ArrayList<Body>();
    }
    protected void addBody(Body body){
        bodies.add(body);
    }
    protected void addBody(List<Body> bodies){
        this.bodies.addAll(bodies);
    }
    protected void removeBody(Body body){
        bodies.add(body);
    }
    public void moveBodiesBy(float dx, float dy){
       for(Body body:bodies){
           body.setTransform(body.getPosition().add(dx,dy),body.getAngle());
       }
    }
    public void rotateBodiesBy(Body leader,float degrees){

        for(Body body:bodies){
                temp1.set(body.getPosition());
                temp1.set(body.getPosition());
                temp1.sub(leader.getPosition());
                temp1.rotate(degrees);
                body.setTransform(leader.getPosition().add(temp1), body.getAngle() + degrees* MathUtils.degRad);
        }
    }
    public void translateBodiesTo(Body leader, Vector2 destination){
        translateBodiesTo(leader,destination.x,destination.y);
    }
    public void translateBodiesTo(Body leader, float x, float y){
        moveBodiesBy(x-leader.getPosition().x,y-leader.getPosition().y);
    }
    public List<Body> getBodies() {
        return bodies;
    }
    protected abstract void makeBodies(World world);
}
