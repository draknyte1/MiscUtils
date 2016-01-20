package binnie.core.machines.inventory;

import binnie.core.machines.IMachine;
import binnie.core.machines.MachineComponent;
import binnie.core.machines.transfer.TransferRequest;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ComponentInventoryTransfer
  extends MachineComponent
{
  private List<Transfer> transfers = new ArrayList();
  
  public ComponentInventoryTransfer(IMachine machine)
  {
    super(machine);
  }
  
  public void addRestock(int[] buffer, int destination, int limit)
  {
    this.transfers.add(new Restock(getMachine(), buffer, destination, limit, null));
  }
  
  public void addRestock(int[] buffer, int destination)
  {
    this.transfers.add(new Restock(getMachine(), buffer, destination, null));
  }
  
  public void addStorage(int source, int[] destination)
  {
    this.transfers.add(new Storage(getMachine(), source, destination, null));
  }
  
  public void performTransfer(int source, int[] destination)
  {
    new Storage(getMachine(), source, destination, null).transfer((IInventory)getMachine().getInterface(IInventoryMachine.class));
  }
  
  public void onUpdate()
  {
    for (Transfer transfer : this.transfers) {
      transfer.transfer((IInventory)getMachine().getInterface(IInventoryMachine.class));
    }
  }
  
  public void addStorage(int source, int[] destination, Condition condition)
  {
    this.transfers.add(new Storage(getMachine(), source, destination, null).setCondition(condition));
  }
  
  public static abstract class Condition
  {
    public ComponentInventoryTransfer.Transfer transfer;
    
    public abstract boolean fufilled(ItemStack paramItemStack);
  }
  
  private class Storage
    extends ComponentInventoryTransfer.Transfer
  {
    int source;
    int[] destination;
    
    private Storage(IMachine machine, int source, int[] destination)
    {
      super(machine, null);
      this.source = source;
      this.destination = destination;
    }
    
    protected void doTransfer(IInventory inv)
    {
      if (inv.getStackInSlot(this.source) != null) {
        inv.setInventorySlotContents(this.source, new TransferRequest(inv.getStackInSlot(this.source), inv).setTargetSlots(this.destination).ignoreValidation().transfer(true));
      }
    }
    
    protected boolean fufilled(IInventory inv)
    {
      ItemStack stack = inv.getStackInSlot(this.source);
      return (stack != null) && (this.condition.fufilled(stack));
    }
  }
  
  private class Restock
    extends ComponentInventoryTransfer.Transfer
  {
    int[] buffer;
    int destination;
    int limit;
    
    private Restock(IMachine machine, int[] buffer, int destination, int limit)
    {
      super(machine, null);
      this.buffer = buffer;
      this.destination = destination;
      this.limit = limit;
    }
    
    private Restock(IMachine machine, int[] buffer, int destination)
    {
      this(machine, buffer, destination, 64);
    }
    
    protected void doTransfer(IInventory inv)
    {
      if (inv.getStackInSlot(this.destination) == null) {
        for (int i : this.buffer)
        {
          ItemStack newStack;
          if ((inv.getStackInSlot(i) != null) && ((newStack = inv.decrStackSize(i, this.limit)) != null))
          {
            inv.setInventorySlotContents(this.destination, newStack);
            return;
          }
        }
      }
    }
  }
  
  public abstract class Transfer
  {
    protected ComponentInventoryTransfer.Condition condition;
    protected IMachine machine;
    
    private Transfer(IMachine machine)
    {
      this.machine = machine;
    }
    
    public final void transfer(IInventory inv)
    {
      if ((this.condition == null) || (fufilled(inv))) {
        doTransfer(inv);
      }
    }
    
    protected boolean fufilled(IInventory inv)
    {
      return true;
    }
    
    protected void doTransfer(IInventory inv) {}
    
    public final Transfer setCondition(ComponentInventoryTransfer.Condition condition)
    {
      this.condition = condition;
      condition.transfer = this;
      return this;
    }
    
    public final IMachine getMachine()
    {
      return this.machine;
    }
  }
}
