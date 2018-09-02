package net.sytes.judgeglass.lwjgl.renderEngine.models;

import textures.ModelTexture;

public class TextureModel {
	private RawModel rawModel;
	private ModelTexture texture;
	private int blockid;
	
	public TextureModel(RawModel model, ModelTexture texture, int blockid) {
		this.rawModel = model;
		this.texture = texture;
		this.blockid = blockid;
	}

	public RawModel getRawModel() {
		return rawModel;
	}

	public void setRawModel(RawModel rawModel) {
		this.rawModel = rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	public void setTexture(ModelTexture texture) {
		this.texture = texture;
	}
	
	
}
