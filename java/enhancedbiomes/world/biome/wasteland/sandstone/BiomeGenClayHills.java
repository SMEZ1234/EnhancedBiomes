package enhancedbiomes.world.biome.wasteland.sandstone;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenSandstoneBase;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenClayHills extends BiomeGenSandstoneBase
{
	private WorldGenerator theWorldGenerator;

	public BiomeGenClayHills(int par1) {
		super(par1);
		this.topBlock = Blocks.hardened_clay;
		this.fillerBlock = Blocks.hardened_clay;
	}/*

		public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);
		int var6;
		int var7;
		int var8;

		for(int c = 20; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = par2Random.nextInt(120);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenMinableEnhancedBiomes(Blocks.clay, 0, 50, Blocks.hardened_clay)).generate(par1World, par2Random, j2, l3, j5);
		}

		for(int c = 20; c > 0; c--) {
			int j2 = par3 + par2Random.nextInt(16) + 8;
			int l3 = par2Random.nextInt(120);
			int j5 = par4 + par2Random.nextInt(16) + 8;
			(new WorldGenMinableEnhancedBiomes(Blocks.hardened_clay, 0, 50, Blocks.sandstone)).generate(par1World, par2Random, j2, l3, j5);
		}
		}*/

	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise) {
		this.topBlock = Blocks.stained_hardened_clay;
		this.field_150604_aj = 0;

		if(stoneNoise > 4) this.topBlock = Blocks.clay;
		else if(stoneNoise > 0.5D && stoneNoise < 2) this.topBlock = Blocks.hardened_clay;
		else this.field_150604_aj = stoneNoise < -4 ? 0 : stoneNoise < -2 ? 4 : stoneNoise < -0.5 ? 1 : stoneNoise < 0.5 ? 14 : 8;

		this.genBiomeTerrain(world, rand, blocks, metas, x, z, stoneNoise);
	}
}
