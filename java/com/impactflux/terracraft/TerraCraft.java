package com.impactflux.terracraft;


import net.minecraft.block.Block;

import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.library.TerraCraftReference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid=TerraCraftReference.MODID, name=TerraCraftReference.NAME, version=TerraCraftReference.VERSION)

public class TerraCraft
{
	public static Block ecopoiesisOre;
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	ecopoiesisOre = new BlockEcopoiesisOre().setBlockName("ecopoiesisOre");
    	RegisterHelper.registerBlock(ecopoiesisOre);
    	
    }

}
