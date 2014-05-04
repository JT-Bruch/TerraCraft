package com.impactflux.terracraft.network;

import io.netty.buffer.ByteBuf;

public class PacketPayload {

	public ByteBuf stream;
	private StreamWriter handler;

	public interface StreamWriter {
		void writeData(ByteBuf data);
	}

	public PacketPayload() {
	}

	public PacketPayload(StreamWriter handler) {
		this.handler = handler;
	}

	public void writeData(ByteBuf data) {
		handler.writeData(data);
	}

	public void readData(ByteBuf data) {
		stream = data;
	}
}