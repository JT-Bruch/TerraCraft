package com.impactflux.terracraft.blocks;

import cofh.api.core.IInitializer;
import cofh.render.IconRegistry;
import cofh.util.ItemHelper;
import cofh.util.StringHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import com.impactflux.terracraft.TerraCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TerraGenesisBlockStorage extends Block implements IInitializer
{
	
	public static final String NAME = "genesis";
	public static final int LIGHT = 4;
	public static final int ORE_NDX = 1;
	public static final float HARDNESS = 40;
	public static final float RESISTANCE = 120;
	public static final int RARITY = 2;
	
	public static ItemStack blockGenesis;

	public TerraGenesisBlockStorage() {

		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(TerraCraft.tab);
		setBlockName("terrcraft.storage");
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
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

		return world.getBlockMetadata(x, y, z) == 10 ? 15 : 0;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {

		return HARDNESS;
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

		return RESISTANCE;
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {

		return false;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {

		return true;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return IconRegistry.getIcon("Storage" + StringHelper.titleCase(NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) 
	{
		
		IconRegistry.addIcon("Storage" + StringHelper.titleCase(NAME), "terracraft:storage/Block_" + StringHelper.titleCase(NAME), ir);
		
	}

	/* IInitializer */
	@Override
	public boolean preInit() 
	{
		GameRegistry.registerBlock(this, TerraGenesisItemBlockStorage.class, "StorageGenesis");
		blockGenesis = new ItemStack(this);
		ItemHelper.registerWithHandlers("blockGenesis", blockGenesis);
		return true;
	}

	@Override
	public boolean initialize() {

		return true;
	}

	@Override
	public boolean postInit() 
	{		
		ItemHelper.addStorageRecipe(blockGenesis, "ingotGenesis");
		return true;
	}

	


}
