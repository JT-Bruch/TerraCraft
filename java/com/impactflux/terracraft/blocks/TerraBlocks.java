package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.library.RegisterHelper;

import net.minecraft.block.Block;

public class TerraBlocks
{
	//
	// Ores
	//
	public static Block oreEcopoiesis;
	
	
	public static void loadBlocks()
	{	
		oreEcopoiesis = new BlockEcopoiesisOre();    	
    	RegisterHelper.registerBlock(oreEcopoiesis);
    	
	}

}
