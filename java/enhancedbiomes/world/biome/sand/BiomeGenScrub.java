package enhancedbiomes.world.biome.sand;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.BlockWithMeta;
import enhancedbiomes.world.biome.base.BiomeGenSandBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenSpikedBush;
import enhancedbiomes.world.gen.WorldGenTinyTree;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenScrub extends BiomeGenSandBase
{
	private WorldGenerator theWorldGenerator;

	public BiomeGenScrub(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
		this.topBlock = BlockWithMeta.coarse_dirt.block;
		this.field_150604_aj = BlockWithMeta.coarse_dirt.meta;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return (WorldGenAbstractTree) (par1Random.nextInt(2) == 0 ? 
			new WorldGenSpikedBush(Blocks.log, 0, Blocks.leaves, 0, EnhancedBiomesMod.soilList[biomeID]) : 
			new WorldGenTinyTree(Blocks.log, 0, Blocks.leaves, 0, EnhancedBiomesMod.soilList[biomeID]));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return new WorldGenTallGrass(Blocks.tallgrass, 1);
	}

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);
		int var6;
		int var7;
		int var8;

		for(int c = 50; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = 64 + par2Random.nextInt(64);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenMinableEnhancedBiomes(Blocks.sand, 0, 50, EnhancedBiomesMod.soilList[biomeID], true)).generate(par1World, par2Random, j2, l3, j5);
		}
	}
}
