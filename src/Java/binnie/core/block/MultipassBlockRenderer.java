package binnie.core.block;

import binnie.core.BinnieCore;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class MultipassBlockRenderer
  implements ISimpleBlockRenderingHandler
{
  public static MultipassBlockRenderer instance;
  
  public MultipassBlockRenderer()
  {
    instance = this;
  }
  
  private void setColour(Tessellator tess, int colour)
  {
    float var6 = (colour >> 16 & 0xFF) / 255.0F;
    float var7 = (colour >> 8 & 0xFF) / 255.0F;
    float var8 = (colour & 0xFF) / 255.0F;
    GL11.glColor3f(var6, var7, var8);
  }
  
  public static int getLayer()
  {
    return layer;
  }
  
  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer)
  {
    block.setBlockBoundsForItemRender();
    renderer.setRenderBoundsFromBlock(block);
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    layer = 0;
    while (layer < ((IMultipassBlock)block).getNumberOfPasses())
    {
      renderItem(block, renderer, meta);
      layer += 1;
    }
    layer = 0;
  }
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
  {
    boolean r = true;
    layer = 0;
    while (layer < ((IMultipassBlock)block).getNumberOfPasses())
    {
      r = renderer.renderStandardBlock(block, x, y, z);
      layer += 1;
    }
    layer = 0;
    return r;
  }
  
  public boolean shouldRender3DInInventory(int i)
  {
    return true;
  }
  
  public int getRenderId()
  {
    return BinnieCore.multipassRenderID;
  }
  
  public void renderItem(Block block, RenderBlocks renderer, int meta)
  {
    setColor(((IMultipassBlock)block).colorMultiplier(meta));
    Tessellator tessellator = Tessellator.instance;
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, -1.0F, 0.0F);
    renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, meta));
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 1.0F, 0.0F);
    renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, meta));
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, -1.0F);
    renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, meta));
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(0.0F, 0.0F, 1.0F);
    renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, meta));
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
    renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, meta));
    tessellator.draw();
    tessellator.startDrawingQuads();
    tessellator.setNormal(1.0F, 0.0F, 0.0F);
    renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, meta));
    tessellator.draw();
  }
  
  public void setColor(int l)
  {
    float f = (l >> 16 & 0xFF) / 255.0F;
    float f2 = (l >> 8 & 0xFF) / 255.0F;
    float f3 = (l & 0xFF) / 255.0F;
    GL11.glColor3f(f, f2, f3);
  }
  
  private static int layer = 0;
}
