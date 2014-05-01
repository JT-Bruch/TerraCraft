package com.impactflux.terracraft.recipes;

import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.items.TerraItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class TerraRecipes
{
	public static void addRecipes()
	{
		
		GameRegistry.addRecipe(new ItemStack(TerraItems.pickaxeEcopoiesis), new Object[]
		{
			"XXX",
			" Y ",
			" Y ",
			'X', TerraItems.ingotEcopoiesis, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(TerraItems.axeEcopoiesis), new Object[]
		{
			"XX ",
			"XY ",
			" Y ",
			'X', TerraItems.ingotEcopoiesis, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(TerraItems.shovelEcopoiesis), new Object[]
		{
			" X ",
			" Y ",
			" Y ",
			'X', TerraItems.ingotEcopoiesis, 'Y', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(TerraItems.swordEcopoiesis), new Object[]
		{
			" X ",
			" X ",
			" Y ",
			'X', TerraItems.ingotEcopoiesis, 'Y', Items.stick
		});
		
		GameRegistry.addRecipe(new ItemStack(TerraItems.hoeEcopoiesis), new Object[]
		{
			"XX ",
			" Y ",
			" Y ",
			'X', TerraItems.ingotEcopoiesis, 'Y', Items.stick
		});
		
		ItemStack ecoNuggetStack = new ItemStack(TerraItems.nuggetEcopoiesis);
		GameRegistry.addShapelessRecipe(new ItemStack(TerraItems.ingotEcopoiesis),
				ecoNuggetStack, ecoNuggetStack, ecoNuggetStack,
				ecoNuggetStack, ecoNuggetStack, ecoNuggetStack,
				ecoNuggetStack, ecoNuggetStack, ecoNuggetStack);
		
		ItemStack ecoIngotStack = new ItemStack(TerraItems.ingotEcopoiesis);
		GameRegistry.addShapelessRecipe(new ItemStack(TerraBlocks.blockEcopoiesis),
				ecoIngotStack, ecoIngotStack, ecoIngotStack,
				ecoIngotStack, ecoIngotStack, ecoIngotStack,
				ecoIngotStack, ecoIngotStack, ecoIngotStack);
		
		GameRegistry.addShapelessRecipe(new ItemStack(TerraItems.nuggetEcopoiesis), new ItemStack(TerraItems.ingotEcopoiesis));

		
	}
	
	public static void addFurnanceRecipes()
	{
		
		GameRegistry.addSmelting(TerraBlocks.oreEcopoiesis, new ItemStack(TerraItems.ingotEcopoiesis), .9F);
		
		
	}

}
