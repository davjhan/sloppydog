package com.davidhan.sloppydog.uireusables;

import com.davidhan.sloppydog.app.IApp;

/**
 * name: AppGameGroup
 * desc:
 * date: 2016-08-17
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class AppGameGroup extends GameGroup {
   protected IApp iApp;

    public AppGameGroup(IApp iApp) {
        super();
        this.iApp = iApp;
    }

    @Override
    public boolean remove() {
        iApp = null;
        return super.remove();
    }
}
