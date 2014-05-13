package com.impactflux.terracraft.blocks;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.library.SimpleInventory;
import com.impactflux.terracraft.network.ISynchronizedTile;
import com.impactflux.terracraft.network.PacketPayload;
import com.impactflux.terracraft.network.PacketUpdate;
import com.impactflux.terracraft.network.TerraCraftPacket;
import com.impactflux.terracraft.network.TilePacketWrapper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileBiomeChanger extends TerraTile
{

	private boolean m_bActivated = false;
	
	public TileBiomeChanger() {
		
	}
	
	@Override
	public void initialize() 
	{
		super.initialize();

		if (worldObj.isRemote) 
		{
			return;
		}
	}
	
	@Override
	public void updateEntity() 
	{
		super.updateEntity();

		if (worldObj.isRemote) 
		{
			return;
		}
	}
	

	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

	
	}
	

	@Override
	public void invalidate() {
		super.invalidate();
		destroy();
	}

	
	
	@Override
	public PacketPayload getPacketPayload() {
		PacketPayload payload = new PacketPayload(new PacketPayload.StreamWriter() {
			@Override
			public void writeData(ByteBuf data) {
			
	
				
			}
		});

		return payload;
	}

	public void handlePacketPayload(ByteBuf data) {

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void handleDescriptionPacket(PacketUpdate packet) throws IOException {
		handlePacketPayload(packet.payload.stream);
	}

	@Override
	public void handleUpdatePacket(PacketUpdate packet) throws IOException {
		handlePacketPayload(packet.payload.stream);
	}



	




}
