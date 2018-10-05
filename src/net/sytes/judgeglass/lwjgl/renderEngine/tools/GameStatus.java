package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import java.io.File;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.FontType;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.GUIText;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;
import net.sytes.judgeglass.lwjgl.renderEngine.test.GameLoop;

public class GameStatus {
	public static final String VERSION = "0.0.1a";
	
	public static Vector3f cameraPos = new Vector3f(0, 0, 0);
	public static Vector2f cameraAngle = new Vector2f(0, 0);
	public static int FPS;
	public static boolean show = true;
	
	private static FontType font;
	
	private static GUIText cameraAngleText;
	private static GUIText cameraPosText;
	private static GUIText fpsText;
	private static GUIText versionText;
	private static GUIText authorText;
	private static String cameraAngleStr;
	private static String cameraPosStr;
	private static GUIText seedText;
	
	public static void initFont(Loader loader) {
		font = new FontType(loader.loadTexture("sans"), new File("assets/fonts/sans.fnt"));
	}
	
	public static String getGameInfo() {
		String cameraAngleStr = String.format("Camera X/Y: (%.2f, %.2f)", cameraAngle.x, cameraAngle.y);
		String cameraPosStr = String.format("x: %.02f\ny: %.2f\nz: %.2f", cameraPos.x, cameraPos.y, cameraPos.z);
		String combine = "FPS: " + FPS +"\n" + cameraAngleStr + "\n" + cameraPosStr;
		return combine;
	}
	
	public static void showGameStatus() {
		cameraAngleStr = String.format("Camera X/Y: (%.2f, %.2f)", cameraAngle.x, cameraAngle.y);
		cameraPosStr = String.format("x: %.02f y: %.2f z: %.2f", cameraPos.x, cameraPos.y, cameraPos.z);
		
		//versionText = new GUIText("Minecraft Clone v" + VERSION, 1.5f, font, new Vector2f(0, 0), 1f, false);
		//versionText.setColour(1, 1, 1);
		
		if(show) {
			fpsText = new GUIText("FPS: " + FPS, 1.5f, font, new Vector2f(0, -.08f), 1f, false);
			fpsText.setColour(1, 1, 1);
			cameraAngleText = new GUIText(cameraAngleStr, 1.5f, font, new Vector2f(0, -.15f), 1f, false);
			cameraAngleText.setColour(1, 1, 1);
			cameraPosText = new GUIText(cameraPosStr, 1.5f, font, new Vector2f(0, -.23f), 1f, false);
			cameraPosText.setColour(1, 1, 1);
		}
		
		
	}
	
	public static void updateStatus() {
		
	}
	
	public static void drawVersion() {
		versionText = new GUIText("Minecraft Clone v" + VERSION, 1.5f, font, new Vector2f(0, 0), 1f, false);
		versionText.setColour(1, 1, 1);
		authorText = new GUIText("Copyright 2018 Hunter Wilcox", 1.0f, font, new Vector2f(0, -.08f), 1f, false);
		authorText.setColour(1, 1, 1);
		
		seedText = new GUIText("Seed:" + GameLoop.seed, 1.35f, font, new Vector2f(1.55f, 0), 1f, false);
		seedText.setColour(1, 1, 1);
	}
	
	public static void disableFPS() {
		TextMaster.removeText(fpsText);
		TextMaster.removeText(cameraAngleText);
		TextMaster.removeText(cameraPosText);
	}
}
