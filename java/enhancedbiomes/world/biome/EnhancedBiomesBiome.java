package enhancedbiomes.world.biome;

import static net.minecraftforge.common.BiomeDictionary.registerBiomeType;
import static enhancedbiomes.world.biome.archipelago.EnhancedBiomesArchipelago.*;
import static enhancedbiomes.world.biome.grass.EnhancedBiomesGrass.*;
import static enhancedbiomes.world.biome.grass.plains.EnhancedBiomesPlains.*;
import static enhancedbiomes.world.biome.sand.EnhancedBiomesSand.*;
import static enhancedbiomes.world.biome.snow.EnhancedBiomesSnow.*;
import static enhancedbiomes.world.biome.snow.snowforest.EnhancedBiomesSnowForest.*;
import static enhancedbiomes.world.biome.woodland.EnhancedBiomesWoodland.*;
import static enhancedbiomes.world.biome.wetland.EnhancedBiomesWetland.*;
import static enhancedbiomes.world.biome.wasteland.rock.EnhancedBiomesRock.*;
import static enhancedbiomes.world.biome.wasteland.sandstone.EnhancedBiomesSandstone.*;
import static enhancedbiomes.world.biome.woodland.tropical.EnhancedBiomesTropical.*;
import static enhancedbiomes.helpers.EBHeights.*;

import java.io.File;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.BiomeDictionary.Type;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.biome.archipelago.EnhancedBiomesArchipelago;
import enhancedbiomes.world.biome.grass.EnhancedBiomesGrass;
import enhancedbiomes.world.biome.sand.EnhancedBiomesSand;
import enhancedbiomes.world.biome.snow.EnhancedBiomesSnow;
import enhancedbiomes.world.biome.wasteland.EnhancedBiomesWasteland;
import enhancedbiomes.world.biome.wetland.EnhancedBiomesWetland;
import enhancedbiomes.world.biome.woodland.EnhancedBiomesWoodland;
import enhancedbiomes.world.biometype.BiomeWoods;

public class EnhancedBiomesBiome 
{	
	public static int riparianId;
	public static boolean riparianGen;
	public static boolean villageRiparian;
	public static BiomeGenBase biomeRiparian;
	
	public static boolean volcanoGen;
	
	public static void config()
	{
		File configFile = new File("config/Enhanced Biomes/Biomes.cfg");
		Configuration config = new Configuration(configFile);
		config.load();

		riparianId = config.get(config.CATEGORY_GENERAL, "Biome ID of Riparian Zone", 135).getInt();
		riparianGen = config.get(config.CATEGORY_GENERAL, "Generate the Riparian Zone instead of Rivers", true).getBoolean(true);
		villageRiparian = config.get(config.CATEGORY_GENERAL, "Generate villages in Riparian Zone biome", false).getBoolean(false);
		
		volcanoGen = config.get(config.CATEGORY_GENERAL, "Generate Volcanoes throughout the world", true).getBoolean(true);
		
		config.save();
		
		EnhancedBiomesArchipelago.config();
		EnhancedBiomesGrass.config();
		EnhancedBiomesSand.config();
		EnhancedBiomesSnow.config();
		EnhancedBiomesWasteland.config();
		EnhancedBiomesWetland.config();
		EnhancedBiomesWoodland.config();
	}
	
	public static void load()
	{
		biomeRiparian = (new BiomeGenRiparianZone(riparianId)).setColor(9286496).setTemperatureRainfall(0.7F, 0.8F).setHeight(heightShallowWaters).setBiomeName("Riparian Zone");
		if (villageRiparian == true) 			{  BiomeManager.addVillageBiome(biomeRiparian, true);  }
		BiomeManager.addStrongholdBiome(biomeRiparian);
		BiomeWoods.register(biomeRiparian, EnhancedBiomesBlocks.planksEB, 8);
		
		EnhancedBiomesArchipelago.load();
		EnhancedBiomesGrass.load();
		EnhancedBiomesSand.load();
		EnhancedBiomesSnow.load();
		EnhancedBiomesWasteland.load();
		EnhancedBiomesWetland.load();
		EnhancedBiomesWoodland.load();
	}
}
