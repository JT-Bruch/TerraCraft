package com.impactflux.terracraft.items;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.impactflux.terracraft.library.AbilityHelper;
import com.impactflux.terracraft.library.items.TerraItemShovelAdv;


public class ItemShovelGenesis extends TerraItemShovelAdv
{

	public ItemShovelGenesis(ToolMaterial toolMaterial) 
	{
		super(toolMaterial);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if (!(entity instanceof EntityPlayer)) {
			return false;
		}
		if (block.getBlockHardness(world, x, y, z) == 0.0D) {
			return true;
		}
		if( !TerraItems.genesisEnableFeature[TerraItems.SHOVEL] )
		{
			return true;
		}
		
		EntityPlayer player = (EntityPlayer) entity;
		
		ArrayList<Block> blockTypes = getGenesisReplacementOreTypeArray();
			
			

        MovingObjectPosition mop = AbilityHelper.raytraceFromEntity(world, player, true, 4.5D);
        if (mop == null)
            return super.onBlockStartBreak(stack, x, y, z, player);

        int xRange = 1;
        int yRange = 1;
        int zRange = 1;
        switch (mop.sideHit)
        {
        case 0:
        case 1:
            yRange = 0;
            break;
        case 2:
        case 3:
            zRange = 0;
            break;
        case 4:
        case 5:
            xRange = 0;
            break;
        }


        Random rand = new Random();
        for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
        {
            for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
            {
                for (int zPos = z - zRange; zPos <= z + zRange; zPos++)
                {
                	int randBlockType = Math.abs( rand.nextInt() % blockTypes.size() );
                	int effectChance = Math.abs(rand.nextInt() % 100);
                	boolean bVal = IsBlockTypeReplaceableForShovel(world.getBlock(xPos, yPos, zPos));
                	if( bVal )
                	{	
                		if( effectChance <= TerraItems.genesisFeatureChance[TerraItems.SHOVEL]  )
                		{
                			world.setBlock(xPos, yPos, zPos, blockTypes.get(randBlockType));
                		}
                	}
                }
            }
        }
	
		return true;
	}

	

}
