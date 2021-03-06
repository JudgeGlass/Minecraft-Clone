package net.sytes.judgeglass.lwjgl.renderEngine.world;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block.Type;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockChest;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockCobbleStone;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockDirt;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockFarmLand;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockGlass;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockGold;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockGrass;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockOakLeaves;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockOakLog;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockOakWood;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockRedFlower;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockSand;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockStone;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockTallGrass;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockWater;
import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Blocks.BlockYellowFlower;
import net.sytes.judgeglass.lwjgl.renderEngine.tools.Vector2;

public class BlockTextureHandler {
	private Block.Type type;

	public Vector2[] frontFace;
	public Vector2[] backFace;
	public Vector2[] topFace;
	public Vector2[] bottomFace;
	public Vector2[] leftFace;
	public Vector2[] rightFace;

	public void setType(Type type) {
		this.type = type;

		getFaces();
	}
	
	public Block.Type getType(){
		return type;
	}

	private void getFaces() {
		switch (type) {
		case GRASS:
			setFaces(BlockGrass.UV_FRONT, BlockGrass.UV_BACK, BlockGrass.UV_TOP, BlockGrass.UV_BOTTOM,
					BlockGrass.UV_LEFT, BlockGrass.UV_RIGHT);
			break;
		case DIRT:
			setFaces(BlockDirt.UV_FRONT, BlockDirt.UV_BACK, BlockDirt.UV_TOP, BlockDirt.UV_BOTTOM, BlockDirt.UV_LEFT,
					BlockDirt.UV_RIGHT);
			break;

		case STONE:
			setFaces(BlockStone.UV_FRONT, BlockStone.UV_BACK, BlockStone.UV_TOP, BlockStone.UV_BOTTOM,
					BlockStone.UV_LEFT, BlockStone.UV_RIGHT);
			break;

		case OAK_LOG:
			setFaces(BlockOakLog.UV_FRONT, BlockOakLog.UV_BACK, BlockOakLog.UV_TOP, BlockOakLog.UV_BOTTOM,
					BlockOakLog.UV_LEFT, BlockOakLog.UV_RIGHT);
			break;

		case OAK_LEAVES:
			setFaces(BlockOakLeaves.UV_FRONT, BlockOakLeaves.UV_BACK, BlockOakLeaves.UV_TOP, BlockOakLeaves.UV_BOTTOM,
					BlockOakLeaves.UV_LEFT, BlockOakLeaves.UV_RIGHT);
			break;
		
		case GOLD_BLOCK:
			setFaces(BlockGold.UV_FRONT, BlockGold.UV_BACK, BlockGold.UV_TOP, BlockGold.UV_BOTTOM,
					BlockGold.UV_LEFT, BlockGold.UV_RIGHT);
			break;
			
		case SAND:
			setFaces(BlockSand.UV_FRONT, BlockSand.UV_BACK, BlockSand.UV_TOP, BlockSand.UV_BOTTOM,
					BlockSand.UV_LEFT, BlockSand.UV_RIGHT);
			break;
		case WATER:
			setFaces(BlockWater.UV_FRONT, BlockWater.UV_BACK, BlockWater.UV_TOP, BlockWater.UV_BOTTOM,
					BlockWater.UV_LEFT, BlockWater.UV_RIGHT);
			break;
		case CHEST:
			setFaces(BlockChest.UV_FRONT, BlockChest.UV_BACK, BlockChest.UV_TOP, BlockChest.UV_BOTTOM,
					BlockChest.UV_LEFT, BlockChest.UV_RIGHT);
			break;
			
		case GLASS:
			setFaces(BlockGlass.UV_FRONT, BlockGlass.UV_BACK, BlockGlass.UV_TOP, BlockGlass.UV_BOTTOM,
					BlockGlass.UV_LEFT, BlockGlass.UV_RIGHT);
			break;
			
		case FARMLAND:
			setFaces(BlockFarmLand.UV_FRONT, BlockFarmLand.UV_BACK, BlockFarmLand.UV_TOP, BlockFarmLand.UV_BOTTOM,
					BlockFarmLand.UV_LEFT, BlockFarmLand.UV_RIGHT);
			break;	
			
		case OAK_WOOD:
			setFaces(BlockOakWood.UV_FRONT, BlockOakWood.UV_BACK, BlockOakWood.UV_TOP, BlockOakWood.UV_BOTTOM,
					BlockOakWood.UV_LEFT, BlockOakWood.UV_RIGHT);
			break;
			
		case COBBLESTONE:
			setFaces(BlockCobbleStone.UV_FRONT, BlockCobbleStone.UV_BACK, BlockCobbleStone.UV_TOP, BlockCobbleStone.UV_BOTTOM,
					BlockCobbleStone.UV_LEFT, BlockCobbleStone.UV_RIGHT);
			break;
			
		case TALLGRASS:
			setFaces(BlockTallGrass.UV_FRONT, BlockTallGrass.UV_BACK, null, null, null, null);
			break;
		
		case YELLOWFLOWER:
			setFaces(BlockYellowFlower.UV_FRONT, BlockYellowFlower.UV_BACK, null, null, null, null);
			break;
			
		case REDFLOWER:
			setFaces(BlockRedFlower.UV_FRONT, BlockRedFlower.UV_BACK, null, null, null, null);
			break;

		default:

		}
	}

	private void setFaces(Vector2[] front, Vector2[] back, Vector2[] top, Vector2[] bottom, Vector2[] left,
			Vector2[] right) {
		frontFace = front;
		backFace = back;
		topFace = top;
		bottomFace = bottom;
		leftFace = left;
		rightFace = right;
	}
}
