package net.sytes.judgeglass.lwjgl.renderEngine;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL15.*;

public class Loader {
	
	private List<Integer> vaos = new ArrayList<>();
	private List<Integer> vbos = new ArrayList<>();
	
	public RawModel loadToVAO(float[] positions, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, positions);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public void clean() {
		for(int vao: vaos) {
			glDeleteVertexArrays(vao);
		}
		
		for(int vbo: vbos) {
			glDeleteBuffers(vbo);
		}
	}
	
	private int createVAO() {
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, createFloatBuffer(data), GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices) {
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntegerBuffer(indices), GL_STATIC_DRAW);
	}
	
	private IntBuffer createIntegerBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
