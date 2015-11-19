package com.bedgame.gamename.game;

import com.bedgame.gamename.game.objects.Football;
import com.bedgame.gamename.util.CameraHelper;

/**
 * Created by Anatolii on 01.07.2015.
 */
public class WorldController {
    public static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public Football football;
    public Level level;

    public WorldController() {
        cameraHelper = new CameraHelper();
        init();
    }

    public void init() {
        football = new Football();
        initLevel();
    }

    private void initLevel() {
        level = new Level();
    }

    public void update(float deltaTime) {
        cameraHelper.update(deltaTime);
        football.update(deltaTime);
    }

}