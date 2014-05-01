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
import com.impactflux.terracraft.library.TerraCraftWorldGenerator;
import com.impactflux.terracraft.recipes.TerraRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;


@Mod(modid=TerraCraftReference.MODID, name=TerraCraftReference.NAME, version=TerraCraftReference.VERSION)

public class TerraCraft
{


	
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
		//
		// Load configuration settings
		//
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		//
		// Load all blocks
		//
		TerraBlocks.loadBlocks();

		//
		// Load all Items
		//
    	TerraItems.loadItems();
    	
    	//
    	// Add all crafting Recipes
    	//
    	TerraRecipes.addRecipes();
    	//
    	// Add all furnance Recipes
    	//
    	TerraRecipes.addFurnanceRecipes();
    	
    	//
    	// Load world generation settings
    	//
    	GameRegistry.registerWorldGenerator(new TerraCraftWorldGenerator(), 0);
		//
		// Save configuration settings
		//
    	config.save();
    }

}
