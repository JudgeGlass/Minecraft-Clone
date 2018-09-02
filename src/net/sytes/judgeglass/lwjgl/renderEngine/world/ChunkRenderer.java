package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.MasterRenderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Dirt;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Grass;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks.Stone;

public class ChunkRenderer {
	private int width;
	private int height;
	private int depth;
	
	private final int DESPAWN_DISTANCE = 24;
	
	private MasterRenderer renderer;
	private HashMap<String, TextureModel> texture;
	
	private ArrayList<Chunk> chunks;
	Chunk c;
	Vector3f start;
	
	public ChunkRenderer(MasterRenderer renderer, HashMap<String, TextureModel> textureModel, int width, int height, int depth) {
		this.renderer = renderer;
		texture = textureModel;
		
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		chunks = new ArrayList<>();
		chunks.add(new Chunk(texture, new Vector3f(0, 0, 0), width, height, depth));
		start = new Vector3f(0, 0, 0);
		c = new Chunk(texture, start, width, height, depth);
		System.out.println("Chunk Size: " + c.getChunk().length);
		
	}
	
	public void drawChunks() {
		
		//chunks.add(new Chunk(texture, new Vector3f(16, 0, 0), width, height, depth));
		//chunks.add(new Chunk(texture, new Vector3f(0, 0, 17), width, height, depth));
		
		/*for(Chunk chunk: chunks) {
			for(Entity block: chunk.getChunk()) {
				renderer.processEntity(block);
			}
		}*/
		
		byte[][][] chunk = c.getChunk();
		
		for(int x = 0; x < start.x + width; x++)
			for(int y = 0; y < start.y + height; y++)
				for(int z = 0; z < start.z + depth; z++) {
					//System.out.println("Block: " + chunk[x][y][z]);
					if(chunk[x][y][z] == Dirt.getID() && !c.checkTileInView(x, y, z)) {
						renderer.processEntity(new Entity(texture.get("dirt"), new Vector3f(x, y, z), 0, 0, 0, 1));
					}
					
					else if(chunk[x][y][z] == Stone.getID() && !c.checkTileInView(x, y, z)) {
						renderer.processEntity(new Entity(texture.get("stone"), new Vector3f(x, y, z), 0, 0, 0, 1));
					}
					
					if(chunk[x][y][z] == Grass.getID() && !c.checkTileInView(x, y, z)) {
						renderer.processEntity(new Entity(texture.get("grass"), new Vector3f(x, y, z), 0, 0, 0, 1));
					}
					
				}
	}
}
