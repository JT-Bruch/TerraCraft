package com.impactflux.terracraft.library;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TerraBiomeChangeLogic
{
	public static void ReplaceWorldBlock(World world, TerraBlockCoord orgBlock, TerraBlockCoord repBlock)
	{
	  int xCoord = repBlock.getXCoord();
	  int yCoord = repBlock.getYCoord();
	  int zCoord = repBlock.getZCoord();
	  Block airBlock = world.getBlock(xCoord, yCoord, zCoord);
	  boolean bIsAir = airBlock.isAir(world, xCoord, yCoord, zCoord);
			 
	  if( !bIsAir )
	  {
		world.setBlock(xCoord, yCoord, zCoord, repBlock.getBlock());
	  }
		
	}
	
	public static void ReplaceBiome(World world, TerraBlockCoord orgBlock)
	{
		BiomeGenBase BiomeBase = world.getBiomeGenForCoords(orgBlock.getXCoord(), orgBlock.getZCoord());
		int CurrentBiomeId = BiomeBase.biomeID,
		    ReplaceBiomeId = -1;
		
		TerraBlockCoord ReplaceBlock = new TerraBlockCoord(0,0,0,Blocks.stone);
		ReplaceBlock.setBlockValid(false);
		
		TerraBlockCoord LastBlockReplaced = orgBlock;
		orgBlock.MoveYCoordByOne(false);
		int direction = 0;
		
		
		
		do
		{
			ReplaceBiomeId = world.getBiomeGenForCoords(ReplaceBlock.getXCoord(), ReplaceBlock.getZCoord()).biomeID;
			if(direction < 6)
			{
				direction++;
			}
			else
			{
				direction = 0;
			}
			do
			{
			
				ReplaceBlock = FindNextBlockToReplace(CurrentBiomeId, LastBlockReplaced, world, direction);
				if(ReplaceBlock.isBlockValid())
				{
					ReplaceWorldBlock(world, orgBlock, ReplaceBlock);
				}
			} while(ReplaceBlock.isBlockValid());
			
			
		}while(ReplaceBiomeId == CurrentBiomeId);
		
		
		
		
	}
	
	public static TerraBlockCoord FindNextBlockToReplace(int CurrentBiomeId, TerraBlockCoord LastReplacedBlock, World world, int direction)
	{
		
		
		TerraBlockCoord ReturnBlock = new TerraBlockCoord(0,0,0,Blocks.stone);
		ReturnBlock.setBlockValid(false);
		
		
		TerraBlockCoord BlockToCheck = LastReplacedBlock;
		switch(direction)
		{
		case 0:
			BlockToCheck.MoveXCoordByOne(true);
			break;
		case 1:
			BlockToCheck.MoveYCoordByOne(true);
			break;
		case 2:
			BlockToCheck.MoveZCoordByOne(true);
			break;	
		case 3:
			BlockToCheck.MoveXCoordByOne(false);
			break;
		case 4:
			BlockToCheck.MoveYCoordByOne(false);
			break;
		case 5:
			BlockToCheck.MoveZCoordByOne(false);
			break;	
		}
		
		
		BiomeGenBase BiomeBase = world.getBiomeGenForCoords(BlockToCheck.getXCoord(), BlockToCheck.getZCoord());
		
		if(CurrentBiomeId == BiomeBase.biomeID)
		{
			ReturnBlock = BlockToCheck;
			ReturnBlock.setBlock(GetBlockToChangeFromBiome(world, ReturnBlock));
		}
			
		
		return(ReturnBlock);
	}
	
	
	
	public static Block GetBlockToChangeFromBiome(World world, TerraBlockCoord currentBlock)
	{
		return(Blocks.sand);
	}
}
