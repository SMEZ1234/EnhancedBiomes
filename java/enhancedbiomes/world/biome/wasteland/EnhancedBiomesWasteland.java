package enhancedbiomes.world.biome.wasteland;

import java.io.File;

import enhancedbiomes.world.biome.wasteland.rock.EnhancedBiomesRock;
import enhancedbiomes.world.biome.wasteland.sandstone.EnhancedBiomesSandstone;

public class EnhancedBiomesWasteland
{
	public static void config()
	{
		EnhancedBiomesRock.config();
		EnhancedBiomesSandstone.config();
	}
	
	public static void load()
	{
		EnhancedBiomesRock.load();
		EnhancedBiomesSandstone.load();
	}
}