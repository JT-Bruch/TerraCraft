package com.impactflux.terracraft.gui;

import com.impactflux.terracraft.items.TerraItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class TerraCraftCreativeTab extends CreativeTabs 
{
	static ItemStack iconStack;
	
	public TerraCraftCreativeTab() 
	{

		super("terracraft");
	}
	
	public static void initialize()
	{
		iconStack = new ItemStack(TerraItems.itemSwordEcopoiesis, Short.MAX_VALUE);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getIconItemStack() {

		return iconStack;
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
