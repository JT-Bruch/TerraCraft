package com.impactflux.terracraft.blocks;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class TileBiomeChanger extends TerraTile implements IInventory
{
	private SimpleInventory inv = new SimpleInventory(27, "BiomeChanger", 64);

	public TileBiomeChanger() {
		inv.addListener(this);

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
	public final int getSizeInventory() {
		return inv.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv.getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inv.decrStackSize(slot, amount);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv.setInventorySlotContents(slot, stack);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inv.getStackInSlotOnClosing(slot);
	}

	@Override
	public String getInventoryName() {
		return "BiomeChanger";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		inv.readFromNBT(nbt);

		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		inv.writeToNBT(nbt);
	}
	@Override
	public int getInventoryStackLimit() {
		return inv.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return entityplayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
				zCoord + 0.5D) <= 64D;
	}

	@Override
	public void invalidate() {
		super.invalidate();
		destroy();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}


	@Override
	public boolean hasCustomInventoryName() {
		return false;
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



	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}





}
