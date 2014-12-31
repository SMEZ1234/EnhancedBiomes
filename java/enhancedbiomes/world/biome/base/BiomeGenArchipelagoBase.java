package enhancedbiomes.world.biome.base;

import java.util.ArrayList;

import enhancedbiomes.handlers.ReplaceBiomeBlocksHandler;
import enhancedbiomes.world.biome.EnhancedBiomesArchipelago;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.ChunkProviderEvent.ReplaceBiomeBlocks;

public class BiomeGenArchipelagoBase extends BiomeGenEBBase
{
	public float archipelagoHeight;

	public BiomeGenArchipelagoBase(int par1) {
		super(par1);
	}

	public void replaceBiomeBlocks(ReplaceBiomeBlocks e, int x, int z, int preHeightIndex, int heightRange, double worldGenNoise) {
		super.replaceBiomeBlocks(e, x, z, preHeightIndex, heightRange, worldGenNoise);

		if(worldGenNoise > 0) {
			for(int a = 0; a < 10 * worldGenNoise * archipelagoHeight; a++)
				e.blockArray[preHeightIndex + ReplaceBiomeBlocksHandler.getTopBlock(e.blockArray, preHeightIndex, heightRange) + 1] = Blocks.stone;
		}
	}

	public BiomeGenArchipelagoBase setupArchipelago(ArrayList<BiomeGenArchipelagoBase> list) {
		EnhancedBiomesArchipelago.archipelagoBiomes.add(this);
		list.add(this);
		this.archipelagoHeight = this.rootHeight;
		this.rootHeight = height_Oceans.rootHeight;
		this.heightVariation = height_Oceans.variation;
		return this;
	}
}
