package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import enhancedbiomes.world.gen.WorldGenRockSpire;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenLowHills extends BiomeGenPlainsBase
{
	public BiomeGenLowHills(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		this.theBiomeDecorator.grassPerChunk = -999;
	}

	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);

		for(int a = 0; a < 4; a++) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int j5 = par4 + par2Random.nextInt(16) + 8;

			int l3 = par1World.getTopSolidOrLiquidBlock(j2, j5);
			if(par1World.getBlock(j2 + 5, l3 - 2, j5) == Blocks.air && par1World.getBlock(j2, l3 - 2, j5 + 5) == Blocks.air && 
			   par1World.getBlock(j2 - 5, l3 - 2, j5) == Blocks.air && par1World.getBlock(j2, l3 - 2, j5 - 5) == Blocks.air)
			(new WorldGenRockSpire(new Block[] {EnhancedBiomesMod.getDominantStone(j2, j5, par1World), EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getDominantStone(j2, j5, par1World))}, new byte[] {EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World), EnhancedBiomesMod.getDominantStoneMeta(j2, j5, par1World)}, 5)).generate(par1World, par2Random, j2, l3, j5);
		}
	}
}
