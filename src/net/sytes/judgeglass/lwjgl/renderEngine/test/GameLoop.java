package net.sytes.judgeglass.lwjgl.renderEngine.test;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Light;
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
	private static Random rand = new Random();
	
	private static final int WORLD = 16 * 16; // WORLD SIZE = 1024
	private static final int viewDistance = 2;
	public static int seed = 0;
	
	
	private static boolean rendered = false;
	private static boolean close = false;
	public static boolean playing = false;
	public static boolean inventoryOpen = false;
	public static boolean menuOpen = false;
	public static boolean polyMode = false;
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		// start();
	}

	public static void start() {
		seed = rand.nextInt();
		System.out.println("SEED: " + seed);
		System.out.println("Working DIR: " + System.getProperty("user.dir"));
		playing = true;
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
				new Vector2f(0.02f, 0.03f));
		GuiTexture hotBar = new GuiTexture(loader.loadTexture("hotbar"), new Vector2f(0.05f, -.89f),
				new Vector2f(.5f, .095f));
		GuiTexture inventory = new GuiTexture(loader.loadTexture("inventory"), new Vector2f(0, 0),
				new Vector2f(.5f, .7f));

		guis.add(gui3);
		guis.add(hotBar);

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
				RawModel rModel = loader.loadToVAOChunk(chunks.get(index).positions, chunks.get(index).normals, chunks.get(index).uvs);
				TextureModel txt = new TextureModel(rModel, mT);
				Entity e = new Entity(txt, chunks.get(index).chunk.origin, chunks.get(index));
				if (!entities.contains(e))
					entities.add(e);

				chunks.get(index).positions = null;
				chunks.get(index).normals = null;
				chunks.get(index).uvs = null;

				index++;
			}

			for (Entity e : entities) {
				Vector3f origin = e.getPosition();

				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);

				if (distX < 0) {
					distX = -distX;
				}

				if (distZ < 0) {
					distZ = -distZ;
				}

				if ((distX <= WORLD - (4 * 16) * viewDistance) && (distZ <= WORLD - (4 * 16) * viewDistance)) {
					renderer.processEntity(e);
				}
			}

			while (Keyboard.next()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
					chunks.remove(entities.get(10).getMesh());
					entities.remove(entities.get(10));
				}else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
					if(inventoryOpen) {
						Mouse.setGrabbed(true);
						inventoryOpen = false;
					}else {
						Mouse.setGrabbed(false);
						inventoryOpen = true;
					}
				}else if(Keyboard.isKeyDown(Keyboard.KEY_P)) {
					if(polyMode) {
						glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
						polyMode = false;
					}else {
						glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
						polyMode = true;
					}
				}
			}

			if (inventoryOpen) {
				if (!guis.contains(inventory)) {
					guis.remove(gui3);
					guis.remove(hotBar);
					guis.add(inventory);
				}
			} else {
				if (guis.contains(inventory)) {
					guis.add(gui3);
					guis.add(hotBar);
					guis.remove(inventory);
				}
			}

			if (!rendered && tick >= 20) {
				guiRenderer.render(loadBackground);
				makeChunks();
				rendered = true;
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					break;
			}
			

			if (!rendered || chunks.size() < 1023) {
				System.out.println(String.format("Building Terrain: %d%%", (int)(((float)chunks.size() / 1024) * 100)));
				
				GUIText text = new GUIText("Building Terrain", 1.25f, loadFont, new Vector2f(.865f, -1f), 1f, false);
				text.setColour(1, 1, 1);
				
				GUIText text2 = new GUIText((int)(((float)chunks.size() / 1024) * 100) + "%", 1.25f, loadFont, new Vector2f(.955f, -1.1f), 1f, false);
				text2.setColour(1, 1, 1);

				guiRenderer.render(loadBackground);
				TextMaster.render();
				TextMaster.removeText(text);
				TextMaster.removeText(text2);
				
				text = null;
				text2 = null;
			} else {
				loadBackground.clear();
			}

			guiRenderer.render(guis);
			TextMaster.render();
			DisplayManager.updateDisplay();
			updateFPS();

			if (tick > 500) {
				tick = 0;
			} else {
				tick++;
			}
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
			PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator(0, 0, 0, rand.nextInt());
			while (!close && chunks.size() <= 400) {
				
				List<Block> blocks = null;
				for (int x = (int) (camPos.x - WORLD) / 16; x < (camPos.x + WORLD) / 16; x++)
					for (int y = (int) (camPos.y - 16) / 16; y < (camPos.y + 16) / 16; y++) {
						for (int z = (int) (camPos.z - WORLD) / 16; z < (camPos.z + WORLD) / 16; z++) {
							int ny = 0;
							if (!usedPos.contains(new Vector3f(x * 16, ny * 16, z * 16))) {
								blocks = new ArrayList<Block>();
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {

										int noise = (int) perlinNoise.generateHeight(i + (x * 16), j + (z * 16)) + 35;
										// int noise = 25;

										Block b;
										boolean noTree = false;
										;
										if (noise <= 21) {
											b = new Block(i, noise, j, Block.Type.SAND, true);

											if (b.y <= 21) {
												int ii = 1;
												while (b.y + ii < 22) {
													blocks.add(new Block(b.x, b.y + ii, b.z, Block.Type.WATER, true));
													ii++;
												}
											}
											noTree = true;
										} else {
											if (rand.nextInt(50) == 10) {
												b = new Block(i, noise, j, Block.Type.DIRT, true);
											}
											else
												b = new Block(i, noise, j, Block.Type.GRASS, true);
										}
										blocks.add(b);

										if (rand.nextInt(200) == 10 && !noTree) {
											blocks = TreeGenerator.makeTree(new Vector3f(b.x, b.y, b.z), blocks);
										}
										
										blocks.add(new Block(i, b.y-1, j, Block.Type.STONE, true));

										int index = 1;
										
										 /*while (true) { if (b.y - index <= 24) { break; }
										 
										 if (index <= 3) { blocks.add(new Block(b.x, b.y - index, b.z,
										  Block.Type.DIRT, true)); index++; continue; }
										  
										  if (b.y - index <= 20) { if (rand.nextInt(200) == 10) { blocks.add(new
										  Block(b.x, b.y - index, b.z, Block.Type.GOLD_BLOCK, true)); index++;
										  continue; } }
										  
										  blocks.add(new Block(b.x, b.y - index, b.z, Block.Type.STONE, true));
										  index++; }*/
										 
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
