package com.bedgame.gamename.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.bedgame.gamename.util.Constants;

/**
 * Created by Anatolii on 16.07.2015.
 */
public class Assets implements Disposable, AssetErrorListener {

    private static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    public Football football;
    public AssetsFonts fonts;
    public Ground ground;

    private Assets() {
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String name : assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "assets: " + name);
        }

        TextureAtlas textureAtlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for (Texture texture : textureAtlas.getTextures()) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        football = new Football(textureAtlas);
        ground = new Ground(textureAtlas);
        fonts = new AssetsFonts();
    }

    public class Football {
        public final AtlasRegion football;

        public Football(TextureAtlas atlas) {
            this.football = atlas.findRegion("football");
        }
    }

    public class Ground {
        public final AtlasRegion ground;

        public Ground(TextureAtlas atlas) {
            this.ground = atlas.findRegion("ground");
        }
    }

    public class AssetsFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public AssetsFonts() {
            defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
            defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
            defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);

            defaultSmall.getRegion().getTexture().setFilter(
                    Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(
                    Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(
                    Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultBig.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Cannot load assets with name '" + asset.fileName + "'", throwable);
    }
}
