package com.impactflux.terracraft.library;

import net.minecraft.block.Block;

public class TerraBlockCoord
{
	private int XCoord = 0;
	private int YCoord = 0;
	private int ZCoord = 0;
	private Block blockType;
	private boolean bValid = true;
	
	public TerraBlockCoord(int X, int Y, int Z, Block block)
	{
		XCoord = X;
		YCoord = Y;
		ZCoord = Z;
		blockType = block;
		
		if(YCoord < 0 && YCoord > 256)
		{
			bValid = false;
		}
	}
	
	public boolean isBlockValid()
	{
		return(bValid);
	}
	
	public void setBlock(Block block)
	{
		blockType = block;
	}
	
	public void setBlockValid(boolean bVal)
	{
		bValid = bVal;
	}
	public int getXCoord()
	{
		return(XCoord);
	}
	
	public int getYCoord()
	{
		return(YCoord);
	}
	
	public int getZCoord()
	{
		return(ZCoord);
	}
	
	public void MoveXCoordByOne(boolean bAdd)
	{
		if(bAdd)
		{
			XCoord = XCoord + 1;
		}
		else
		{
			XCoord = XCoord - 1;
		}
		
	}
	
	public void MoveYCoordByOne(boolean bAdd)
	{
		if(bAdd)
		{
			XCoord = YCoord + 1;
		}
		else
		{
			YCoord = YCoord - 1;
		}
	}
	
	public void MoveZCoordByOne(boolean bAdd)
	{
		if(bAdd)
		{
			ZCoord = ZCoord + 1;
		}
		else
		{
			ZCoord = ZCoord - 1;
		}
	}
	
	public Block getBlock()
	{
		return(blockType);
	}

}
