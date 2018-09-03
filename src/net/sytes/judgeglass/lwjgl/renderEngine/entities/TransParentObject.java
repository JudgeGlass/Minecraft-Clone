package net.sytes.judgeglass.lwjgl.renderEngine.entities;

public class TransParentObject {
	public static float[] vertices = {
			-0.5f, 0.5f, 0.5f,
			0.5f, 0.5f, -0.5f,
			-0.5f, -0.5f, 0.5f,
			
			-0.5f, -0.5f, 0.5f,
			0.5f, -0.5f, -0.5f,
			0.5f, 0.5f, -0.5f
	};
	
	public static int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
	};
}
