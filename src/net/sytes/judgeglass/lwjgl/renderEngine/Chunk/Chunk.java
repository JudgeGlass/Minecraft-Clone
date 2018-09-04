package net.sytes.judgeglass.lwjgl.renderEngine.Chunk;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;

public class Chunk {
	public List<Block> blocks;
	public Vector3f origin;
	
	public Chunk(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}
}
