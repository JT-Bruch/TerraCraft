package com.impactflux.terracraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cofh.util.StringHelper;

public class TerraBasicBiomeChangerItemBlock extends ItemBlock
{
	public TerraBasicBiomeChangerItemBlock(Block block) {

		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getItemStackDisplayName(ItemStack item) {

		return StringHelper.localize(getUnlocalizedName(item));
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {

		return "tile.terracraft.biomechanger." + TerraBasicBiomeChangerBlock.NAME + ".name";
	}

	@Override
	public int getMetadata(int i) {

		return i;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {

		return EnumRarity.values()[TerraEcopoiesisBlockOre.RARITY];
	}

}
