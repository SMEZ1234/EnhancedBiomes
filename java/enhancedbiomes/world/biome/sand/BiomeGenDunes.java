package enhancedbiomes.world.biome.sand;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.world.biome.EnhancedBiomesSand;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.gen.WorldGenRockSpire;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenDunes extends BiomeGenSandBase
{
	public BiomeGenDunes(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.sand;
		this.fillerBlock = Blocks.sand;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.reedsPerChunk = 50;
		this.theBiomeDecorator.cactiPerChunk = 10;
	}

	private final int maxDuneComp = 21;
	private final double rateDuneComp = 0.5D;

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);

		if(par2Random.nextInt(500) == 0) {
			int var5 = par3 + par2Random.nextInt(16) + 8;
			int var6 = par4 + par2Random.nextInt(16) + 8;
			WorldGenDesertWells var7 = new WorldGenDesertWells();
			var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
		}
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);
		
		boolean biome00 = e.biomeArray[0] != EnhancedBiomesSand.biomeSahara;
		boolean biome01 = e.biomeArray[15] != EnhancedBiomesSand.biomeSahara;
		boolean biome10 = e.biomeArray[240] != EnhancedBiomesSand.biomeSahara;
		boolean biome11 = e.biomeArray[255] != EnhancedBiomesSand.biomeSahara;
		
		int workX = (e.chunkX % 4) * 16 + x;
		if(workX < 0) workX += 64;
		int workZ = (e.chunkZ % 8) * 16 + z;
		if(workZ < 0) workZ += 128;
		
		int pos = (workX - ((int) (workZ/2))) % 64;
		if(pos < 0) pos += 64;
		for(int h = 0; h < getDuneHeight(pos) - getDuneModification(x, z, biome00, biome01, biome10, biome11); h++)
			e.blockArray[preHeightIndex + ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1] = Blocks.stone;
	}

	private int getDuneHeight(int pos) {
		int[] heights = new int[] {21, 21, 20, 20, 19, 19, 18, 18, 17, 17, 16, 16, 15, 15, 14, 14,
								   13, 13, 12, 12, 11, 11, 10, 10,  9,  9,  8,  8,  7,  7,  6,  6,
								    5,  5,  4,  4,  3,  3,  2,  2,  1,  1,  1,  2,  3,  4,  5,  6,
								    7,  8,  9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
		if(pos < heights.length) return heights[pos];
		return 1;
	}

	private int getDuneModification(int x, int z, boolean biome00, boolean biome01, boolean biome10, boolean biome11) {
		int mod = 0;
		if(biome00) {
			int dis = x + z;
			mod += Math.max(maxDuneComp - (dis / rateDuneComp), 0);
		}
		if(biome01) {
			int dis = x + 16 - z;
			mod += Math.max(maxDuneComp - (dis / rateDuneComp), 0);
		}
		if(biome10) {
			int dis = 16 - x + z;
			mod += Math.max(maxDuneComp - (dis / rateDuneComp), 0);
		}
		if(biome11) {
			int dis = 32 - x - z;
			mod += Math.max(maxDuneComp - (dis / rateDuneComp), 0);
		}
		return mod;
	}
}
