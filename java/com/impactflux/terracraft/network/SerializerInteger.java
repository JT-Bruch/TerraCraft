package com.impactflux.terracraft.network;
import io.netty.buffer.ByteBuf;

public class SerializerInteger extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		Integer i = (Integer) o;

		data.writeInt(i);
	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		return new Integer(data.readInt());
	}
}