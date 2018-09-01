package net.sytes.judgeglass.lwjgl.renderEngine.test;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.Renderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Cube;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;
import textures.ModelTexture;

public class GameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		
		
		RawModel model = loader.loadToVAO(Cube.vertices, Cube.textureCoords, Cube.indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirt"));
		TextureModel textureModel = new TextureModel(model, texture);
		
		ArrayList<Entity> blocks = new ArrayList<>();
		
		for(int x = 0; x <= 16; x++)
			for(int y = 0; y <= 16; y++)
				for(int z = 0; z <= 16; z++) {
					blocks.add(new Entity(textureModel, new Vector3f(x, y, z), 0, 0, 0, 1));
				}
		
		Camera camera = new Camera(0.3f);
		
		while(!Display.isCloseRequested()) {
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			//renderer.render(entity, shader);
			for(Entity entity: blocks) {
				renderer.render(entity, shader);
			}
			
			shader.stop();
			DisplayManager.updateDisplay();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}
		}
		
		shader.clean();
		loader.clean();
		DisplayManager.closeDisplay();
	}

}
