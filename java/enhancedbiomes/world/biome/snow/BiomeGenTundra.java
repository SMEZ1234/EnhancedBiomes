package enhancedbiomes.world.biome.snow;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enhancedbiomes.world.biome.base.BiomeGenSnowBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTundra extends BiomeGenSnowBase
{
	public BiomeGenTundra(int par1) {
		super(par1);
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.topBlock = Blocks.grass;
		this.field_150604_aj = 0;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return (WorldGenAbstractTree) new WorldGenShrub(1, 0);
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

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		return new WorldGenTallGrass(Blocks.tallgrass, 1);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
		return 0xD4AD00;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
		return 0x800404;
	}
}
