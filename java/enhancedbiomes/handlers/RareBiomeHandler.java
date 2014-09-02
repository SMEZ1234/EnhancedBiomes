package enhancedbiomes.handlers;

import static enhancedbiomes.handlers.BiomeGenManager.*;
import static enhancedbiomes.world.biome.EnhancedBiomesBiome.*;
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
import static net.minecraft.world.biome.BiomeGenBase.*;
import enhancedbiomes.world.biome.BiomeGenWetlandBase;
import enhancedbiomes.world.biome.BiomeGenWoodlandBase;
import net.minecraft.world.biome.BiomeGenBase;

public class RareBiomeHandler 
{
	public static void registerRareBiomes() {
		registerVanilla();
		registerEB();
	}

	private static void registerEB() {
		setRareBiome(biomeForestArchipelago, biomeFlowerArchipelago);
		setRareBiome(biomePineArchipelago, biomeBorealArchipelago);
		setRareBiome(biomePolarDesert, biomeIceSheet);
		setRareBiome(biomeWoodLands, biomeForestMountains);
		setRareBiome(biomeWoodLandHills, biomeForestValley);
		setRareBiome(biomePlateau, biomePlateauSnow);
		setRareBiome(biomeSandStoneRanges, biomeSandStoneRangesM);
		setRareBiome(biomeGrasslands, biomeGrasslandsRoofed);
		setRareBiome(biomeMeadow, biomeMeadowM);
		setRareBiome(biomeAlpine, biomeAlpineM);
		setRareBiome(biomeTundra, biomeSnowDesert);
		setRareBiome(biomeWasteLands, biomeWasteLandsSnowy);
		setRareBiome(biomeSandStoneCanyon, biomeStoneCanyon);
		setRareBiome(biomeEphemeralLake, biomeWoodlandLake);
		setRareBiome(biomeBorealPlateau, biomeBorealPlateauM);
	}

	private static void registerVanilla() {
		setRareBiomeTo128(plains);
		setRareBiomeTo128(desert);
		setRareBiomeTo128(extremeHills);
		setRareBiomeTo128(forest);
		setRareBiomeTo128(taiga);
		setRareBiomeTo128(swampland);
		setRareBiomeTo128(icePlains);
		setRareBiomeTo128(jungle);
		setRareBiomeTo128(jungleEdge);
		setRareBiomeTo128(birchForest);
		setRareBiomeTo128(birchForestHills);
		setRareBiomeTo128(roofedForest);
		setRareBiomeTo128(coldTaiga);
		setRareBiomeTo128(megaTaiga);
		setRareBiomeTo128(megaTaigaHills);
		setRareBiomeTo128(extremeHillsPlus);
		setRareBiomeTo128(savanna);
		setRareBiomeTo128(savannaPlateau);
		setRareBiomeTo128(mesa);
		setRareBiomeTo128(mesaPlateau_F);
		setRareBiomeTo128(mesaPlateau);
	}

	private static void setRareBiome(BiomeGenBase defaultBiome, BiomeGenBase rareBiome) {
		BiomeGenManager.setRareBiome(defaultBiome.biomeID, rareBiome.biomeID);
	}
	
	private static void setRareBiomeTo128(BiomeGenBase defaultBiome) {
		BiomeGenManager.setRareBiome(defaultBiome.biomeID, get128(defaultBiome));
	}
	
	private static int get128(BiomeGenBase defaultBiome) {
		return defaultBiome.biomeID + 128;
	}
}
