package binnie.craftgui.mod.database;

import binnie.core.genetics.BreedingSystem;
import binnie.craftgui.controls.listbox.ControlList;
import binnie.craftgui.controls.listbox.ControlListBox;
import binnie.craftgui.controls.scroll.ControlScrollableContent;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.minecraft.Window;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IMutation;
import java.util.List;

class ControlMutationBox
  extends ControlListBox<IMutation>
{
  private int index;
  private Type type;
  private IAlleleSpecies species;
  
  public IWidget createOption(IMutation value, int y)
  {
    return new ControlMutationItem((ControlList)getContent(), value, this.species, y);
  }
  
  public ControlMutationBox(IWidget parent, int x, int y, int width, int height, Type type)
  {
    super(parent, x, y, width, height, 12.0F);
    this.species = null;
    this.type = type;
  }
  
  public void setSpecies(IAlleleSpecies species)
  {
    if (species != this.species)
    {
      this.species = species;
      this.index = 0;
      movePercentage(-100.0F);
      BreedingSystem system = ((WindowAbstractDatabase)getSuperParent()).getBreedingSystem();
      List<IMutation> discovered = system.getDiscoveredMutations(Window.get(this).getWorld(), Window.get(this).getUsername());
      if (species != null) {
        if (this.type == Type.Resultant)
        {
          setOptions(system.getResultantMutations(species));
        }
        else
        {
          List<IMutation> mutations = system.getFurtherMutations(species);
          int i = 0;
          while (i < mutations.size())
          {
            IMutation mutation = (IMutation)mutations.get(i);
            if ((!discovered.contains(mutations)) && (!((IAlleleSpecies)mutation.getTemplate()[0]).isCounted())) {
              mutations.remove(i);
            } else {
              i++;
            }
          }
          setOptions(mutations);
        }
      }
    }
  }
  
  static enum Type
  {
    Resultant,  Further;
    
    private Type() {}
  }
}
