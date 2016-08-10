package com.davidhan.lonelyball.resources;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * name: TexturePackerManager
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TexturePackerManager {
    public static final String INPUT_DIR = "../preassets/";
    public static final String OUTPUT_DIR = "./";
    public static final String PACK_FILENAME = "packed_assets";
    public static TexturePacker.Settings settings;
    public static void packTextures(){
        settings = new TexturePacker.Settings();
        settings.silent = true;
        TexturePacker.process(settings,INPUT_DIR, OUTPUT_DIR, PACK_FILENAME);

    }
}
