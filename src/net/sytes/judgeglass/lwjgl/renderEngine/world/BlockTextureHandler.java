package net.sytes.judgeglass.lwjgl.renderEngine.world;

import org.lwjgl.util.vector.Vector2f;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block.Type;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockDirt;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockGrass;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockStone;

public class BlockTextureHandler {
	private Block.Type type;
	
	public Vector2f[] frontFace;
	public Vector2f[] backFace;
	public Vector2f[] topFace;
	public Vector2f[] bottomFace;
	public Vector2f[] leftFace;
	public Vector2f[] rightFace;
	
	public void setType(Type type) {
		this.type = type;
		
		getFaces();
	}
	
	private void getFaces() {
		switch(type) {
		case GRASS:
			setFaces(BlockGrass.UV_FRONT, BlockGrass.UV_BACK, BlockGrass.UV_TOP, BlockGrass.UV_BOTTOM, BlockGrass.UV_LEFT, BlockGrass.UV_RIGHT);
			break;
		case DIRT:
			setFaces(BlockDirt.UV_FRONT, BlockDirt.UV_BACK, BlockDirt.UV_TOP, BlockDirt.UV_BOTTOM, BlockDirt.UV_LEFT, BlockDirt.UV_RIGHT);
			break;
			
		case STONE:
			setFaces(BlockStone.UV_FRONT, BlockStone.UV_BACK, BlockStone.UV_TOP, BlockStone.UV_BOTTOM, BlockStone.UV_LEFT, BlockStone.UV_RIGHT);
			break;
		
		default:
			
		}
	}
	
	private void setFaces(Vector2f[] front, Vector2f[] back, Vector2f[] top, Vector2f[] bottom, Vector2f[] left, Vector2f[] right) {
		frontFace = front;
		backFace = back;
		topFace = top;
		bottomFace = bottom;
		leftFace = left;
		rightFace = right;
	}
}
