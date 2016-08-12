package com.davidhan.armball.resources;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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

    public TextureAssets(Assets assets){
        assets.load(PACKED_ATLAS, TextureAtlas.class);
    }

    public void prepare(Assets assets) {
        TextureAtlas atlas = assets.get(PACKED_ATLAS,TextureAtlas.class);
        bgNinePatches = cutNinesGroup(atlas,"bg-ninepatches",24,24,9);
        buttonNinePatches =cutNinesGroup(atlas,"btn-ninepatch",30,19,3,3,6);
        listItemUnderline = cutNinesGroup(atlas, "list-item-bg-nine", 12, 12, 3, 3, 3);
    }


}
