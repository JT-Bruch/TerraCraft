package com.impactflux.terracraft.fluid;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TerraFluids 
{
	public static void preInit() {

		fluidEcopoiesis = new Fluid("ecopoiesis").setLuminosity(7).setDensity(1200).setViscosity(1500).setTemperature(350).setRarity(EnumRarity.uncommon);
		fluidGenesis = new Fluid("genesis").setLuminosity(15).setDensity(1200).setViscosity(100).setTemperature(350).setRarity(EnumRarity.uncommon);
		
		FluidRegistry.registerFluid(fluidEcopoiesis);
		FluidRegistry.registerFluid(fluidGenesis);

 
	}

	public static void initialize() {

	}

	public static void postInit() {

	}

	public static Fluid fluidEcopoiesis;
	public static Fluid fluidGenesis;

}
