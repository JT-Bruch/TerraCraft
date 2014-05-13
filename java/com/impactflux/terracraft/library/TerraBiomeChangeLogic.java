package com.impactflux.terracraft.library;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TerraBiomeChangeLogic
{

	
	public static void ReplaceBiome(World world, TerraBlockCoord originBlock)
	{
		for( int yNdx = originBlock.getYCoord(); yNdx >= 0; yNdx-- )
	    {
	  	
			for(int magX = 0; magX < 100; magX++)
			{	
				ReplaceBlocksInRingFromOrigin(world, originBlock, magX, yNdx);
			}
		}
		
		
		
		
	}
	
	public static void ReplaceBlocksInRingFromOrigin(World world, TerraBlockCoord originBlock, int magnitude, int yCoord)
	{
		int xOriginCoord = originBlock.getXCoord(),
			zOriginCoord = originBlock.getZCoord();
		
		
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
			ReplaceWorldBlock(world, originBlock, nextBlockToReplace);
		}
		
		//
		// Should be in bottom left & move right by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, false);
			ReplaceWorldBlock(world, originBlock, nextBlockToReplace);
		}
		
		//
		// Should be in bottom right & move up by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveXCoord(1, false);
			ReplaceWorldBlock(world, originBlock, nextBlockToReplace);
		}
		
		//
		// Should be in top right & move left by magnitude
		//
		for(int magX = 0; magX < magnitude * 2; magX++)
		{	
			nextBlockToReplace.moveZCoord(1, true);
			ReplaceWorldBlock(world, originBlock, nextBlockToReplace);
		}
		

		
	
	
	}
	
	
	public static void ReplaceWorldBlock(World world, TerraBlockCoord originBlock, TerraBlockCoord replaceBlock)
	{
	  int xCoord = replaceBlock.getXCoord(),
		  yCoord = replaceBlock.getYCoord(),
	  	  zCoord = replaceBlock.getZCoord();
	  
	  Block blockToReplace = world.getBlock(xCoord, yCoord, zCoord);
			 
	  if(IsBlockTypeReplaceable(blockToReplace) && IsBlockSameBiome(originBlock, replaceBlock))
	  {
		world.setBlock(xCoord, yCoord, zCoord, GetBlockToChange(replaceBlock));
	  }
	}
	
	public static Block GetBlockToChange(TerraBlockCoord blockInfo)
	{
		return(GetBlockToChangeFromYLevel(blockInfo.getYCoord()));
	}

	
	public static boolean IsBlockTypeReplaceable(Block blockType)
	{
		boolean bRetVal = false;
		if( blockType == Blocks.dirt  ||
			blockType == Blocks.stone ||
			blockType == Blocks.sand  ||
			blockType == Blocks.gravel||
			blockType == Blocks.grass ||
			blockType == Blocks.snow
											)
		{
			bRetVal = true;
		}
		return(bRetVal);
		
	}

	
	public static boolean IsBlockSameBiome(TerraBlockCoord originBlock, TerraBlockCoord replaceBlock)
	{
		boolean bRetVal = false;
		if( originBlock.getBiomeType() == replaceBlock.getBiomeType() )
		{
			bRetVal = true;
		}
		return(bRetVal);
		
	}
	
	public static Block GetBlockToChangeFromYLevel(int yCoord)
	{
		Block blockTypeToReturn = Blocks.sandstone;
		
		if( yCoord > 55)
		{
			blockTypeToReturn = Blocks.sandstone;
		}
		else if ( yCoord < 5 )
		{
			blockTypeToReturn = Blocks.bedrock;
		}
			
		return(blockTypeToReturn);
	}
	
	
	public static Block GetBlockToChangeFromBiome(int biomeID)
	{
		Block blockTypeToReturn = Blocks.sandstone;
		
		if( biomeID > 0)
		{
			blockTypeToReturn = Blocks.sandstone;
		}
		return(blockTypeToReturn);
	}
}
