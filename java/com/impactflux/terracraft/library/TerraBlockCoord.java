package com.impactflux.terracraft.library;

import net.minecraft.block.Block;

public class TerraBlockCoord
{
	private int xCoord = 0;
	private int yCoord = 0;
	private int zCoord = 0;
	private Block blockType;
	private boolean bValid = true;
	private int biomeType = -1;
	
	public TerraBlockCoord(int X, int Y, int Z, int BiomeType, Block block)
	{
		xCoord = X;
		yCoord = Y;
		zCoord = Z;
		blockType = block;
		biomeType = BiomeType;
		
		if(yCoord < 0 && yCoord > 256)
		{
			bValid = false;
		}
	}
	
	public TerraBlockCoord copy()
	{
		return new TerraBlockCoord(xCoord, yCoord, zCoord, biomeType, blockType);
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
		return(xCoord);
	}
	
	public int getYCoord()
	{
		return(yCoord);
	}
	
	public int getZCoord()
	{
		return(zCoord);
	}
	
	public int getBiomeType()
	{
		return(biomeType);
	}
	
	
	public void moveXCoord(int nVal, boolean bAdd)
	{
		if(bAdd)
		{
			xCoord = xCoord + nVal;
		}
		else
		{
			xCoord = xCoord - nVal;
		}
		
	}
	
	public void moveYCoord(int nVal, boolean bAdd)
	{
		if(bAdd)
		{
			yCoord = yCoord + nVal;
		}
		else
		{
			yCoord = yCoord - nVal;
		}
	}
	
	public void moveZCoord(int nVal, boolean bAdd)
	{
		if(bAdd)
		{
			zCoord = zCoord + nVal;
		}
		else
		{
			zCoord = zCoord - nVal;
		}
	}
	
	
	
	public Block getBlock()
	{
		return(blockType);
	}

	public void setYCoord(int y)
	{
		yCoord = y;
		
	}

}
