package com.impactflux.terracraft.fluid;


import cofh.util.ServerHelper;
import cpw.mods.fml.common.registry.GameRegistry;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

import com.impactflux.terracraft.TerraCraft;

public class BlockFluidEcopoiesis extends BlockFluidTerraBase
{
	public static final int LEVELS = 6;
	public static final Material materialFluidEcopoiesis = new MaterialLiquid(MapColor.magentaColor);

	private static boolean bEffect = true;

	private static int maxEcopoiesisHeight = 120;

	public BlockFluidEcopoiesis() {

		super("terracraft", TerraFluids.fluidEcopoiesis, materialFluidEcopoiesis, "Ecopoiesis");
		setQuantaPerBlock(LEVELS);
		setTickRate(20);

		setHardness(1F);
		setLightOpacity(0);
		setParticleColor(1.0F, 0.9F, 0.05F);
	}

	@Override
	public boolean preInit() {

		String category = "tweak";
		String comment = "Enable this for Fluid Ecopoiesis to have an effect.";
		bEffect = TerraCraft.config.get(category, "Fluid.Ecopoiesis.Effect", true, comment);

		 
		GameRegistry.registerBlock(this, "FluidEcopoiesis");
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

		if (!bEffect) {
			return;
		}
		if (entity instanceof EntityLivingBase) {
			if (entity.motionY < -0.2) {
				entity.motionY *= 0.5;
				if (entity.fallDistance > 20) {
					entity.fallDistance = 20;
				} else {
					entity.fallDistance *= 0.95;
				}
			}
		}
		if (ServerHelper.isClientWorld(world)) {
			return;
		}
		if (world.getTotalWorldTime() % 8 == 0 && entity instanceof EntityLivingBase && !((EntityLivingBase) entity).isEntityUndead()) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.digSpeed.id, 6 * 20, 0));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.jump.id, 6 * 20, 0));
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return TerraFluids.fluidEcopoiesis.getLuminosity();
	}

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

	

}
