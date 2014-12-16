package enhancedbiomes.world.biome.base;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenEBBase extends BiomeGenBase
{
	public BiomeGenEBBase(int id) {
		super(id);
	}
	
	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {		
	}
}
