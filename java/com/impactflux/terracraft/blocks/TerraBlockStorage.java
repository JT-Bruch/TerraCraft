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

public class TerraBlockStorage extends Block implements IInitializer
{

	public TerraBlockStorage() {

		super(Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setCreativeTab(TerraCraft.tab);
		setBlockName("terrcraft.storage");
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for (int i = 0; i < NAMES.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return LIGHT[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {

		return world.getBlockMetadata(x, y, z) == 10 ? 15 : 0;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {

		return HARDNESS[world.getBlockMetadata(x, y, z)];
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {

		return RESISTANCE[world.getBlockMetadata(x, y, z)];
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

		return IconRegistry.getIcon("Storage", metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		for (int i = 0; i < NAMES.length; i++) {
			IconRegistry.addIcon("Storage" + i, "terracraft:storage/Block_" + StringHelper.titleCase(NAMES[i]), ir);
		}
	}

	/* IInitializer */
	@Override
	public boolean preInit() {

		GameRegistry.registerBlock(this, TerraItemBlockStorage.class, "Storage");


		blockEcopoiesis = new ItemStack(this, 1, 5);
		blockGenesis = new ItemStack(this, 1, 6);


		ItemHelper.registerWithHandlers("blockEcopoiesis", blockEcopoiesis);
		ItemHelper.registerWithHandlers("blockGenesis", blockGenesis);


		return true;
	}

	@Override
	public boolean initialize() {

		return true;
	}

	@Override
	public boolean postInit() {

		ItemHelper.addStorageRecipe(blockEcopoiesis, "ingotEcopoiesis");
		ItemHelper.addStorageRecipe(blockGenesis, "ingotGenesis");


		return true;
	}

	public static final String[] NAMES = {  "ecopoiesis", "genesis" };
	public static final int[] LIGHT = { 15, 4 };
	public static final float[] HARDNESS = { 5, 40 };
	public static final float[] RESISTANCE = { 9, 120 };
	public static final int[] RARITY = {  1, 2 };

	public static ItemStack blockEcopoiesis;
	public static ItemStack blockGenesis;


}
