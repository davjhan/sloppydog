package com.davidhan.sloppydog.screens.gamescreen.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.davidhan.sloppydog.app.IApp;

import java.util.Random;

/**
 * name: BgGrass
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class BgGrass extends BaseEntity {
    public static final int TILESIZE = 15;
    public static final float BLANK_CHANCE = 0.6f;
    public Random random;
    Sprite[][] tiles;

    public BgGrass(IApp iApp) {
        super(iApp);
        random = new Random();
        makeTiles();
    }

    private void makeTiles() {
        tiles = new Sprite[34][18];
        int tileIndex= 0;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                if(random.nextFloat() < BLANK_CHANCE){
                    tileIndex = 0;
                }else{
                    tileIndex = random.nextInt(iApp.res().textures.grassTiles.length-1)+1;
                }
                tiles[row][col] = new Sprite(iApp.res().textures.grassTiles[tileIndex]);
                tiles[row][col].setPosition(TILESIZE*col,TILESIZE*row);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                tiles[row][col].draw(batch);
            }
        }
    }
}
