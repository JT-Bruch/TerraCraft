package com.impactflux.terracraft.items;

import java.util.ArrayList;
import java.util.Random;

import cofh.util.BlockCoord;

import com.impactflux.terracraft.blocks.TerraBlocks;
import com.impactflux.terracraft.blocks.TerraEcopoiesisBlockOre;
import com.impactflux.terracraft.blocks.TerraGenesisBlockOre;
import com.impactflux.terracraft.library.AbilityHelper;
import com.impactflux.terracraft.library.items.TerraItemPickaxeAdv;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;




public class ItemPickaxeGenesis extends TerraItemPickaxeAdv
{

	public ItemPickaxeGenesis(ToolMaterial toolMaterial) 
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
		EntityPlayer player = (EntityPlayer) entity;
		
		if( !isGenesisReplaceable(block) )
		{
			return true;
		}
		else
		{
			ArrayList<Block> blockTypes = getReplacementBlockTypeArray();

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
	                	int test = Math.abs( rand.nextInt() % blockTypes.size() );
	                	world.setBlock(xPos, yPos, zPos, blockTypes.get(test));
	                }
	            }
	        }
            
	        
	        
			
		}
		return true;
	}
	
	public boolean isGenesisReplaceable(Block block)
	{
		boolean bRetVal = false;
		
		if( TerraBlocks.blockGenesisOre == block )
		{
			bRetVal = true;
		}
		return(bRetVal);
	}
	
	public ArrayList<BlockCoord> getReplaceableBlockArray(World world, int x, int y, int z)
	{
		ArrayList<BlockCoord> repBlockArray = new ArrayList<BlockCoord>();
		
		repBlockArray.add(new BlockCoord(x, y, z - 1));
		repBlockArray.add(new BlockCoord(x, y + 1, z - 1));
		repBlockArray.add(new BlockCoord(x, y - 1, z - 1));
		repBlockArray.add(new BlockCoord(x, y, z + 1));
		repBlockArray.add(new BlockCoord(x, y + 1, z + 1));
		repBlockArray.add(new BlockCoord(x, y - 1, z + 1));
		repBlockArray.add(new BlockCoord(x, y + 1, z));
		repBlockArray.add(new BlockCoord(x, y - 1, z));
		return(repBlockArray);
	}
	
	
	public ArrayList<Block> getReplacementBlockTypeArray()
	{	
		ArrayList<Block> repBlockArray = new ArrayList<Block>();
		
		repBlockArray.add(Blocks.diamond_block);
		repBlockArray.add(Blocks.gold_block);
		repBlockArray.add(Blocks.iron_block);
		repBlockArray.add(Blocks.emerald_block);
		repBlockArray.add(Blocks.lapis_block);
		repBlockArray.add(Blocks.quartz_block);
		
		
		return(repBlockArray);
	}


	

}
