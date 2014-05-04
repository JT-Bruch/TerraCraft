package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;

import com.impactflux.terracraft.library.TerraUtilities;

public class SerializerObject extends ClassSerializer {

	@Override
	public void write(ByteBuf data, Object o, SerializationContext context)
			throws IllegalArgumentException, IllegalAccessException {

		if (o == null) {
			data.writeBoolean(false);
		} else {
			data.writeBoolean(true);
			Class realClass = o.getClass();

			ClassSerializer delegateMapping;

			if (context.classToId.containsKey(realClass.getCanonicalName())) {
				int index = context.classToId.get(realClass.getCanonicalName()) + 1;
				data.writeByte(index);
				delegateMapping = context.idToClass.get(index - 1);
			} else {
				int index = context.classToId.size() + 1;
				delegateMapping = ClassMapping.get(realClass);
				data.writeByte(index);
				TerraUtilities.writeUTF(data, realClass.getCanonicalName());
				context.classToId.put(realClass.getCanonicalName(),
						context.classToId.size());
				context.idToClass.add(delegateMapping);
			}

			if (delegateMapping instanceof ClassMapping) {
				((ClassMapping) delegateMapping).writeClass(o, data, context);
			} else {
				delegateMapping.write(data, o, context);
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
			int index = data.readByte();

			ClassSerializer delegateMapping;

			if (context.idToClass.size() < index) {
				String className = TerraUtilities.readUTF(data);

				Class cls = Class.forName(className);
				delegateMapping = ClassMapping.get(cls);

				context.idToClass.add(ClassMapping.get(cls));
			} else {
				delegateMapping = context.idToClass.get(index - 1);
			}

			if (delegateMapping instanceof ClassMapping) {
				return ((ClassMapping) delegateMapping).readClass(o, data, context);
			} else {
				return delegateMapping.read(data, o, context);
			}
		}
	}

}