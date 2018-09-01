package net.sytes.judgeglass.lwjgl.renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;

import net.sytes.judgeglass.lwjgl.renderEngine.entities.Camera;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Maths;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/vertex.glsl";
	private static final String FRAGMENT_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/fragment.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
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
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f mat) {
		super.loadMatrix4f(location_transformationMatrix, mat);
	}
	
	public void loadProjectionMatrix(Matrix4f mat) {
		super.loadMatrix4f(location_projectionMatrix, mat);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMat = Maths.createViewMatrix(camera);
		super.loadMatrix4f(location_viewMatrix, viewMat);
	}
	
}
