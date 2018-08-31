package net.sytes.judgeglass.lwjgl.renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/vertex.glsl";
	private static final String FRAGMENT_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/fragment.glsl";
	
	private int location_transformationMatrix = 0;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f mat) {
		super.loadMatrix4f(location_transformationMatrix, mat);
	}
	
}
