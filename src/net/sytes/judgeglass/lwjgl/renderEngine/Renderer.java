package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.opengl.GL11;

import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.models.TextureModel;

public class Renderer {
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0, 0, 0, 1);
	}
	
	public void render(TextureModel textureModel) {
		RawModel model = textureModel.getRawModel();
		
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureModel.getTexture().getID());
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
}
