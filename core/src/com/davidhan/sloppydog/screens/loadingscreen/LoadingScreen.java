package com.davidhan.sloppydog.screens.loadingscreen;

import com.badlogic.gdx.Gdx;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.screens.ScreenBase;
import com.davidhan.sloppydog.screens.homescreen.HomeScreen;

/**
 * name: LoadingScreen
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class LoadingScreen extends ScreenBase {
    public boolean dataLoaded;
    public LoadingScreen(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        dataLoaded = false;
        startLoad();
    }

    private void startLoad(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        iApp.initUser();
                        dataLoaded = true;
                        iApp.user().updateNumAppOpened();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void update(float delta) {
        if (iApp.res().update() && this.dataLoaded) {
            doneLoading();
        }
        super.update(delta);
    }

    private void doneLoading() {
        iApp.res().prepAssets();
        iApp.setScreen(new HomeScreen(iApp));
   }

}
