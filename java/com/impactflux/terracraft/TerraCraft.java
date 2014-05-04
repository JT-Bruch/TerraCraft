package com.impactflux.terracraft;


import net.minecraft.block.Block;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import java.util.EnumMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

import com.impactflux.terracraft.blocks.BlockEcopoiesisOre;
import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.items.ItemEcopoiesisIngot;
import com.impactflux.terracraft.items.TerraItems;
import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.library.TerraCraftReference;
import com.impactflux.terracraft.library.TerraCraftWorldGenerator;
import com.impactflux.terracraft.network.TerraCraftPacket;
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
	
	public static TerraCraft instance;


	
	
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
	
	
	public EnumMap<Side, FMLEmbeddedChannel> channels;

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(packet);
	}

	public void sendToPlayers(TerraCraftPacket packet, World world, int x, int y, int z, int maxDistance) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeOutbound(packet);
	}

	public void sendToPlayer(EntityPlayer entityplayer, TerraCraftPacket packet) {
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(entityplayer);
		channels.get(Side.SERVER).writeOutbound(packet);
	}

	public void sendToServer(TerraCraftPacket packet) {
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeOutbound(packet);
	}

}
