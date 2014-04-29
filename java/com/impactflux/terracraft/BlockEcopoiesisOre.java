package com.impactflux.terracraft;

import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockEcopoiesisOre extends Block
{
	public BlockEcopoiesisOre()
	{
		super(Material.rock);
		setBlockName("ecopoiesisOre");
		setBlockTextureName(TerraCraftReference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundTypePiston);
		setHardness(3.0F);
		setResistance(5.0F);
		setHarvestLevel("pickaxe", 2);
	}

}
