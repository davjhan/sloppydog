package com.davidhan.lonelyball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.app.IScreen;
import com.davidhan.lonelyball.constants.Display;

/**
 * name: ScreenBase
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class ScreenBase implements Screen,IScreen, InputProcessor {
    protected Stage stage;
    protected IApp iApp;
    

    public ScreenBase(IApp iApp) {
        this.iApp = iApp;

        //  OrthographicCamera camera = new OrthographicCamera(Vis.WIDTH,Vis.HEIGHT);
        //    camera.zoom = 0.5f;
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(1 / Display.scale());
        stage = new Stage(viewport);
        begin();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            onBackPressed();
        }
        return false;
    }

    protected void onBackPressed() {


    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public InputProcessor getInputProcessor() {
        return this;
    }

    protected abstract void begin();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.act(delta);
        stage.draw();
    }

    protected void update(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        iApp = null;
    }

    //Input Processor:
    @Override
    public boolean keyUp(int keycode) {
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
}
