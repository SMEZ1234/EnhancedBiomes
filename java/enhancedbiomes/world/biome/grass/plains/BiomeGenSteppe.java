package enhancedbiomes.world.biome.grass.plains;

import java.util.Random;

import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.world.biome.base.BiomeGenPlainsBase;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenSteppe extends BiomeGenPlainsBase
{
	public BiomeGenSteppe(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
		this.theBiomeDecorator.reedsPerChunk = -999;
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);

		double noise = Math.abs(worldGenNoise);
		if(noise > 4) {
			double dif = Math.sqrt(noise - 4) * 5;
			int top = ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange);
			for(int a = 0; a < dif; a++) e.blockArray[preHeightIndex + top + a] = Blocks.stone;
		}
	}
}
