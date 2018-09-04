package net.sytes.judgeglass.lwjgl.renderEngine.Cube;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Vertex {
	public Vector3f positions;
	public Vector3f normals;
	
	public Vector2f uvs;
	
	public Vertex(Vector3f positions, Vector2f uvs, Vector3f normals) {
		this.positions = positions;
		this.uvs = uvs;
		this.normals = normals;
	}
}
