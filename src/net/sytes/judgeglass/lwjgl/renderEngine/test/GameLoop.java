package net.sytes.judgeglass.lwjgl.renderEngine.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.MasterRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.Chunk;
import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.ChunkMesh;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.GameStatus;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.PerlinNoiseGenerator;
import textures.ModelTexture;

public class GameLoop {

	static long lastFrame;
	static int fps;
	static long lastFPS;

	private static Loader loader;
	private static List<Entity> entities = new ArrayList<Entity>();
	private static Vector3f camPos;
	private static PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator();

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		loader = new Loader();
		GameStatus.initFont(loader);
		TextMaster.init(loader);

		Camera camera = new Camera(0.3f);

		MasterRenderer renderer = new MasterRenderer();

		getDelta();
		lastFPS = getTime();

		
		
		List<Block> b = new ArrayList<Block>();
		for(int x = 0; x < 128; x++) {
			for(int z = 0; z < 128; z++) {
				b.add(new Block(x, (int)perlinNoise.generateHeight(x, z), z, Block.Type.DIRT));
			}
		}
		
		Chunk c = new Chunk(b, new Vector3f(0, 0, 0));
		ChunkMesh mesh = new ChunkMesh(c);
		RawModel model123 = loader.loadToVAOChunk(mesh.positions, mesh.uvs);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stone"));
		TextureModel texMod = new TextureModel(model123, texture);
		entities.add(new Entity(texMod, mesh.chunk.origin, 0.f, 0.f, 0.f, 1));
		
		int index = 0;
		while (!Display.isCloseRequested() && !DisplayManager.awtCloseRequested) {
			camera.move();
			camPos = camera.getPosition();
			
			for(Entity e: entities) {
				renderer.processEntity(e);
			}
			
			renderer.render(camera);

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
				if (GameStatus.show) {
					GameStatus.show = false;
				} else {
					GameStatus.show = true;
				}
			}
				
			GameStatus.showGameStatus();
			TextMaster.render();
			DisplayManager.updateDisplay();
			updateFPS();
		}

		renderer.clean();
		loader.clean();
		DisplayManager.closeDisplay();
		DisplayManager.closeAWT();
	}

	public static int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			GameStatus.FPS = fps;
			System.out.println("FPS: " + fps + "   Pos: " + camPos);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

}
