package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryJavaSound;

public class MusicManager {
	
	
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
		try
		{
		    SoundSystemConfig.addLibrary( LibraryJavaSound.class );
		    SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );	
		}
		catch( SoundSystemException e )
		{
		    System.err.println("error linking with the plug-ins" );
		}
		SoundSystem mySoundSystem = new SoundSystem();
		
		try {
			mySoundSystem.backgroundMusic("OGG Music", new File("music/hal1.ogg").toURI().toURL(), "hal1.ogg", true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mySoundSystem.setVolume("OGG Music", 100);
		mySoundSystem.play("OGG Music");
		
		mySoundSystem.cleanup();
    }
 
    
}
