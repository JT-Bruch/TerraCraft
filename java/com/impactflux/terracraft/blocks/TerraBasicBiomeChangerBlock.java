package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.tileentities.TerraBasicBiomeChangerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cofh.api.core.IInitializer;
import cpw.mods.fml.common.registry.GameRegistry;




public class TerraBasicBiomeChangerBlock extends Block implements IInitializer 
{
	public static Block blockBasicBiomeChanger;
	public static final String NAME = "basic";
	
	public TerraBasicBiomeChangerBlock()
	{
		super(Material.anvil);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypeStone);
		setCreativeTab(TerraCraft.tab);
		setBlockName("terracraft.biomechanger");
		
	}

	@Override
	public boolean preInit() 
	{
		GameRegistry.registerBlock(this, TerraBasicBiomeChangerItemBlock.class, "BiomeChanger");
		return true;
	}

	@Override
	public boolean initialize() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean postInit() 
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }
 
    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new TerraBasicBiomeChangerTileEntity();
    }

}
