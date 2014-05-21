package com.impactflux.terracraft.gui;

import com.impactflux.terracraft.blocks.TerraBlockOre;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TerraCraftCreativeTab extends CreativeTabs 
{
	public TerraCraftCreativeTab() {

		super("terracraft");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {

		return TerraBlockOre.oreEcopoiesis;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {

		return getIconItemStack().getItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel() {

		return "terracraft.creativeTab";
	}

}
