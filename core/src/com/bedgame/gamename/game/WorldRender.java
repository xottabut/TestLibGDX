package com.bedgame.gamename.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.bedgame.gamename.util.Constants;

/**
 * Created by Anatolii on 01.07.2015.
 */
public class WorldRender implements Disposable {

    private OrthographicCamera camera;
    private OrthographicCamera cameraGUI;
    private SpriteBatch batch;
    private WorldController worldController;

    public WorldRender(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        cameraGUI.position.set(0, 0, 0);
        cameraGUI.setToOrtho(true);
        cameraGUI.update();
    }

    public void render() {
        renderWorld(batch);
        renderGui(batch);
    }

    private void renderWorld(SpriteBatch batch) {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        worldController.level.render(batch);
        worldController.football.render(batch);
        batch.end();
    }

    private void renderGui(SpriteBatch batch) {
        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();
        renderGuiFpsCounter(batch);
        renderCoordinates(batch);
        batch.end();
    }

    private void renderCoordinates(SpriteBatch batch) {
        BitmapFont font = Assets.instance.fonts.defaultNormal;
        font.setColor(0, 1, 0, 1);
        font.draw(batch,
                "Coordinats: " + worldController.level.grounds.get(1).position.x + ":" + worldController.level.grounds.get(1).position.y,
                cameraGUI.viewportWidth / 2,
                cameraGUI.viewportHeight / 2);
    }

    private void renderGuiFpsCounter(SpriteBatch batch) {
        float x = cameraGUI.viewportWidth - 55 * 2;
        float y = cameraGUI.viewportHeight - 15 * 2;
        int fps = Gdx.graphics.getFramesPerSecond();
        BitmapFont fpsFont = Assets.instance.fonts.defaultBig;
        if (fps >= 45) {
            fpsFont.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
            fpsFont.setColor(1, 1, 0, 1);
        } else {
            fpsFont.setColor(1, 0, 0, 1);
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y);
        fpsFont.setColor(1, 1, 1, 1);
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();
        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT / height) * width;
        cameraGUI.position.set(cameraGUI.viewportWidth / 2, cameraGUI.viewportHeight / 2, 0);
        cameraGUI.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
