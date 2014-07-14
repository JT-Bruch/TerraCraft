package com.impactflux.terracraft.library;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cofh.util.BlockCoord;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TerraBiomeChangeLogic implements Runnable
{
	
	private Block m_StoneBlockToReplaceWith = Blocks.sandstone;
	private Block m_DirtBlockToReplaceWith = Blocks.sand;
	private Block m_GravelBlockToReplaceWith = Blocks.sand;
	private Block m_ClayBlockToReplaceWith = Blocks.dirt;
	private Block m_FluidBlockToReplaceWith = Blocks.water;
	private int m_MaxRadius = 200;
	private	int m_ReplaceRate = 10;  // blocks per second
	private boolean m_bOkayToReplace = false;
	private World m_World;
	private TerraBlockCoord m_OriginBlock;
	private int m_ReplaceNdx = 0;
	
	Map<Integer, BlockCoord> m_BiomeToReplace = new HashMap<Integer, BlockCoord>();
	Iterator m_Iter;
	
	public boolean m_bAbort = false;
	
	public void SetBiomeToReplace(Map<Integer, BlockCoord> biomeMap)
	{
		m_BiomeToReplace = (biomeMap);
	}
	
	public void SetAbort(boolean val)
	{
		m_bAbort = val;
	}
	
	public void SetOkayToReplace(boolean val)
	{
		m_bOkayToReplace = val;
	}
	
	public void SetMaxRadus(int radius)
	{
		m_MaxRadius = radius;
	}
	public void SetStoneToReplace(Block block)
	{
		m_StoneBlockToReplaceWith = block;
	}
	
	public void SetDirtToReplace(Block block)
	{
		m_DirtBlockToReplaceWith = block;
	}
	
	public void SetGravelToReplace(Block block)
	{
		m_GravelBlockToReplaceWith = block;
	}
	
	public void SetClayToReplace(Block block)
	{
		m_ClayBlockToReplaceWith = block;
	}
	
	public void SetFluidToReplace(Block block)
	{
		m_FluidBlockToReplaceWith = block;
	}
	
	public void SetWorld(World world)
	{
		m_World = world;
	}
	
	public void SetOriginBlock(TerraBlockCoord originBlock)
	{
		m_OriginBlock = originBlock;
	}
	
	public boolean IsOkToTerraform()
	{
		m_bOkayToReplace = false;
		if(m_World != null && m_BiomeToReplace != null)
		{
			m_bOkayToReplace = true;
		}
		return(m_bOkayToReplace);
	}
	
	
	public void ReplaceBiome()
	{
		m_Iter = m_BiomeToReplace.entrySet().iterator();
		
		int Ndx = 0;
		
		while(m_Iter.hasNext())
		{
			Map.Entry entry = (Map.Entry) m_Iter.next();
			BlockCoord blockToReplace = (BlockCoord) entry.getValue();
			ReplaceWorldBlock(blockToReplace.x, blockToReplace.y, blockToReplace.z);
			m_Iter.remove();
			m_ReplaceNdx++;
			Ndx++;
			
			if( Ndx > (m_ReplaceNdx % m_ReplaceRate)  )
			{
				break;
			}
			
			
		}
	
	
	}
	
	
	public void ReplaceWorldBlock(int xCoord, int yCoord, int zCoord)
	{
		
	  Block blockToReplace = m_World.getBlock(xCoord, yCoord, zCoord);
	  TerraBlockCoord terraBlockToReplace = new TerraBlockCoord(xCoord, yCoord, zCoord, blockToReplace);
	  
	  
	  if( IsBlockTypeReplaceable(blockToReplace) )
	  {
		  m_World.setBlock(xCoord, yCoord, zCoord, GetBlockToChange(terraBlockToReplace));
	  }
	}
	
	public Block GetBlockToChange(TerraBlockCoord blockInfo)
	{
		Block blockType = blockInfo.getBlock();
		GetBlockToChangeFromYLevel(blockInfo.getYCoord(), blockType);
		//
		// Replace "stone" blocks
		//
		if(	blockType == Blocks.stone	||	
				blockType == Blocks.sandstone		)
		{
			blockType = m_StoneBlockToReplaceWith;
		}
		
		//
		// Replace "dirt" blocks
		//
		if( blockType == Blocks.dirt  ||
			blockType == Blocks.mycelium    ||
			blockType == Blocks.grass 		||
			blockType == Blocks.snow )
		{
			blockType = m_DirtBlockToReplaceWith;
		}
		
		//
		// Replace "gravel" blocks
		//
		if(	blockType == Blocks.gravel	||	
			blockType == Blocks.sand		)
		{
			blockType = m_GravelBlockToReplaceWith;
		}
		
		//
		// Replace "clay" blocks
		//
		if(	blockType == Blocks.clay	||	
				blockType == Blocks.hardened_clay		)
		{
			blockType = m_ClayBlockToReplaceWith;
		}
		
		//
		// Replace "fluid" blocks
		//
		if(	blockType == Blocks.water	||	
			blockType == Blocks.ice		)
		{
			blockType = m_FluidBlockToReplaceWith;
		}
		return(blockType);
	}

	
	public static boolean IsBlockTypeReplaceable(Block blockType)
	{
		boolean bRetVal = false;
		if( blockType == Blocks.dirt  ||
			blockType == Blocks.stone ||
			blockType == Blocks.sand  ||
			blockType == Blocks.sandstone   ||
			blockType == Blocks.mycelium    ||
			blockType == Blocks.gravel		||
			blockType == Blocks.grass 		||
			blockType == Blocks.snow
											)
		{
			bRetVal = true;  
		}
		return(bRetVal);
		
	}

	
	public static boolean IsBlockSameBiome(World world, TerraBlockCoord originBlock, TerraBlockCoord replaceBlock)
	{
		boolean bRetVal = true;
		
		BiomeGenBase biomeOrg = world.getBiomeGenForCoords(originBlock.getXCoord(), originBlock.getYCoord());
		BiomeGenBase biomeRep = world.getBiomeGenForCoords(replaceBlock.getXCoord(), replaceBlock.getYCoord());
		
		if( biomeRep.biomeID != biomeOrg.biomeID )
		{
			bRetVal = false;
		}
		return(bRetVal);
		
	}
	
	public Block GetBlockToChangeFromYLevel(int yCoord, Block blockTypeToReturn)
	{	
		
		if ( yCoord < 5 )
		{
			blockTypeToReturn = Blocks.bedrock;
		}
			
		return(blockTypeToReturn);
	}

	@Override
	public void run() 
	{
		
		ReplaceBiome();
		
	}
	
}
