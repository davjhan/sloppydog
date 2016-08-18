package com.davidhan.sloppydog.modals;

import com.badlogic.gdx.Gdx;
import com.davidhan.sloppydog.resources.HanSkin;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.uireusables.ListItem;

/**
 * name: SettingsModal
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SettingsModal extends ModalBase {
    public SettingsModal(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void initContents() {
        HanLabel title = new HanLabel(iApp,"Settings", HanSkin.LabelStyles.TITLE);

        ListItem versionLabel = new ListItem(iApp,null,"version ","0.1",false);
        ListItem resetLabel = new ListItem(iApp, new com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener() {
            @Override
            public void onClick() {
                Gdx.app.log("tttt SettingsModal", "reset game");
            }
        }, "Reset Game");
        table.add(title).spaceBottom(com.davidhan.sloppydog.constants.Spacing.SPACE_MD);
        table.row();
        table.add(versionLabel).fillX();
        table.row();
        table.add(resetLabel).fillX();
    }
}
