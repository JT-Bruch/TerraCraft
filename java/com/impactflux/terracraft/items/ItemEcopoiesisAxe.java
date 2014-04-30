package com.impactflux.terracraft.items;

import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEcopoiesisAxe extends Item
{
	public ItemEcopoiesisAxe()
	{
		super();
		setUnlocalizedName("axeEcopoiesis");
		setTextureName(TerraCraftReference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabTools);
	}

}
