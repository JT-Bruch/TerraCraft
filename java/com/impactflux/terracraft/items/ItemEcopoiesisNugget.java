package com.impactflux.terracraft.items;

import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEcopoiesisNugget extends Item
{
	public ItemEcopoiesisNugget()
	{
		super();
		setUnlocalizedName("nuggetEcopoiesis");
		setTextureName(TerraCraftReference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabMaterials);
	}

}
