package com.impactflux.terracraft.library.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;

public class TerraItemPickaxeAdv extends TerraItemToolAdv {

	public TerraItemPickaxeAdv(ToolMaterial toolMaterial) 
	{

		super(2.0F, toolMaterial);
		addToolClass("pickaxe");

		//effectiveBlocks.addAll(ItemPickaxe.field_150915_c);
		effectiveMaterials.add(Material.iron);
		effectiveMaterials.add(Material.anvil);
		effectiveMaterials.add(Material.rock);
	}
	


}
