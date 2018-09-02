package net.sytes.judgeglass.lwjgl.renderEngine.world;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Air;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Dirt;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Grass;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Stone;

public class Chunk {
	private Vector3f start;

	private int width;
	private int height;
	private int depth;

	private ArrayList<Entity> chunk;
	private HashMap<String, TextureModel> textureModel;
	private Random random;
	private byte[][][] blocks;

	public Chunk(HashMap<String, TextureModel> textureModels, Vector3f start, int width, int height, int depth) {
		this.start = start;
		this.width = width;
		this.height = height;
		this.depth = depth;

		this.textureModel = textureModels;

		chunk = new ArrayList<>();
		random = new Random();
		blocks = new byte[(int) (start.x + width + 1)][(int) (start.y + height + 1)][(int) (start.z + depth + 1)];
		makeChunk();
	}

	private void makeChunk() {
		for (int x = (int) start.x; x <= width + start.x; x++) {
			for (int y = (int) start.y; y <= height + start.y; y++) {
				for (int z = (int) start.z; z <= depth + start.z; z++) {
					if(random.nextInt(10) == Air.getID()) { blocks[x][y][z] = (byte) Air.getID(); continue;}
					if(y < 14)
						blocks[x][y][z] = (byte) Stone.getID();
					else if(y >= 14 && y < 15)
						blocks[x][y][z] = (byte) Dirt.getID();
					else
						blocks[x][y][z] = (byte) Grass.getID();

				}
			}

		}
	}
	
	public void rerender() {
		makeChunk();
	}
	
	public byte[][][] getChunk() {
		return blocks;
	}

	public boolean checkTileInView(int x, int y, int z) {
		boolean facesHidden[] = new boolean[6];
		if (x > start.x) {
			if (blocks[x - 1][y][z] != Air.getID())
				facesHidden[0] = true;
			else
				facesHidden[0] = false;
		} else {
			facesHidden[0] = false;
		}

		if (x < width + start.x - 1) {
			if (blocks[x + 1][y][z] != Air.getID())
				facesHidden[1] = true;
			else
				facesHidden[1] = false;
		} else {
			facesHidden[1] = false;
		}

		if (y > start.y) {
			if (blocks[x][y - 1][z] != Air.getID())
				facesHidden[2] = true;
			else
				facesHidden[2] = false;
		} else {
			facesHidden[2] = false;
		}

		if (y < height + start.x - 1) {
			if (blocks[x][y + 1][z] != Air.getID())
				facesHidden[3] = true;
			else
				facesHidden[3] = false;
		} else {
			facesHidden[3] = false;
		}

		if (z > start.z) {
			if (blocks[x][y][z - 1] != Air.getID())
				facesHidden[4] = true;
			else
				facesHidden[4] = false;
		} else {
			facesHidden[4] = false;
		}

		if (z < depth + start.z - 1) {
			if (blocks[x][y][z + 1] != Air.getID())
				facesHidden[5] = true;
			else
				facesHidden[5] = false;
		} else {
			facesHidden[5] = false;
		}

		return facesHidden[0] && facesHidden[1] && facesHidden[2] && facesHidden[3] && facesHidden[4] && facesHidden[5];
	}
}
