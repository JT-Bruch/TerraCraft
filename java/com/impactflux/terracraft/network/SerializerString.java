package com.impactflux.terracraft.network;

import com.impactflux.terracraft.library.TerraUtilities;

import io.netty.buffer.ByteBuf;


public class SerializerString extends ClassSerializer {

	@Override
	public void write (ByteBuf data, Object o, SerializationContext context) {
		String s = (String) o;

		if (s == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			TerraUtilities.writeUTF(data, s);
		}
	}

	@Override
	public Object read (ByteBuf data, Object o, SerializationContext context) {
		if (!data.readBoolean()) {
			return null;
		} else {
			return TerraUtilities.readUTF(data);
		}
	}
}
