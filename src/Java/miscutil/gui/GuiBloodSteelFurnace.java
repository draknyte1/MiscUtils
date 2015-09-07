package miscutil.gui;

import org.lwjgl.opengl.GL11;

import miscutil.inventory.ContainerBloodSteelFurnace;
import miscutil.lib.Strings;
import miscutil.tile_entity.TileEntityBloodSteelFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBloodSteelFurnace extends GuiContainer{
	
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(Strings.MODID+":textures/gui/container/ArcaneInfuser.png");
	private TileEntityBloodSteelFurnace tileFurnace;

	public GuiBloodSteelFurnace(InventoryPlayer invPlayer, TileEntityBloodSteelFurnace tile) {
		super(new ContainerBloodSteelFurnace(invPlayer, tile));
		this.tileFurnace = tile;
		
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		String string = this.tileFurnace.hasCustomInventoryName() ? this.tileFurnace.getInventoryName() : I18n.format(this.tileFurnace.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string)-60, -30, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8-150, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		 GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k-32, l-53, 0, 0, this.xSize+85, this.ySize+75);
	        int i1;

	        if (this.tileFurnace.isBurning())
	        {
	            i1 = this.tileFurnace.getBurnTimeRemainingScaled(12);
	            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
	        }

	        i1 = this.tileFurnace.getCookProgressScaled(24);
	        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

}
