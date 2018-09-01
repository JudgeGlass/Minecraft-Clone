package net.sytes.judgeglass.lwjgl.renderEngine.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OBJLoader {
	public static float[] getVertices(String file) {
		StringBuilder strBuilder = new StringBuilder();
		
		ArrayList<Float> verts = new ArrayList<>();
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while((line = buffer.readLine()) != null) {
				String[] seg = line.split(" ");
				if(seg[0].equals("v")) {
					verts.add(Float.parseFloat(seg[1]));
					verts.add(Float.parseFloat(seg[2]));
					verts.add(Float.parseFloat(seg[3]));
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		float[] array = new float[verts.size()];
		for(int i = 0; i < verts.size(); i++) {
			array[i] = verts.get(i);
		}
		
		return array;
	}
	
	public static int[] getIndices(String file) {
		StringBuilder strBuilder = new StringBuilder();
		
		ArrayList<Integer> verts = new ArrayList<>();
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			while((line = buffer.readLine()) != null) {
				String[] seg = line.split(" ");
				if(seg[0].equals("i")) {
					verts.add(Integer.parseInt(seg[1]));
					verts.add(Integer.parseInt(seg[2]));
					verts.add(Integer.parseInt(seg[3]));
					verts.add(Integer.parseInt(seg[4]));
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		int[] array = new int[verts.size()];
		for(int i = 0; i < verts.size(); i++) {
			array[i] = verts.get(i);
		}
		
		return array;
	}
}
