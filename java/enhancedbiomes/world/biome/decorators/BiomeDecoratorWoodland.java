package enhancedbiomes.world.biome.decorators;

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

public class BiomeDecoratorWoodland extends BiomeDecorator
{
	public BiomeDecoratorWoodland() {
		super();
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
		for(i = 0; doGen && i < this.sandPerChunk2; ++i) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
		for(i = 0; doGen && i < this.clayPerChunk; ++i) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.clayGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
		for(i = 0; doGen && i < this.sandPerChunk; ++i) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
		}

		if(biome instanceof BiomeGenWoodlandBase) ((BiomeGenWoodlandBase) biome).generateTrees(currentWorld, randomGenerator, chunk_X, chunk_Z);
		
		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
		for(j = 0; doGen && j < this.bigMushroomsPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
		for(j = 0; doGen && j < this.flowersPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) + 32);
			String s = biome.func_150572_a(this.randomGenerator, k, i1, l);
			BlockFlower blockflower = BlockFlower.func_149857_e(s);

			if(blockflower.getMaterial() != Material.air) {
				this.yellowFlowerGen.func_150550_a(blockflower, BlockFlower.func_149856_f(s));
				this.yellowFlowerGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
		for(j = 0; doGen && j < this.grassPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			WorldGenerator worldgenerator = biome.getRandomWorldGenForGrass(this.randomGenerator);
			worldgenerator.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
		for(j = 0; doGen && j < this.deadBushPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			(new WorldGenDeadBush(Blocks.deadbush)).generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
		for(j = 0; doGen && j < this.waterlilyPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			for(i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2); i1 > 0 && this.currentWorld.isAirBlock(k, i1 - 1, l); --i1) {
				;
			}

			this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
		for(j = 0; doGen && j < this.mushroomsPerChunk; ++j) {
			if(this.randomGenerator.nextInt(4) == 0) {
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				i1 = this.currentWorld.getHeightValue(k, l);
				this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}

			if(this.randomGenerator.nextInt(8) == 0) {
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
				this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
			}
		}

		if(doGen && this.randomGenerator.nextInt(4) == 0) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		if(doGen && this.randomGenerator.nextInt(8) == 0) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
		for(j = 0; doGen && j < this.reedsPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		for(j = 0; doGen && j < 10; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, PUMPKIN);
		if(doGen && this.randomGenerator.nextInt(32) == 0) {
			j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
			(new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, j, l, k);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CACTUS);
		for(j = 0; doGen && j < this.cactiPerChunk; ++j) {
			k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			l = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
			this.cactusGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
		}

		doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
		if(doGen && this.generateLakes) {
			for(j = 0; j < 50; ++j) {
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
				i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
			}

			for(j = 0; j < 20; ++j) {
				k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
				i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				(new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
			}
		}

		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}

	private int nextInt(int i) {
		if(i <= 1) return 0;
		return this.randomGenerator.nextInt(i);
	}
}