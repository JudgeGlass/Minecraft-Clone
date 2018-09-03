package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.MasterRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Dirt;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Grass;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.GrassTile;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Stone;

public class ChunkRenderer {
	private int width;
	private int height;
	private int depth;

	private MasterRenderer renderer;
	private HashMap<String, TextureModel> texture;
	private ArrayList<Chunk> chunks = new ArrayList<>();

	Vector3f start;

	public ChunkRenderer(MasterRenderer renderer, HashMap<String, TextureModel> textureModel, int width, int height,
			int depth) {
		this.renderer = renderer;
		texture = textureModel;

		this.width = width;
		this.height = height;
		this.depth = depth;

		start = new Vector3f(0, 0, 0);

		chunks.add(new Chunk(start, width, height, depth));
		chunks.add(new Chunk(new Vector3f(16, 0, 0), width, height, depth));
		chunks.add(new Chunk(new Vector3f(32, 0, 0), width, height, depth));
		chunks.add(new Chunk(new Vector3f(48, 0, 0), width, height, depth));
		// chunks.add(new Chunk(new Vector3f(-16, 0, -16), width, height, depth));
		// chunks.add(new Chunk(new Vector3f(-16, 0, 16), width, height, depth));
	}

	int counter = 0;

	public void drawChunks(Camera camera) {

		
		
		while (Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				chunks.get(0).removeBlock(2 + counter, 0 + counter, 1 + counter);
				counter++;
			}
		}

		for (Chunk chunk : chunks) {
			byte[][][] blocks = chunk.getChunk();
			for (int x = 0; x < chunk.getStart().x + width; x++)
				for (int y = 0; y < chunk.getStart().y + height; y++)
					for (int z = 0; z < chunk.getStart().z + depth; z++) {
						// System.out.println("Block: " + chunk[x][y][z]);
						if (blocks[x][y][z] == Dirt.getID() && !chunk.checkTileInView(x, y, z)) {
							renderer.processEntity(new Entity(texture.get("dirt"), new Vector3f(x, y, z), 0, 0, 0, 1));
						}

						else if (blocks[x][y][z] == Stone.getID() && !chunk.checkTileInView(x, y, z)) {
							renderer.processEntity(new Entity(texture.get("stone"), new Vector3f(x, y, z), 0, 0, 0, 1));
						}

						if (blocks[x][y][z] == Grass.getID() && !chunk.checkTileInView(x, y, z)) {
							renderer.processEntity(new Entity(texture.get("grass"), new Vector3f(x, y, z), 0, 0, 0, 1));
						}else if(blocks[x][y][z] == GrassTile.getID()) {
							renderer.processEntity(new Entity(texture.get("dirt"), new Vector3f(x, y, z), 0, 0, 0, 1));
						}

					}
		}

	}
}
