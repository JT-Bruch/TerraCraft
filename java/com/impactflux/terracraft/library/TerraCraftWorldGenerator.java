package com.impactflux.terracraft.library;
import java.util.Random;

import com.impactflux.terracraft.blocks.TerraBlocks;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class TerraCraftWorldGenerator implements IWorldGenerator
{

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
		
	}
		

	private void generateEnd(World world, Random random, int i, int j)
	{
		// TODO Auto-generated method stub
		
	}
	

}
