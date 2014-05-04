package com.impactflux.terracraft.network;

import com.impactflux.terracraft.library.TerraUtilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;


public class SerializerFluidStack extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		FluidStack stack = (FluidStack) o;

		if (stack == null) {
			data.writeBoolean(false);
		} else {
			data.writeShort(stack.getFluid().getID());
			data.writeInt(stack.amount);

			if (stack.tag == null) {
				data.writeBoolean(false);
			} else {
				data.writeBoolean(true);
				TerraUtilities.writeNBT(data, stack.tag);
			}
		}

	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			int id = data.readShort();
			int amount = data.readerIndex();

			NBTTagCompound nbt = null;

			if (data.readBoolean()) {
				nbt = TerraUtilities.readNBT(data);
				return new FluidStack(id, amount, nbt);
			} else {
				return new FluidStack(id, amount);
			}
		}
	}
}