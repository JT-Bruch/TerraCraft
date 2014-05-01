package com.impactflux.terracraft.fluid;


import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEcopoiesisFluid extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
    
    public BlockEcopoiesisFluid(Fluid fluid, Material material) {
            super(fluid, material);
            fluid.setUnlocalizedName("yourFluid");
            setCreativeTab(CreativeTabs.tabMisc);
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon(TerraCraftReference.MODID + ":ecopoiesisstill");
            flowingIcon = register.registerIcon(TerraCraftReference.MODID + ":ecopoiesisflow");
    }
   /* 
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
    
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }*/

}
