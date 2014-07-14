package com.impactflux.terracraft;


import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import java.io.File;
import java.util.EnumMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.fluid.TerraFluids;
import com.impactflux.terracraft.gui.TerraCraftCreativeTab;
import com.impactflux.terracraft.items.TerraItems;
import com.impactflux.terracraft.library.ConfigHandler;
import com.impactflux.terracraft.library.GuiHandler;
import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.library.TerraCraftReference;
import com.impactflux.terracraft.library.TerraCraftWorldGenerator;
import com.impactflux.terracraft.recipes.TerraRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;


@Mod(modid=TerraCraftReference.MODID, name=TerraCraftReference.NAME, version=TerraCraftReference.VERSION)

public class TerraCraft
{
	public static final String version = TerraCraftReference.VERSION;
	public static final String modId = TerraCraftReference.MODID;
	@Mod.Instance
	public static TerraCraft instance;
	public static final CreativeTabs tab = new TerraCraftCreativeTab();
	public static ConfigHandler config = new ConfigHandler(version);
	public static TerraCraftWorldGenerator worldGen;

	
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		TerraCraftReference.configDir = event.getModConfigurationDirectory();

		
		config.setConfiguration(new Configuration(new File(TerraCraftReference.configDir, "/TerraCraft/TerraCraft.cfg")));
		

		TerraFluids.preInit();
		TerraItems.preInit();
		TerraBlocks.preInit();
		
		
		worldGen = new TerraCraftWorldGenerator();
		worldGen.preInit();
    	GameRegistry.registerWorldGenerator(worldGen , 0);
		


		config.save();
	}

	@EventHandler
	public void initialize(FMLInitializationEvent event) 
	{
		TerraCraftCreativeTab.initialize();

		TerraFluids.initialize();
		TerraItems.initialize();
		TerraBlocks.initialize();

	
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		TerraFluids.postInit();
		TerraItems.postInit();
		TerraBlocks.postInit();

		
		config.cleanUp(false, true);
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {

		
	}
	


}
