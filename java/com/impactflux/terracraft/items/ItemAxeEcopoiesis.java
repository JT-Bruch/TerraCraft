package com.impactflux.terracraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;

import com.impactflux.terracraft.blocks.TerraEcopoiesisBlockOre;
import com.impactflux.terracraft.library.items.TerraItemAxeAdv;


public class ItemAxeEcopoiesis extends TerraItemAxeAdv 
{
	public ItemAxeEcopoiesis(ToolMaterial toolMaterial) 
	{
		super(toolMaterial);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		if (block.getBlockHardness(world, x, y, z) == 0.0D) {
			return true;
		}
		EntityPlayer player = (EntityPlayer) entity;

		if( block == TerraEcopoiesisBlockOre.getBlockFromItem(getContainerItem()) ) 
		{
			
		//	block.dropBlockAsItem(world, x, y, z, stack);
			
		}

		return true;
	}


}
