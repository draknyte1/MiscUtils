package binnie.extrabees.apiary;

import binnie.core.BinnieCore;
import binnie.core.machines.Machine;
import binnie.core.machines.TileEntityMachine;
import binnie.core.proxy.BinnieProxy;
import binnie.extrabees.apiary.machine.AlvearyMachine.AlvearyPackage;
import com.mojang.authlib.GameProfile;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeListener;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.genetics.IIndividual;
import forestry.api.multiblock.IAlvearyComponent;
import forestry.api.multiblock.IMultiblockController;
import forestry.api.multiblock.IMultiblockLogicAlveary;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TileExtraBeeAlveary
  extends TileEntityMachine
  implements IAlvearyComponent, IBeeModifier, IBeeListener
{
  boolean init;
  private boolean isMaster;
  protected int masterX;
  protected int masterZ;
  protected int masterY;
  List<TileEntity> tiles;
  
  public void updateEntity()
  {
    super.updateEntity();
    if (!BinnieCore.proxy.isSimulating(this.worldObj)) {
      return;
    }
    if (this.worldObj.getWorldTime() % 200L == 0L)
    {
      if ((!isIntegratedIntoStructure()) || (isMaster())) {
        validateStructure();
      }
      this.init = true;
    }
  }
  
  public void readFromNBT(NBTTagCompound nbttagcompound)
  {
    super.readFromNBT(nbttagcompound);
    this.isMaster = nbttagcompound.getBoolean("IsMaster");
    this.masterX = nbttagcompound.getInteger("MasterX");
    this.masterY = nbttagcompound.getInteger("MasterY");
    this.masterZ = nbttagcompound.getInteger("MasterZ");
    if (this.isMaster) {
      makeMaster();
    }
    this.init = false;
  }
  
  public void writeToNBT(NBTTagCompound nbttagcompound)
  {
    super.writeToNBT(nbttagcompound);
    nbttagcompound.setBoolean("IsMaster", this.isMaster);
    nbttagcompound.setInteger("MasterX", this.masterX);
    nbttagcompound.setInteger("MasterY", this.masterY);
    nbttagcompound.setInteger("MasterZ", this.masterZ);
  }
  
  AlvearyMachine.AlvearyPackage getAlvearyPackage()
  {
    return (AlvearyMachine.AlvearyPackage)getMachine().getPackage();
  }
  
  public TileExtraBeeAlveary()
  {
    this.init = false;
    this.masterY = -99;
    this.tiles = new ArrayList();
  }
  
  public TileExtraBeeAlveary(AlvearyMachine.AlvearyPackage alvearyPackage)
  {
    super(alvearyPackage);
    this.init = false;
    this.masterY = -99;
    this.tiles = new ArrayList();
  }
  
  public String getTypeUID()
  {
    return null;
  }
  
  public void makeMaster() {}
  
  public void onStructureReset()
  {
    setCentralTE(null);
    this.isMaster = false;
    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
  }
  
  public void validateStructure() {}
  
  private boolean isSameTile(TileEntity tile)
  {
    return (tile.xCoord == this.xCoord) && (tile.yCoord == this.yCoord) && (tile.zCoord == this.zCoord);
  }
  
  public void setCentralTE(TileEntity tile)
  {
    if ((tile == null) || (tile == this) || (isSameTile(tile)))
    {
      boolean b = false;
      this.masterZ = 0;
      this.masterX = 0;
      this.masterY = -99;
      
      return;
    }
    this.isMaster = false;
    this.masterX = tile.xCoord;
    this.masterY = tile.yCoord;
    this.masterZ = tile.zCoord;
    markDirty();
  }
  
  public boolean isMaster()
  {
    return this.isMaster;
  }
  
  protected boolean hasMaster()
  {
    return this.masterY >= 0;
  }
  
  public boolean isIntegratedIntoStructure()
  {
    return (this.isMaster) || (this.masterY >= 0);
  }
  
  public void registerBeeModifier(IBeeModifier modifier) {}
  
  public void removeBeeModifier(IBeeModifier modifier) {}
  
  public void addTemperatureChange(float change, float boundaryDown, float boundaryUp) {}
  
  public void addHumidityChange(float change, float boundaryDown, float boundaryUp) {}
  
  public boolean hasFunction()
  {
    return true;
  }
  
  public IBeeModifier getBeeModifier()
  {
    return (IBeeModifier)getMachine().getInterface(IBeeModifier.class);
  }
  
  public IBeeListener getBeeListener()
  {
    return (IBeeListener)getMachine().getInterface(IBeeListener.class);
  }
  
  public float getTerritoryModifier(IBeeGenome genome, float currentModifier)
  {
    return getBeeModifier() == null ? 1.0F : getBeeModifier().getTerritoryModifier(genome, currentModifier);
  }
  
  public float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier)
  {
    return getBeeModifier() == null ? 1.0F : getBeeModifier().getMutationModifier(genome, mate, currentModifier);
  }
  
  public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier)
  {
    return getBeeModifier() == null ? 1.0F : getBeeModifier().getLifespanModifier(genome, mate, currentModifier);
  }
  
  public float getProductionModifier(IBeeGenome genome, float currentModifier)
  {
    return getBeeModifier() == null ? 1.0F : getBeeModifier().getProductionModifier(genome, currentModifier);
  }
  
  public float getFloweringModifier(IBeeGenome genome, float currentModifier)
  {
    return getBeeModifier() == null ? 1.0F : getBeeModifier().getFloweringModifier(genome, currentModifier);
  }
  
  public boolean isSealed()
  {
    return (getBeeModifier() != null) && (getBeeModifier().isSealed());
  }
  
  public boolean isSelfLighted()
  {
    return (getBeeModifier() != null) && (getBeeModifier().isSelfLighted());
  }
  
  public boolean isSunlightSimulated()
  {
    return (getBeeModifier() != null) && (getBeeModifier().isSunlightSimulated());
  }
  
  public boolean isHellish()
  {
    return (getBeeModifier() != null) && (getBeeModifier().isHellish());
  }
  
  public void registerBeeListener(IBeeListener event) {}
  
  public void removeBeeListener(IBeeListener event) {}
  
  public void wearOutEquipment(int amount)
  {
    if (getBeeListener() != null) {
      getBeeListener().wearOutEquipment(amount);
    }
  }
  
  public void onQueenDeath() {}
  
  public boolean onPollenRetrieved(IIndividual pollen)
  {
    return false;
  }
  
  public boolean onPollenRetrieved(IBee queen, IIndividual pollen, boolean isHandled)
  {
    return false;
  }
  
  public boolean onEggLaid(IBee queen)
  {
    return false;
  }
  
  public float getGeneticDecay(IBeeGenome genome, float currentModifier)
  {
    return 1.0F;
  }
  
  public ISidedInventory getStructureInventory()
  {
    return (ISidedInventory)getMachine().getInterface(ISidedInventory.class);
  }
  
  public ChunkCoordinates getCoordinates()
  {
    return null;
  }
  
  public GameProfile getOwner()
  {
    return null;
  }
  
  public IMultiblockLogicAlveary getMultiblockLogic()
  {
    return null;
  }
  
  public void onMachineAssembled(IMultiblockController multiblockController, ChunkCoordinates minCoord, ChunkCoordinates maxCoord) {}
  
  public void onMachineBroken() {}
  
  public IBeeHousing getBeeHousing()
  {
    return null;
  }
}
