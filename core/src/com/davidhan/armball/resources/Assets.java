package com.davidhan.armball.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * name: Assets
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class Assets extends AssetManager {
    public com.davidhan.armball.resources.FontAssets fonts;
    public com.davidhan.armball.resources.TextureAssets textures;
    public Skin skin;
    public Assets(){
        super();
        ColorNames.init();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        textures = new com.davidhan.armball.resources.TextureAssets(this);
        fonts = new com.davidhan.armball.resources.FontAssets(this);
    }
    public void prepAssets() {
        textures.prepare(this);
        prepSkin();
    }

    private void prepSkin() {
        skin = new com.davidhan.armball.resources.HanSkin(this);

    }
}
