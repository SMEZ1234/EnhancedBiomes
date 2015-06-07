package enhancedbiomes.world.biome.woodland;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenFirForest extends BiomeGenWoodlandBase
{
	public BiomeGenFirForest(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = 10;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return (WorldGenAbstractTree) (par1Random.nextInt(10) == 0 ? TreeGen.fir_large(par1Random) : TreeGen.fir(par1Random));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return new WorldGenTallGrass(Blocks.tallgrass, 2);
	}

	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise) {
		this.topBlock = Blocks.grass;
		this.field_150604_aj = 0;

		if(stoneNoise > 1) {
			this.topBlock = Blocks.dirt;
			this.field_150604_aj = 2;
		}

		this.genBiomeTerrain(world, rand, blocks, metas, x, z, stoneNoise);
	}
}
