package com.bedgame.gamename;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.bedgame.gamename.game.Assets;
import com.bedgame.gamename.game.WorldController;
import com.bedgame.gamename.game.WorldRender;

/**
 * Created by Anatolii on 01.07.2015.
 */
public class MainGame implements ApplicationListener {

    private static final String TAG = MainGame.class.getName();

    private WorldController worldController;
    private WorldRender worldRender;

    private boolean paused;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Assets.instance.init(new AssetManager());
        worldController = new WorldController();
        worldRender = new WorldRender(worldController);
        paused = false;
    }

    @Override
    public void resize(int width, int height) {
        worldRender.resize(width, height);
    }

    @Override
    public void render() {
        if (!paused) {
            worldController.update(Gdx.graphics.getDeltaTime());
        }

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldRender.render();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        worldRender.dispose();
        Assets.instance.dispose();
    }
}
