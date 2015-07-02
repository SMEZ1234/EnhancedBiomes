package enhancedbiomes.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.EnhancedBiomesMath;
import enhancedbiomes.world.gen.geometry.WorldGenCylinderIfEmpty;
import static enhancedbiomes.helpers.EnhancedBiomesWorldHelper.setBlockIfEmpty;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenWillow extends WorldGenAbstractTree
{
	int height;
	Block woodId;
	int woodMeta;
	Block leavesId;
	int leavesMeta;

	public WorldGenWillow(int height, Block woodId, int woodMeta, Block leavesId, int leavesMeta) {
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
		
		for(int i = 0; i < this.height; i++) for(int x = -1; x <= 1; x++) for(int z = -1; z <= 1; z++) if(var1.getBlock(var3 + x, var4 + i, var5 + z) != Blocks.air) return false;
		
		//Trunk
		int span = 3;
		
		int disX = (var2.nextInt((span * 2) + 1)) - span, 
			disY = height, 
			disZ = (var2.nextInt((span * 2) + 1)) - span;

		int posX = var3 + disX, 
			posY = var4 + height, 
			posZ = var5 + disZ;

		for(int b = 0; b < height; b++) {
			int x = (int) EnhancedBiomesMath.increaseMagnitude((double) (disX) * (b + 1) / height, 0.5D),
				y = disY * b / height,
				z = (int) EnhancedBiomesMath.increaseMagnitude((double) (disZ) * (b + 1) / height, 0.5D);

			var1.setBlock(var3 + x, var4 + y, var5 + z, this.woodId, this.woodMeta, 3);
		}

		//Crown
		var1.setBlock(posX, posY, posZ, this.leavesId, this.leavesMeta, 3);
		
		for(int x = -1; x <= 1; x++) for(int y = -1; y <= 0; y++) for(int z = -1; z <= 1; z++) 
			setBlockIfEmpty(posX + x, posY + y, posZ + z, this.leavesId, this.leavesMeta, 3, var1);
		
		//Branches
		for(int branches = 0; branches < 16; branches++) {
			int pos = branches % 8 + 1;
			
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

			if(pos == 2 || pos == 4 || pos == 5 || pos == 7) {
				setBlockIfEmpty(posX + 2*mods[0], posY - 1, posZ + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + 2*mods[0], posY - 2, posZ + 2*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + (2+var2.nextInt(2))*mods[0], posY - 3, posZ + (2+var2.nextInt(2))*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				
				int variationX = mods[0] == 0 ? var2.nextInt(3) - 1 : 0,
					variationZ = mods[1] == 0 ? var2.nextInt(3) - 1 : 0;
				
				int variation2X = variationX * (1 + var2.nextInt(2)),
					variation2Z = variationZ * (1 + var2.nextInt(2));
									
				setBlockIfEmpty(posX + 3*mods[0] + variationX, posY - 4, posZ + 3*mods[1] + variationZ, this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + 3*mods[0] + variationX, posY - 5, posZ + 3*mods[1] + variationZ, this.leavesId, this.leavesMeta, 3, var1);
				
				for(int y = -6; y > -8 - var2.nextInt(3); y--) {
					setBlockIfEmpty(posX + 3*mods[0] + variation2X, posY + y, posZ + 3*mods[1] + variation2Z, this.leavesId, this.leavesMeta, 3, var1);
				}
			}
			else {
				int mulX = var2.nextInt(2),
					mulZ = 1 - mulX;
				
				mulX++; mulZ++;
				
				setBlockIfEmpty(posX + mulX*mods[0], posY - 1, posZ + mulZ*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + mulX*mods[0], posY - 2, posZ + mulZ*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + (mulX + var2.nextInt(2))*mods[0], posY - 3, posZ + (mulZ + var2.nextInt(2))*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				
				mulX++; mulZ++;
				
				setBlockIfEmpty(posX + mulX*mods[0], posY - 4, posZ + mulZ*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				setBlockIfEmpty(posX + mulX*mods[0], posY - 5, posZ + mulZ*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				
				mulX += var2.nextInt(2); mulZ += var2.nextInt(2);
				
				for(int y = -6; y > -8 - var2.nextInt(3); y--) {
					setBlockIfEmpty(posX + mulX*mods[0], posY + y, posZ + mulZ*mods[1], this.leavesId, this.leavesMeta, 3, var1);
				}
			}
		}

		return true;
	}
}
