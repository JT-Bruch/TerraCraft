package com.impactflux.terracraft.library.items;

import cofh.util.ItemHelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TerraItemSwordAdv extends ItemSword {

	public String repairIngot = "";

	public TerraItemSwordAdv(Item.ToolMaterial toolMaterial) {

		super(toolMaterial);
	}

	public TerraItemSwordAdv setRepairIngot(String repairIngot) {

		this.repairIngot = repairIngot;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return ItemHelper.isOreName(stack, repairIngot);
	}

}
