package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.fluid.BlockEcopoiesisFluid;
import com.impactflux.terracraft.library.RegisterHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TerraBlocks
{
	//
	// Ores
	//
	public static Block oreEcopoiesis;
	
	//
	// Metal Blocks
	// 
	public static Block blockEcopoiesis;
	
	//
	// Fluid Blocks
	//
	public static Fluid fluidEcopoiesis;
	public static BlockEcopoiesisFluid fluidEcopoiesisBlock;
	
	//
	//
	//
	public static Block terraBiomeChanger;
	
	public static void loadBlocks()
	{	
		oreEcopoiesis = new BlockEcopoiesisOre();
		blockEcopoiesis = new BlockEcopoiesis();
		
		fluidEcopoiesis = new Fluid("fluidEcopoiesis");
		FluidRegistry.registerFluid(fluidEcopoiesis);
		
		fluidEcopoiesisBlock = new BlockEcopoiesisFluid(fluidEcopoiesis, Material.water);
		GameRegistry.registerBlock(fluidEcopoiesisBlock, "fluidEcopoiesis");
		
		terraBiomeChanger = new BlockBiomeChanger();
		GameRegistry.registerTileEntity(TileBiomeChanger.class, "BiomeChanger");
		
		RegisterHelper.registerBlock(terraBiomeChanger);
		RegisterHelper.registerBlock(oreEcopoiesis);
    	RegisterHelper.registerBlock(blockEcopoiesis);
    	
	}

}
