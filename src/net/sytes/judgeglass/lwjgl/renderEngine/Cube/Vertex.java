package net.sytes.judgeglass.lwjgl.renderEngine.Cube;

import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector2;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector3;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector3i;

public class Vertex {
	public Vector3 positions;
	public Vector3 normals;
	
	public Vector2 uvs;
	
	public Vertex(Vector3 positions, Vector2 uvs, Vector3 normals) {
		this.positions = positions;
		this.uvs = uvs;
		this.normals = normals;
	}
}
