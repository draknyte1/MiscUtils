package binnie.craftgui.minecraft;

import binnie.core.machines.inventory.SlotValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class WindowInventory
  implements IInventory
{
  private Window window;
  private Map<Integer, ItemStack> inventory;
  private Map<Integer, SlotValidator> validators;
  private List<Integer> disabledAutoDispenses;
  
  public WindowInventory(Window window)
  {
    this.inventory = new HashMap();
    this.validators = new HashMap();
    this.disabledAutoDispenses = new ArrayList();
    this.window = window;
  }
  
  public int getSizeInventory()
  {
    if (this.inventory.size() == 0) {
      return 0;
    }
    int max = 0;
    for (Iterator localIterator = this.inventory.keySet().iterator(); localIterator.hasNext();)
    {
      int i = ((Integer)localIterator.next()).intValue();
      if (i > max) {
        max = i;
      }
    }
    return max + 1;
  }
  
  public ItemStack getStackInSlot(int var1)
  {
    if (this.inventory.containsKey(Integer.valueOf(var1))) {
      return (ItemStack)this.inventory.get(Integer.valueOf(var1));
    }
    return null;
  }
  
  public ItemStack decrStackSize(int index, int amount)
  {
    if (this.inventory.containsKey(Integer.valueOf(index)))
    {
      ItemStack item = (ItemStack)this.inventory.get(Integer.valueOf(index));
      ItemStack output = item.copy();
      int available = item.stackSize;
      if (amount > available) {
        amount = available;
      }
      ItemStack itemStack = item;
      itemStack.stackSize -= amount;
      output.stackSize = amount;
      if (item.stackSize == 0) {
        setInventorySlotContents(index, null);
      }
      return output;
    }
    return null;
  }
  
  public ItemStack getStackInSlotOnClosing(int var1)
  {
    return null;
  }
  
  public void setInventorySlotContents(int var1, ItemStack var2)
  {
    this.inventory.put(Integer.valueOf(var1), var2);
    markDirty();
  }
  
  public String getInventoryName()
  {
    return "window.inventory";
  }
  
  public int getInventoryStackLimit()
  {
    return 64;
  }
  
  public void markDirty()
  {
    this.window.onWindowInventoryChanged();
  }
  
  public boolean isUseableByPlayer(EntityPlayer var1)
  {
    return true;
  }
  
  public void openInventory() {}
  
  public void closeInventory() {}
  
  public boolean hasCustomInventoryName()
  {
    return false;
  }
  
  public boolean isItemValidForSlot(int i, ItemStack itemstack)
  {
    return (!this.validators.containsKey(Integer.valueOf(i))) || (((SlotValidator)this.validators.get(Integer.valueOf(i))).isValid(itemstack));
  }
  
  public void createSlot(int slot)
  {
    this.inventory.put(Integer.valueOf(slot), null);
  }
  
  public void setValidator(int slot, SlotValidator validator)
  {
    this.validators.put(Integer.valueOf(slot), validator);
  }
  
  public void disableAutoDispense(int i)
  {
    this.disabledAutoDispenses.add(Integer.valueOf(i));
  }
  
  public boolean dispenseOnClose(int i)
  {
    return !this.disabledAutoDispenses.contains(Integer.valueOf(i));
  }
  
  public SlotValidator getValidator(int i)
  {
    return (SlotValidator)this.validators.get(Integer.valueOf(i));
  }
}
