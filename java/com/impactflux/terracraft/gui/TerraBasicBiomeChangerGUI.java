package com.impactflux.terracraft.gui;

import com.impactflux.terracraft.TerraCraft;
import com.impactflux.terracraft.library.GuiHandler;

import cofh.api.core.IInitializer;
import cofh.gui.GuiBase;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TerraBasicBiomeChangerGUI extends GuiBase implements IInitializer
{
	/**
     * The gui file needs to be 256x256.
     * The GUI ITSELF can have any size you want
     * Define them here
     * These are the GUI sizes!
     */
    int xSize = 176;
    int ySize = 214;
    private static final ResourceLocation backgroundimage = new ResourceLocation("terracraft:textures/gui/gui_basicbiomechanger.png");
 

	public TerraBasicBiomeChangerGUI(Container container) {

		super(container);
	}

	public TerraBasicBiomeChangerGUI(Container container, ResourceLocation texture) {

		super(container);
		this.texture = texture;
	}
    
    public void drawScreen(int par1, int par2, float par3)
    {
        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize - 30) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize,  ySize);
    }
    
    /**
     * Prevents game from being paused upon opening
     *
     */
 
    public boolean doesGuiPauseGame()
    {
        return false;
    }

	@Override
	public boolean preInit() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean initialize() {
		NetworkRegistry.INSTANCE.registerGuiHandler(TerraCraft.instance, new GuiHandler());
		return true;
	}

	@Override
	public boolean postInit() {
		// TODO Auto-generated method stub
		return true;
	}

}
