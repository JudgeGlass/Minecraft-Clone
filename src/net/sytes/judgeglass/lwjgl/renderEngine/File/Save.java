package net.sytes.judgeglass.lwjgl.renderEngine.File;

import java.util.List;

import net.sytes.judgeglass.lwjgl.renderEngine.Chunk.ChunkMesh;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;

public class Save {
	public static void saveChunks(List<ChunkMesh> chunks) {
		String file = "";
		for(ChunkMesh mesh: chunks) {
			for(Block block: mesh.chunk.blocks) {
				file += block.x + "," + block.y + "," + block.z + ";" + block.type + "\n";
				System.out.println("DATA: " + block.x + "," + block.y + "," + block.z + ";" + block.type + " SIZE: " + (mesh.chunk.blocks.size()));
			}
		}
		
		System.out.println("FILE: " + file);
	}
}
