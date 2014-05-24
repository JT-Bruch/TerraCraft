package com.impactflux.terracraft.blocks;

import cofh.api.core.IInitializer;
import cofh.render.IconRegistry;
import cofh.util.ItemHelper;
import cofh.util.StringHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.items.TerraItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.ForgeEventFactory;

public class TerraEcopoiesisBlockOre extends Block implements IInitializer
{
	
	public static final String NAME = "ecopoiesis";
	public static final int ORE_NDX = 0;
	public static final int LIGHT = 4;
	public static final int RARITY = 1;

	public static ItemStack oreEcopoiesis;
	
	
	public TerraEcopoiesisBlockOre() {

		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(TerraCraft.tab);
		setBlockName("terracraft.ore");

		setHarvestLevel("pickaxe", 2);
		setHarvestLevel("pickaxe", 1, 0);
		setHarvestLevel("pickaxe", 1, 1);
		setHarvestLevel("pickaxe", 3, 6);
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) 
	{		
		list.add(new ItemStack(item, 1));
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return LIGHT;
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return IconRegistry.getIcon("Ore" + StringHelper.titleCase(NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) 
	{
		
	  IconRegistry.addIcon("Ore" + StringHelper.titleCase(NAME), "terracraft:ore/Ore_" + StringHelper.titleCase(NAME), ir);

	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float dropchance, int fortune)
    {
        if( !world.isRemote )
        {

            ArrayList<ItemStack> items = getDrops(world, x, y, z, metadata, fortune);
            dropchance = 100;

            for (ItemStack item : items)
            {
                if (world.rand.nextFloat() <= dropchance)
                {
                    this.dropBlockAsItem(world, x, y, z, item);
                }
            }
        }
    }
	
	

	/* IInitializer */
	@Override
	public boolean preInit() {

		GameRegistry.registerBlock(this, TerraEcopoiesisItemBlockOre.class, "Ore");
		oreEcopoiesis = new ItemStack(this, 1, 5);
		ItemHelper.registerWithHandlers("oreEcopoiesis", oreEcopoiesis);

		return true;
	}

	@Override
	public boolean initialize() {

		return true;
	}

	@Override
	public boolean postInit() 
	{
		FurnaceRecipes.smelting().func_151394_a(oreEcopoiesis, TerraItems.ingotEcopoiesis, 1.0F);
		return true;
	}

	


}
