package com.davidhan.lonelyball.modals;

import com.badlogic.gdx.Gdx;
import com.davidhan.lonelyball.app.IApp;
import com.davidhan.lonelyball.constants.Spacing;
import com.davidhan.lonelyball.resources.HanSkin;
import com.davidhan.lonelyball.uireusables.ListItem;
import com.davidhan.lonelyball.uireusables.scene2dhan.ClickListener;
import com.davidhan.lonelyball.uireusables.scene2dhan.HanLabel;

/**
 * name: SettingsModal
 * desc:
 * date: 2016-08-09
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SettingsModal extends ModalBase{
    public SettingsModal(IApp iApp) {
        super(iApp);
    }

    @Override
    protected void initContents() {
        HanLabel title = new HanLabel(iApp,"Settings", HanSkin.LabelStyles.TITLE);

        ListItem versionLabel = new ListItem(iApp,null,"version ","0.1",false);
        ListItem resetLabel = new ListItem(iApp, new ClickListener() {
            @Override
            public void onClick() {
                Gdx.app.log("tttt SettingsModal", "reset game");
            }
        }, "Reset Game");
        table.add(title).spaceBottom(Spacing.SPACE_MD);
        table.row();
        table.add(versionLabel).fillX();
        table.row();
        table.add(resetLabel).fillX();
    }
}
