package net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks;

import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Cube;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Block;
import textures.ModelTexture;

public class Dirt extends Block{
	
	private static final String NAME = "dirt";
	private static final int ID = 1;
	
	public Dirt(String name, int id) {
		super(NAME, ID);
	}
	
	public static int getID() {
		return ID;
	}
	
	public static TextureModel getTextureModel() {
		Loader loader = new Loader();
		RawModel m = loader.loadToVAO(Cube.vertices, uv, Cube.indices);
		ModelTexture mt = new ModelTexture(loader.loadTexture("textureAtlas"));
		TextureModel tx = new TextureModel(m, mt, ID);
		
		return tx;
	}
	
	private static float[] uv = {
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0,		
			
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0,
			
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0,
			
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0,
			
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0,
			
			6.27f / 64f, 0,
			6.27f / 64f, 3f / 64f,
			8.95f / 64f, 3f / 64f,
			9f / 64f, 0
	};

	@Override
	public String getName() {
		return super.name;
	}
}
