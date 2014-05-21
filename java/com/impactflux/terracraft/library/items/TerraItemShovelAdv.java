package com.impactflux.terracraft.library.items;

import net.minecraft.item.ItemSpade;

public class TerraItemShovelAdv extends TerraItemToolAdv {

	public TerraItemShovelAdv(ToolMaterial toolMaterial) {

		super(1.0F, toolMaterial);
		addToolClass("shovel");

	//	effectiveBlocks.addAll(ItemSpade.field_150916_c);
	}

}
