package enhancedbiomes.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.helpers.EnhancedBiomesWorldHelper;
//import enhancedbiomes.world.biome.decorators.BiomeDecoratorVanillaWoodland;
import enhancedbiomes.world.gen.*;

public class PreDecorationHandler
{
	@SubscribeEvent
	public void correctBiomeDecorations(DecorateBiomeEvent.Pre e) {
		BiomeDecorator dec = e.world.getBiomeGenForCoords(e.chunkX * 16, e.chunkZ * 16).theBiomeDecorator;
		//if(dec instanceof BiomeDecoratorVanillaWoodland) dec = ((BiomeDecoratorVanillaWoodland) dec).oldbiomedeco;
		dec.bigMushroomGen = new WorldGenBigMushroomEB();
		dec.clayGen = new WorldGenClayEB(4);
		dec.sandGen = new WorldGenSandEB(Blocks.sand, 7);
		dec.gravelAsSandGen = new WorldGenSandEB(Blocks.gravel, 6);
	}/*
	
	@SubscribeEvent
	public void addBiomeDecorations(DecorateBiomeEvent.Pre e) {
        int x;
        int y;
        int z;
        
        if(e.rand.nextInt(48) == 0) {            
            x = e.chunkX + e.rand.nextInt(16) + 8;
            z = e.chunkZ + e.rand.nextInt(16) + 8;
            y = -25;
            for(int x3 = -2; x3 <= 2; x3++)
            	for(int z3 = -2; z3 <= 2; z3++)
                    y += EnhancedBiomesWorldHelper.getTopSolidBlock(x + x3, z + z3, e.world);
            y /= 25;
            if(y > 60) new WorldGenBadlands(3 + e.rand.nextInt(3), 1, EnhancedBiomesMod.getDominantStone(e.world.getBiomeGenForCoords(x, z).biomeID), EnhancedBiomesMod.getDominantStoneMeta(e.world.getBiomeGenForCoords(x, z).biomeID)).generate(e.world, e.rand, x, y, z);        	
        }
	}*/
}
