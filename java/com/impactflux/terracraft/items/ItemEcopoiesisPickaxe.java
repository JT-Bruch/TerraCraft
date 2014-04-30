package com.impactflux.terracraft.items;

import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEcopoiesisPickaxe extends Item
{
	public ItemEcopoiesisPickaxe()
	{
		super();
		setUnlocalizedName("pickaxeEcopoiesis");
		setTextureName(TerraCraftReference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabTools);
	}

}
