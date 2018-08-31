package net.sytes.judgeglass.lwjgl.renderEngine.test;

import org.lwjgl.opengl.Display;

import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.Renderer;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;

public class GameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f
		};
		
		int[] indices = {
				0, 1, 3,
				3, 1, 2
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			
			
			renderer.render(model);
			
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.clean();
		loader.clean();
		DisplayManager.closeDisplay();
	}

}
