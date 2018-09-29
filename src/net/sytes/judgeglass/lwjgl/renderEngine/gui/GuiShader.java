package net.sytes.judgeglass.lwjgl.renderEngine.gui;

import org.lwjgl.util.vector.Matrix4f;

import net.sytes.judgeglass.lwjgl.renderEngine.shaders.ShaderProgram;

public class GuiShader extends ShaderProgram{
	private static final String VERTEX_FILE = "assets/graphics/vertexGUI.glsl";
    private static final String FRAGMENT_FILE = "assets/graphics/fragmentGUI.glsl";
     
    private int location_transformationMatrix;
 
    public GuiShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
     
    public void loadTransformation(Matrix4f matrix){
        super.loadMatrix4f(location_transformationMatrix, matrix);
    }
 
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
     
     
}
