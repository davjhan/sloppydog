package com.davidhan.armball.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * name: GraphicAssets
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TextureAssets extends TextureCutter {
    public static final String PACKED_ATLAS = "packed_assets.atlas";
    public NinePatch[] buttonNinePatches;
    public NinePatch[] bgNinePatches;
    public NinePatch[] listItemUnderline;
    public TextureRegion hand[][];
    public Animation handAnimation;
    public TextureRegion knob;
    public TextureRegion shaft;
    public TextureRegion[][] link;
    public TextureRegion ball;

    public TextureAssets(Assets assets){
        assets.load(PACKED_ATLAS, TextureAtlas.class);
    }

    public void prepare(Assets assets) {
        TextureAtlas atlas = assets.get(PACKED_ATLAS,TextureAtlas.class);
        bgNinePatches = cutNinesGroup(atlas,"bg-ninepatches",24,24,9);
        buttonNinePatches =cutNinesGroup(atlas,"btn-ninepatch",30,19,3,3,6);
        listItemUnderline = cutNinesGroup(atlas, "list-item-bg-nine", 12, 12, 3, 3, 3);

        hand = cut(atlas,"hand",40,50);
        knob = cutSingle(atlas,"knob");
        shaft = cutLinear(atlas,"shaft",20,18)[0];
        link = cut(atlas,"link",20,30);
        ball = cutSingle(atlas,"ball");
        Gdx.app.log("tttt TextureAssets", "sizeof: " +Array.with(hand).size);
        //handAnimation = new Animation(0.16f, Array.with(hand), Animation.PlayMode.NORMAL);
    }


}
