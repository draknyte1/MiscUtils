package binnie.core.machines.power;

import binnie.core.machines.IMachine;
import binnie.core.machines.MachineComponent;
import binnie.core.machines.MachineUtil;
import binnie.core.machines.network.INetwork.TilePacketSync;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ComponentProcessIndefinate
  extends MachineComponent
  implements IProcess, INetwork.TilePacketSync
{
  private float energyPerTick;
  private boolean inProgress;
  private float actionPauseProcess;
  private float actionCancelTask;
  int clientEnergyPerSecond;
  int clientInProgress;
  
  public void syncFromNBT(NBTTagCompound nbt)
  {
    this.inProgress = nbt.getBoolean("progress");
  }
  
  public void syncToNBT(NBTTagCompound nbt)
  {
    nbt.setBoolean("progress", this.inProgress);
  }
  
  public ComponentProcessIndefinate(IMachine machine, float energyPerTick)
  {
    super(machine);
    this.energyPerTick = 0.1F;
    this.actionPauseProcess = 0.0F;
    this.actionCancelTask = 0.0F;
    this.clientEnergyPerSecond = 0;
    this.energyPerTick = energyPerTick;
  }
  
  protected final IPoweredMachine getPower()
  {
    return (IPoweredMachine)getMachine().getInterface(IPoweredMachine.class);
  }
  
  public float getEnergyPerTick()
  {
    return this.energyPerTick;
  }
  
  public void onUpdate()
  {
    float energyAvailable = (float)getPower().getInterface().useEnergy(PowerSystem.RF, getEnergyPerTick(), false);
    if (canWork() == null)
    {
      if ((!isInProgress()) && (canProgress() == null))
      {
        onStartTask();
      }
      else if (canProgress() == null)
      {
        progressTick();
        onTickTask();
      }
    }
    else if (isInProgress()) {
      onCancelTask();
    }
    if (this.actionPauseProcess > 0.0F) {
      this.actionPauseProcess -= 1.0F;
    }
    if (this.actionCancelTask > 0.0F) {
      this.actionCancelTask -= 1.0F;
    }
    super.onUpdate();
    if (this.inProgress != inProgress())
    {
      this.inProgress = inProgress();
      getUtil().refreshBlock();
    }
  }
  
  protected void progressTick()
  {
    getPower().getInterface().useEnergy(PowerSystem.RF, getEnergyPerTick(), true);
  }
  
  public ErrorState canWork()
  {
    return this.actionCancelTask == 0.0F ? null : new ErrorState("Task Cancelled", "Cancelled by Buildcraft Gate");
  }
  
  public ErrorState canProgress()
  {
    if (this.actionPauseProcess != 0.0F) {
      return new ErrorState("Process Paused", "Paused by Buildcraft Gate");
    }
    return getPower().getInterface().getEnergy(PowerSystem.RF) < getEnergyPerTick() ? new ErrorState.InsufficientPower() : null;
  }
  
  public final boolean isInProgress()
  {
    return this.inProgress;
  }
  
  protected abstract boolean inProgress();
  
  protected void onCancelTask() {}
  
  protected void onStartTask() {}
  
  protected void onTickTask() {}
  
  public String getTooltip()
  {
    return "Processing";
  }
  
  public final ProcessInfo getInfo()
  {
    return new ProcessInfo(this);
  }
}
