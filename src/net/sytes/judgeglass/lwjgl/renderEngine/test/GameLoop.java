package net.sytes.judgeglass.lwjgl.renderEngine.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.DisplayManager;
import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.MasterRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.Chunk;
import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.ChunkMesh;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.FontType;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.GUIText;
import net.sytes.judgeglass.lwjgl.renderEngine.fontRendering.TextMaster;
import net.sytes.judgeglass.lwjgl.renderEngine.gui.GuiRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.gui.GuiTexture;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.textures.ModelTexture;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.GameStatus;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.MusicManager;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.PerlinNoiseGenerator;
import net.sytes.judgeglass.lwjgl.renderEngine.world.TreeGenerator;

public class GameLoop {

	static long lastFrame;
	static int fps;
	static long lastFPS;

	private static Loader loader;
	private static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
	private static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>());
	private static List<Vector3f> usedPos = Collections.synchronizedList(new ArrayList<Vector3f>());
	private static Vector3f camPos = new Vector3f(0, 0, 0);
	private static final int WORLD = 4 * 16;
	private static boolean rendered = false;
	private static boolean close = false;

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		new Thread(() -> {
			MusicManager.play();
		}).start();

		loader = new Loader();
		GameStatus.initFont(loader);
		TextMaster.init(loader);

		Camera camera = new Camera(0.3f);

		MasterRenderer renderer = new MasterRenderer();

		getDelta();
		lastFPS = getTime();

		List<GuiTexture> guis = new ArrayList<GuiTexture>();

		GuiRenderer guiRenderer = new GuiRenderer(loader);
		GuiTexture gui3 = new GuiTexture(loader.loadTexture("ch"), new Vector2f(0.0f, 0.0f),
				new Vector2f(0.025f, 0.04f));

		guis.add(gui3);

		List<GuiTexture> loadBackground = new ArrayList<GuiTexture>();
		for (float i = 0; i < 2; i += 0.14) {
			for (float j = 0; j < 2; j += .20) {
				loadBackground.add(new GuiTexture(loader.loadTexture("bg32"), new Vector2f(-0.95f + i, .9f - j),
						new Vector2f(0.07f, 0.10f)));
			}
		}

		GameStatus.drawVersion();
		ModelTexture mT = new ModelTexture(loader.loadTexture("atlas"));
		FontType loadFont = new FontType(loader.loadTexture("sans"), new File("assets/fonts/sans.fnt"));

		int tick = 0;
		int index = 0;
		while (!Display.isCloseRequested() && !DisplayManager.awtCloseRequested) {
			camera.move();
			camPos = camera.getPosition();

			renderer.render(camera);

			if (index < chunks.size() && tick > 20) {

				RawModel rModel = loader.loadToVAOChunk(chunks.get(index).positions, chunks.get(index).uvs);
				TextureModel txt = new TextureModel(rModel, mT);
				Entity e = new Entity(txt, chunks.get(index).chunk.origin, 0, 0, 0, 1);
				if (!entities.contains(e))
					entities.add(e);
				index++;
			}

			for (Entity e : entities) {
				renderer.processEntity(e);
			}

			if (!rendered && tick >= 20) {
				guiRenderer.render(loadBackground);
				makeChunks();
				rendered = true;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				break;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_F3)) {

			}

			if (!rendered || chunks.size() < 10) {
				GUIText text = new GUIText("Building Terrain", 1.25f, loadFont, new Vector2f(.865f, -1f), 1f, false);
				text.setColour(1, 1, 1);

				guiRenderer.render(loadBackground);
				TextMaster.render();
				TextMaster.removeText(text);
			} else {
				loadBackground.clear();
			}
			guiRenderer.render(guis);
			TextMaster.render();
			DisplayManager.updateDisplay();
			updateFPS();
			tick++;
		}
		
		close = true;
		guiRenderer.clean();
		TextMaster.cleanUp();
		MusicManager.stop();
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

	private static void makeChunks() {

		new Thread(() -> {
			Random rand = new Random();
			PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator(0, 0, 0, rand.nextInt());
			while (!close) {
				List<Block> blocks = null;
				for (int x = (int) (camPos.x - WORLD) / 16; x < (camPos.x + WORLD) / 16; x++)
					for (int y = (int) (camPos.y - 16) / 16; y < (camPos.y + 16) / 16; y++) {
						for (int z = (int) (camPos.z - WORLD) / 16; z < (camPos.z + WORLD) / 16; z++) {
							int ny = 0;
							/*if(camPos.y >= 24) {
								ny = 0;
							}else {
								ny = y;
							}*/
							if (!usedPos.contains(new Vector3f(x * 16, ny * 16, z * 16))) {
								blocks = new ArrayList<Block>();
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {
											
										int noise = (int) perlinNoise.generateHeight(i + (x * 16), j + (z * 16)) + 40;

										Block b;
										if (noise <= 20)
											b = new Block(i, noise, j, Block.Type.SAND, true);
										else
											b = new Block(i, noise, j, Block.Type.GRASS, true);
										blocks.add(b);

										if (rand.nextInt(200) == 10) {
											blocks = TreeGenerator.makeTree(new Vector3f(b.x, b.y, b.z), blocks);
										}

										int index = 1;
										while (true) {
											if (b.y - index <= 24) {
												break;
											}

											if (index <= 3) {
												blocks.add(new Block(b.x, b.y - index, b.z, Block.Type.DIRT, true));
												index++;
												continue;
											}

											if (b.y - index <= 20) {
												if (rand.nextInt(200) == 10) {
													blocks.add(new Block(b.x, b.y - index, b.z, Block.Type.GOLD_BLOCK,
															true));
													index++;
													continue;
												}
											}

											blocks.add(new Block(b.x, b.y - index, b.z, Block.Type.STONE, true));
											index++;
										}
									}
								}

								Chunk c = new Chunk(blocks, new Vector3f(x * 16, ny * 16, z * 16));
								ChunkMesh mesh = new ChunkMesh(c);
								chunks.add(mesh);
								usedPos.add(new Vector3f(x * 16, ny * 16, z * 16));
							}
						}
					}
			}

		}).start();

	}
}
