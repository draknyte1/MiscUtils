package binnie.core.machines.inventory;

import binnie.Binnie;
import binnie.core.AbstractMod;
import binnie.core.resource.BinnieIcon;
import binnie.core.resource.ManagerResource;
import java.util.ArrayList;
import java.util.List;

public class ValidatorIcon
{
  private List<BinnieIcon> iconsInput;
  private List<BinnieIcon> iconsOutput;
  
  public ValidatorIcon(AbstractMod mod, String pathInput, String pathOutput)
  {
    this.iconsInput = new ArrayList();
    this.iconsOutput = new ArrayList();
    this.iconsInput.add(Binnie.Resource.getItemIcon(mod, pathInput));
    this.iconsOutput.add(Binnie.Resource.getItemIcon(mod, pathOutput));
  }
  
  public BinnieIcon getIcon(boolean input)
  {
    return input ? (BinnieIcon)this.iconsInput.get(0) : (BinnieIcon)this.iconsOutput.get(0);
  }
}
