package com.impactflux.terracraft;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.impactflux.terracraft.blocks.BlockEcopoiesisOre;
import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.items.ItemEcopoiesisIngot;
import com.impactflux.terracraft.items.TerraItems;
import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.library.TerraCraftReference;
import com.impactflux.terracraft.recipes.TerraRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.util.EnumHelper;


@Mod(modid=TerraCraftReference.MODID, name=TerraCraftReference.NAME, version=TerraCraftReference.VERSION)

public class TerraCraft
{


	
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
		//
		// Load all blocks
		//
		TerraBlocks.loadBlocks();

		//
		// Load all Items
		//
    	TerraItems.loadItems();
    	
    	//
    	// Add all Recipes
    	//
    	TerraRecipes.addRecipes();

    	
    }

}
