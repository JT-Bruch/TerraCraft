package com.impactflux.terracraft.library;

import java.io.Serializable;

import cofh.util.BlockCoord;
import net.minecraft.block.Block;

public class TerraBlockCoord implements Comparable<TerraBlockCoord>, Serializable 
{
	private int xCoord = 0;
	private int yCoord = 0;
	private int zCoord = 0;
	private Block blockType;
	private Block initialblockType;
	
	public TerraBlockCoord(int X, int Y, int Z, Block block)
	{
		xCoord = X;
		yCoord = Y;
		zCoord = Z;
		blockType = block;
	}
	
	public TerraBlockCoord(TerraBlockCoord nextBlockToReplace) 
	{
		this.xCoord = nextBlockToReplace.xCoord;
		this.yCoord = nextBlockToReplace.yCoord;
		this.zCoord = nextBlockToReplace.zCoord;
		this.blockType = nextBlockToReplace.blockType;		
	}

	public TerraBlockCoord copy()
	{
		return new TerraBlockCoord(xCoord, yCoord, zCoord, blockType);
	}
	
	@Override
	public boolean equals(Object obj) 
	{

		if (!(obj instanceof TerraBlockCoord)) 
		{
			return false;
		}
		TerraBlockCoord other = (TerraBlockCoord) obj;
		return this.xCoord == other.xCoord && this.yCoord == other.yCoord && this.zCoord == other.zCoord;
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
	
	@Override
	public String toString() 
	{
		return "[" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + "]";
	}

	@Override
	public int compareTo(TerraBlockCoord other) 
	{
		return this.xCoord == other.xCoord ? this.yCoord == other.yCoord ? this.zCoord - other.zCoord : this.yCoord - other.yCoord : this.xCoord - other.xCoord;
	}

}
