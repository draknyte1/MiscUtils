package binnie.craftgui.botany;

import binnie.Binnie;
import binnie.botany.api.EnumFlowerChromosome;
import binnie.botany.api.EnumFlowerStage;
import binnie.botany.api.IAlleleFlowerSpecies;
import binnie.botany.api.IFlower;
import binnie.botany.api.IFlowerGenome;
import binnie.botany.api.IFlowerRoot;
import binnie.botany.core.BotanyCore;
import binnie.core.genetics.ManagerGenetics;
import binnie.core.language.ManagerLanguage;
import binnie.craftgui.controls.ControlText;
import binnie.craftgui.controls.core.Control;
import binnie.craftgui.controls.scroll.ControlScrollableContent;
import binnie.craftgui.core.IWidget;
import binnie.craftgui.core.geometry.IArea;
import binnie.craftgui.core.geometry.IPoint;
import binnie.craftgui.core.geometry.TextJustification;
import binnie.craftgui.minecraft.control.ControlItemDisplay;
import binnie.craftgui.mod.database.DatabaseTab;
import binnie.craftgui.mod.database.PageSpecies;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IIndividual;
import net.minecraft.item.ItemStack;

public class PageSpeciesFlowerGenome
  extends PageSpecies
{
  public PageSpeciesFlowerGenome(IWidget parent, DatabaseTab tab)
  {
    super(parent, tab);
  }
  
  public void onValueChanged(IAlleleSpecies species)
  {
    deleteAllChildren();
    IAllele[] template = Binnie.Genetics.getFlowerRoot().getTemplate(species.getUID());
    if (template == null) {
      return;
    }
    IFlower tree = Binnie.Genetics.getFlowerRoot().templateAsIndividual(template);
    if (tree == null) {
      return;
    }
    IFlowerGenome genome = tree.getGenome();
    IAlleleFlowerSpecies treeSpecies = genome.getPrimary();
    int w = 144;
    int h = 176;
    new ControlText(this, new IArea(0.0F, 4.0F, 144.0F, 16.0F), "Genome", TextJustification.MiddleCenter);
    ControlScrollableContent scrollable = new ControlScrollableContent(this, 4.0F, 20.0F, 136.0F, 152.0F, 12.0F);
    Control contents = new Control(scrollable, 0.0F, 0.0F, 124.0F, 152.0F);
    int tw = 124;
    int w2 = 55;
    int w3 = 74;
    int y = 0;
    int th = 14;
    int th2 = 18;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "Temp. : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), treeSpecies.getTemperature().getName(), TextJustification.MiddleLeft);
    y += 14;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "Moist. : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), Binnie.Language.localise(treeSpecies.getMoisture()), TextJustification.MiddleLeft);
    y += 14;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "pH. : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), Binnie.Language.localise(treeSpecies.getPH()), TextJustification.MiddleLeft);
    y += 14;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "Fertility : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), genome.getFertility() + "x", TextJustification.MiddleLeft);
    y += 14;
    float lifespan = genome.getLifespan() * 68.269997F / genome.getAgeChance() / 24000.0F;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "Lifespan : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), "" + String.format("%.2f", new Object[] { Float.valueOf(lifespan) }) + " days", TextJustification.MiddleLeft);
    y += 14;
    new ControlText(contents, new IArea(0.0F, y, 55.0F, 14.0F), "Nectar : ", TextJustification.MiddleRight);
    new ControlText(contents, new IArea(55.0F, y, 74.0F, 14.0F), genome.getActiveAllele(EnumFlowerChromosome.SAPPINESS).getName(), TextJustification.MiddleLeft);
    y += 14;
    int x = 55;
    int tot = 0;
    for (IIndividual vid : BotanyCore.getFlowerRoot().getIndividualTemplates()) {
      if (vid.getGenome().getPrimary() == treeSpecies)
      {
        if ((tot > 0) && (tot % 3 == 0))
        {
          x -= 54;
          y += 18;
        }
        ItemStack stack = BotanyCore.getFlowerRoot().getMemberStack(vid, EnumFlowerStage.FLOWER.ordinal());
        ControlItemDisplay display = new ControlItemDisplay(contents, x, y);
        display.setItemStack(stack);
        tot++;
        x += 18;
      }
    }
    int numOfLines = 1 + (tot - 1) / 3;
    new ControlText(contents, new IArea(0.0F, y - (numOfLines - 1) * 18, 55.0F, 4 + 18 * numOfLines), "Varieties : ", TextJustification.MiddleRight);
    y += 14;
    contents.setSize(new IPoint(contents.size().x(), y));
    scrollable.setScrollableContent(contents);
  }
  
  public static String tolerated(boolean t)
  {
    if (t) {
      return "Tolerated";
    }
    return "Not Tolerated";
  }
}
