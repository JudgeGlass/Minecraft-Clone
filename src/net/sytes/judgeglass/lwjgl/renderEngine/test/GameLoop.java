package net.sytes.judgeglass.lwjgl.renderEngine.test;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.Renderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Maths;
import textures.ModelTexture;

public class GameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
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
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirt"));
		TextureModel textureModel = new TextureModel(model, texture);
		
		Entity entity = new Entity(textureModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		
		while(!Display.isCloseRequested()) {
			entity.increasePosition(0, 0, -0.1f);
			
			renderer.prepare();
			shader.start();
			
			
			
			renderer.render(entity, shader);
			
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.clean();
		loader.clean();
		DisplayManager.closeDisplay();
	}

}
