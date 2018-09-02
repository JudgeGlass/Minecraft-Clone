package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;

public class Chunk {
	private Vector3f start;
	
	private int width;
	private int height;
	private int depth;
	
	private ArrayList<Entity> chunk;
	private HashMap<String, TextureModel> textureModel;
	
	
	public Chunk(HashMap<String, TextureModel> textureModels, Vector3f start, int width, int height, int depth) {
		this.start = start;
		this.width = width;
		this.height = height;
		this.depth = depth;
		
		this.textureModel = textureModels;
		
		chunk = new ArrayList<>();
		
		makeChunk();
	}
	
	private void makeChunk() {
		
		
		for(float x = start.x; x <= width + start.x; x++) {
			for(float y = start.y; y <= height + start.y; y++) {
				for(float z = start.z; z <= depth + start.z; z++) {
					if(y < 15)
						chunk.add(new Entity(textureModel.get("stone"), new Vector3f(x, y, z), 0f, 0f, 0f, 1f));
					else if(y > 12 && y <= 15) {
						chunk.add(new Entity(textureModel.get("dirt"), new Vector3f(x, y, z), 0f, 0f, 0f, 1f));
					}
					else
						chunk.add(new Entity(textureModel.get("grass"), new Vector3f(x, y, z), 0f, 0f, 0f, 1f));
				}
			}
		}
	}
	
	public ArrayList<Entity> getChunk(){
		return chunk;
	}
}
