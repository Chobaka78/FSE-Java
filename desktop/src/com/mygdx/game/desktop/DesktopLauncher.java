package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Main(), config);
		config.width = 1100; // sets the width of screen
		config.height = 660; // set height of screen
		config.title = "Dragon ball";
		config.addIcon("Assets/Backgrounds/icon.png", Files.FileType.Internal);
		config.foregroundFPS = 36;

	}
}

