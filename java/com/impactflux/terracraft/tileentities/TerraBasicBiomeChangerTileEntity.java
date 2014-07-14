package com.impactflux.terracraft.tileentities;

import java.util.HashMap;
import java.util.Map;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.blocks.TerraEcopoiesisBlockStorage;
import com.impactflux.terracraft.blocks.TerraGenesisBlockStorage;
import com.impactflux.terracraft.library.TerraBiomeChangeLogic;
import com.impactflux.terracraft.library.TerraBiomeHelper;
import com.impactflux.terracraft.library.TerraBlockCoord;

import cofh.api.core.IInitializer;
import cofh.util.BlockCoord;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TerraBasicBiomeChangerTileEntity extends TerraTileEntity implements IInitializer 
{
	public static Block terraBasicBiomeChangerBlock;
	
	public TerraBiomeChangeLogic m_BiomeLogic;
	
	private Map<Integer, BlockCoord> m_BiomeMap;
	
	int m_TickRate = 20,  // Once a second
	    m_Tick;
	
	boolean m_bReplacingBiome = false,
			m_bBiomeReplaceInit = false,
			m_bValidStructure = false;
	

	@Override
	public boolean preInit() {
		GameRegistry.registerTileEntity(TerraBasicBiomeChangerTileEntity.class, "TerraBasicBiomeTileEntity");
		return true;
	}

	@Override
	public boolean initialize() {
		
		return true;
	}

	@Override
	public boolean postInit() {
		// TODO Auto-generated method stub
		return true;
	}
	
	 @Override
    public void updateEntity()
    {

		 
        //Only on the server side do
        if(!worldObj.isRemote)
        {
        	if(m_BiomeLogic == null)
        	{
        		m_BiomeLogic = new TerraBiomeChangeLogic();
        	}
        	//
        	// Check surrounding blocks, if valid structure begin terraforming
        	//
        	
            if(m_Tick % m_TickRate == 0 && m_BiomeLogic != null)
            {
            	m_bValidStructure = IsValidStructure();
            	if(m_bValidStructure)
            	{
            		if(!m_bBiomeReplaceInit)
            		{
            			
            			BlockCoord originBlock = new BlockCoord(xCoord, yCoord - 1, zCoord);
            			m_BiomeMap = TerraBiomeHelper.GetMapToReplaceFromBiomeMin(worldObj, originBlock);
            			
            			
            			m_BiomeLogic.SetWorld(getWorldObj());
            			m_BiomeLogic.SetBiomeToReplace(m_BiomeMap);
            			
            			
            			m_bBiomeReplaceInit = true;
            		}
            		
            		if(m_bBiomeReplaceInit && m_bValidStructure && !m_bReplacingBiome)
            		{
            			SetBiomeTypeToChange();
            			m_BiomeLogic.ReplaceBiome();
            		}		
            	}
            	else
            	{
            		m_bBiomeReplaceInit = false;
            	}
                
            }
            //
            // Increment tick, don't care if it runs back to zero.
            //
            m_Tick++;
        }
    }
	 
	private boolean IsValidStructure()
	{
		boolean bValid = false;
		
		TerraEcopoiesisBlockStorage ecoBlock = new TerraEcopoiesisBlockStorage();
		TerraGenesisBlockStorage genBlock = new TerraGenesisBlockStorage();
		
		int xCoordToCheck = 1;
		
		if(worldObj.getBlock(xCoord + xCoordToCheck, yCoord, zCoord).getMaterial() == ecoBlock.getMaterial() &&
		   worldObj.getBlock(xCoord - xCoordToCheck, yCoord, zCoord).getMaterial() == genBlock.getMaterial()	)
		{
			bValid = true;
		}
		
		
		
		return(bValid);
		
	}
	 
	private boolean SetBiomeTypeToChange()
	{
		boolean bRetVal = false,
				bAirBlock = false;
		int yCoordToCheck = 0;
		
		do
		{
			bAirBlock = worldObj.isAirBlock(xCoord, yCoord + yCoordToCheck, zCoord);
			if(bAirBlock)
			{
				bAirBlock = true;
				bRetVal = false;
			}
			else
			{
			
				switch(yCoordToCheck)
				{
				case 1:
					m_BiomeLogic.SetStoneToReplace(worldObj.getBlock(xCoord, yCoord + yCoordToCheck, zCoord));
					break;
				case 2:
					m_BiomeLogic.SetDirtToReplace(worldObj.getBlock(xCoord, yCoord + yCoordToCheck, zCoord));
					break;
				case 3:
					m_BiomeLogic.SetGravelToReplace(worldObj.getBlock(xCoord, yCoord + yCoordToCheck, zCoord));
					break;
				case 4:
					m_BiomeLogic.SetClayToReplace(worldObj.getBlock(xCoord, yCoord + yCoordToCheck, zCoord));
					break;
				case 5:
					m_BiomeLogic.SetFluidToReplace(worldObj.getBlock(xCoord, yCoord + yCoordToCheck, zCoord));
					break;
				}
				bRetVal = true;
				m_BiomeLogic.SetMaxRadus(10);
				
				
				yCoordToCheck++;
			}
		}
		while(yCoordToCheck < 5 && !bAirBlock);
		
		return(bRetVal);
	}
	
	
	
	

}
