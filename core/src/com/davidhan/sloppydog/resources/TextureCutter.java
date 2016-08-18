package com.davidhan.sloppydog.resources;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

/**
 * name: TextureCutter
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TextureCutter {
    public TextureRegion cutSingle(TextureAtlas atlas, String regionName){
        return atlas.findRegion(regionName);
    }
    public TextureRegion[][] cut(TextureAtlas atlas, String regionName, int width, int height){
        return atlas.findRegion(regionName).split(width,height);
    }
    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], (width/2)-1, (width/2)-1, (height/2)-1, (height/2)-1);
                count ++;
            }
        }
        return ret;
    }
    public NinePatchDrawable[] getDrawables(NinePatch[] patches){
        NinePatchDrawable[] drawables = new NinePatchDrawable[patches.length];
        for (int i = 0; i < patches.length; i++) {
            drawables[i] = new NinePatchDrawable(patches[i]);
        }
        return drawables;
    }
    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height
            ,int cornerSize){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], cornerSize,cornerSize,cornerSize,cornerSize);
                count ++;
            }
        }
        return ret;
    }
    public NinePatch[] cutNinesGroup(TextureAtlas atlas, String regionName, int width, int height
            ,int sides,int top,int bottom){

        TextureRegion[][]regs = cut(atlas,regionName,width,height);
        NinePatch[] ret = new NinePatch[regs.length*regs[0].length];
        int count = 0;
        for(int r = 0; r < regs.length; r ++) {
            for(int c = 0; c < regs[0].length; c ++) {
                ret[count] = new NinePatch(regs[r][c], sides,sides,top,bottom);
                count ++;
            }
        }
        return ret;
    }
    public TextureRegionDrawable[] getDrawables(TextureRegion[] regions){
        TextureRegionDrawable[] drawables = new TextureRegionDrawable[regions.length];
        for (int i = 0; i < regions.length; i++) {
            drawables[i] = new TextureRegionDrawable(regions[i]);
        }
        return drawables;
    }
    public TextureRegion[] cutLinear(TextureAtlas atlas, String regionName,int width, int height) {
        TextureRegion[][] regs = cut(atlas,regionName,width,height);
        int len = regs[0].length*regs.length;
        TextureRegion[] ret = new TextureRegion[len];
        for(int i = 0; i < len; i ++){
            ret[i] = regs[i/regs[0].length][i%regs[0].length];
        }
        return ret;
    }

    protected Array<TextureRegion> getFramesConsecutive(TextureRegion[] regions, int from, int count) {
        TextureRegion[] dest = new TextureRegion[count];
        for(int i = 0; i < count; i ++){
                dest[i] = regions[from + i];
        }
        return Array.with(dest);
    }


}
