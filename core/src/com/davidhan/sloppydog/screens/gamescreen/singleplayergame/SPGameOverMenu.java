package com.davidhan.sloppydog.screens.gamescreen.singleplayergame;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.davidhan.sloppydog.app.IApp;
import com.davidhan.sloppydog.constants.Spacing;
import com.davidhan.sloppydog.modals.ModalBase;
import com.davidhan.sloppydog.resources.ColorNames;
import com.davidhan.sloppydog.resources.HanSkin;
import com.davidhan.sloppydog.screens.gamescreen.GameScreen;
import com.davidhan.sloppydog.uireusables.scene2dhan.ClickListener;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanLabel;
import com.davidhan.sloppydog.uireusables.scene2dhan.HanTextButton;
import com.davidhan.sloppydog.uireusables.scene2dhan.IconAndLabel;

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
    HanLabel roundScoreLabel;
    HanLabel recordScoreLabel;
    HanLabel totalApplesLabel;

    public SPGameOverMenu(IApp iApp, GameScreen gameScreen, SinglePlayerGameLog spGameLog) {
        super(iApp, false);
        this.spGameLog = spGameLog;
        this.gameScreen = gameScreen;

        begin();
        setTableFullWidth(table);
    }

    @Override
    protected void initContents() {
        HanLabel gameOverLabel = new HanLabel(iApp, "GAME OVER", HanSkin.LabelStyles.TITLE);
        HanTextButton newGameLabel = new HanTextButton(iApp, "NEW GAME", HanSkin.ButtonStyles.PRIMARY);
        newGameLabel.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                gameScreen.restartGame();
                close();
            }
        });

        Table scoreAndTotalTable = new Table();
        scoreAndTotalTable.add(getScoreTable()).fillX().spaceRight(Spacing.SMALL);
        scoreAndTotalTable.add(getRightSideTable()).expandX().fill();
        scoreAndTotalTable.pack();

        table.add(gameOverLabel).spaceBottom(Spacing.LARGE).expandX();
        table.row();
        table.add(scoreAndTotalTable).expandX().fillX();
        table.row();
        table.add(newGameLabel).spaceTop(Spacing.LARGE).height(32).fillX();
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

    public Table getScoreTable() {
        Table scoreTable = new Table();
        recordScoreLabel = new HanLabel(iApp, "RECORD: " + spGameLog.getScore(), HanSkin.LabelStyles.TITLE);
        roundScoreLabel = new HanLabel(iApp, String.valueOf(spGameLog.getScore()), HanSkin.LabelStyles.EXTRABIG);
        HanLabel titleLabelText =   new HanLabel(iApp, "SCORE:", HanSkin.LabelStyles.TITLE);
        titleLabelText.setFontColor(Colors.get(ColorNames.BTN_REGULAR_TEXT));
        roundScoreLabel.setFontColor(Colors.get(ColorNames.OFF_WHITE));

        Table roundScoreGroup = new Table();
        roundScoreGroup.add(
                new IconAndLabel(new Image(iApp.res().textures.appleIcon),
                        titleLabelText)
        ).spaceBottom(Spacing.SMALL);
        roundScoreGroup.center();
        roundScoreGroup.pad(Spacing.REG);
        roundScoreGroup.setBackground(new NinePatchDrawable(iApp.res().textures.bgNinePatches[2]));
        roundScoreGroup.row();
        roundScoreGroup.add(roundScoreLabel).height(34);
        roundScoreGroup.pack();

        Table records = new Table();
        records.pad(Spacing.REG);
        records.add(recordScoreLabel);
        records.setBackground(new NinePatchDrawable(iApp.res().textures.bgNinePatches[1]));

        scoreTable.add(roundScoreGroup).expandX().center().expandX().fillX();
        scoreTable.row();
        scoreTable.add(records).spaceTop(Spacing.SMALL).center().expandX().fillX();
        return scoreTable;
    }

    public Table getTotalsTable() {
        Table table = new Table();
        table.top();
        HanLabel lifetimeLabel = new HanLabel(iApp, "LIFETIME:", HanSkin.LabelStyles.TITLE);
        lifetimeLabel.setFontColor(Colors.get(ColorNames.NEAR_BLACK));
//        IconAndLabel titleLabel =   new IconAndLabel(new Image(iApp.res().textures.appleIcon),
//                new HanLabel(iApp, "LIFETIME:", HanSkin.LabelStyles.TITLE)
//                );

        totalApplesLabel = new HanLabel(iApp, "0000[#"+ColorNames.NEAR_BLACK+"ff]0", HanSkin.LabelStyles.BIG);
        totalApplesLabel.setFontColor(Colors.get(ColorNames.TEXT_DIM));
        totalApplesLabel.setAlignment(Align.right);
        table.pad(Spacing.REG);
        table.setBackground(new NinePatchDrawable(iApp.res().textures.bgNinePatches[1]));
        table.add(lifetimeLabel).right().expandX().spaceBottom(Spacing.SMALL).colspan(2);
        table.row();
       // table.add(new Image(iApp.res().textures.appleIcon)).left();
        table.add(totalApplesLabel).fillX().right();
        table.pack();
        return table;
    }
    public Table getLevelTable() {
        Table table = new Table();
        table.top();
        IconAndLabel titleLabel =   new IconAndLabel(new Image(iApp.res().textures.appleIcon),
                new HanLabel(iApp, "LEVEL 1", HanSkin.LabelStyles.TITLE));
        table.pad(Spacing.REG);
        table.setBackground(new NinePatchDrawable(iApp.res().textures.bgNinePatches[1]));
        table.add( new HanLabel(iApp, "LEVEL 1", HanSkin.LabelStyles.TITLE)).expandX().fillX().top();
        table.row();
        table.pack();
        return table;
    }

    public Table getRightSideTable() {
        Table table = new Table();
        table.add(getTotalsTable()).fill();
        table.row();
        table.add(getLevelTable()).spaceTop(Spacing.SMALL).fill().expand();
        table.pack();
        return table;
    }
}
