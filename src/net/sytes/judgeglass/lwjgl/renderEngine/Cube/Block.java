package net.sytes.judgeglass.lwjgl.renderEngine.Cube;

import org.lwjgl.util.vector.Vector3f;

public class Block {
	public int x;
	public int y;
	public int z;
	
	public static enum Type {
		AIR,
		DIRT,
		GRASS,
		STONE
	};
	
	public Type type;
	
	public Block(int x, int y, int z, Type type) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	
	public Vector3f getPosition() {
		return new Vector3f(x, y, z);
	}
}
