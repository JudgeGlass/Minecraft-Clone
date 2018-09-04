package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.Chunk;
import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.ChunkMesh;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;

public class ChunkRenderer {
	private final int CHUNK_SIZE = 16;
	
	private Vector3f cameraPos;
	
	private List<Block> blocks;
	private List<Entity> chunk;
	private List<Chunk> rawChunks;
	
	private ChunkMesh mesh;
	
	public ChunkRenderer(Vector3f cameraPos) {
		this.cameraPos = cameraPos;
		
		rawChunks = new ArrayList<Chunk>();
		
		makeChunks();
	}
	
	public List<Entity> getGeneratedChunks(){
		return null;
	}
	
	private void makeChunks() {
		for(int i = 0; i < 10; i++) {
			List<Block> blocks = new ArrayList<Block>();
			for(int x = 0; x < CHUNK_SIZE; x++) {
				for(int z = 0; z < CHUNK_SIZE; z++) {
					blocks.add(new Block(x, 0, z, Block.Type.DIRT));
				}
			}
			
			rawChunks.add(new Chunk(blocks, new Vector3f(i * CHUNK_SIZE, 0, i * CHUNK_SIZE)));
		}
	}
}
