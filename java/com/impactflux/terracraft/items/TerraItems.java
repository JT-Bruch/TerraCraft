package com.impactflux.terracraft.items;
import cofh.util.ItemHelper;






import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.fluid.BucketHandler;
import com.impactflux.terracraft.fluid.TerraFluids;
import com.impactflux.terracraft.library.RegisterHelper;
import com.impactflux.terracraft.library.items.TerraItemBase;
import com.impactflux.terracraft.library.items.TerraItemBucket;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class TerraItems
{
	public static void preInit() {

		itemBucket = (TerraItemBucket) new TerraItemBucket("terracraft").setUnlocalizedName("bucket").setCreativeTab(TerraCraft.tab);
		itemMaterial = (TerraItemBase) new TerraItemBase("terracraft").setUnlocalizedName("material").setCreativeTab(TerraCraft.tab);
	}

	public static void initialize() {

		bucketEcopoiesis = itemBucket.addItem(0, "bucketEcopoiesis", 1);
		bucketGenesis = itemBucket.addItem(1, "bucketGenesis", 1);
		

		dustEcopoiesis = itemMaterial.addItem(37, "dustEcopoiesis", 1);
		dustGenesis = itemMaterial.addItem(38, "dustGenesis", 2);

		ingotEcopoiesis = itemMaterial.addItem(69, "ingotEcopoiesis", 1);
		ingotGenesis = itemMaterial.addItem(70, "ingotGenesis", 2);

		nuggetEcopoiesis = itemMaterial.addItem(101, "nuggetEcopoiesis");
		nuggetGenesis = itemMaterial.addItem(102, "nuggetGenesis");

		gearEcopoiesis = itemMaterial.addItem(133, "gearEcopoiesis");
		gearGenesis = itemMaterial.addItem(134, "gearGenesis");

		
		ItemHelper.addGearRecipe(gearEcopoiesis, "ingotEcopoiesis");
		ItemHelper.addGearRecipe(gearGenesis, "ingotGenesis");
		
		OreDictionary.registerOre("dustEcopoiesis", dustEcopoiesis);
		OreDictionary.registerOre("dustGenesis", dustGenesis);

		OreDictionary.registerOre("ingotEcopoiesis", ingotEcopoiesis);
		OreDictionary.registerOre("ingotGenesis", ingotGenesis);

		OreDictionary.registerOre("nuggetEcopoiesis", nuggetEcopoiesis);
		OreDictionary.registerOre("nuggetGenesis", nuggetGenesis);

		OreDictionary.registerOre("gearEcopoiesis", gearEcopoiesis);
		OreDictionary.registerOre("gearGenesis", gearGenesis);
		
		FurnaceRecipes.smelting().func_151394_a(dustEcopoiesis, ingotEcopoiesis, 0.0F);
		FurnaceRecipes.smelting().func_151394_a(dustGenesis, ingotGenesis, 0.0F);
		// No Enderium
	}

	public static void postInit() {

		BucketHandler.registerBucket(TerraBlocks.blockFluidEcopoiesis, 0, bucketEcopoiesis);
		BucketHandler.registerBucket(TerraBlocks.blockFluidGenesis, 0, bucketGenesis);


		FluidContainerRegistry.registerFluidContainer(TerraFluids.fluidEcopoiesis, bucketEcopoiesis, FluidContainerRegistry.EMPTY_BUCKET);
		FluidContainerRegistry.registerFluidContainer(TerraFluids.fluidGenesis, bucketGenesis, FluidContainerRegistry.EMPTY_BUCKET);

	}

	public static TerraItemBucket itemBucket;
	public static TerraItemBase itemMaterial;

	public static ItemStack bucketEcopoiesis;
	public static ItemStack bucketGenesis;

	public static ItemStack ingotEcopoiesis;
	public static ItemStack ingotGenesis;

	public static ItemStack dustEcopoiesis;
	public static ItemStack dustGenesis;

	public static ItemStack nuggetEcopoiesis;
	public static ItemStack nuggetGenesis;

	public static ItemStack gearEcopoiesis;
	public static ItemStack gearGenesis;




	
}
