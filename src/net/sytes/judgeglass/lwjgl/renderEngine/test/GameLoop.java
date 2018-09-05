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
import net.sytes.judgeglass.lwjgl.renderEngine.world.ChunkRenderer;
import textures.ModelTexture;

public class GameLoop {

	static long lastFrame;
	static int fps;
	static long lastFPS;

	private static Loader loader;
	private static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
	private static Vector3f camPos = new Vector3f(0, 0, 0);
	private static List<Block> blocks;
	private static List<ChunkMesh> chunks = new ArrayList<ChunkMesh>();
	private static final int WORLD = 5 * 8;

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		loader = new Loader();
		//GameStatus.initFont(loader);
		//TextMaster.init(loader);

		Camera camera = new Camera(0.3f);

		MasterRenderer renderer = new MasterRenderer();

		getDelta();
		lastFPS = getTime();

		PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator();
		for(int x = (int)(1 - WORLD) / 8; x < (1 + WORLD) / 8; x++)
			for(int z = (int)(1 - WORLD) / 8; z < (1 + WORLD) / 8; z++) {
				List<Block> blocks = new ArrayList<Block>();
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						blocks.add(new Block(i, (int) perlinNoise.generateHeight(i + (x * 8), j + (z * 8)) - 15, j, Block.Type.GRASS));
					}
				}
				
				Chunk c = new Chunk(blocks, new Vector3f(x * 8, 0, z * 8));
				ChunkMesh mesh = new ChunkMesh(c);
				RawModel rModel = loader.loadToVAOChunk(mesh.positions, mesh.uvs);
				ModelTexture mT = new ModelTexture(loader.loadTexture("atlas"));
				TextureModel txt = new TextureModel(rModel, mT);
				entities.add(new Entity(txt, mesh.chunk.origin, 0, 0, 0, 1));
			}

		int index = 0;
		while (!Display.isCloseRequested() && !DisplayManager.awtCloseRequested) {
			camera.move();
			camPos = camera.getPosition();
			
			if(index < chunks.size()) {
				RawModel rModel = loader.loadToVAOChunk(chunks.get(index).positions, chunks.get(index).uvs);
				ModelTexture mT = new ModelTexture(loader.loadTexture("atlas"));
				TextureModel txt = new TextureModel(rModel, mT);
				//entities.add(new Entity(txt, chunks.get(index).chunk.origin, 0, 0, 0, 1));
				index++;
			}

			renderer.render(camera);

			for (Entity e : entities) {
				renderer.processEntity(e);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {
				
			}

			//GameStatus.showGameStatus();
			//TextMaster.render();
			DisplayManager.updateDisplay();
			updateFPS();
		}

		//TextMaster.cleanUp();
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

	/*private static List<Block> getChunk(int xx, int zz) {
		List<Block> b = new ArrayList<Block>();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				Block newBlock = new Block(x, (int) perlinNoise.generateHeight(x, z) + 17, z, Block.Type.GRASS);
				System.out.println("BLOCK: " + newBlock.getPosition());
				b.add(newBlock);

				int index = 1;
				while (true) {
					if (newBlock.y - index <= 0) {
						break;
					}

					if (index <= 4) {
						b.add(new Block(x, newBlock.y - index, z, Block.Type.DIRT));
					} else {
						b.add(new Block(x, newBlock.y - index, z, Block.Type.STONE));
					}
					index++;
				}
			}
		}

		return b;
	}*/

}
