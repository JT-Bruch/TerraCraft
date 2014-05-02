package com.impactflux.terracraft.fluid;


import java.util.Random;

import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.library.TerraCraftReference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
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
    
    
    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int quantaRemaining = quantaPerBlock - world.getBlockMetadata(x, y, z);
        int expQuanta = -101;

        // check adjacent block levels if non-source
        if (quantaRemaining < quantaPerBlock)
        {
            int y2 = y - densityDir;

            if (world.getBlock(x,     y2, z    ) == this ||
                world.getBlock(x - 1, y2, z    ) == this ||
                world.getBlock(x + 1, y2, z    ) == this ||
                world.getBlock(x,     y2, z - 1) == this ||
                world.getBlock(x,     y2, z + 1) == this)
            {
                expQuanta = quantaPerBlock - 1;
                setEcoFlowingFluidBlock(world, x, y,  z);
            }
            else
            {
                int maxQuanta = -100;
                maxQuanta = getLargerQuanta(world, x - 1, y, z,     maxQuanta);
                maxQuanta = getLargerQuanta(world, x + 1, y, z,     maxQuanta);
                maxQuanta = getLargerQuanta(world, x,     y, z - 1, maxQuanta);
                maxQuanta = getLargerQuanta(world, x,     y, z + 1, maxQuanta);

                expQuanta = maxQuanta - 1;
            }

            // decay calculation
            if (expQuanta != quantaRemaining)
            {
                quantaRemaining = expQuanta;

                if (expQuanta <= 0)
                {
                	setEcoFlowingFluidBlock(world, x, y,  z);
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y, z, quantaPerBlock - expQuanta, 3);
                    world.scheduleBlockUpdate(x, y, z, this, tickRate);
                    world.notifyBlocksOfNeighborChange(x, y, z, this);
                }
            }
        }
        // This is a "source" block, set meta to zero, and send a server only update
        else if (quantaRemaining >= quantaPerBlock)
        {
            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        // Flow vertically if possible
        if (canDisplace(world, x, y + densityDir, z))
        {
            flowIntoBlock(world, x, y + densityDir, z, 1);
            return;
        }

        // Flow outward if possible
        int flowMeta = quantaPerBlock - quantaRemaining + 1;
        if (flowMeta >= quantaPerBlock)
        {
        	setEcoFlowingFluidBlock(world, x, y,  z);
            return;
        }

        if (isSourceBlock(world, x, y, z) || !isFlowingVertically(world, x, y, z))
        {
            if (world.getBlock(x, y - densityDir, z) == this)
            {
                flowMeta = 1;
            }
            boolean flowTo[] = getOptimalFlowDirections(world, x, y, z);

            if (flowTo[0])
        	{
            	flowIntoBlock(world, x - 1, y, z,     flowMeta);
        	}
            if (flowTo[1])
        	{
            	flowIntoBlock(world, x + 1, y, z,     flowMeta);
        	}
            if (flowTo[2])
        	{
        		flowIntoBlock(world, x,     y, z - 1, flowMeta);
        	}
            if (flowTo[3])
            {
            	flowIntoBlock(world, x,     y, z + 1, flowMeta);
            }
            
        }
        
       
    }
    @Override
    protected void flowIntoBlock(World world, int x, int y, int z, int meta)
    {
        if (meta < 0) return;
        if (displaceIfPossible(world, x, y, z))
        {
            world.setBlock(x, y, z, this, meta, 3);
        }
    }
    
    @Override
    public boolean isFlowingVertically(IBlockAccess world, int x, int y, int z)
    {
        boolean bIsFlowingVert = world.getBlock(x, y + densityDir, z) == this ||
            (world.getBlock(x, y, z) == this && canFlowInto(world, x, y + densityDir, z));
        if(!bIsFlowingVert)
        {
       
        }
        return(bIsFlowingVert);
    }
    
    private void setEcoFlowingFluidBlock(World world, int x, int y, int z)
    {
    	Block blockToSet;
    	if( y > 0  && y < 5 )
    	{
    		world.setBlock(x, y, z, Blocks.bedrock);
    	}
    	
    	if( y > 5 )
    	{
    		world.setBlock(x, y, z, Blocks.stone);
    	}
		world.markBlockForUpdate(x, y, z);
    	
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
