package net.sytes.judgeglass.lwjgl.renderEngine.gui;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL11.*;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;

import net.sytes.judgeglass.lwjgl.renderEngine.Loader;
import net.sytes.judgeglass.lwjgl.renderEngine.models.RawModel;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Maths;

public class GuiRenderer {
	private final RawModel quad;
	private GuiShader shader;

	public GuiRenderer(Loader loader) {
		float[] pos = { -1, 1, -1, -1, 1, 1, 1, -1 };
		quad = loader.loadToVAO(pos);
		shader = new GuiShader();
	}
	
	public void render(List<GuiTexture> guis) {
		shader.start();
		glBindVertexArray(quad.getVaoID());
		glEnableVertexAttribArray(0);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		// render
		for(GuiTexture gui: guis) {
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, gui.getTexture());
			Matrix4f matrix = Maths.createTransformationMatrix(gui.getPos(), gui.getScale());
			shader.loadTransformation(matrix);
			glDrawArrays(GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
		}
		glDisable(GL_BLEND);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		shader.stop();
	}
	
	public void clean() {
		shader.clean();
	}
}
