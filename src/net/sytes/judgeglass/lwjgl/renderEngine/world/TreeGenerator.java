package net.sytes.judgeglass.lwjgl.renderEngine.world;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import net.sytes.judgeglass.lwjgl.renderEngine.Cube.Block;

public class TreeGenerator {
	public static List<Block> makeTree(Vector3f pos, List<Block> blocks) {
		int x = (int) pos.x;
		int y = (int) pos.y;
		int z = (int) pos.z;
		Block t1 = new Block(x, y + 1, z, Block.Type.OAK_LOG, true);
		Block t2 = new Block(x, y + 2, z, Block.Type.OAK_LOG, true);
		Block t3 = new Block(x, y + 3, z, Block.Type.OAK_LOG, true);
		Block t4 = new Block(x, y + 4, z, Block.Type.OAK_LOG, false);
		Block t5 = new Block(x, y + 5, z, Block.Type.OAK_LOG, false);

		blocks.add(t1);
		blocks.add(t2);
		blocks.add(t3);
		blocks.add(t4);
		blocks.add(t5);

		Block l1 = new Block(x, y + 4, z + 1, Block.Type.OAK_LEAVES, true);
		Block l2 = new Block(x, y + 4, z + 2, Block.Type.OAK_LEAVES, true);
		Block l3 = new Block(x + 1, y + 4, z + 1, Block.Type.OAK_LEAVES, true);
		Block l4 = new Block(x + 2, y + 4, z + 1, Block.Type.OAK_LEAVES, true);

		Block l5 = new Block(x + 1, y + 4, z + 2, Block.Type.OAK_LEAVES, true);
		Block l6 = new Block(x + 2, y + 4, z + 2, Block.Type.OAK_LEAVES, true);

		Block l11 = new Block(x, y + 4, z - 1, Block.Type.OAK_LEAVES, true);
		Block l12 = new Block(x, y + 4, z - 2, Block.Type.OAK_LEAVES, true);

		Block l7 = new Block(x - 1, y + 4, z - 1, Block.Type.OAK_LEAVES, true);
		Block l8 = new Block(x - 2, y + 4, z - 1, Block.Type.OAK_LEAVES, true);

		Block l9 = new Block(x - 1, y + 4, z - 2, Block.Type.OAK_LEAVES, true);
		Block l10 = new Block(x - 2, y + 4, z - 2, Block.Type.OAK_LEAVES, true);

		Block l13 = new Block(x - 1, y + 4, z, Block.Type.OAK_LEAVES, true);
		Block l14 = new Block(x - 2, y + 4, z, Block.Type.OAK_LEAVES, true);
		Block l15 = new Block(x - 1, y + 4, z + 1, Block.Type.OAK_LEAVES, true);
		Block l16 = new Block(x - 2, y + 4, z + 1, Block.Type.OAK_LEAVES, true);
		Block l17 = new Block(x - 1, y + 4, z + 2, Block.Type.OAK_LEAVES, true);
		Block l18 = new Block(x - 2, y + 4, z + 2, Block.Type.OAK_LEAVES, true);

		Block l19 = new Block(x + 1, y + 4, z, Block.Type.OAK_LEAVES, true);
		Block l20 = new Block(x + 2, y + 4, z, Block.Type.OAK_LEAVES, true);
		Block l21 = new Block(x + 1, y + 4, z - 1, Block.Type.OAK_LEAVES, true);
		Block l22 = new Block(x + 2, y + 4, z - 1, Block.Type.OAK_LEAVES, true);
		Block l23 = new Block(x + 1, y + 4, z - 2, Block.Type.OAK_LEAVES, true);
		Block l24 = new Block(x + 2, y + 4, z - 2, Block.Type.OAK_LEAVES, true);

		blocks.add(l1);
		blocks.add(l2);
		blocks.add(l3);
		blocks.add(l4);
		blocks.add(l5);
		blocks.add(l6);
		blocks.add(l7);
		blocks.add(l8);
		blocks.add(l9);
		blocks.add(l10);
		blocks.add(l11);
		blocks.add(l12);
		blocks.add(l13);
		blocks.add(l14);
		blocks.add(l15);
		blocks.add(l16);
		blocks.add(l17);
		blocks.add(l18);
		blocks.add(l19);
		blocks.add(l20);
		blocks.add(l21);
		blocks.add(l22);
		blocks.add(l23);
		blocks.add(l24);

		Block ll1 = new Block(x, y + 5, z + 1, Block.Type.OAK_LEAVES, true);
		Block ll2 = new Block(x, y + 5, z + 2, Block.Type.OAK_LEAVES, true);
		Block ll3 = new Block(x + 1, y + 5, z + 1, Block.Type.OAK_LEAVES, true);
		Block ll4 = new Block(x + 2, y + 5, z + 1, Block.Type.OAK_LEAVES, true);

		Block ll5 = new Block(x + 1, y + 5, z + 2, Block.Type.OAK_LEAVES, true);
		// Block ll6 = new Block(x + 2, y + 5, z + 2, Block.Type.OAK_LEAVES, true);

		Block ll11 = new Block(x, y + 5, z - 1, Block.Type.OAK_LEAVES, true);
		Block ll12 = new Block(x, y + 5, z - 2, Block.Type.OAK_LEAVES, true);

		Block ll7 = new Block(x - 1, y + 5, z - 1, Block.Type.OAK_LEAVES, true);
		Block ll8 = new Block(x - 2, y + 5, z - 1, Block.Type.OAK_LEAVES, true);

		Block ll9 = new Block(x - 1, y + 5, z - 2, Block.Type.OAK_LEAVES, true);
		// Block ll10 = new Block(x - 2, y + 5, z - 2, Block.Type.OAK_LEAVES, true);

		Block ll13 = new Block(x - 1, y + 5, z, Block.Type.OAK_LEAVES, true);
		Block ll14 = new Block(x - 2, y + 5, z, Block.Type.OAK_LEAVES, true);
		Block ll15 = new Block(x - 1, y + 5, z + 1, Block.Type.OAK_LEAVES, true);
		Block ll16 = new Block(x - 2, y + 5, z + 1, Block.Type.OAK_LEAVES, true);
		Block ll17 = new Block(x - 1, y + 5, z + 2, Block.Type.OAK_LEAVES, true);
		Block ll18 = new Block(x - 2, y + 5, z + 2, Block.Type.OAK_LEAVES, true);

		Block ll19 = new Block(x + 1, y + 5, z, Block.Type.OAK_LEAVES, true);
		Block ll20 = new Block(x + 2, y + 5, z, Block.Type.OAK_LEAVES, true);
		Block ll21 = new Block(x + 1, y + 5, z - 1, Block.Type.OAK_LEAVES, true);
		Block ll22 = new Block(x + 2, y + 5, z - 1, Block.Type.OAK_LEAVES, true);
		Block ll23 = new Block(x + 1, y + 5, z - 2, Block.Type.OAK_LEAVES, true);
		Block ll24 = new Block(x + 2, y + 5, z - 2, Block.Type.OAK_LEAVES, true);

		blocks.add(ll1);
		blocks.add(ll2);
		blocks.add(ll3);
		blocks.add(ll4);
		blocks.add(ll5);
		// blocks.add(ll6);
		blocks.add(ll7);
		blocks.add(ll8);
		blocks.add(ll9);
		// blocks.add(ll10);
		blocks.add(ll11);
		blocks.add(ll12);
		blocks.add(ll13);
		blocks.add(ll14);
		blocks.add(ll15);
		blocks.add(ll16);
		blocks.add(ll17);
		blocks.add(ll18);
		blocks.add(ll19);
		blocks.add(ll20);
		blocks.add(ll21);
		blocks.add(ll22);
		blocks.add(ll23);
		blocks.add(ll24);

		Block tt1 = new Block(x + 1, y + 6, z - 1, Block.Type.OAK_LEAVES, true);
		// Block tt2 = new Block(x - 1, y + 6, z - 1, Block.Type.OAK_LEAVES, true);
		Block tt3 = new Block(x + 1, y + 6, z, Block.Type.OAK_LEAVES, true);
		Block tt4 = new Block(x, y + 6, z, Block.Type.OAK_LEAVES, true);
		Block tt5 = new Block(x, y + 6, z - 1, Block.Type.OAK_LEAVES, true);

		Block tt6 = new Block(x, y + 6, z + 1, Block.Type.OAK_LEAVES, true);
		Block tt7 = new Block(x - 1, y + 6, z + 1, Block.Type.OAK_LEAVES, true);
		// Block tt8 = new Block(x + 1, y + 6, z + 1, Block.Type.OAK_LEAVES, true);
		Block tt9 = new Block(x - 1, y + 6, z, Block.Type.OAK_LEAVES, true);

		Block tt10 = new Block(x, y + 7, z, Block.Type.OAK_LEAVES, true);
		Block tt11 = new Block(x - 1, y + 7, z, Block.Type.OAK_LEAVES, true);
		Block tt12 = new Block(x + 1, y + 7, z, Block.Type.OAK_LEAVES, true);
		Block tt13 = new Block(x, y + 7, z - 1, Block.Type.OAK_LEAVES, true);
		Block tt14 = new Block(x, y + 7, z, Block.Type.OAK_LEAVES, true);

		blocks.add(tt1);

		blocks.add(tt3);
		blocks.add(tt4);
		blocks.add(tt5);
		blocks.add(tt6);
		blocks.add(tt7);

		blocks.add(tt9);
		blocks.add(tt10);
		blocks.add(tt11);
		blocks.add(tt12);
		blocks.add(tt13);
		blocks.add(tt14);

		return blocks;
	}
}
