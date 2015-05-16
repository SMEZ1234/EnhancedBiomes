package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.blocks.BlockSaplingEnhancedBiomes;
import enhancedbiomes.blocks.BlockWithMeta;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenMeadow extends BiomeGenPlainsBase
{
	public BiomeGenMeadow(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = 15;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenAbstractTree func_150567_a(Random par1Random) {
		return (WorldGenAbstractTree) (TreeGen.poplar(par1Random));
	}

	public void decorate(World worldObj, Random rand, int x, int z) {
		super.decorate(worldObj, rand, x, z);

		for(int c = 25; c > 0; c--) {
			int j2 = x + rand.nextInt(16) + 8;
			int l3 = rand.nextInt(120);
			int j5 = z + rand.nextInt(16) + 8;
			if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5))) {
				(TreeGen.poplar(rand)).generate(worldObj, rand, j2, l3, j5);
			}
		}

		for(int c = 8; c > 0; c--) {
			int j2 = x + rand.nextInt(16) + 8;
			int j5 = z + rand.nextInt(16) + 8;
			int l3 = worldObj.getTopSolidOrLiquidBlock(j2, j5);
			if(worldObj.getBlock(j2, l3, j5) == Blocks.air && EnhancedBiomesBlocks.isGrass(worldObj.getBlock(j2, l3 - 1, j5))) {
				WorldGenFlowers flowerGen = new WorldGenFlowers(Blocks.red_flower);
				flowerGen.func_150550_a(Blocks.red_flower, rand.nextInt(9));
				flowerGen.generate(worldObj, rand, j2, l3, j5);
			}
		}
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);
		int y = ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1;
				
		if(e.world.rand.nextInt(100) == 0 && EnhancedBiomesBlocks.isGrass(e.blockArray[preHeightIndex + y - 1])) {
			byte meta = (byte) (worldGenNoise + 5);
			Block flower = Blocks.red_flower;
			if(meta < 0) meta = 0;
			else if(meta > 8) {
				flower = Blocks.yellow_flower;
				meta = 0;
			}
			e.blockArray[preHeightIndex + y] = flower;
			e.metaArray[preHeightIndex + y] = meta;
		}
	}
}
