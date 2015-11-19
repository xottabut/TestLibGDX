package com.bedgame.gamename.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bedgame.gamename.game.Assets;

/**
 * Created by Anatolii on 23.07.2015.
 */
public class Ground extends AbstractGameObject {

    private static final String TAG = Ground.class.getName();

    public static final float WIDTH = 3.0f;

    private TextureRegion region = Assets.instance.ground.ground;

    public Ground() {
        super();
        init();
    }

    private void init(){
        aspectRatio = region.getRegionWidth() / region.getRegionHeight();
        dimension.set(WIDTH, WIDTH / aspectRatio);
        bounds.set(0, 0, dimension.x, dimension.y);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(region, position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation);

    }
}
