package enhancedbiomes.world.biome.woodland;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenCypressForest extends BiomeGenWoodlandBase
{
	public BiomeGenCypressForest(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 5;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return TreeGen.cypress(par1Random);
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
