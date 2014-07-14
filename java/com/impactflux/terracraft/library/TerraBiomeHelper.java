package com.impactflux.terracraft.library;

import java.util.HashMap;
import java.util.Map;

import cofh.util.BlockCoord;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TerraBiomeHelper 
{
	public static Map<Integer, TerraBlockCoord> GetMapToReplaceFromBiome(World world, TerraBlockCoord originBlock)
	{
		
		Map<Integer, TerraBlockCoord> biomeMap = new HashMap<Integer, TerraBlockCoord>(); 
		for( int yNdx = originBlock.getYCoord(); yNdx >= 0; yNdx-- )
	    {
			int magX = 0;
			do
			{
				magX++;
			}while(AddBlocksInRingFromOriginToMap(biomeMap, originBlock, magX, yNdx, world));
		
		}
		return biomeMap;
	}
	
	public static boolean AddBlocksInRingFromOriginToMap(Map<Integer, TerraBlockCoord> map, TerraBlockCoord originBlock, int magnitude, int yCoord, World world)
	{
		boolean bSameBiome = false;
		int xOriginCoord = originBlock.getXCoord(),
			zOriginCoord = originBlock.getZCoord();
		
		BiomeGenBase originBiomeGenBase = world.getBiomeGenForCoords(xOriginCoord, zOriginCoord);
		BiomeGenBase replaceBiomeGenBase;
		
		TerraBlockCoord upperLeftCornerBlock = originBlock.copy(),
						nextBlockToReplace;
		
		upperLeftCornerBlock.moveXCoord(magnitude, false);
		upperLeftCornerBlock.moveZCoord(magnitude, true);
		
		//
		// Start in upper left & move down by magnitude
		//
		
		nextBlockToReplace = upperLeftCornerBlock.copy();
		nextBlockToReplace.setYCoord(yCoord);
		
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveXCoord(1, true);
			
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.getXCoord(), nextBlockToReplace.getYCoord());
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new TerraBlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

			
		}
		
		//
		// Should be in bottom left & move right by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, false);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.getXCoord(), nextBlockToReplace.getYCoord());
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new TerraBlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}
	
		}
		
		//
		// Should be in bottom right & move up by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveXCoord(1, false);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.getXCoord(), nextBlockToReplace.getYCoord());
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new TerraBlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

		}
		
		//
		// Should be in top right & move left by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, true);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.getXCoord(), nextBlockToReplace.getYCoord());
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new TerraBlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

		}
		
		return(bSameBiome);
	}
	
	
	public static Map<Integer, BlockCoord> GetMapToReplaceFromBiomeMin(World world, BlockCoord originBlock)
	{
		
		Map<Integer, BlockCoord> biomeMap = new HashMap<Integer, BlockCoord>(); 
		for( int yNdx = originBlock.y; yNdx >= 0; yNdx-- )
	    {
			int magX = 0;
			do
			{
				magX++;
			}while(AddBlocksInRingFromOriginToMapMin(biomeMap, originBlock, magX, yNdx, world) && magX < 64);
		
		}
		return biomeMap;
	}
	
	public static boolean AddBlocksInRingFromOriginToMapMin(Map<Integer, BlockCoord> map, BlockCoord originBlock, int magnitude, int yCoord, World world)
	{
		boolean bSameBiome = false;
		int xOriginCoord = originBlock.x,
			zOriginCoord = originBlock.z;
		
		BiomeGenBase originBiomeGenBase = world.getBiomeGenForCoords(xOriginCoord, zOriginCoord);
		BiomeGenBase replaceBiomeGenBase;
		
		BlockCoord upperLeftCornerBlock = originBlock.copy(),
						nextBlockToReplace;
		
		upperLeftCornerBlock.moveXCoord(magnitude, false);
		upperLeftCornerBlock.moveZCoord(magnitude, true);
		
		//
		// Start in upper left & move down by magnitude
		//
		
		nextBlockToReplace = upperLeftCornerBlock.copy();
		nextBlockToReplace.y = yCoord;
		
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveXCoord(1, true);
			
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.x, nextBlockToReplace.y);
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new BlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

			
		}
		
		//
		// Should be in bottom left & move right by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, false);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.x, nextBlockToReplace.y);
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new BlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}
	
		}
		
		//
		// Should be in bottom right & move up by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveXCoord(1, false);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.x, nextBlockToReplace.y);
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new BlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

		}
		
		//
		// Should be in top right & move left by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, true);
			replaceBiomeGenBase = world.getBiomeGenForCoords(nextBlockToReplace.x, nextBlockToReplace.y);
			if(replaceBiomeGenBase.equals(originBiomeGenBase))
			{
				map.put(map.size(), new BlockCoord(nextBlockToReplace));
				bSameBiome = true;
			}
			else
			{
				bSameBiome = false;
			}

		}
		
		return(bSameBiome);
	}

}
