package com.impactflux.terracraft.network;

import com.impactflux.terracraft.library.TerraUtilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class SerializerItemStack extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		ItemStack stack = (ItemStack) o;

		if (stack == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			NBTTagCompound nbt = new NBTTagCompound();
			stack.writeToNBT(nbt);
			TerraUtilities.writeNBT(data, nbt);
		}

	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			return ItemStack.loadItemStackFromNBT(TerraUtilities.readNBT(data));
		}
	}
}
