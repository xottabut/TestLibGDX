package com.bedgame.gamename.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.bedgame.gamename.game.objects.Ground;
import com.bedgame.gamename.util.Constants;

/**
 * Created by Anatolii on 23.07.2015.
 */
public class Level {
    private static final String TAG = Level.class.getName();

    public Array<Ground> grounds;

    public Level() {
        init();
    }

    private void init() {
        grounds = new Array<>();
        float x = -Constants.VIEWPORT_WIDTH / 2;
        float y = -Constants.VIEWPORT_HEIGHT / 3;
        for (int i = 0; i < 15; i++) {
            Ground ground = new Ground();
            ground.position.set(x, y);
            grounds.add(ground);
            x += Ground.WIDTH + MathUtils.random(2f);
            y += MathUtils.randomSign() * MathUtils.random(1f);
        }
    }

    public void render(SpriteBatch batch) {
        for (Ground ground : grounds) {
            ground.render(batch);
        }
    }
}
