package net.sytes.judgeglass.lwjgl.renderEngine.entities;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.ChunkMesh;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;

public class Entity {
	private TextureModel model;
	
	private Vector3f position;
	
	private float rotX;
	private float rotY;
	private float rotZ;
	private float scale;
	private ChunkMesh mesh = null;
	
	public Entity(TextureModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	public Entity(TextureModel model, Vector3f position, ChunkMesh mesh) {
		this.model = model;
		this.position = position;
		this.mesh = mesh;
		this.scale = 1;
		this.rotX = 0;
		this.rotY = 0;
		this.rotZ = 0;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public TextureModel getModel() {
		return model;
	}
	
	public ChunkMesh getMesh() {
		return mesh;
	}

	public void setModel(TextureModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
}
