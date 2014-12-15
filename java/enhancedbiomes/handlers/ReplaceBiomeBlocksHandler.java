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
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;

public class ReplaceBiomeBlocksHandler
{
	private static double[] stoneNoise = new double[256];
	private static double[] stoneVariantNoise = new double[256];
	private static NoiseGeneratorPerlin perlin;
	
	private final int maxDuneComp = 5;
	private final int rateDuneComp = 4;
	
	public ReplaceBiomeBlocksHandler() {
	}

	/** Modifies the default biome block placement **/
	@SubscribeEvent
	public void replaceBlocksForBiome(ReplaceBiomeBlocks e) {
		if(e.biomeArray != null && (e.chunkProvider instanceof ChunkProviderGenerate || e.chunkProvider instanceof ChunkProviderEnhancedBiomes) && e.world.provider.dimensionId == 0) {
			if(this.perlin == null) this.perlin = new NoiseGeneratorPerlin(e.world.rand, 4);
			
			double d0 = 0.03125D;
			this.stoneNoise = this.perlin.func_151599_a(this.stoneNoise, (double) (e.chunkX * 16), (double) (e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
			this.stoneVariantNoise = this.perlin.func_151599_a(this.stoneVariantNoise, (double) (e.chunkX * 16), (double) (e.chunkZ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
			
			for(int xInChunk = 0; xInChunk < 16; ++xInChunk) {
				for(int zInChunk = 0; zInChunk < 16; ++zInChunk) {
					EnhancedBiomesMod.setStoneNoiseForCoords(e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, this.stoneVariantNoise[zInChunk + xInChunk * 16]);

					int x = (e.chunkX * 16 + xInChunk) & 15;
					int z = (e.chunkZ * 16 + zInChunk) & 15;
					int heightRange = e.blockArray.length / 256;
					int preHeightIndex = (z * 16 + x) * heightRange;

					BiomeGenBase biomegenbase = e.biomeArray[zInChunk + xInChunk * 16];

					if(biomegenbase == EnhancedBiomesSand.biomeSahara) {
						boolean biome00 = e.biomeArray[0] != EnhancedBiomesSand.biomeSahara;
						boolean biome01 = e.biomeArray[15] != EnhancedBiomesSand.biomeSahara;
						boolean biome10 = e.biomeArray[240] != EnhancedBiomesSand.biomeSahara;
						boolean biome11 = e.biomeArray[255] != EnhancedBiomesSand.biomeSahara;
						int pos = ((z + 1) / 2 + x + (Math.abs(e.chunkX) % 2 * 8)) % 16;
						for(int h = 0; h < getDuneHeight(pos) - getDuneModification(x, z, biome00, biome01, biome10, biome11); h++) {
							e.blockArray[preHeightIndex + getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1] = Blocks.stone;
						}
					}

					biomegenbase.genTerrainBlocks(e.world, e.world.rand, e.blockArray, e.metaArray, e.chunkX * 16 + xInChunk, e.chunkZ * 16 + zInChunk, this.stoneNoise[zInChunk + xInChunk * 16]);

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
							e.blockArray[index] = EnhancedBiomesMod.soilList[biomegenbase.biomeID];
							e.metaArray[index] = EnhancedBiomesMod.soilMetaList[biomegenbase.biomeID];
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
					}
				}
			}
			new MapGenCavesEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
			new MapGenRavineEnhancedBiomes().create(e.chunkProvider, e.world, e.chunkX, e.chunkZ, e.blockArray, e.metaArray);
			e.setResult(Result.DENY);
		}
	}

	@SubscribeEvent
	public void receiveNoiseGens(InitNoiseGensEvent e) {
		if(e.world.provider.dimensionId == 0 && e.originalNoiseGens[3] instanceof NoiseGeneratorPerlin && this.perlin == null) this.perlin = (NoiseGeneratorPerlin) e.originalNoiseGens[3];
		e.newNoiseGens = e.originalNoiseGens;
	}

	private int getTopBlock(Block[] blocks, int preHeightIndex, int heightRange) {
		for (int h = heightRange - 1; h >= 0; h--)
			if (blocks[preHeightIndex + h] != null && blocks[preHeightIndex + h].isOpaqueCube()) {
				return h;
			}
		return -1;
	}
	
	private int getDuneHeight(int pos) {
		int[] heights = new int[] {5, 5, 4, 4, 3, 3, 2, 2, 1, 1, 1, 2, 3, 4, 5, 6};
		if(pos < heights.length) return heights[pos];
		return 1;
	}
	
	private int getDuneModification(int x, int z, boolean biome00, boolean biome01, boolean biome10, boolean biome11) {
		int mod = 0;
		if(biome00) {
			int dis = x + z;
			mod += Math.max(maxDuneComp - (dis/rateDuneComp), 0);
		}
		if(biome01) {
			int dis = x + 16 - z;
			mod += Math.max(maxDuneComp - (dis/rateDuneComp), 0);
		}
		if(biome10) {
			int dis = 16 - x + z;
			mod += Math.max(maxDuneComp - (dis/rateDuneComp), 0);
		}
		if(biome11) {
			int dis =  32 - x - z;
			mod += Math.max(maxDuneComp - (dis/rateDuneComp), 0);
		}
		return mod;
	}
}