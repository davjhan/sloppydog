package com.davidhan.sloppydog;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.app.IScreen;
import com.davidhan.sloppydog.backend.User;
import com.davidhan.sloppydog.constants.Display;
import com.davidhan.sloppydog.resources.Assets;
import com.davidhan.sloppydog.screens.loadingscreen.LoadingScreen;

public class Armball extends Game implements IApp{

	private Assets assets;
    private User user;
    private Stage modalStage;
    InputMultiplexer inputMultiplexer;
    private boolean modalDebugAll =false;

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
    public User user() {
        return user;
    }

    @Override
    public Stage modalStage() {
        return modalStage;
    }

    @Override
    public void initUser() {
        user = new User();
        user.init();
    }

    @Override
    public void setScreen(Screen screen) {
        inputMultiplexer.clear();
        inputMultiplexer.addProcessor(modalStage);
        inputMultiplexer.addProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if(keycode == Input.Keys.M){
                    modalDebugAll = !modalDebugAll;
                    modalStage().setDebugAll(modalDebugAll);
                }
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
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
