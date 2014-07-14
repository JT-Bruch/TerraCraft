package com.impactflux.terracraft.library;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class AbilityHelper
{
    public static Random random = new Random();
    public static boolean necroticUHS;



    

   
    static void alertPlayerWolves (EntityPlayer player, EntityLivingBase living, boolean par2)
    {
        if (!(living instanceof EntityCreeper) && !(living instanceof EntityGhast))
        {
            if (living instanceof EntityWolf)
            {
                EntityWolf var3 = (EntityWolf) living;

                if (var3.isTamed() && player.getDisplayName().equals(var3.getOwner()))
                {
                    return;
                }
            }

            if (!(living instanceof EntityPlayer) || player.canAttackPlayer((EntityPlayer) living))
            {
                List var6 = player.worldObj.getEntitiesWithinAABB(EntityWolf.class,
                        AxisAlignedBB.getBoundingBox(player.posX, player.posY, player.posZ, player.posX + 1.0D, player.posY + 1.0D, player.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
                Iterator var4 = var6.iterator();

                while (var4.hasNext())
                {
                    EntityWolf var5 = (EntityWolf) var4.next();

                    if (var5.isTamed() && var5.getEntityToAttack() == null && player.getDisplayName().equals(var5.getOwner()) && (!par2 || !var5.isSitting()))
                    {
                        var5.setSitting(false);
                        var5.setTarget(living);
                    }
                }
            }
        }
    }

   

    public static void breakTool (ItemStack stack, NBTTagCompound tags, Entity entity)
    {
        tags.getCompoundTag("InfiTool").setBoolean("Broken", true);
        if (entity != null)
            entity.worldObj.playSound(entity.posX, entity.posY, entity.posZ, "random.break", 1f, 1f, true);
    }

    public static void repairTool (ItemStack stack, NBTTagCompound tags)
    {
        tags.getCompoundTag("InfiTool").setBoolean("Broken", false);
        tags.getCompoundTag("InfiTool").setInteger("Damage", 0);
    }


    public static void knockbackEntity (EntityLivingBase living, double boost)
    {
        living.motionX *= boost;
        // living.motionY *= boost/2;
        living.motionZ *= boost;
    }


    public static void spawnItemAtEntity (Entity entity, ItemStack stack, int delay)
    {
        if (!entity.worldObj.isRemote)
        {
            EntityItem entityitem = new EntityItem(entity.worldObj, entity.posX + 0.5D, entity.posY + 0.5D, entity.posZ + 0.5D, stack);
            entityitem.delayBeforeCanPickup = delay;
            entity.worldObj.spawnEntityInWorld(entityitem);
        }
    }

    public static void spawnItemAtPlayer (EntityPlayer player, ItemStack stack)
    {
        if (!player.worldObj.isRemote)
        {
            EntityItem entityitem = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, stack);
            player.worldObj.spawnEntityInWorld(entityitem);
            if (!(player instanceof FakePlayer))
                entityitem.onCollideWithPlayer(player);
        }

    }

    /* Ranged weapons */

    public static void forceAddToInv (EntityPlayer entityplayer, ItemStack itemstack, int i, boolean flag)
    {
        ItemStack itemstack1 = entityplayer.inventory.getStackInSlot(i);
        entityplayer.inventory.setInventorySlotContents(i, itemstack);
        if (itemstack1 != null)
        {
            addToInv(entityplayer, itemstack1, flag);
        }
    }

    public static boolean addToInv (EntityPlayer entityplayer, ItemStack itemstack, boolean flag)
    {
        return addToInv(entityplayer, itemstack, entityplayer.inventory.currentItem, flag);
    }

    public static boolean addToInv (EntityPlayer entityplayer, ItemStack itemstack, int i, boolean flag)
    {
        ItemStack itemstack1 = entityplayer.inventory.getStackInSlot(i);
        boolean flag1;
        if (itemstack1 == null)
        {
            entityplayer.inventory.setInventorySlotContents(i, itemstack);
            flag1 = true;
        }
        else
        {
            flag1 = entityplayer.inventory.addItemStackToInventory(itemstack);
        }
        if (flag && !flag1)
        {
            addItemStackToWorld(entityplayer.worldObj, (float) Math.floor(entityplayer.posX), (float) Math.floor(entityplayer.posY), (float) Math.floor(entityplayer.posZ), itemstack);
            return true;
        }
        else
        {
            return flag1;
        }
    }

    public static EntityItem addItemStackToWorld (World world, float f, float f1, float f2, ItemStack itemstack)
    {
        return addItemStackToWorld(world, f, f1, f2, itemstack, false);
    }

    public static EntityItem addItemStackToWorld (World world, float f, float f1, float f2, ItemStack itemstack, boolean flag)
    {
        EntityItem entityitem;
        if (flag)
        {
            entityitem = new EntityItem(world, f, f1, f2, itemstack);
        }
        else
        {
            float f3 = 0.7F;
            float f4 = random.nextFloat() * f3 + (1.0F - f3) * 0.5F;
            float f5 = 1.2F;
            float f6 = random.nextFloat() * f3 + (1.0F - f3) * 0.5F;
            entityitem = new EntityItem(world, f + f4, f1 + f5, f2 + f6, itemstack);
        }
        entityitem.delayBeforeCanPickup = 10;
        world.spawnEntityInWorld(entityitem);
        return entityitem;
    }

    public static MovingObjectPosition raytraceFromEntity (World world, Entity player, boolean par3, double range)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
        if (!world.isRemote && player instanceof EntityPlayer)
            d1 += 1.62D;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP)
        {
            d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
    }

}