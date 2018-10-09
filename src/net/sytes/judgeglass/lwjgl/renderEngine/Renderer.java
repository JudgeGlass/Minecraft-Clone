package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.entities.Entity;
import net.sytes.judgeglass.lwjgl.renderEngine.models.Texture;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;
import net.sytes.judgeglass.lwjgl.renderEngine.shaders.StaticShader;
import net.sytes.judgeglass.lwjgl.renderEngine.textures.ModelTexture;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Maths;

public class Renderer {

	public static final float FOV = 70;
	public static final float NEAR = 0.05f;
	public static final float FAR = 1000.0f;

	private Matrix4f projectionMatrix;

	private StaticShader shader;
	
	Texture tL = new Texture();
	ModelTexture texture = new ModelTexture(tL.loadTexture("assets/textures/atlas.png", GL_NEAREST));

	public Renderer(StaticShader shader) {
		this.shader = shader;
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Map<TextureModel, List<Entity>> entities) {

		for (TextureModel model : entities.keySet()) {

			prepareTextureModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareInstance(entity);
				 //glDrawElements(GL_TRIANGLES, model.getRawModel().getVertexCount(),
				 //GL11.GL_UNSIGNED_INT, 0);
				glDrawArrays(GL_TRIANGLES, 0, model.getRawModel().getVertexCount() / 3);
			}

			unbindTextureModel();
		}
	}

	private void prepareTextureModel(TextureModel model) {
		glBindVertexArray(model.getRawModel().getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glEnableVertexAttribArray(2);

		
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, texture.getID());
	}

	private void unbindTextureModel() {
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Matrix4f transformMatrix = Maths.createTransformationMatrix(new Vector3f(entity.getPosition().x,
												entity.getPosition().y, entity.getPosition().z), entity.getRotX(),
				entity.getRotY(), entity.getRotZ(), entity.getScale());

		shader.loadTransformationMatrix(transformMatrix);
	}

	public Matrix4f getProjectedMatrix() {
		return projectionMatrix;
	}

	public void prepare() {
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glEnable(GL_DEPTH_TEST);
		
		glClearColor(0.4f, 0.7f, 1.0f, 1);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	private void createProjectionMatrix() {
		float aspectRatio = (float) DisplayManager.WIDTH / (float) DisplayManager.HEIGHT;
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR - NEAR;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR + NEAR) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR * FAR) / frustum_length);
		projectionMatrix.m33 = 0;
	}

}
