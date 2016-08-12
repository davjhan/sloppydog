package com.davidhan.armball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.davidhan.armball.Armball;
import com.davidhan.armball.constants.Display;
import com.davidhan.armball.resources.TexturePackerManager;

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
