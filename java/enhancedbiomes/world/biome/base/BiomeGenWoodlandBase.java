package enhancedbiomes.world.biome.base;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import enhancedbiomes.world.biome.decorators.BiomeDecoratorWoodland;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenWoodlandBase extends BiomeGenEBBase
{
	boolean[] treeCheck = new boolean[256];

	public BiomeGenWoodlandBase(int par1) {
		super(par1);
		this.theBiomeDecorator = new BiomeDecoratorWoodland();
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);
		treeCheck[x * 16 + z] = worldGenNoise <= 2.5D;
	}

	public void generateTrees(World world, Random rand, int chunk_X, int chunk_Z) {
		int i = theBiomeDecorator.treesPerChunk;

		if(rand.nextInt(10) == 0) {
			++i;
		}

		int x;
		int y;
		int z;

		boolean doGen = TerrainGen.decorate(world, rand, chunk_X, chunk_Z, TREE);
		for(int j = 0; doGen && j < i; ++j) {
			int xInc = rand.nextInt(16);
			int zInc = rand.nextInt(16);
			x = chunk_X + xInc + 8;
			z = chunk_Z + zInc + 8;
			if(treeCheck[xInc * 16 + zInc]) {
				y = world.getHeightValue(x, z);
				WorldGenAbstractTree worldgenabstracttree = this.func_150567_a(rand);
				worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

				if(worldgenabstracttree.generate(world, rand, x, y, z)) {
					worldgenabstracttree.func_150524_b(world, rand, x, y, z);
				}
			}
		}
	}
}
