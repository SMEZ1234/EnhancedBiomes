package enhancedbiomes.handlers;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.helpers.TreeGen;
import enhancedbiomes.world.gen.*;

public class DecorationHandler
{
	@SubscribeEvent
	public void treeGeneration(Decorate e) {
		if(e.world.getBiomeGenForCoords(e.chunkX + 16, e.chunkZ + 16) == BiomeGenBase.river && e.type == EventType.TREE) {
			for (int j = 0; j < 35; ++j)
	        {
	            int k = e.chunkX + e.rand.nextInt(16) + 8;
	            int l = e.chunkZ + e.rand.nextInt(16) + 8;
	            int i1 = e.world.getHeightValue(k, l);
	            WorldGenAbstractTree worldgenabstracttree = TreeGen.alder(e.rand);
	            worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

	            if (worldgenabstracttree.generate(e.world, e.rand, k, i1, l)) 
	            	worldgenabstracttree.func_150524_b(e.world, e.rand, k, i1, l);
	        }
		}
		int rain = (int) (e.world.getBiomeGenForCoords(e.chunkX + 16, e.chunkZ + 16).getFloatRainfall() * 10);
		
		if(e.type == EventType.TREE && rain >= 6 && e.rand.nextInt(96) == 0 && e.world.getBiomeGenForCoords(e.chunkX + 16, e.chunkZ + 16).getTempCategory() != BiomeGenBase.TempCategory.WARM) {
			for(int a = 0; a < e.world.getBiomeGenForCoords(e.chunkX + 16, e.chunkZ + 16).theBiomeDecorator.treesPerChunk; a++) {
				int k = e.chunkX + e.rand.nextInt(16) + 8;
	            int l = e.chunkZ + e.rand.nextInt(16) + 8;
	            
				int i1 = e.world.getHeightValue(k, l);
	            WorldGenAbstractTree worldgenabstracttree = TreeGen.willow_large(e.rand);
	            worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

	            if (worldgenabstracttree.generate(e.world, e.rand, k, i1, l)) 
	            	worldgenabstracttree.func_150524_b(e.world, e.rand, k, i1, l);
			}
		}
	}
}
