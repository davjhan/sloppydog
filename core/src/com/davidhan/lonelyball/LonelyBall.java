package com.davidhan.lonelyball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.app.IScreen;
import com.davidhan.lonelyball.constants.Display;
import com.davidhan.lonelyball.resources.Assets;
import com.davidhan.lonelyball.screens.loadingscreen.LoadingScreen;

public class LonelyBall extends Game implements IApp{

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
        super.setScreen(screen);
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(modalStage);
        inputMultiplexer.addProcessor(((IScreen)screen).getInputProcessor());
        inputMultiplexer.addProcessor(((IScreen)screen).getStage());

    }

    protected void initInputProcessors() {
        inputMultiplexer = new InputMultiplexer(modalStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.input.setCatchBackKey(true);
    }

}
