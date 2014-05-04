package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;

public abstract class TerraCraftPacket {

	protected boolean isChunkDataPacket = false;

	public abstract int getID();

	public abstract void readData(ByteBuf data);

	public abstract void writeData(ByteBuf data);
}
