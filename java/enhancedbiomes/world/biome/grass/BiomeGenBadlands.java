package enhancedbiomes.world.biome.grass;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.helpers.EnhancedBiomesMath;
import enhancedbiomes.helpers.EnhancedBiomesWorldHelper;
import enhancedbiomes.world.biome.base.BiomeGenGrassBase;
import enhancedbiomes.world.gen.WorldGenBadlands;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenBadlands extends BiomeGenGrassBase
{
	public BiomeGenBadlands(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.reedsPerChunk = 50;
		this.theBiomeDecorator.cactiPerChunk = 10;
		this.fillerBlock = Blocks.sandstone;
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);

		double noise = (EnhancedBiomesMath.sqrtMagnitude(Math.cos(worldGenNoise / 1.5D * Math.PI)) + 1) * (3.5D + Math.abs(worldGenNoise) / 2);
		
		int top = ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange);
		for(int a = 0; a <= noise; a++) e.blockArray[preHeightIndex + top + a] = Blocks.stone;
		/*if(worldGenNoise > 1) {
			for(int a = 0; a < 2 * worldGenNoise + 3; a++)
				e.blockArray[preHeightIndex + ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1] = Blocks.stone;
		}
		else if(Math.abs(worldGenNoise) % 3 < 1) {
			for(int a = 0; a < 2 * Math.abs(worldGenNoise) + 3; a++)
				e.blockArray[preHeightIndex + ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange)] = ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) < 63 ? Blocks.flowing_water : null;
		}*/
	}
}
