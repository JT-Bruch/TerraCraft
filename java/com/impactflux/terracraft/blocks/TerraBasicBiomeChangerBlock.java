package com.impactflux.terracraft.blocks;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.tileentities.TerraBasicBiomeChangerTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cofh.api.core.IInitializer;
import cofh.render.IconRegistry;
import cofh.util.StringHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;




public class TerraBasicBiomeChangerBlock extends Block implements IInitializer 
{
	public static Block blockBasicBiomeChanger;
	public static final String NAME = "basicBiomeChanger";
	public static final int LIGHT = 4;
	
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
    
    @Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return LIGHT;
	}

	@Override
	public int damageDropped(int i) {

		return i;
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		return IconRegistry.getIcon("Tile" + StringHelper.titleCase(NAME));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) 
	{
		
	  IconRegistry.addIcon("Tile" + StringHelper.titleCase(NAME), "terracraft:tile/Tile_" + StringHelper.titleCase(NAME), ir);

	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(TerraCraft.instance, 0, world, x ,y, z);
        return true;
    }

}
