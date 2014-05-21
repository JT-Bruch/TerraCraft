package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.fluid.BlockEcopoiesisFluid;
import com.impactflux.terracraft.fluid.BlockFluidEcopoiesis;
import com.impactflux.terracraft.fluid.BlockFluidGenesis;
import com.impactflux.terracraft.fluid.BlockFluidTerraBase;
import com.impactflux.terracraft.library.RegisterHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TerraBlocks
{
	public static TerraBlockOre blockOre;
	public static TerraBlockStorage blockStorage;

	public static BlockFluidTerraBase blockFluidEcopoiesis;
	public static BlockFluidTerraBase blockFluidGenesis;
	
	public static void preInit() {

		blockOre = new TerraBlockOre();
		blockStorage = new TerraBlockStorage();

		blockFluidEcopoiesis = new BlockFluidEcopoiesis();
		blockFluidGenesis = new BlockFluidGenesis();
		
		blockOre.preInit();
		blockStorage.preInit();

		blockFluidEcopoiesis.preInit();
		blockFluidGenesis.preInit();

	}

	public static void initialize() {

	}

	public static void postInit() {

		blockOre.postInit();
	}


	

}
