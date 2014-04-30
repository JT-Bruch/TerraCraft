package com.impactflux.terracraft.items;
import com.impactflux.terracraft.library.RegisterHelper;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class TerraItems
{
	//
	// Ingots
	//
	public static Item ingotEcopoiesis;
	//
	// Tools 
	//
	public static Item axeEcopoiesis;
	public static Item pickaxeEcopoiesis;
	public static Item swordEcopoiesis;
	public static Item shovelEcopoiesis;
	
	//
	// Tool Material
	//
	
	
	static ToolMaterial materialToolEcopoiesis = EnumHelper.addToolMaterial("materialToolEcopoiesis", 2, 750, 7, 2.0F, 20);
	
	public static void loadItems()
	{
    	ingotEcopoiesis = new ItemEcopoiesisIngot();
		
		axeEcopoiesis = new ItemEcopoiesisAxe();
    	pickaxeEcopoiesis = new ItemEcopoiesisPickaxe();
    	swordEcopoiesis = new ItemEcopoiesisSword(); 
    	shovelEcopoiesis = new ItemEcopoiesisShovel();
    	
    	RegisterHelper.registerItem(ingotEcopoiesis);
    	
    	RegisterHelper.registerItem(axeEcopoiesis);
    	RegisterHelper.registerItem(pickaxeEcopoiesis);
    	RegisterHelper.registerItem(swordEcopoiesis);
    	RegisterHelper.registerItem(shovelEcopoiesis);
	}

}
