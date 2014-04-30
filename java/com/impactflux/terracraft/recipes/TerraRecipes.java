package com.impactflux.terracraft.recipes;

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

		
	}

}
