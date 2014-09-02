package enhancedbiomes.world.biome;

import java.util.Random;

import enhancedbiomes.helpers.TreeGen;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenRiparianZone extends BiomeGenBase
{
    public BiomeGenRiparianZone(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.theBiomeDecorator.treesPerChunk = 15;
        this.theBiomeDecorator.grassPerChunk = 5;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return TreeGen.alder(par1Random);
    }
}
