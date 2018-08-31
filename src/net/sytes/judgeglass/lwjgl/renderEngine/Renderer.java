package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.opengl.GL11;

public class Renderer {
	public void prepare() {
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(0, 0, 0, 1);
	}
	
	public void render(RawModel model) {
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(0);
		glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
}
