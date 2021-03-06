package enhancedbiomes.world.biome.wasteland.rock;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.BlockWithMeta;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenRockBase;
import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenTreesEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenStoneCanyon extends BiomeGenRockBase
{
	private WorldGenerator theWorldGenerator;

	public BiomeGenStoneCanyon(int par1) {
		super(par1);
		this.theWorldGenerator = new WorldGenMinable(Blocks.monster_egg, 8);
		this.topBlock = Blocks.stone;
		this.fillerBlock = Blocks.stone;
		this.theBiomeDecorator.treesPerChunk = 30;
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);

		int h = ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange);

		if(worldGenNoise > 2 && h > 88) {
			for(h += 0; h > 75 - worldGenNoise * 3; h--)
				e.blockArray[preHeightIndex + h] = h < 63 ? Blocks.flowing_water : Blocks.air;
			e.blockArray[preHeightIndex + h] = worldGenNoise > 4.2 ? Blocks.flowing_water : worldGenNoise > 3 ? Blocks.dirt : Blocks.stone;
			if(worldGenNoise > 3 && worldGenNoise <= 4.2) e.metaArray[preHeightIndex + h] = BlockWithMeta.coarse_dirt.meta;
		}
		else if(h >= 75 && (worldGenNoise > 1.85D || (h < 85 && worldGenNoise > 1.35D))) {
			for(int h1 = h; h1 >= (75 + h) / 2; h1--)
				e.blockArray[preHeightIndex + h1] = Blocks.air;
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return (WorldGenAbstractTree) TreeGen.fir(par1Random);
	}

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);
		int var6;
		int var7;
		int var8;

		for(int c = 5; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = par2Random.nextInt(120);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenMinableEnhancedBiomes(EnhancedBiomesMod.soilList[biomeID], EnhancedBiomesMod.soilMetaList[biomeID] + 8, 50, EnhancedBiomesMod.getDominantStone(j2, j5, par1World))).generate(par1World, par2Random, j2, l3, j5);
		}

		for(int c = 20; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = par2Random.nextInt(120);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenShrub(1, 1)).generate(par1World, par2Random, j2, l3, j5);
		}

		for(int c = 500; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = par2Random.nextInt(120);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenTallGrass(Blocks.tallgrass, 1)).generate(par1World, par2Random, j2, l3, j5);
		}

		for(var6 = 0; var6 < var5; ++var6) {
			var7 = par3 + par2Random.nextInt(16);
			var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			Block var10 = par1World.getBlock(var7, var8, var9);

			if(var10.isReplaceableOreGen(par1World, var7, var8, var9, Blocks.stone)) {
				EnhancedBiomesBlocks.setStoneBlock(var7, var8, var9, Blocks.iron_ore, EnhancedBiomesBlocks.oreIronEB, 0, 2, par1World);
			}
		}

		for(var5 = 0; var5 < 7; ++var5) {
			var6 = par3 + par2Random.nextInt(16);
			var7 = par2Random.nextInt(64);
			var8 = par4 + par2Random.nextInt(16);
			this.theWorldGenerator.generate(par1World, par2Random, var6, var7, var8);
		}
	}
}
