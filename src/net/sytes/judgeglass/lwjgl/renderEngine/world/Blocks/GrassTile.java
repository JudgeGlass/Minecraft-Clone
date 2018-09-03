package net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks;

import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Cube;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.TransParentObject;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Block;
import textures.ModelTexture;

public class GrassTile extends Block{

	private static final String NAME = "grass_tile";
	private static final int ID = 4;
	
	public GrassTile(String name, int id) {
		super(NAME, ID);
	}
	
	public static TextureModel getTextureModel() {
		Loader loader = new Loader();
		RawModel m = loader.loadToVAO(TransParentObject.vertices, uv, TransParentObject.indices);
		ModelTexture mt = new ModelTexture(loader.loadTexture("textureAtlas"));
		TextureModel tx = new TextureModel(m, mt, ID);
		
		return tx;
	}
	
	public static int getID() {
		return ID;
	}
	
	private static float[] uv = {
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
			
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
			
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
			
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
			
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
			
			12.1f/64f, 0,
			12.1f/64, 2.83f/64,
			14.99f/64, 2.83f/64,
			14.99f/64, 0,
	};

	@Override
	public String getName() {
		return super.name;
	}

}
