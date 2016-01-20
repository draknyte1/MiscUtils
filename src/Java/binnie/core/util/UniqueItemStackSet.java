package binnie.core.util;

import java.util.List;
import net.minecraft.item.ItemStack;

public class UniqueItemStackSet
  extends ItemStackSet
{
  public boolean add(ItemStack e)
  {
    return (e != null) && (getExisting(e) == null) && (this.itemStacks.add(e.copy()));
  }
  
  public boolean remove(Object o)
  {
    if (contains(o))
    {
      ItemStack r = (ItemStack)o;
      ItemStack existing = getExisting(r);
      this.itemStacks.remove(existing);
    }
    return false;
  }
}
