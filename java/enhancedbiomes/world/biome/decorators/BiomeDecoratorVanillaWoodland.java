/*package enhancedbiomes.world.biome.decorators;

import java.util.Random;

import enhancedbiomes.world.biome.base.BiomeGenWoodlandBase;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.*;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.terraingen.*;

public class BiomeDecoratorVanillaWoodland extends BiomeDecorator
{
	public BiomeDecorator oldbiomedeco;

	public BiomeDecoratorVanillaWoodland(BiomeDecorator originalDecorator) {
		super();
		oldbiomedeco = originalDecorator;
	}

	boolean[] treeCheck = new boolean[256];

	public void setTreeCheck(boolean check, int pos) {
		treeCheck[pos] = check;
	}

	protected void genDecorations(BiomeGenBase biome) {
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		this.generateOres();
		int i;
		int j;
		int k;

		int l;
		int i1;

		boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND);
		for(i = 0; doGen && i < oldbiomedeco.sandPerChunk2; ++i) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			oldbiomedeco.sandGen.generate(currentWorld, randomGenerator, j, currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
		for(i = 0; doGen && i < oldbiomedeco.clayPerChunk; ++i) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			oldbiomedeco.clayGen.generate(currentWorld, randomGenerator, j, currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
		for(i = 0; doGen && i < oldbiomedeco.sandPerChunk; ++i) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			oldbiomedeco.gravelAsSandGen.generate(currentWorld, randomGenerator, j, currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		if(biome instanceof BiomeGenWoodlandBase) ((BiomeGenWoodlandBase) biome).generateTrees(currentWorld, randomGenerator, chunk_X, chunk_Z);
		else {
			i = oldbiomedeco.treesPerChunk;

			if(randomGenerator.nextInt(10) == 0) {
				++i;
			}

			int x;
			int y;
			int z;

			doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
			for(j = 0; doGen && j < i; ++j) {
				int xInc = randomGenerator.nextInt(16);
				int zInc = randomGenerator.nextInt(16);
				x = chunk_X + xInc + 8;
				z = chunk_Z + zInc + 8;
				if(treeCheck[xInc * 16 + zInc]) {
					y = currentWorld.getHeightValue(x, z);
					WorldGenAbstractTree worldgenabstracttree = biome.func_150567_a(randomGenerator);
					worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

					if(worldgenabstracttree.generate(currentWorld, randomGenerator, x, y, z)) {
						worldgenabstracttree.func_150524_b(currentWorld, randomGenerator, x, y, z);
					}
				}
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
		for(j = 0; doGen && j < oldbiomedeco.bigMushroomsPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			oldbiomedeco.bigMushroomGen.generate(currentWorld, randomGenerator, k, currentWorld.getHeightValue(k, l), l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
		for(j = 0; doGen && j < oldbiomedeco.flowersPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) + 32);
			String s = biome.func_150572_a(randomGenerator, k, i1, l);
			BlockFlower blockflower = BlockFlower.func_149857_e(s);

			if(blockflower.getMaterial() != Material.air) {
				oldbiomedeco.yellowFlowerGen.func_150550_a(blockflower, BlockFlower.func_149856_f(s));
				oldbiomedeco.yellowFlowerGen.generate(currentWorld, randomGenerator, k, i1, l);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
		for(j = 0; doGen && j < oldbiomedeco.grassPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
			WorldGenerator worldgenerator = biome.getRandomWorldGenForGrass(randomGenerator);
			worldgenerator.generate(currentWorld, randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
		for(j = 0; doGen && j < oldbiomedeco.deadBushPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
			(new WorldGenDeadBush(Blocks.deadbush)).generate(currentWorld, randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
		for(j = 0; doGen && j < oldbiomedeco.waterlilyPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;

			for(i1 = nextInt(currentWorld.getHeightValue(k, l) * 2); i1 > 0 && currentWorld.isAirBlock(k, i1 - 1, l); --i1) {
				;
			}

			oldbiomedeco.waterlilyGen.generate(currentWorld, randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
		for(j = 0; doGen && j < oldbiomedeco.mushroomsPerChunk; ++j) {
			if(randomGenerator.nextInt(4) == 0) {
				k = chunk_X + randomGenerator.nextInt(16) + 8;
				l = chunk_Z + randomGenerator.nextInt(16) + 8;
				i1 = currentWorld.getHeightValue(k, l);
				oldbiomedeco.mushroomBrownGen.generate(currentWorld, randomGenerator, k, i1, l);
			}

			if(randomGenerator.nextInt(8) == 0) {
				k = chunk_X + randomGenerator.nextInt(16) + 8;
				l = chunk_Z + randomGenerator.nextInt(16) + 8;
				i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
				oldbiomedeco.mushroomRedGen.generate(currentWorld, randomGenerator, k, i1, l);
			}
		}

		if(doGen && randomGenerator.nextInt(4) == 0) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			l = nextInt(currentWorld.getHeightValue(j, k) * 2);
			oldbiomedeco.mushroomBrownGen.generate(currentWorld, randomGenerator, j, l, k);
		}

		if(doGen && randomGenerator.nextInt(8) == 0) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			l = nextInt(currentWorld.getHeightValue(j, k) * 2);
			oldbiomedeco.mushroomRedGen.generate(currentWorld, randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
		for(j = 0; doGen && j < oldbiomedeco.reedsPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
			oldbiomedeco.reedGen.generate(currentWorld, randomGenerator, k, i1, l);
		}

		for(j = 0; doGen && j < 10; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
			oldbiomedeco.reedGen.generate(currentWorld, randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, PUMPKIN);
		if(doGen && randomGenerator.nextInt(32) == 0) {
			j = chunk_X + randomGenerator.nextInt(16) + 8;
			k = chunk_Z + randomGenerator.nextInt(16) + 8;
			l = nextInt(currentWorld.getHeightValue(j, k) * 2);
			(new WorldGenPumpkin()).generate(currentWorld, randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CACTUS);
		for(j = 0; doGen && j < oldbiomedeco.cactiPerChunk; ++j) {
			k = chunk_X + randomGenerator.nextInt(16) + 8;
			l = chunk_Z + randomGenerator.nextInt(16) + 8;
			i1 = nextInt(currentWorld.getHeightValue(k, l) * 2);
			oldbiomedeco.cactusGen.generate(currentWorld, randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
		if(doGen && oldbiomedeco.generateLakes) {
			for(j = 0; j < 50; ++j) {
				k = chunk_X + randomGenerator.nextInt(16) + 8;
				l = randomGenerator.nextInt(randomGenerator.nextInt(248) + 8);
				i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_water)).generate(currentWorld, randomGenerator, k, l, i1);
			}

			for(j = 0; j < 20; ++j) {
				k = chunk_X + randomGenerator.nextInt(16) + 8;
				l = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(240) + 8) + 8);
				i1 = chunk_Z + randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_lava)).generate(currentWorld, randomGenerator, k, l, i1);
			}
		}

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}

	private int nextInt(int i) {
		if(i <= 1) return 0;
		return randomGenerator.nextInt(i);
	}

	/**
	 * Generates ores in the current chunk
	 *//*
	protected void generateOres() {
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.dirtGen, chunk_X, chunk_Z, DIRT)) this.genStandardOre1(20, oldbiomedeco.dirtGen, 0, 256);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.gravelGen, chunk_X, chunk_Z, GRAVEL)) this.genStandardOre1(10, oldbiomedeco.gravelGen, 0, 256);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.coalGen, chunk_X, chunk_Z, COAL)) this.genStandardOre1(20, oldbiomedeco.coalGen, 0, 128);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.ironGen, chunk_X, chunk_Z, IRON)) this.genStandardOre1(20, oldbiomedeco.ironGen, 0, 64);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.goldGen, chunk_X, chunk_Z, GOLD)) this.genStandardOre1(2, oldbiomedeco.goldGen, 0, 32);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.redstoneGen, chunk_X, chunk_Z, REDSTONE)) this.genStandardOre1(8, oldbiomedeco.redstoneGen, 0, 16);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.diamondGen, chunk_X, chunk_Z, DIAMOND)) this.genStandardOre1(1, oldbiomedeco.diamondGen, 0, 16);
		if(TerrainGen.generateOre(currentWorld, randomGenerator, oldbiomedeco.lapisGen, chunk_X, chunk_Z, LAPIS)) this.genStandardOre2(1, oldbiomedeco.lapisGen, 16, 16);
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}
}*/