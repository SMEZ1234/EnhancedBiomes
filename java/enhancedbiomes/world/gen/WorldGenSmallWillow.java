package enhancedbiomes.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.gen.geometry.WorldGenCylinderIfEmpty;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenSmallWillow extends WorldGenAbstractTree
{
	int height;
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;

	public WorldGenSmallWillow(int height, Block woodId, int woodMeta, Block leavesId, int leavesMeta) {
		super(true);
		this.height = height;
		this.woodId = woodId;
		this.woodMeta = woodMeta;
		this.leavesId = leavesId;
		this.leavesMeta = leavesMeta;
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
		if(!var1.getBlock(var3, var4 - 1, var5).canSustainPlant(var1, var3, var4 - 1, var5, ForgeDirection.UP, (BlockSaplingEnhancedBiomes) EnhancedBiomesBlocks.saplingEB)) {
			return false;
		}

		for(int i = 0; i < this.height; i++) if(var1.getBlock(var3, var4 + i, var5) != Blocks.air) return false;
		for(int i = 0; i < this.height; i++) var1.setBlock(var3, var4 + i, var5, this.woodId, this.woodMeta, 3);
		
		for(int x = -1; x <= 1; x++) for(int z = -1; z <= 1; z++) 
			setBlockIfEmpty(var3 + x, var4 + this.height, var5 + z, this.leavesId, this.leavesMeta, 3, var1);
		
		setBlockIfEmpty(var3, var4 + this.height + 1, var5, this.leavesId, this.leavesMeta, 3, var1);
		
		ArrayList<Integer> positions = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
		
		for(int branches = 0; branches < 3; branches++) {
			int index = var2.nextInt(positions.size());
			int pos = positions.get(index);
			positions.remove(index);
			
			int[] mods = new int[2];
			
			switch(pos) {
				case 2:
					mods[0] = -1;
					break;
				case 4:
					mods[1] = -1;
					break;
				case 5:
					mods[1] =  1;
					break;
				case 7:
					mods[0] =  1;
					break;
				case 1:
					mods[0] = -1;
					mods[1] = -1;
					break;
				case 3:
					mods[0] = -1;
					mods[1] =  1;
					break;
				case 6:
					mods[0] =  1;
					mods[1] = -1;
					break;
				case 8:
					mods[0] =  1;
					mods[1] =  1;
					break;
			}

			setBlockIfEmpty(var3 + 2*mods[0], var4 + this.height, var5 + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
			setBlockIfEmpty(var3 + 2*mods[0], var4 + this.height - 1, var5 + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
			
			if(pos == 2 || pos == 4 || pos == 5 || pos == 7) {
				setBlockIfEmpty(var3 + 3*mods[0], var4 + this.height - 1, var5 + 3*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + 3*mods[0], var4 + this.height - 2, var5 + 3*mods[1], this.leavesId, this.leavesMeta, 3, var1);
			}
			else if(var2.nextInt(2) == 0) {
				setBlockIfEmpty(var3 + 2*mods[0], var4 + this.height, var5 + mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + 3*mods[0], var4 + this.height - 1, var5 + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + 3*mods[0], var4 + this.height - 2, var5 + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
			}
			else {
				setBlockIfEmpty(var3 + mods[0], var4 + this.height, var5 + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + 2*mods[0], var4 + this.height - 1, var5 + 3*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(var3 + 2*mods[0], var4 + this.height - 2, var5 + 3*mods[1], this.leavesId, this.leavesMeta, 3, var1);
			}
		}

		return true;
	}
}
