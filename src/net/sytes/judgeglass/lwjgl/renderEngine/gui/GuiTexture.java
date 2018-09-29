package net.sytes.judgeglass.lwjgl.renderEngine.gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {
	private int texture;
	private Vector2f pos;
	private Vector2f scale;
	
	public GuiTexture(int texture, Vector2f pos, Vector2f scale) {
		this.texture = texture;
		this.pos = pos;
		this.scale = scale;
	}

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public Vector2f getPos() {
		return pos;
	}

	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	
}
