package com.impactflux.terracraft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;

import com.impactflux.terracraft.library.items.TerraItemPickaxeAdv;


public class ItemPickaxeEcopoiesis extends TerraItemPickaxeAdv
{
	public ItemPickaxeEcopoiesis(ToolMaterial toolMaterial) 
	{
		super(toolMaterial);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void harvestBlock(World world, int x, int y, int z, EntityPlayer player) {

		Block block = world.getBlock(x, y, z);

		if (block.getBlockHardness(world, x, y, z) < 0) {
			return;
		}
		int bMeta = world.getBlockMetadata(x, y, z);

		if (block.canHarvestBlock(player, bMeta)) {
			block.harvestBlock(world, player, x, y, z, bMeta);
		}
		world.setBlockToAir(x, y, z);
	}

}
