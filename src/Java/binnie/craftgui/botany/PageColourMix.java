package binnie.craftgui.botany;

import binnie.botany.api.IColourMix;
import binnie.botany.api.IFlowerColour;
import binnie.botany.api.IFlowerRoot;
import binnie.botany.core.BotanyCore;
import binnie.craftgui.controls.ControlText;
import binnie.craftgui.controls.ControlTextCentered;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.mod.database.DatabaseTab;
import binnie.craftgui.mod.database.PageAbstract;
import java.util.ArrayList;
import java.util.List;

public class PageColourMix
  extends PageAbstract<IFlowerColour>
{
  ControlText pageSpeciesFurther_Title;
  ControlColourMixBox pageSpeciesFurther_List;
  
  public PageColourMix(IWidget parent, DatabaseTab tab)
  {
    super(parent, tab);
    this.pageSpeciesFurther_Title = new ControlTextCentered(this, 8.0F, "Further Mixes");
    this.pageSpeciesFurther_List = new ControlColourMixBox(this, 4, 20, 136, 152, ControlColourMixBox.Type.Further);
  }
  
  public void onValueChanged(IFlowerColour colour)
  {
    List<IColourMix> mixes = new ArrayList();
    for (IColourMix mix : BotanyCore.getFlowerRoot().getColourMixes(false)) {
      if ((mix.getColour1() == colour) || (mix.getColour2() == colour)) {
        mixes.add(mix);
      }
    }
    this.pageSpeciesFurther_List.setOptions(mixes);
  }
}
