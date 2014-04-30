package com.impactflux.terracraft.library;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RegisterHelper
{
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, TerraCraftReference.MODID + "_" + block.getUnlocalizedName().substring(5));
	}
	
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, TerraCraftReference.MODID + "_" + item.getUnlocalizedName().substring(5));
	}
}
