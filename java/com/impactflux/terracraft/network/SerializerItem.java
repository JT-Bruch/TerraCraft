package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;

public class SerializerItem extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		Item i = (Item) o;

		if (i == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			data.writeInt(Item.getIdFromItem(i));
		}
	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			return Item.getItemById(data.readInt());
		}
	}
}