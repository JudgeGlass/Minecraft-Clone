package net.sytes.judgeglass.lwjgl.renderEngine.test;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_E;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
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
import net.sytes.judgeglass.lwjgl.renderEngine.models.Texture;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.textures.ModelTexture;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.GameStatus;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.KeyboardHandler;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Maths;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.MusicManager;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.PerlinNoiseGenerator;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector3;
import net.sytes.judgeglass.lwjgl.renderEngine.world.TreeGenerator;

public class GameLoop {

	static double lastTime;
	static int fps;
	static double lastFPS;

	private static Loader loader;
	private static List<Entity> entities = Collections.synchronizedList(new ArrayList<Entity>());
	public static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>());
	private static List<Vector3f> usedPos = Collections.synchronizedList(new ArrayList<Vector3f>());
	private static Vector3f camPos = new Vector3f(0, 0, 0);
	private static Random rand = new Random();

	private static final int WORLD = 16 * 16; // WORLD SIZE = 1024
	private static final int viewDistance = 1;
	public static int seed = 0;
	private static int tick = 0;
	private static String[] args;

	private static boolean rendered = false;
	private static boolean close = false;
	public static boolean playing = false;
	public static boolean inventoryOpen = false;
	public static boolean menuOpen = false;
	public static boolean polyMode = false;
	public static boolean updateView = false;
	public static boolean lockCamera = false;

	public static void main(String[] args) {
		GameLoop.args = args;
		DisplayManager.createDisplay();
	}

	public static void start() {
		GL.createCapabilities();
	
		seed = rand.nextInt();

		System.out.println("SEED: " + seed);
		System.out.println("Working DIR: " + System.getProperty("user.dir"));
		playing = true;
		
		

		loader = new Loader();
		GameStatus.initFont(loader);
		TextMaster.init(loader);

		Camera camera = new Camera(0.3f);

		MasterRenderer renderer = new MasterRenderer();

		lastFPS = GLFW.glfwGetTime();

		List<GuiTexture> guis = new ArrayList<GuiTexture>();

		GuiRenderer guiRenderer = new GuiRenderer(loader);
		GuiTexture gui3 = new GuiTexture(loader.loadTexture("ch"), new Vector2f(0.0f, 0.0f),
				new Vector2f(0.015f, 0.02f));
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

		Texture t = new Texture();
		FontType loadFont = new FontType(t.loadTexture("assets/textures/sans.png", GL_NEAREST),
				new File("assets/fonts/sans.fnt"));

		camPos = camera.getPosition();

		
		int index = 0;

		while (!glfwWindowShouldClose(DisplayManager.window)) {
			glfwSwapBuffers(DisplayManager.window);
			input();
			

			camera.move();
			camera.setPosition(camPos);


			renderer.render(camera);

			if (index < chunks.size() && tick > 20) {
				RawModel rModel = loader.loadToVAOChunk(chunks.get(index).positions, chunks.get(index).normals,
						chunks.get(index).uvs);
				TextureModel txt = new TextureModel(rModel, mT);
				Entity e = new Entity(txt,
						new Vector3(chunks.get(index).chunk.x, chunks.get(index).chunk.y, chunks.get(index).chunk.z),
						chunks.get(index));
				if (!entities.contains(e))
					entities.add(e);

				chunks.get(index).positions = null;
				chunks.get(index).normals = null;
				chunks.get(index).uvs = null;

				index++;
			}

			for (Entity e : entities) {
				Vector3 origin = e.getPosition();

				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);

				if (distX < 0) {
					distX = -distX;
				}

				if (distZ < 0) {
					distZ = -distZ;
				}

				if ((distX <= (WORLD - (4 * 16)) * viewDistance) && (distZ <= (WORLD - (4 * 16)) * viewDistance)) {
					renderer.processEntity(e);
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

			if (KeyboardHandler.isPressed(GLFW_KEY_ESCAPE)) {
				break;
			}

			if (!rendered || chunks.size() < 1023) {
				System.out
						.println(String.format("Building Terrain: %d%%", (int) (((float) chunks.size() / 1024) * 100)));

				GUIText text = new GUIText("Building Terrain", 1.25f, loadFont, new Vector2f(.865f, -1f), 1f, false);
				text.setColour(1, 1, 1);

				GUIText text2 = new GUIText((int) (((float) chunks.size() / 1024) * 100) + "%", 1.25f, loadFont,
						new Vector2f(.955f, -1.1f), 1f, false);
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

			//GameStatus.showGameStatus();

			guiRenderer.render(guis);
			TextMaster.render();

			glfwPollEvents();

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
		//MusicManager.stop();
		renderer.clean();
		loader.clean();
		DisplayManager.destroy();
	}

	public static void updateFPS() {
		double currentTime = glfwGetTime();
		fps++;
		if (currentTime - lastTime >= 1.0) { // If last prinf() was more than 1 sec ago
			// printf and reset timer
			System.out.println(String.format("%f ms/frame(FPS: %f)", 1000.0 / (fps), (float)fps));
			System.out.println(String.format("Total: %.3fMB / %.3fMB",((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) 
					/ (1024f * 1024f)),	Runtime.getRuntime().totalMemory() / (1024f * 1024f)));
			fps = 0;
			lastTime += 1.0;
		}
	}

	private static void input() {
		if (KeyboardHandler.isPressed(GLFW_KEY_F)) {
			chunks.remove(entities.get(10).getMesh());
			entities.remove(entities.get(10));
		} else if (KeyboardHandler.isClicked(GLFW_KEY_E)) {
			if (inventoryOpen) {
				DisplayManager.hideMouse(true);
				inventoryOpen = false;
			} else {
				DisplayManager.hideMouse(false);
				inventoryOpen = true;
			}
		} else if (KeyboardHandler.isClicked(GLFW_KEY_P)) {
			if (polyMode) {
				glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
				polyMode = false;
			} else {
				glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
				polyMode = true;
			}
		} else if (KeyboardHandler.isPressed(GLFW_KEY_TAB)) {
			lockCamera = true;
			DisplayManager.hideMouse(false);
		}
	}

	private static void makeChunks() {
		Thread t = new Thread(() -> {
			PerlinNoiseGenerator perlinNoise = new PerlinNoiseGenerator(0, 0, 0, seed);
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
												b = new Block(i, noise, j, Block.Type.FARMLAND, true);
											}
											else
												b = new Block(i, noise, j, Block.Type.GRASS, true);
										}
										blocks.add(b);
										
										if(!noTree) {
											if(rand.nextInt(100) == 23) {
												blocks.add(new Block(i, noise+1, j, Block.Type.YELLOWFLOWER, true));
											}
											else if(rand.nextInt(100) == 33) {
												blocks.add(new Block(i, noise+1, j, Block.Type.TALLGRASS, true));
											}else if(rand.nextInt(100) == 63) {
												blocks.add(new Block(i, noise+1, j, Block.Type.REDFLOWER, true));
											}
										}

										if (rand.nextInt(200) == 10 && !noTree) {
											blocks = TreeGenerator.makeTree(new Vector3f(b.x, b.y, b.z), blocks);
										}
										
										blocks.add(new Block(i, b.y-1, j, Block.Type.DIRT, true));



									}
								}

								Chunk c = new Chunk(blocks, x * 16, ny * 16, z * 16);
								ChunkMesh mesh = new ChunkMesh(c);
								chunks.add(mesh);
								usedPos.add(new Vector3f(x * 16, ny * 16, z * 16));
							}
						}
					}
			}
			System.out.println("Chunk Thread DONE!");
		});
		t.setName("Chunk Thread");
		t.start();

	}
}
