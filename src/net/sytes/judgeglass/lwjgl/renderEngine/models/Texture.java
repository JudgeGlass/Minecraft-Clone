package net.sytes.judgeglass.lwjgl.renderEngine.models;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture {

	public int loadTexture(String resourceName, int mode) {
		// load png file
		PNGDecoder decoder = null;
		try {
			decoder = new PNGDecoder(new FileInputStream(resourceName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create a byte buffer big enough to store RGBA values
		ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

		// decode
		try {
			decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// flip the buffer so its ready to read
		buffer.flip();

		// create a texture
		int id = glGenTextures();

		// bind the texture
		glBindTexture(GL_TEXTURE_2D, id);

		// tell opengl how to unpack bytes
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		// set the texture parameters, can be GL_LINEAR or GL_NEAREST
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, mode);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, mode);

		// upload texture
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				buffer);

		// Generate Mip Map
		glGenerateMipmap(GL_TEXTURE_2D);

		return id;
	}

}
