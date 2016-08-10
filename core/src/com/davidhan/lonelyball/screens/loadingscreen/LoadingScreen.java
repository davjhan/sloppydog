package com.davidhan.lonelyball.screens.loadingscreen;

import com.badlogic.gdx.Gdx;
import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.screens.ScreenBase;
import com.davidhan.lonelyball.screens.homescreen.HomeScreen;

/**
 * name: LoadingScreen
 * desc:
 * date: 2016-08-08
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class LoadingScreen extends ScreenBase {
    private boolean dataLoaded = true;
    public LoadingScreen(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void begin() {
        Gdx.app.log("tttt LoadingScreen", "at begin()");
        startLoad();
    }

    private void startLoad() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final SavedData loadedSaveData = iApp.dataResolver().loadInThread();
//                Gdx.app.log("tttt LoadingScreen", "loaded done");
//                Gdx.app.postRunnable(new Runnable() {
//                    @Override
//                    public void run() {
//                        iApp.setUserData(new UserData(iApp.dataResolver(),iApp.getActionResolver(), iApp.analytics(), loadedSaveData));
//                        dataLoaded = true;
//                    }
//                });
//            }
//        }).start();
    }

    @Override
    protected void update(float delta) {
        if (iApp.res().update() && dataLoaded) {
            doneLoading();
        }

        super.update(delta);
    }

    private void doneLoading() {
        iApp.res().prepAssets();
        iApp.setScreen(new HomeScreen(iApp));
    }

}
