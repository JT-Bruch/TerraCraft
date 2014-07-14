package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.fluid.BlockEcopoiesisFluid;
import com.impactflux.terracraft.fluid.BlockFluidEcopoiesis;
import com.impactflux.terracraft.fluid.BlockFluidGenesis;
import com.impactflux.terracraft.fluid.BlockFluidTerraBase;
import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.tileentities.TerraBasicBiomeChangerTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TerraBlocks
{
	public static TerraGenesisBlockOre blockGenesisOre;
	public static TerraGenesisBlockStorage blockGenesisStorage;
	
	public static TerraEcopoiesisBlockOre blockEcopoiesisOre;
	public static TerraEcopoiesisBlockStorage blockEcopoiesisStorage;

	public static BlockFluidTerraBase blockFluidEcopoiesis;
	public static BlockFluidTerraBase blockFluidGenesis;
	
	public static TerraBasicBiomeChangerBlock blockBasicBiomeChanger;
	public static TerraBasicBiomeChangerTileEntity tileBasicBiomeChanger;
	
	public static void preInit() 
	{
		blockGenesisOre = new TerraGenesisBlockOre();
		blockGenesisStorage = new TerraGenesisBlockStorage();
		
		blockEcopoiesisOre = new TerraEcopoiesisBlockOre();
		blockEcopoiesisStorage = new TerraEcopoiesisBlockStorage();

		blockFluidEcopoiesis = new BlockFluidEcopoiesis();
		blockFluidGenesis = new BlockFluidGenesis();
		
		blockBasicBiomeChanger = new TerraBasicBiomeChangerBlock();
		tileBasicBiomeChanger = new TerraBasicBiomeChangerTileEntity();
		
		blockGenesisOre.preInit();
		blockGenesisStorage.preInit();
		
		blockEcopoiesisOre.preInit();
		blockEcopoiesisStorage.preInit();

		blockFluidEcopoiesis.preInit();
		blockFluidGenesis.preInit();
		
		blockBasicBiomeChanger.preInit();
		tileBasicBiomeChanger.preInit();
		

	}

	public static void initialize() 
	{
		
		tileBasicBiomeChanger.initialize();

	}

	public static void postInit() 
	{
		blockGenesisOre.postInit();
		blockGenesisStorage.postInit();
		
		blockEcopoiesisOre.postInit();
		blockEcopoiesisStorage.postInit();
	}


	

}
