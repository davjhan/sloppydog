package com.davidhan.sloppydog.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.davidhan.sloppydog.Armball;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.resources.TexturePackerManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		int scale = 2;
		TexturePackerManager.packTextures();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.foregroundFPS = 10;
		config.width = Display.WIDTH*scale;
		config.height = Display.HEIGHT*scale;
		new LwjglApplication(new Armball(), config);
	}
}
