package net.sytes.judgeglass.lwjgl.renderEngine.Chunk;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Vertex;
import net.sytes.judgeglass.lwjgl.renderEngine.entities.AbstractCube;
import net.sytes.judgeglass.lwjgl.renderEngine.world.BlockTextureHandler;

public class ChunkMesh {
	private List<Vertex> vertices;

	private List<Float> positionsList;
	private List<Float> uvsList;
	private List<Float> normalsList;
	
	private BlockTextureHandler blockHandler = new BlockTextureHandler();

	public float[] positions;
	public float[] uvs;
	public float[] normals;

	public Chunk chunk;

	public ChunkMesh(Chunk chunk) {
		this.chunk = chunk;

		vertices = new ArrayList<Vertex>();
		positionsList = new ArrayList<Float>();
		uvsList = new ArrayList<Float>();
		normalsList = new ArrayList<Float>();

		buildMesh();
		populateList();
	}

	public void update(Chunk chunk) {
		this.chunk = chunk;

		buildMesh();
		populateList();
	}

	private void buildMesh() {
		//blockHandler.setType(Block.Type.GRASS);
		// Loop through in chunk and determine what face to draw
		for (int i = 0; i < chunk.blocks.size(); i++) {
			Block blockI = chunk.blocks.get(i);
			

			boolean px = false;
			boolean nx = false;
			boolean py = false;
			boolean ny = true;
			boolean pz = false;
			boolean nz = false;

			if(blockI.hideFaces) {
				for (int j = 0; j < chunk.blocks.size(); j++) {
					Block blockJ = chunk.blocks.get(j);
					blockHandler.setType(blockI.type);
					
	
					// PX
					if (((blockI.x + 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
						px = true;
					}
	
					// NX
					if (((blockI.x - 1) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
						nx = true;
					}
	
					// PY
					if (((blockI.x) == (blockJ.x)) && ((blockI.y + 1) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
						py = true;
					}
	
					// NY
					if (((blockI.x) == (blockJ.x)) && ((blockI.y - 1) == (blockJ.y)) && ((blockI.z) == (blockJ.z))) {
						ny = true;
					}
	
					// PZ
					if (((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z + 1) == (blockJ.z))) {
						pz = true;
					}
	
					// NZ
					if (((blockI.x) == (blockJ.x)) && ((blockI.y) == (blockJ.y)) && ((blockI.z - 1) == (blockJ.z))) {
						nz = true;
					}
	
				}
			}

			// Add visible faces to mesh

			if (!px) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.PX_POS[k].x + blockI.x,
											AbstractCube.PX_POS[k].y + blockI.y, AbstractCube.PX_POS[k].z + blockI.z), // Left or Right?
									blockHandler.leftFace[k], AbstractCube.NORMALS[k]));
				}
			}

			if (!nx) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.NX_POS[k].x + blockI.x,
											AbstractCube.NX_POS[k].y + blockI.y, AbstractCube.NX_POS[k].z + blockI.z), // Left or Right?
									blockHandler.rightFace[k], AbstractCube.NORMALS[k]));
				}
			}else if(!blockI.hideFaces){
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.NX_POS[k].x + blockI.x,
											AbstractCube.NX_POS[k].y + blockI.y, AbstractCube.NX_POS[k].z + blockI.z), // Left or Right?
									blockHandler.rightFace[k], AbstractCube.NORMALS[k]));
				}
			}

			if (!py) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.PY_POS[k].x + blockI.x,
											AbstractCube.PY_POS[k].y + blockI.y, AbstractCube.PY_POS[k].z + blockI.z), // Top face
									blockHandler.topFace[k], AbstractCube.NORMALS[k]));
				}
			}

			if (!ny) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.NY_POS[k].x + blockI.x,
											AbstractCube.NY_POS[k].y + blockI.y, AbstractCube.NY_POS[k].z + blockI.z), // Bottom face
									blockHandler.bottomFace[k], AbstractCube.NORMALS[k]));
				}
			}

			if (!pz) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.PZ_POS[k].x + blockI.x,
											AbstractCube.PZ_POS[k].y + blockI.y, AbstractCube.PZ_POS[k].z + blockI.z), // Front or back?
									blockHandler.frontFace[k], AbstractCube.NORMALS[k]));
				}
			}

			if (!nz) {
				for (int k = 0; k < 6; k++) {
					vertices.add(
							new Vertex(
									new Vector3f(AbstractCube.NZ_POS[k].x + blockI.x,
											AbstractCube.NZ_POS[k].y + blockI.y, AbstractCube.NZ_POS[k].z + blockI.z), // Front or back?
									blockHandler.backFace[k], AbstractCube.NORMALS[k]));
				}
			}
		}
	}

	private void populateList() {
		for (int i = 0; i < vertices.size(); i++) {
			positionsList.add(vertices.get(i).positions.x);
			positionsList.add(vertices.get(i).positions.y);
			positionsList.add(vertices.get(i).positions.z);
			uvsList.add(vertices.get(i).uvs.x);
			uvsList.add(vertices.get(i).uvs.y);
			normalsList.add(vertices.get(i).normals.x);
			normalsList.add(vertices.get(i).normals.y);
			normalsList.add(vertices.get(i).normals.z);
		}

		positions = new float[positionsList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];

		for (int i = 0; i < positionsList.size(); i++) {
			positions[i] = positionsList.get(i);
		}

		for (int i = 0; i < uvsList.size(); i++) {
			uvs[i] = uvsList.get(i);
		}

		for (int i = 0; i < normalsList.size(); i++) {
			normals[i] = normalsList.get(i);
		}

		positionsList.clear();
		uvsList.clear();
		normalsList.clear();
	}
}
