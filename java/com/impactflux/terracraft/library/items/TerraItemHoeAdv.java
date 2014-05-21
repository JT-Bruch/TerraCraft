package com.impactflux.terracraft.library.items;

import cofh.util.ItemHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class TerraItemHoeAdv extends ItemHoe {

	public String repairIngot = "";

	public TerraItemHoeAdv(Item.ToolMaterial toolMaterial) {

		super(toolMaterial);
	}

	public TerraItemHoeAdv setRepairIngot(String repairIngot) {

		this.repairIngot = repairIngot;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return ItemHelper.isOreName(stack, repairIngot);
	}

}