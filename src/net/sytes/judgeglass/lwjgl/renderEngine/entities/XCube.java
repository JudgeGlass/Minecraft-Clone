package net.sytes.judgeglass.lwjgl.renderEngine.entities;

import org.lwjgl.util.vector.Vector2f;

import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector3;

public class XCube {
	
	public static Vector3[] PZ_POS = {
			
			new Vector3(-0.37f,0.37f,0.37f),
			new Vector3(-0.37f,-0.37f,0.37f),
			new Vector3(0.37f,-0.37f,-0.37f),
			new Vector3(0.37f,-0.37f,-0.37f),
			new Vector3(0.37f,0.37f,-0.37f),
			new Vector3(-0.37f,0.37f,0.37f)
			
	};
	
	public static Vector3[] NZ_POS = {
			
			new Vector3(-0.37f,0.37f,-0.37f),
			new Vector3(-0.37f,-0.37f,-0.37f),
			new Vector3(0.37f,-0.37f,0.37f),
			new Vector3(0.37f,-0.37f,0.37f),
			new Vector3(0.37f,0.37f,0.37f),
			new Vector3(-0.37f,0.37f,-0.37f)
			
	};
	
	public static Vector2f[] UV = {
			
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 0.f),
			new Vector2f(0.f, 0.f)
			
	};
	
	public static Vector3[] NORMALS = {
			
			new Vector3(0.f, 0.f, 0.f),
			new Vector3(0.f, 0.f, 0.f),
			new Vector3(0.f, 0.f, 0.f),
			new Vector3(0.f, 0.f, 0.f),
			new Vector3(0.f, 0.f, 0.f),
			new Vector3(0.f, 0.f, 0.f)
			
	};
}
