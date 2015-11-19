package com.bedgame.gamename.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.bedgame.gamename.game.Assets;

/**
 * Created by Anatolii on 16.07.2015.
 */
public class Football extends AbstractGameObject {

    private static final String TAG = Football.class.getName();

    private TextureRegion footbalRegion = Assets.instance.football.football;

    private int rotationSign;

    public Football() {
        init();
    }

    private void init() {
        dimension.set(0.5f, 0.5f);
        origin.set(dimension.x / 2, dimension.y / 2);
        rotationSign = MathUtils.randomSign();
        bounds.set(0, 0, dimension.x, dimension.y);
        scale.set(2, 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(footbalRegion, position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation);
/*
        batch.draw(footbalRegion.getTexture(), position.x + 1, position.y + 1, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                footbalRegion.getRegionX(), footbalRegion.getRegionY(),
                footbalRegion.getRegionWidth(), footbalRegion.getRegionHeight(),
                false, false);*/
    }

    @Override
    public void update(float deltaTime) {
        //position.x = (position.x + deltaTime) % 2.0f;
        rotation = (rotation + rotationSign * 90 * deltaTime) % 360;
    }

}
