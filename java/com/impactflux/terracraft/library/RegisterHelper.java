package com.impactflux.terracraft.library;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RegisterHelper
{
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, TerraCraftReference.MODID + "_" + block.getUnlocalizedName().substring(5));
	}
}
