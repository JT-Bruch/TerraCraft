package com.impactflux.terracraft.tileentities;

import cofh.api.core.IInitializer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

public class TerraBasicBiomeChangerTileEntity extends TileEntity implements IInitializer 
{

	@Override
	public boolean preInit() {
		GameRegistry.registerTileEntity(TerraBasicBiomeChangerTileEntity.class, "TerraBasicBiomeTileEntity");
		return true;
	}

	@Override
	public boolean initialize() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean postInit() {
		// TODO Auto-generated method stub
		return true;
	}

}
