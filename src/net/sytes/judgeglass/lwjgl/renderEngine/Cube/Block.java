package net.sytes.judgeglass.lwjgl.renderEngine.Cube;

import org.lwjgl.util.vector.Vector3f;

public class Block {
	public int x;
	public int y;
	public int z;

	public boolean hideFaces;

	public static enum Type {
		AIR, DIRT, GRASS, STONE, OAK_LOG, OAK_LEAVES, GOLD_BLOCK, SAND, WATER
	};

	public Type type;

	public Block(int x, int y, int z, Type type, boolean hideFaces) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.hideFaces = hideFaces;
	}

	public Vector3f getPosition() {
		return new Vector3f(x, y, z);
	}
}
