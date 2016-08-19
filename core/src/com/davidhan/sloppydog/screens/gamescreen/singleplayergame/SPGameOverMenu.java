package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.modals.ModalBase;
import com.davidhan.sloppydog.resources.HanSkin;
import com.davidhan.sloppydog.screens.gamescreen.GameScreen;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton;

/**
 * name: SPGameOverMenu
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SPGameOverMenu extends ModalBase {
    private SinglePlayerGameLog spGameLog;
    private GameScreen gameScreen;
    public SPGameOverMenu(IApp iApp, GameScreen gameScreen, SinglePlayerGameLog spGameLog) {
        super(iApp,false);
        this.spGameLog = spGameLog;
        this.gameScreen = gameScreen;
        begin();
    }

    @Override
    protected void initContents() {
        HanLabel gameOverLabel = new HanLabel(iApp,"GAME OVER", HanSkin.LabelStyles.TITLE);
        HanLabel scoreLabel = new HanLabel(iApp,"SCORE: "+spGameLog.getScore(), HanSkin.LabelStyles.TITLE);
        HanTextButton newGameLabel = new HanTextButton(iApp,"NEW GAME",HanSkin.ButtonStyles.PRIMARY);
        newGameLabel.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.restartGame();
                close();
            }
        });

        table.add(gameOverLabel).spaceBottom(Spacing.SPACE_MD);
        table.row();
        table.add(scoreLabel).spaceBottom(Spacing.SPACE_MD);
        table.row();
        table.add(newGameLabel).fill();

    }

    @Override
    protected void onCloseComplete() {

    }

    @Override
    public boolean remove() {
        spGameLog = null;
        gameScreen = null;
        return super.remove();
    }
}
