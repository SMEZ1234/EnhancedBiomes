package enhancedbiomes.world.gen;

import java.util.Random;

import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTinyTree extends WorldGenAbstractTree
{
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;
	Block growOn;

	public WorldGenTinyTree(Block woodId, int woodMeta, Block leavesId, int leavesMeta, Block growOnBlockID) {
		super(true);
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
		this.growOn = growOnBlockID;
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		if(var1.getBlock(var3, var4 - 1, var5) != growOn) {
			return false;
		}

		for(int i = 0; i < 2; i++) {
			if(var1.getBlock(var3, var4 + i, var5) != Blocks.air) {
				return false;
			}
		}

		var1.setBlock(var3, var4, var5, this.woodId, this.woodMeta, 3);
		setBlockIfEmpty(var3, var4 + 1, var5, this.leavesId, this.leavesMeta, 3, var1);

		return true;
	}
}
