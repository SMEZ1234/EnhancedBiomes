package enhancedbiomes.handlers;

import java.awt.Event;
import java.util.ArrayList;
import java.util.Random;

import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.ChunkProviderEnhancedBiomes;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;
import enhancedbiomes.world.biome.EnhancedBiomesSand;
import enhancedbiomes.world.biome.EnhancedBiomesSnow;
import enhancedbiomes.world.biome.EnhancedBiomesWoodland;
import enhancedbiomes.world.biome.base.BiomeGenEBBase;
//import enhancedbiomes.world.biome.decorators.BiomeDecoratorVanillaWoodland;
import enhancedbiomes.world.biome.decorators.BiomeDecoratorWoodland;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;

public class ReplaceBiomeBlocksHandler
{
	private double[] stoneNoise = new double[256];
	private double[] stoneVariantNoise = new double[256];
	private NoiseGeneratorPerlin stonePerlin;

	private NoiseGeneratorPerlin worldGenPerlin;
	private double[] worldGenNoise = new double[256];

	private Random rand;

	public ReplaceBiomeBlocksHandler() {
	}

	/** Modifies the default biome block placement **/
	@SubscribeEvent
	public void replaceBlocksForBiome(ReplaceBiomeBlocks e) {
		if(e.biomeArray != null && (e.chunkProvider instanceof ChunkProviderGenerate || e.chunkProvider instanceof ChunkProviderEnhancedBiomes) && e.world.provider.dimensionId == 0) {
			if(this.rand == null) this.rand = new Random(e.world.getSeed());
			if(this.stonePerlin == null) this.stonePerlin = new NoiseGeneratorPerlin(rand, 4);
			if(this.worldGenPerlin == null) this.worldGenPerlin = new NoiseGeneratorPerlin(rand, 4);

			double d0 = 0.03125D;
			this.stoneNoise = this.stonePerlin.func_151599_a(this.stoneNoise, (double) (e.chunkX * 16), (double) (e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
			this.stoneVariantNoise = this.stonePerlin.func_151599_a(this.stoneVariantNoise, (double) (e.chunkX * 16), (double) (e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
			this.worldGenNoise = this.worldGenPerlin.func_151599_a(this.worldGenNoise, (double) (e.chunkX * 16), (double) (e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

			for(int xInChunk = 0; xInChunk < 16; ++xInChunk) {
				for(int zInChunk = 0; zInChunk < 16; ++zInChunk) {
					EnhancedBiomesMod.setStoneNoiseForCoords(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, this.stoneVariantNoise[zInChunk + xInChunk * 16]);

					int heightRange = e.blockArray.length / 256;

					int x = (e.chunkX * 16 + xInChunk) & 15;
					int z = (e.chunkZ * 16 + zInChunk) & 15;
					int preHeightIndex = (z * 16 + x) * heightRange;

					BiomeGenBase biomegenbase = e.biomeArray[zInChunk + xInChunk * 16];

					if(biomegenbase instanceof BiomeGenEBBase) {
						((BiomeGenEBBase) biomegenbase).replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise[x * 16 + z]);
					}
					/*else if(biomegenbase instanceof BiomeGenForest || biomegenbase instanceof BiomeGenTaiga || biomegenbase instanceof BiomeGenJungle) {
						((BiomeDecoratorVanillaWoodland) biomegenbase.theBiomeDecorator).setTreeCheck(worldGenNoise[x * 16 + z] <= 2.5D, x * 16 + z);
					}*/

					biomegenbase.genTerrainBlocks(e.world, rand, e.blockArray, e.metaArray, e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, this.stoneNoise[zInChunk + xInChunk * 16]);

					for(int yInChunk = 255; yInChunk >= 0; --yInChunk) {
						int index = (z * 16 + x) * heightRange + yInChunk;

						if(e.blockArray[index] == Blocks.stone) {
							e.blockArray[index] = EnhancedBiomesMod.getRockForCoordsAndBiome(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, biomegenbase.biomeID);
							e.metaArray[index] = EnhancedBiomesMod.getRockMetaForCoordsAndBiome(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, biomegenbase.biomeID);
						}
						else if(e.blockArray[index] == Blocks.cobblestone) {
							e.blockArray[index] = EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getRockForCoordsAndBiome(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, biomegenbase.biomeID));
							e.metaArray[index] = EnhancedBiomesMod.getRockMetaForCoordsAndBiome(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, biomegenbase.biomeID);
						}
						else if(e.blockArray[index] == Blocks.dirt) {
							if(e.metaArray[index] == 0) {
								e.blockArray[index] = EnhancedBiomesMod.soilList[biomegenbase.biomeID];
								e.metaArray[index] = EnhancedBiomesMod.soilMetaList[biomegenbase.biomeID];
							}
							else if(e.metaArray[index] == 1 && EnhancedBiomesMod.soilList[biomegenbase.biomeID] != Blocks.dirt) {
								e.blockArray[index] = EnhancedBiomesMod.soilList[biomegenbase.biomeID];
								e.metaArray[index] = (byte) (EnhancedBiomesMod.soilMetaList[biomegenbase.biomeID] + 8);
							}
						}
						else if(e.blockArray[index] == Blocks.grass) {
							e.blockArray[index] = EnhancedBiomesMod.grassList[biomegenbase.biomeID];
							e.metaArray[index] = EnhancedBiomesMod.grassMetaList[biomegenbase.biomeID];
						}
						else if(biomegenbase == EnhancedBiomesSand.biomeRedDesert) {
							if(e.blockArray[index] == Blocks.sand) {
								e.metaArray[index] = 1;
							}
							else if(e.blockArray[index] == Blocks.sandstone) {
								e.blockArray[index] = EnhancedBiomesBlocks.stoneEB;
								e.metaArray[index] = 2;
							}
						}
						else if(biomegenbase == EnhancedBiomesSand.biomeRockyDesert) {
							if(e.blockArray[index] == Blocks.glass) e.blockArray[index] = Blocks.stone;
							else if(e.blockArray[index] == Blocks.sponge) e.blockArray[index] = Blocks.cobblestone;
						}
					}
				}
			}
			new MapGenCavesEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
			new MapGenRavineEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
			e.setResult(Result.DENY);
		}
	}

	public static int getTopBlock(Block[] blocks, int preHeightIndex, int heightRange) {
		for(int h = heightRange - 1; h >= 0; h--)
			if(blocks[preHeightIndex + h] != null && blocks[preHeightIndex + h].isOpaqueCube()) {
				return h;
			}
		return -1;
	}
}