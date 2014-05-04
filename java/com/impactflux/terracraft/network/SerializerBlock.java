package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;

public class SerializerBlock extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		Block b = (Block) o;

		if (b == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			data.writeInt(Block.getIdFromBlock(b));
		}
	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			return Block.getBlockById(data.readInt());
		}
	}
}