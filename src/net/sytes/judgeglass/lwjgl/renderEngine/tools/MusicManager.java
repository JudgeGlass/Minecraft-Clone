package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import java.io.IOException;
import java.util.Random;

import org.newdawn.easyogg.OggClip;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MusicManager {
	private static Audio oggEffect;
	
	private static String[] musicFiles = {
			"creative1.ogg",
			"creative2.ogg",
			"creative3.ogg",
			"creative4.ogg",
			"creative5.ogg",
			"creative6.ogg",
			"calm1.ogg",
			"calm2.ogg",
			"calm3.ogg",
			"hal1.ogg",
			"hal2.ogg",
			"hal3.ogg"
	};
	
	
	public static void play() {
		Random rand = new Random();
		try {
			String musicFile = musicFiles[rand.nextInt(musicFiles.length)];
			System.out.println("Current Music: " + musicFile);
			oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("music/" + musicFile));
			oggEffect.playAsMusic(1.0f, 1.0f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void stop() {
		if(oggEffect != null)
			oggEffect.stop();
	}
}
