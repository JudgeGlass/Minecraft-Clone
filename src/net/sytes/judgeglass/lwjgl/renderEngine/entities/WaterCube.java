package net.sytes.judgeglass.lwjgl.renderEngine.entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class WaterCube {
public static Vector3f[] PX_POS = {
			
			new Vector3f(0.5f,0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,0.5f),
			new Vector3f(0.5f,-0.47f,0.5f),
			new Vector3f(0.5f,0.47f,0.5f),
			new Vector3f(0.5f,0.47f,-0.5f)
			
	};
	
	public static Vector3f[] NX_POS = {
			
			new Vector3f(-0.5f,0.47f,-0.5f),
			new Vector3f(-0.5f,-0.47f,-0.5f),
			new Vector3f(-0.5f,-0.47f,0.5f),
			new Vector3f(-0.5f,-0.47f,0.5f),
			new Vector3f(-0.5f,0.47f,0.5f),
			new Vector3f(-0.5f,0.47f,-0.5f)
			
	};
	
	public static Vector3f[] PY_POS = {
			
			new Vector3f(-0.47f,0.47f,0.47f),
			new Vector3f(-0.47f,0.47f,-0.47f),
			new Vector3f(0.47f,0.47f,-0.47f),
			new Vector3f(0.47f,0.47f,-0.47f),
			new Vector3f(0.47f,0.47f,0.47f),
			new Vector3f(-0.47f,0.47f,0.47f)
			
	};
	
	public static Vector3f[] NY_POS = {
			
			new Vector3f(-0.5f,-0.47f,0.5f),
			new Vector3f(-0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,0.5f),
			new Vector3f(-0.5f,-0.47f,0.5f)
			
	};
	
	public static Vector3f[] PZ_POS = {
			
			new Vector3f(-0.5f,0.47f,0.5f),
			new Vector3f(-0.5f,-0.47f,0.5f),
			new Vector3f(0.5f,-0.47f,0.5f),
			new Vector3f(0.5f,-0.47f,0.5f),
			new Vector3f(0.5f,0.47f,0.5f),
			new Vector3f(-0.5f,0.47f,0.5f)
			
	};
	
	public static Vector3f[] NZ_POS = {
			
			new Vector3f(-0.5f,0.47f,-0.5f),
			new Vector3f(-0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,-0.47f,-0.5f),
			new Vector3f(0.5f,0.47f,-0.5f),
			new Vector3f(-0.5f,0.47f,-0.5f)
			
	};
	
	public static Vector2f[] UV = {
			
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 0.f),
			new Vector2f(0.f, 0.f)
			
	};
	
	public static Vector3f[] NORMALS = {
			
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f),
			new Vector3f(0.f, 0.f, 0.f)
			
	};
	
	public static float[] vertices = {
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
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
	
	public static float[] uv = {
			
			0, 0,
			0, 1,
			1, 1,
			1, 0,
			0, 0,
			0, 1,
			1, 1,
			1, 0,
			0, 0,
			0, 1,
			1, 1,
			1, 0,
			0, 0,
			0, 1,
			1, 1,
			1, 0,
			0, 0,
			0, 1,
			1, 1,
			1, 0,
			0, 0,
			0, 1,
			1, 1,
			1, 0
			
	};
}
