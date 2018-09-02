package net.sytes.judgeglass.lwjgl.renderEngine.test;

import java.util.HashMap;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.MasterRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.GameStatus;
import net.sytes.judgeglass.lwjgl.renderEngine.world.ChunkRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Air;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Dirt;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Grass;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Stone;

public class GameLoop {

	static long lastFrame;
	static int fps;
	static long lastFPS;
	
	private static Loader loader;

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		loader = new Loader();

		TextMaster.init(loader);
		GameStatus.initFont(loader);

		Camera camera = new Camera(0.3f);
		
		HashMap<String, TextureModel> blocks = new HashMap<String, TextureModel>();
		blocks.put("air", null);
		blocks.put("grass", Grass.getTextureModel());
		blocks.put("dirt", Dirt.getTextureModel());
		blocks.put("stone", Stone.getTextureModel());
		MasterRenderer renderer = new MasterRenderer();
		ChunkRenderer chunkRenderer = new ChunkRenderer(renderer, blocks, 16, 16, 16);
		

		getDelta();
		lastFPS = getTime();
		
		while (!Display.isCloseRequested() && !DisplayManager.awtCloseRequested) {
			
			camera.move();
			renderer.render(camera);
			chunkRenderer.drawChunks();

			GameStatus.showGameStatus();
			TextMaster.render();
			
			
			while(Keyboard.next()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					break;
				} else if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
					if (GameStatus.show) {
						GameStatus.disableFPS();
						GameStatus.show = false;
					} else {
						GameStatus.show = true;
					}
				}
			}
			DisplayManager.updateDisplay();
			updateFPS();
		}

		TextMaster.cleanUp();
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
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

}
