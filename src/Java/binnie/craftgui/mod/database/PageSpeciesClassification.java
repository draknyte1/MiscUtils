package binnie.craftgui.mod.database;

import binnie.craftgui.controls.ControlText;
import binnie.craftgui.controls.ControlTextCentered;
import binnie.craftgui.core.IWidget;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;
import forestry.api.genetics.IClassification.EnumClassLevel;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageSpeciesClassification
  extends PageSpecies
{
  private Map<IClassification.EnumClassLevel, ControlText> levels;
  private ControlText genus;
  
  public PageSpeciesClassification(IWidget parent, DatabaseTab tab)
  {
    super(parent, tab);
    this.levels = new LinkedHashMap();
    int y = 16;
    for (IClassification.EnumClassLevel level : IClassification.EnumClassLevel.values())
    {
      ControlText text = new ControlTextCentered(this, y, "");
      text.setColour(level.getColour());
      this.levels.put(level, text);
      y += 12;
    }
    (this.genus = new ControlTextCentered(this, y, "")).setColour(16759415);
  }
  
  public void onValueChanged(IAlleleSpecies species)
  {
    if (species != null)
    {
      for (ControlText text : this.levels.values()) {
        text.setValue("- - -");
      }
      this.genus.setValue(species.getBinomial());
      for (IClassification classification = species.getBranch(); classification != null; classification = classification.getParent())
      {
        IClassification.EnumClassLevel level = classification.getLevel();
        String text2 = "";
        int n = level.ordinal();
        text2 = text2 + classification.getScientific();
        ((ControlText)this.levels.get(level)).setValue(text2);
      }
    }
  }
}
