package net.sytes.judgeglass.lwjgl.renderEngine.test;


import java.io.File;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.DrawText;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.Renderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Cube;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.FontType;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.GUIText;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;
import textures.ModelTexture;

public class GameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		TextMaster.init(loader);
		DrawText.init(loader);
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
			
		RawModel model = loader.loadToVAO(Cube.vertices, Cube.textureCoords, Cube.indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirtA"));
		TextureModel textureModel = new TextureModel(model, texture);
		
		ArrayList<Entity> blocks = new ArrayList<>();
		
		for(int x = 0; x <= 63; x++)
			for(int y = 0; y <= 1; y++)
				for(int z = 0; z <= 63; z++) {
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
			
			
			TextMaster.render();
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			}
			DisplayManager.updateDisplay();
		}
		
		TextMaster.cleanUp();
		shader.clean();
		loader.clean();
		DisplayManager.closeDisplay();
	}

}
