package com.impactflux.terracraft.network;

import java.util.LinkedList;

import io.netty.buffer.ByteBuf;

public class SerializerLinkedList extends ClassSerializer {

	private static SerializerObject anonymousSerializer = new SerializerObject();

	@Override
	public void write(ByteBuf data, Object o, SerializationContext context)
			throws IllegalArgumentException, IllegalAccessException {

		LinkedList list = (LinkedList) o;

		if (o == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			data.writeShort(list.size());

			for (Object val : list) {
				anonymousSerializer.write(data, val, context);
			}
		}
	}

	@Override
	public Object read(ByteBuf data, Object o, SerializationContext context)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException, ClassNotFoundException {

		if (!data.readBoolean()) {
			return null;
		} else {
			int size = data.readShort();

			LinkedList list = new LinkedList ();

			for (int i = 0; i < size; ++i) {
				Object val = anonymousSerializer.read(data, null, context);

				list.add(val);
			}

			return list;
		}
	}

}
