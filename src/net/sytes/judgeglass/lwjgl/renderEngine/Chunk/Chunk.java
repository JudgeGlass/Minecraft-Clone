package net.sytes.judgeglass.lwjgl.renderEngine.Chunk;

import java.util.List;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;

public class Chunk {
	public List<Block> blocks;
	//public Vector3 origin;
	
	public int x;
	public int y;
	public int z;
	
	public Chunk(List<Block> blocks, int x, int y, int z) {
		this.blocks = blocks;
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
