package com.impactflux.terracraft.library.items;

import cofh.util.ItemHelper;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class TerraItemArmorAdv extends ItemArmor {

	public String repairIngot = "";
	public String[] textures = new String[2];

	public TerraItemArmorAdv(ArmorMaterial material, int type) {

		super(material, 0, type);
	}

	public TerraItemArmorAdv setRepairIngot(String repairIngot) {

		this.repairIngot = repairIngot;
		return this;
	}

	public TerraItemArmorAdv setArmorTextures(String[] textures) {

		this.textures = textures;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return ItemHelper.isOreName(stack, repairIngot);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		if (slot == 2) {
			return textures[1];
		}
		return textures[0];
	}

}
