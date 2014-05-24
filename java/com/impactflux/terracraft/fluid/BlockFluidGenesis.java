package com.impactflux.terracraft.fluid;

import com.impactflux.terracraft.TerraCraft;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidGenesis extends BlockFluidTerraBase
{
	public static final int LEVELS = 4;
	public static final Material materialFluidGenesis = new MaterialLiquid(MapColor.greenColor);

	private static boolean effect = false;

	public BlockFluidGenesis() {

		super("terracraft", TerraFluids.fluidGenesis, materialFluidGenesis, "Genesis");
		setQuantaPerBlock(LEVELS);
		setTickRate(30);
		setHardness(2000F);
		setLightOpacity(7);
		setParticleColor(0.05F, 0.2F, 0.2F);
	}

	@Override
	public boolean preInit() {

		String category = "tweak";
		String comment = "Enable this for Fluid Genesis to randomly teleport entities on contact.";
		effect = TerraCraft.config.get(category, "Fluid.Genesis.Effect", true, comment);

		GameRegistry.registerBlock(this, "FluidGenesis");
		return true;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

		if (!effect) {
			return;
		}
		if (world.getTotalWorldTime() % 4 == 0) {
			int x2 = x - 8 + world.rand.nextInt(17);
			int y2 = y + world.rand.nextInt(8);
			int z2 = z - 8 + world.rand.nextInt(17);

			
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {

		return TerraFluids.fluidGenesis.getLuminosity();
	}

}
