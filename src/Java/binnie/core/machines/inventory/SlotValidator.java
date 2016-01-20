package binnie.core.machines.inventory;

import binnie.core.resource.BinnieIcon;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public abstract class SlotValidator
  extends Validator<ItemStack>
{
  public static ValidatorIcon IconBee;
  public static ValidatorIcon IconFrame;
  public static ValidatorIcon IconCircuit;
  public static ValidatorIcon IconBlock;
  private ValidatorIcon icon;
  
  public SlotValidator(ValidatorIcon icon)
  {
    this.icon = icon;
  }
  
  public IIcon getIcon(boolean input)
  {
    return this.icon == null ? null : this.icon.getIcon(input).getIcon();
  }
  
  public static class Item
    extends SlotValidator
  {
    private ItemStack target;
    
    public Item(ItemStack target, ValidatorIcon icon)
    {
      super();
      this.target = target;
    }
    
    public boolean isValid(ItemStack itemStack)
    {
      return itemStack.isItemEqual(this.target);
    }
    
    public String getTooltip()
    {
      return this.target.getDisplayName();
    }
  }
  
  public static class Individual
    extends SlotValidator
  {
    public Individual()
    {
      super();
    }
    
    public boolean isValid(ItemStack itemStack)
    {
      return AlleleManager.alleleRegistry.getIndividual(itemStack) != null;
    }
    
    public String getTooltip()
    {
      return "Breedable Individual";
    }
  }
}
