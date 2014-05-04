package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.library.TerraUtilities;
import com.impactflux.terracraft.network.ISynchronizedTile;
import com.impactflux.terracraft.network.PacketPayload;
import com.impactflux.terracraft.network.PacketTileUpdate;
import com.impactflux.terracraft.network.PacketUpdate;
import com.impactflux.terracraft.network.TerraCraftPacket;
import com.impactflux.terracraft.network.TilePacketWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TerraTile extends TileEntity implements ISynchronizedTile 
{
	@SuppressWarnings("rawtypes")
	private static Map<Class, TilePacketWrapper> updateWrappers = new HashMap<Class, TilePacketWrapper>();
	@SuppressWarnings("rawtypes")
	private static Map<Class, TilePacketWrapper> descriptionWrappers = new HashMap<Class, TilePacketWrapper>();
	private final TilePacketWrapper descriptionPacket;
	private final TilePacketWrapper updatePacket;
	private boolean init = false;
	private String owner = "[TerraCraft]";

	public TerraTile() {
		if (!updateWrappers.containsKey(this.getClass())) {
			updateWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
		}

		if (!descriptionWrappers.containsKey(this.getClass())) {
			descriptionWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
		}

		updatePacket = updateWrappers.get(this.getClass());
		descriptionPacket = descriptionWrappers.get(this.getClass());

	}

	public String getOwner() {
		return owner;
	}

	@Override
	public void updateEntity() {
		if (!init && !isInvalid()) {
			initialize();
			init = true;
		}

		/*if (this instanceof IPowerReceptor) {
			IPowerReceptor receptor = (IPowerReceptor) this;
			receptor.getPowerReceiver(null).update();
		}*/
	}

	@Override
	public void invalidate() {
		init = false;
		super.invalidate();
	}

	public void initialize() {
		//TerraUtilities.handleBufferedDescription(this);
	}

	public void onBlockPlacedBy(EntityLivingBase entity, ItemStack stack) {
		if (entity instanceof EntityPlayer) {
			owner = ((EntityPlayer) entity).getDisplayName();
		}
	}

	public void destroy() {
	}

	public void sendNetworkUpdate() {
		if (!worldObj.isRemote) {
			TerraCraft.instance.sendToPlayers(getUpdatePacket(), worldObj,
					xCoord, yCoord, zCoord, 30);
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		return TerraUtilities.toPacket(getUpdatePacket(), 0);
	}

	@Override
	public PacketPayload getPacketPayload() {
		return updatePacket.toPayload(this);
	}

	@Override
	public TerraCraftPacket getUpdatePacket() {
		return new PacketTileUpdate(this);
	}

	@Override
	public void handleDescriptionPacket(PacketUpdate packet) throws IOException {
		descriptionPacket.fromPayload(this, packet.payload);
	}

	@Override
	public void handleUpdatePacket(PacketUpdate packet) throws IOException {
		updatePacket.fromPayload(this, packet.payload);
	}

	@Override
	public void postPacketHandling(PacketUpdate packet) {
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("owner", owner);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("owner")) {
			owner = nbt.getString("owner");
		}
	}

	public World getWorld() {
		return worldObj;
	}

}
