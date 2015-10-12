package enhancedbiomes.world.biomestats;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeIDs {
	static int id = 39;
	static int getNextID() {
		id++;
		return id;
	}
	
	public static final int 
	  forestedArchipelago = getNextID()
	, pineForestArchipelago = getNextID()
	, polarDesert = getNextID()
	, woodlands = getNextID()				, forestedMountains = woodlands + 128
	, woodlandHills = getNextID()
	, cypressForest = getNextID()
	, borealForest = getNextID()	
	, volcano = getNextID()
	, plateau = getNextID()					, snowyPlateau = plateau + 128
	, sandstoneRanges = getNextID()			, sandstoneRangesM = sandstoneRanges + 128
	, shrublands = getNextID()				, roofedShrublands = shrublands + 128
	, meadow = getNextID()					, meadowM = meadow + 128
	, alpineMountains = getNextID()
	, tundra = getNextID()					, snowyDesert = tundra + 128
	, firForest = getNextID()
	, wastelands = getNextID()
	, sandstoneCanyons = getNextID()		, stoneCanyons = sandstoneCanyons + 128
	, ephemeralLake = getNextID()			, woodlandLake = ephemeralLake + 128
//	, ephemeralLakeEdge = getNextID()		, woodlandLakeEdge = ephemeralLakeEdge + 128
	, cypressPlateau = getNextID()
	, pineForest = getNextID()
	, mountainousArchipelago = getNextID()
	, desertArchipelago = getNextID()
	, tropicalArchipelago = getNextID()
	, frozenArchipelago = getNextID()
	, grassyArchipelago = getNextID()
	, rockyDesert = getNextID()
	, scrub = getNextID()
	, creekBed = getNextID()
	, sahara = getNextID()
	, badlands = getNextID()
	, oasis = getNextID()
	, rainforest = getNextID()
	, rainforestValley = getNextID()
	, blossomWoods = getNextID()
	, blossomHills = getNextID()
	, oakForest = getNextID()
	, kakadu = getNextID()
	, mountains = getNextID()
	, mountainsEdge = getNextID()
	, scree = getNextID()
	, xericSavannah = getNextID()
	, steppe = getNextID()
	, prairie = getNextID()
	, lowHills = getNextID()
	, alpineMountainsEdge = getNextID()
	, glacier = getNextID()
	, rockyHills = getNextID()
	, shield = getNextID()
	, clayHills = getNextID()
	, mangroves = getNextID()
	, fens = getNextID()
	, carr = getNextID()
	, silverPineForest = getNextID()
	, silverPineHills = getNextID()
	, woodlandField = getNextID()
	, aspenForest = getNextID()
	, aspenHills = getNextID()
	, basin = getNextID()
	, lake = getNextID()
	, marsh = getNextID()
	, alpineTundra = getNextID()
	, snowyRanges = getNextID()
	, redDesert = getNextID()
	, xericShrubland = getNextID()
	, drifts = getNextID();
}
