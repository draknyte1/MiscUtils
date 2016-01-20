package binnie.craftgui.resource;

import binnie.craftgui.core.CraftGUI;
import binnie.craftgui.resource.minecraft.CraftGUIResourceManager;

public class StyleSheetManager
{
  public static Texture getTexture(Object key)
  {
    return defaultSS.getTexture(key);
  }
  
  public static IStyleSheet getDefault()
  {
    return defaultSS;
  }
  
  static IStyleSheet defaultSS = new DefaultStyleSheet(null);
  
  private static class DefaultStyleSheet
    implements IStyleSheet
  {
    public Texture getTexture(Object key)
    {
      return CraftGUI.ResourceManager.getTexture(key.toString());
    }
  }
}
