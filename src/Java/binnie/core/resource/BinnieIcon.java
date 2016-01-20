package binnie.core.resource;

import binnie.Binnie;
import binnie.core.AbstractMod;
import binnie.core.BinnieCore;
import binnie.core.proxy.BinnieProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BinnieIcon
  extends BinnieResource
{
  private int textureSheet;
  private IIcon icon;
  
  public BinnieIcon(AbstractMod mod, ResourceType type, String path)
  {
    super(mod, type, path);
    this.textureSheet = 0;
    this.icon = null;
    this.textureSheet = (type != ResourceType.Block ? 1 : 0);
    Binnie.Resource.registerIcon(this);
  }
  
  public IIcon getIcon()
  {
    return this.icon;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(IIconRegister register)
  {
    registerIcon(register);
    return this.icon;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcon(IIconRegister register)
  {
    this.icon = BinnieCore.proxy.getIcon(register, this.mod, this.path);
  }
  
  public int getTextureSheet()
  {
    return this.textureSheet;
  }
}
