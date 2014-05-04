package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;

public abstract class ClassSerializer {

	public Class<? extends Object> mappedClass;

	public abstract void write(ByteBuf data, Object o,
			SerializationContext context) throws IllegalArgumentException,
			IllegalAccessException;

	public abstract Object read(ByteBuf data, Object o,
			SerializationContext context) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException,
			ClassNotFoundException;

}