package net.sytes.judgeglass.lwjgl.renderEngine.Cube;

public class Block {
	public int x;
	public int y;
	public int z;
	
	public static enum Type {
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
}
