package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 480;
	private static final int MAX_FPS = 120;
	
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3, 2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("LWJGL Test");
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void updateDisplay() {
		Display.sync(MAX_FPS);
		Display.update();
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
}
