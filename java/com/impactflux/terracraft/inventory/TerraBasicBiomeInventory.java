package com.impactflux.terracraft.inventory;

import com.impactflux.terracraft.tileentities.TerraBasicBiomeChangerTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.inventory.InventoryManagerStandard;

public class TerraBasicBiomeInventory extends Container 
{
	private TerraBasicBiomeChangerTileEntity tileBasic;

	public TerraBasicBiomeInventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return false;
	}

}
