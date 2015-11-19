package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.bedgame.gamename.MainGame;

public class DesktopLauncher {

    private static boolean rebuildAtlas = false;
    private static boolean drawDebugOutline = true;

    public static void main(String[] arg) {

        if (rebuildAtlas) {
            Settings settings = new Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.duplicatePadding = false;
            settings.debug = drawDebugOutline;
            TexturePacker.process(settings, "desktop/assets-raw/images", "android/assets/images",
                    "test.pack");
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new MainGame(), config);
    }
}
