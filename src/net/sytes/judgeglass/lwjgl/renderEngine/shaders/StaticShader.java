package net.sytes.judgeglass.lwjgl.renderEngine.shaders;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/vertex.glsl";
	private static final String FRAGMENT_FILE = "src/net/sytes/judgeglass/lwjgl/renderEngine/shaders/fragment.glsl";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
}
