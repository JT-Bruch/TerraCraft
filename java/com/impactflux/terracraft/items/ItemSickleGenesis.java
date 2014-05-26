package com.impactflux.terracraft.items;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.impactflux.terracraft.library.items.TerraItemSickleAdv;




public class ItemSickleGenesis extends TerraItemSickleAdv
{

	public ItemSickleGenesis(ToolMaterial toolMaterial) 
	{
		super(toolMaterial);
		// TODO Auto-generated constructor stub
	}
	

    
	
	@Override
    public boolean onBlockStartBreak (ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        if (!stack.hasTagCompound())
            return false;

        World world = player.worldObj;
        final Block blockB = world.getBlock(x, y, z);
        final int meta = world.getBlockMetadata(x, y, z);
        if (!stack.hasTagCompound())
            return false;
        NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
        boolean butter = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack) > 0;
        for (int xPos = x - 1; xPos <= x + 1; xPos++)
        {
            for (int yPos = y - 1; yPos <= y + 1; yPos++)
            {
                for (int zPos = z - 1; zPos <= z + 1; zPos++)
                {
                   boolean cancelHarvest = false;


                    if (!cancelHarvest)
                    {
                        Block localBlock = world.getBlock(xPos, yPos, zPos);
                        int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
                        float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);
                        if (localBlock != null)// && (block.blockMaterial == Material.leaves || block.isLeaves(world, xPos, yPos, zPos)))
                        {
                            for (int iter = 0; iter < materials.length; iter++)
                            {
                                if (materials[iter] == localBlock.getMaterial())
                                {
                                    if (!player.capabilities.isCreativeMode)
                                    {
                                        if (butter && localBlock instanceof IShearable && ((IShearable) localBlock).isShearable(stack, player.worldObj, x, y, z))
                                        {
                                            ArrayList<ItemStack> drops = ((IShearable) localBlock).onSheared(stack, player.worldObj, x, y, z,
                                                    EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
                                            Random rand = new Random();

                                            if (!world.isRemote)
                                                for (ItemStack dropStack : drops)
                                                {
                                                    float f = 0.7F;
                                                    double d = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                    double d1 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                    double d2 = (double) (rand.nextFloat() * f) + (double) (1.0F - f) * 0.5D;
                                                    EntityItem entityitem = new EntityItem(player.worldObj, (double) xPos + d, (double) yPos + d1, (double) zPos + d2, dropStack);
                                                    entityitem.delayBeforeCanPickup = 10;
                                                    player.worldObj.spawnEntityInWorld(entityitem);
                                                }

                                            if (localHardness > 0f)
                                                onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                            player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(localBlock)], 1);
                                            world.setBlockToAir(xPos, yPos, zPos);
                                        }
                                        else
                                        {
                                            if (localBlock.removedByPlayer(world, player, xPos, yPos, zPos))
                                            {
                                                localBlock.onBlockDestroyedByPlayer(world, xPos, yPos, zPos, localMeta);
                                            }
                                            localBlock.harvestBlock(world, player, xPos, yPos, zPos, localMeta);
                                            localBlock.onBlockHarvested(world, xPos, yPos, zPos, localMeta, player);
                                            if (localHardness > 0f)
                                                onBlockDestroyed(stack, world, localBlock, xPos, yPos, zPos, player);
                                        }
                                    }
                                    else
                                    {
                                        world.setBlockToAir(xPos, yPos, zPos);
                                    }
                                }
                            }
                        }
                    }
                
                }
            }
        }
        if (!world.isRemote)
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(blockB) + (meta << 12));
        return super.onBlockStartBreak(stack, x, y, z, player);
    }

	

}

