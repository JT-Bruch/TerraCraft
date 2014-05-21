package com.impactflux.terracraft.library.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TerraItemAxeAdv  extends TerraItemToolAdv {

	public TerraItemAxeAdv(Item.ToolMaterial toolMaterial) {

		super(3.0F, toolMaterial);
		addToolClass("axe");

		//effectiveBlocks.addAll(ItemAxe.field_150917_c);
		effectiveMaterials.add(Material.wood);
		effectiveMaterials.add(Material.plants);
		effectiveMaterials.add(Material.vine);
	}

}
