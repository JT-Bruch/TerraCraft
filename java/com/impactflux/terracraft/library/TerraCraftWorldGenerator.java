package com.impactflux.terracraft.library;
import java.util.Random;

import cofh.api.core.IInitializer;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.blocks.TerraBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class TerraCraftWorldGenerator implements IWorldGenerator, IInitializer
{
	boolean bGenerateEcopoiesis,
			bGenerateGenesis;
	
	int MinYEco,
		MaxYEco,
		MinYGenesis,
		MaxYGenesis,
		GenesisDensity,
		EcopoiesisDensity;
	
    WorldGenMinable worldGenEco;
    WorldGenMinable worldGenGenesis;
    
    public TerraCraftWorldGenerator()
    {
    	worldGenEco = new WorldGenMinable(TerraBlocks.blockEcopoiesisOre, 3, 8, Blocks.stone);
    	worldGenGenesis = new WorldGenMinable(TerraBlocks.blockGenesisOre, 4, 8, Blocks.stone);
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId){
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		}
		
	}

	private void generateNether(World world, Random random, int i, int j)
	{
		// TODO Auto-generated method stub
		
	}

	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		generateUndergroundOres(random, chunkX, chunkZ, world);
	}
	
	void generateUndergroundOres (Random random, int xChunk, int zChunk, World world)
    {
        int xPos, yPos, zPos;
		
        if(bGenerateEcopoiesis)
        {
            for (int q = 0; q <= EcopoiesisDensity; q++)
            {
                xPos = xChunk + random.nextInt(16);
                yPos = MinYEco + random.nextInt(MaxYEco - MinYEco);
                zPos = zChunk + random.nextInt(16);
                worldGenEco.generate(world, random, xPos, yPos, zPos);
            }
        }
        if(bGenerateGenesis)
        {
            for (int q = 0; q <= GenesisDensity; q++)
            {
                xPos = xChunk + random.nextInt(16);
                yPos = MinYGenesis + random.nextInt(MaxYGenesis - MinYGenesis);
                zPos = zChunk + random.nextInt(16);
                worldGenGenesis.generate(world, random, xPos, yPos, zPos);
            }
        }
    }
		

	private void generateEnd(World world, Random random, int i, int j)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean preInit() 
	{
		
		String category = "OreGeneration";
		
		String comment = "Enable generation of Ecopoiesis.";
		bGenerateEcopoiesis = TerraCraft.config.get(category, "Ore.Ecopoiesis.Generate", true, comment);
		
		comment = "Minimum Y level for Ecopoiesis generation.";
		MinYEco = TerraCraft.config.get(category, "Ore.Ecopoiesis.GenerateMinY", 1, comment);
		
		comment = "Maximum Y level for Ecopoiesis generation.";
		MaxYEco = TerraCraft.config.get(category, "Ore.Ecopoiesis.GenerateMaxY", 10, comment);
		
		comment = "Density level for Ecopoiesis generation.";
		EcopoiesisDensity = TerraCraft.config.get(category, "Ore.Ecopoiesis.Density", 10, comment);
		
		comment = "Enable generation of Genesis.";
		bGenerateGenesis = TerraCraft.config.get(category, "Ore.Genesis.Generate", true, comment);
		
		comment = "Minimum Y level for Genesis generation.";
		MinYGenesis = TerraCraft.config.get(category, "Ore.Genesis.GenerateMinY", 1, comment);
		
		comment = "Maximum Y level for Genesis generation.";
		MaxYGenesis = TerraCraft.config.get(category, "Ore.Genesis.GenerateMaxY", 10, comment);
		
		comment = "Density level for Genesis generation.";
		GenesisDensity = TerraCraft.config.get(category, "Ore.Genesis.Density", 10, comment);
		
		return true;
	}
	
	@Override
	public boolean initialize() 
	{
		return true;
	}
	
	@Override
	public boolean postInit() 
	{
		return true;
	}
	

	

}
