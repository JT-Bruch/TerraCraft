package com.impactflux.terracraft.network;

import java.io.IOException;

import net.minecraft.network.Packet;

public interface ISynchronizedTile {

	void handleDescriptionPacket(PacketUpdate packet) throws IOException;

	void handleUpdatePacket(PacketUpdate packet) throws IOException;

	void postPacketHandling(PacketUpdate packet);

	TerraCraftPacket getUpdatePacket();

	Packet getDescriptionPacket();

	PacketPayload getPacketPayload();
}
