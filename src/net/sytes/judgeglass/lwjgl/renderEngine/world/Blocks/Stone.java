package net.sytes.judgeglass.lwjgl.renderEngine.world.Blocks;

import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.Cube;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.world.Block;
import textures.ModelTexture;

public class Stone extends Block{
	public Stone(String name, int id) {
		super(name, id);
	}

	public static TextureModel getTextureModel() {
		Loader loader = new Loader();
		RawModel m = loader.loadToVAO(Cube.vertices, uv, Cube.indices);
		ModelTexture mt = new ModelTexture(loader.loadTexture("textureAtlas"));
		TextureModel tx = new TextureModel(m, mt);
		
		return tx;
	}
	
	private static float[] uv = {
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
			
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
			
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
			
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
			
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
			
			9.1f/64f, 0,
			9.1f/64, 2.83f/64,
			11.99f/64, 2.83f/64,
			11.99f/64, 0,
	};

	@Override
	protected int getID() {
		return super.id;
	}

	@Override
	protected String getName() {
		return super.name;
	}
}
