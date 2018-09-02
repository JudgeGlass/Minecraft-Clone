package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Renderer;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;

public class ChunkRenderer {
	private int width;
	private int height;
	private int depth;
	
	private final int DESPAWN_DISTANCE = 24;
	
	private Renderer renderer;
	private HashMap<String, TextureModel> texture;
	private StaticShader shader;
	
	public ChunkRenderer(Renderer renderer, StaticShader shader, HashMap<String, TextureModel> textureModel, int width, int height, int depth) {
		this.renderer = renderer;
		texture = textureModel;
		this.shader = shader;
		
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public void drawChunks() {
		ArrayList<Chunk> chunks = new ArrayList<>();
		chunks.add(new Chunk(texture, new Vector3f(0, 0, 0), width, height, depth));
		chunks.add(new Chunk(texture, new Vector3f(16, 0, 0), width, height, depth));
		chunks.add(new Chunk(texture, new Vector3f(0, 0, 17), width, height, depth));
		
		for(Chunk chunk: chunks) {
			for(Entity block: chunk.getChunk()) {
				renderer.render(block, shader);
			}
		}
	}
}
