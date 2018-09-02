package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.HashMap;

import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;

public abstract class Block {
	protected String name;
	protected int id;
	
	protected TextureModel texture;
	
	
	public Block(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public abstract String getName();
}
