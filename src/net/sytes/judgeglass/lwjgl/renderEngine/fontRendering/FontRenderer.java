package net.sytes.judgeglass.lwjgl.renderEngine.fontRendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.List;
import java.util.Map;

import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.FontType;
import net.sytes.judgeglass.lwjgl.renderEngine.fontMeshCreator.GUIText;

public class FontRenderer {

	private FontShader shader;

	public FontRenderer() {
		shader = new FontShader();
	}

	public void render(Map<FontType, List<GUIText>> texts) {
		prepare();

		for (FontType font : texts.keySet()) {
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, font.getTextureAtlas());

			for (GUIText text : texts.get(font)) {
				renderText(text);
			}
		}

		endRendering();
	}

	public void cleanUp() {
		shader.clean();
	}

	public void prepare() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDisable(GL_DEPTH_TEST);
		shader.start();
	}

	private void renderText(GUIText text) {
		glBindVertexArray(text.getMesh());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		shader.loadColor(text.getColour());
		shader.loadTranslation(text.getPosition());
		glDrawArrays(GL_TRIANGLES, 0, text.getVertexCount());

		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}

	private void endRendering() {
		shader.stop();
		glDisable(GL_BLEND);
		glEnable(GL_DEPTH_TEST);
	}

}
