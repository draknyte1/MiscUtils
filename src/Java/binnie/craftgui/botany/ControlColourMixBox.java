package binnie.craftgui.botany;

import binnie.botany.api.IColourMix;
import binnie.craftgui.controls.listbox.ControlList;
import binnie.craftgui.controls.listbox.ControlListBox;
import binnie.craftgui.controls.scroll.ControlScrollableContent;
import binnie.craftgui.core.IWidget;

public class ControlColourMixBox
  extends ControlListBox<IColourMix>
{
  private int index;
  private Type type;
  
  public IWidget createOption(IColourMix value, int y)
  {
    return new ControlColourMixItem((ControlList)getContent(), value, y);
  }
  
  public ControlColourMixBox(IWidget parent, int x, int y, int width, int height, Type type)
  {
    super(parent, x, y, width, height, 12.0F);
    this.type = type;
  }
  
  static enum Type
  {
    Resultant,  Further;
    
    private Type() {}
  }
}
