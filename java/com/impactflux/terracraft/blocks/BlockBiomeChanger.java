package com.impactflux.terracraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.impactflux.terracraft.library.TerraCraftReference;
import com.impactflux.terracraft.library.TerraUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBiomeChanger extends BlockContainer
{
	private IIcon textureSides;
	private IIcon textureTopOn;
	private IIcon textureTopOff;
	
	public BlockBiomeChanger()
	{
		super(Material.iron);
		setBlockName("blockBiomeChanger");
		setBlockTextureName(TerraCraftReference.MODID + ":" + getUnlocalizedName().substring(5));
		setCreativeTab(CreativeTabs.tabBlock);
		setStepSound(soundTypePiston);
		setHardness(10F);
		setResistance(10F);
		setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {

		// Drop through if the player is sneaking
		if (entityplayer.isSneaking()) {
			return false;
		}

		
		return true;

	}

	


	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileBiomeChanger();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		TerraUtilities.preDestroyBlock(world, x, y, z);
		super.breakBlock(world, x, y, z, block, par6);
	}



	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return false;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return 1;
	}
	
	
	

}
