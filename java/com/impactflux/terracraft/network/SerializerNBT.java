package com.impactflux.terracraft.network;

import com.impactflux.terracraft.library.TerraUtilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;


public class SerializerNBT extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		NBTTagCompound nbt = (NBTTagCompound) o;

		if (nbt == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			TerraUtilities.writeNBT(data, nbt);
		}
	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			return TerraUtilities.readNBT(data);
		}
	}
}