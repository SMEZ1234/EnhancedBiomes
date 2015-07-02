package enhancedbiomes.world.biome.snow;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.EnhancedBiomesSnow;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import enhancedbiomes.world.gen.WorldGenRockSpire;

public class BiomeGenDrifts extends BiomeGenSnowBase
{
	public BiomeGenDrifts(int par1) {
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.snow;
		this.theBiomeDecorator.treesPerChunk = 16;
	}

	private final int maxDuneComp = 5;
	private final int rateDuneComp = 4;

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);

		if(par2Random.nextInt(3) == 0) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int j5 = par4 + par2Random.nextInt(16) + 8;

			int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5);
			(new WorldGenRockSpire(new Block[] {EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getDominantStone(j2, j5, par1World)), Blocks.ice}, new byte[] {EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), 0}, 6)).generate(par1World, par2Random, j2, l3, j5);
		}
	}
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return TreeGen.fir(par1Random);
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);
		
		boolean biome00 = e.biomeArray[0] != EnhancedBiomesSnow.biomeDrifts;
		boolean biome01 = e.biomeArray[15] != EnhancedBiomesSnow.biomeDrifts;
		boolean biome10 = e.biomeArray[240] != EnhancedBiomesSnow.biomeDrifts;
		boolean biome11 = e.biomeArray[255] != EnhancedBiomesSnow.biomeDrifts;
		int pos = ((z + 1) / 2 + x + (Math.abs(e.chunkX) % 2 * 8)) % 16;
		for(int h = 0; h < getDuneHeight(pos) - getDuneModification(x, z, biome00, biome01, biome10, biome11) + worldGenNoise; h++) {
			e.blockArray[preHeightIndex + ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1] = Blocks.stone;
		}
	}

	private int getDuneHeight(int pos) {
		int[] heights = new int[] {3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3};
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

	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise) {
		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.snow;

		if(Math.abs(stoneNoise) > 4) {
			this.topBlock = Blocks.grass;
			this.fillerBlock = Blocks.dirt;
		}

		this.genBiomeTerrain(world, rand, blocks, metas, x, z, stoneNoise);
	}
}
