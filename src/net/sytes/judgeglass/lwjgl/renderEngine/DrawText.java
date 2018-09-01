package net.sytes.judgeglass.lwjgl.renderEngine;

import java.io.File;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.FontType;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.GUIText;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;

public class DrawText {
	private static FontType font;
	private static GUIText text;
	
	public static void init(Loader loader) {
		font = new FontType(loader.loadTexture("sans"), new File("assets/fonts/sans.fnt"));
	}
	
	public static void drawString(String strText, float x, float y, float size, boolean center, Vector3f color) {
		if(text != null)
			TextMaster.removeText(text);
		text = new GUIText(strText, size, font, new Vector2f(x, y), 1, center);
		text.setColour(color.x, color.y, color.z);
	}
}
