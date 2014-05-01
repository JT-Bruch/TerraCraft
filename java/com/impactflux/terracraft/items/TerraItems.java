package com.impactflux.terracraft.items;
import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.library.RegisterHelper;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class TerraItems
{
	//
	// Ingots
	//
	public static Item ingotEcopoiesis;
	public static Item nuggetEcopoiesis;
	//
	// Tools 
	//
	public static Item axeEcopoiesis;
	public static Item pickaxeEcopoiesis;
	public static Item swordEcopoiesis;
	public static Item shovelEcopoiesis;
	public static Item hoeEcopoiesis;
	public static Item bucketEcopoiesis;
	//
	// Tool Material
	//
	
	
	static ToolMaterial materialToolEcopoiesis = EnumHelper.addToolMaterial("materialToolEcopoiesis", 2, 750, 7, 2.0F, 20);
	
	public static void loadItems()
	{
    	ingotEcopoiesis = new ItemEcopoiesisIngot();
    	nuggetEcopoiesis = new ItemEcopoiesisNugget();
		
    	axeEcopoiesis = new ItemEcopoiesisAxe();
    	pickaxeEcopoiesis = new ItemEcopoiesisPickaxe();
    	swordEcopoiesis = new ItemEcopoiesisSword(); 
    	shovelEcopoiesis = new ItemEcopoiesisShovel();
    	hoeEcopoiesis = new ItemEcopoiesisHoe();
    	bucketEcopoiesis = new ItemEcopoiesisBucket(TerraBlocks.fluidEcopoiesisBlock);
    	
    	
    	
    	RegisterHelper.registerItem(ingotEcopoiesis);
    	RegisterHelper.registerItem(nuggetEcopoiesis);
    	
    	RegisterHelper.registerItem(axeEcopoiesis);
    	RegisterHelper.registerItem(pickaxeEcopoiesis);
    	RegisterHelper.registerItem(swordEcopoiesis);
    	RegisterHelper.registerItem(shovelEcopoiesis);
    	RegisterHelper.registerItem(hoeEcopoiesis);
    	
    	GameRegistry.registerItem(bucketEcopoiesis, "bucketEcopoiesis");
    	//FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("fluidName", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketEcopoiesis), new ItemStack(Items.bucket));
	}

}
