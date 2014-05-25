package com.impactflux.terracraft.library.items;

import cofh.util.ItemHelper;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLinkedHashSet;

import java.util.ArrayList;
import java.util.Set;

import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.blocks.TerraGenesisBlockOre;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public abstract class TerraItemToolAdv extends ItemTool {

	public String repairIngot = "";
	private final TLinkedHashSet<String> toolClasses = new TLinkedHashSet<String>();
	private final Set<String> immutableClasses = java.util.Collections.unmodifiableSet(toolClasses);

	protected THashSet<Block> effectiveBlocks = new THashSet<Block>();
	protected THashSet<Material> effectiveMaterials = new THashSet<Material>();
	protected int harvestLevel = -1;

	public TerraItemToolAdv(float baseDamage, Item.ToolMaterial toolMaterial) {

		super(baseDamage, toolMaterial, null);
	}

	public TerraItemToolAdv(float baseDamage, Item.ToolMaterial toolMaterial, int harvestLevel) {

		this(baseDamage, toolMaterial);
		this.harvestLevel = harvestLevel;
	}

	public TerraItemToolAdv setRepairIngot(String repairIngot) {

		this.repairIngot = repairIngot;
		return this;
	}

	protected void addToolClass(String string) {

		toolClasses.add(string);
	}

	protected THashSet<Block> getEffectiveBlocks(ItemStack stack) {

		return effectiveBlocks;
	}

	protected THashSet<Material> getEffectiveMaterials(ItemStack stack) {

		return effectiveMaterials;
	}

	protected boolean isClassValid(String toolClass, ItemStack stack) {

		return true;
	}

	protected float getEfficiency(ItemStack stack) {

		return efficiencyOnProperMaterial;
	}

	protected int getHarvestLevel(ItemStack stack, int level) {

		return level;
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

		return ItemHelper.isOreName(stack, repairIngot);
	}

	@Override
	public float func_150893_a(ItemStack stack, Block block) {

		return (getEffectiveMaterials(stack).contains(block.getMaterial()) || getEffectiveBlocks(stack).contains(block)) ? getEfficiency(stack) : 1.0F;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {

		return func_150893_a(stack, block) > 1.0f;
	}

	protected void harvestBlock(World world, int x, int y, int z, EntityPlayer player) {

		Block block = world.getBlock(x, y, z);

		if (block.getBlockHardness(world, x, y, z) < 0) {
			return;
		}
		int bMeta = world.getBlockMetadata(x, y, z);

		if (block.canHarvestBlock(player, bMeta)) {
			block.harvestBlock(world, player, x, y, z, bMeta);
		}
		world.setBlockToAir(x, y, z);
	}

	protected boolean isValidHarvestMaterial(ItemStack stack, World world, int x, int y, int z) {

		return getEffectiveMaterials(stack).contains(world.getBlock(x, y, z).getMaterial());
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {

		if (harvestLevel != -1) {
			return harvestLevel;
		}
		int level = super.getHarvestLevel(stack, toolClass);
		if (level == -1 && isClassValid(toolClass, stack) && toolClasses.contains(toolClass)) {
			level = toolMaterial.getHarvestLevel();
		}
		return getHarvestLevel(stack, level);
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {

		return toolClasses.isEmpty() ? super.getToolClasses(stack) : immutableClasses;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {

		for (String type : getToolClasses(stack)) {
			int level = getHarvestLevel(stack, type);

			if (type.equals(block.getHarvestTool(meta))) {
				if (block.getHarvestLevel(meta) < level) {
					return getEfficiency(stack);
				}
			}
		}
		return super.getDigSpeed(stack, block, meta);
	}
	
	public boolean IsBlockTypeReplaceableForPickaxe(Block blockType)
	{
		boolean bRetVal = false;
		if( blockType == Blocks.sandstone  ||
			blockType == Blocks.stone      ||
			blockType == Blocks.netherrack       ||
			blockType == Blocks.end_stone  
											)
		{
			bRetVal = true;  
		}
		return(bRetVal);
		
	}
	
	public boolean IsBlockTypeReplaceableForShovel(Block blockType)
	{
		boolean bRetVal = false;
		if( blockType == Blocks.gravel  ||
			blockType == Blocks.dirt      ||
			blockType == Blocks.sand       ||
			blockType == Blocks.clay  
											)
		{
			bRetVal = true;  
		}
		return(bRetVal);
		
	}

	public boolean IsBlockTypeReplaceableForAxe(Block blockType)
	{
		boolean bRetVal = false;
		if( blockType == Blocks.log )
		{
			bRetVal = true;  
		}
		return(bRetVal);
		
	}
	
	public boolean allowGenesisEffectToReplace(Block block)
	{
		boolean bRetVal = false;
		
		if( TerraBlocks.blockGenesisOre == block )
		{
			bRetVal = true;
		}
		return(bRetVal);
	}
	

	
	public ArrayList<Block> getGenesisReplacementBlockTypeArray()
	{	
		ArrayList<Block> repBlockArray = new ArrayList<Block>();
		
		repBlockArray.add(Blocks.diamond_block);
		repBlockArray.add(Blocks.gold_block);
		repBlockArray.add(Blocks.iron_block);
		repBlockArray.add(Blocks.emerald_block);
		repBlockArray.add(Blocks.lapis_block);
		repBlockArray.add(Blocks.quartz_block);
		
		
		return(repBlockArray);
	}
	
	public ArrayList<Block> getGenesisReplacementOreTypeArray()
	{	
		ArrayList<Block> repBlockArray = new ArrayList<Block>();
		
		repBlockArray.add(Blocks.diamond_ore);
		repBlockArray.add(Blocks.gold_ore);
		repBlockArray.add(Blocks.iron_ore);
		repBlockArray.add(Blocks.emerald_ore);
		repBlockArray.add(Blocks.lapis_ore);
		repBlockArray.add(Blocks.quartz_ore);
		
		
		return(repBlockArray);
	}
	//
	// Method to use to add enchanting effect
	//
	/*@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
	     par1ItemStack.setTagInfo("ench", new NBTTagList());
	     return true;
	}*/
	
	


}
