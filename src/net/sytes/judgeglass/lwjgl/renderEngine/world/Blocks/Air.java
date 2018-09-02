package net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks;

import net.sytes.judgeglass.lwjgl.renderEngine.world.Block;

public class Air extends Block{

	private static final String NAME = "air";
	private static final int ID = 0;
	
	public Air(String name, int id) {
		super(NAME, ID);
	}
	
	public static int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return super.name;
	}
	
}
