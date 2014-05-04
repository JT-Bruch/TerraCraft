package com.impactflux.terracraft.library;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.network.ISynchronizedTile;
import com.impactflux.terracraft.network.PacketUpdate;
import com.impactflux.terracraft.network.TerraCraftPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;

public final class TerraUtilities
{
	private TerraUtilities()
	{
		
	}
	
	/**
	 * Returns the cardinal direction of the entity depending on its
	 * rotationYaw
	 */
	public static ForgeDirection get2dOrientation(EntityLivingBase entityliving) {
		ForgeDirection[] orientationTable = { ForgeDirection.SOUTH,
				ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST };
		int orientationIndex = MathHelper.floor_double((entityliving.rotationYaw + 45.0) / 90.0) & 3;
		return orientationTable[orientationIndex];
	}
	
	public static TileEntity getTile(World world, TerraPosition pos, ForgeDirection step) {
		TerraPosition tmp = new TerraPosition(pos);
		tmp.orientation = step;
		tmp.moveForwards(1.0);

		return world.getTileEntity((int) tmp.x, (int) tmp.y, (int) tmp.z);
	}
	public static int[] createSlotArray(int first, int count) {
		int[] slots = new int[count];
		for (int k = first; k < first + count; k++) {
			slots[k - first] = k;
		}
		return slots;
	}

	public static void writeUTF (ByteBuf data, String str) {
		try {
			byte [] b = str.getBytes("UTF-8");
			data.writeInt (b.length);
			data.writeBytes(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			data.writeInt (0);
		}
	}

	public static String readUTF (ByteBuf data) {
		try {
			int len = data.readInt();
			byte [] b = new byte [len];
			data.readBytes(b);
			return new String (b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeNBT (ByteBuf data, NBTTagCompound nbt) {
		try {
			byte[] compressed = CompressedStreamTools.compress(nbt);
			data.writeInt(compressed.length);
			data.writeBytes(compressed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static NBTTagCompound readNBT(ByteBuf data) {
		try {
			int length = data.readInt();
			byte[] compressed = new byte[length];
			data.readBytes(compressed);
			return CompressedStreamTools.decompress(compressed);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void writeStack (ByteBuf data, ItemStack stack) {
		if (stack == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			NBTTagCompound nbt = new NBTTagCompound();
			stack.writeToNBT(nbt);
			TerraUtilities.writeNBT(data, nbt);
		}
	}


	public static ItemStack readStack(ByteBuf data) {
		if (!data.readBoolean()) {
			return null;
		} else {
			NBTTagCompound nbt = readNBT(data);
			return ItemStack.loadItemStackFromNBT(nbt);
		}
	}

	/**
	 * This subprogram transforms a packet into a FML packet to be send in the
	 * minecraft default packet mechanism. This always use BC-CORE as a
	 * channel, and as a result, should use discriminators declared there.
	 *
	 * WARNING! The implementation of this subprogram relies on the internal
	 * behavior of #FMLIndexedMessageToMessageCodec (in particular the encode
	 * member). It is probably opening a maintenance issue and should be
	 * replaced eventually by some more solid mechanism.
	 */
	public static FMLProxyPacket toPacket (TerraCraftPacket packet, int discriminator) {
		ByteBuf buf = Unpooled.buffer();

		buf.writeByte((byte) discriminator);
		packet.writeData(buf);

		return new FMLProxyPacket(buf, "TERRA-CORE");
	}

	public static void readStacksFromNBT(NBTTagCompound nbt, String name, ItemStack[] stacks) {
		NBTTagList nbttaglist = nbt.getTagList(name, Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < stacks.length; ++i) {
			if (i < nbttaglist.tagCount()) {
				NBTTagCompound nbttagcompound2 = nbttaglist.getCompoundTagAt(i);

				stacks[i] = ItemStack.loadItemStackFromNBT(nbttagcompound2);
			} else {
				stacks[i] = null;
			}
		}
	}

	public static void writeStacksToNBT(NBTTagCompound nbt, String name, ItemStack[] stacks) {
		NBTTagList nbttaglist = new NBTTagList();

		for (ItemStack stack : stacks) {
			NBTTagCompound cpt = new NBTTagCompound();
			nbttaglist.appendTag(cpt);
			if (stack != null) {
				stack.writeToNBT(cpt);
			}

		}

		nbt.setTag(name, nbttaglist);
	}
	
	public static void preDestroyBlock(World world, int i, int j, int k) {
		TileEntity tile = world.getTileEntity(i, j, k);

		
	}


}
