package com.davidhan.sloppydog;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.app.IScreen;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.resources.Assets;
import com.davidhan.sloppydog.screens.loadingscreen.LoadingScreen;

public class Armball extends Game implements IApp{

	private Assets assets;
    private Stage modalStage;
    InputMultiplexer inputMultiplexer;
	@Override
	public void create () {
		assets = new Assets();

        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(1 / Display.scale());
        modalStage = new Stage(viewport);
        initInputProcessors();
		setScreen(new LoadingScreen(this));

	}

	@Override
	public void render () {
        super.render();
        modalStage.act(Gdx.graphics.getDeltaTime());
        modalStage.draw();
	}

    @Override
    public void dispose() {
        super.dispose();
        res().dispose();
    }

    @Override
	public Assets res() {
		return assets;
	}

    @Override
    public Stage modalStage() {
        return modalStage;
    }

    @Override
    public void setScreen(Screen screen) {
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(modalStage);
        inputMultiplexer.addProcessor(((IScreen)screen).getInputProcessor());
        inputMultiplexer.addProcessor(((IScreen)screen).getStage());
        super.setScreen(screen);


    }

    protected void initInputProcessors() {
        inputMultiplexer = new InputMultiplexer(modalStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);
    }

}
