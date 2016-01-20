package binnie.core.machines;

import binnie.Binnie;
import binnie.core.AbstractMod;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.creativetab.CreativeTabs;

public class MachineGroup
{
  private AbstractMod mod;
  private String blockName;
  private String uid;
  private Map<String, MachinePackage> packages;
  private Map<Integer, MachinePackage> packagesID;
  private BlockMachine block;
  public boolean customRenderer;
  private boolean renderedTileEntity;
  
  public MachineGroup(AbstractMod mod, String uid, String blockName, IMachineType[] types)
  {
    this.packages = new LinkedHashMap();
    this.packagesID = new LinkedHashMap();
    this.customRenderer = true;
    this.renderedTileEntity = true;
    this.mod = mod;
    this.uid = uid;
    this.blockName = blockName;
    for (IMachineType type : types) {
      if ((type.getPackageClass() != null) && 
        (type.isActive())) {
        try
        {
          MachinePackage pack = (MachinePackage)type.getPackageClass().newInstance();
          pack.assignMetadata(type.ordinal());
          pack.setActive(type.isActive());
          addPackage(pack);
        }
        catch (Exception e)
        {
          throw new RuntimeException("Failed to create machine package " + type.toString(), e);
        }
      }
    }
    Binnie.Machine.registerMachineGroup(this);
    this.block = new BlockMachine(this, blockName);
    if (this.block != null)
    {
      GameRegistry.registerBlock(this.block, ItemMachine.class, blockName);
      for (??? = getPackages().iterator(); ((Iterator)???).hasNext();)
      {
        MachinePackage pack2 = (MachinePackage)((Iterator)???).next();
        pack2.register();
      }
    }
  }
  
  private void addPackage(MachinePackage pack)
  {
    this.packages.put(pack.getUID(), pack);
    this.packagesID.put(pack.getMetadata(), pack);
    pack.setGroup(this);
  }
  
  public Collection<MachinePackage> getPackages()
  {
    return this.packages.values();
  }
  
  public BlockMachine getBlock()
  {
    return this.block;
  }
  
  public MachinePackage getPackage(int metadata)
  {
    return (MachinePackage)this.packagesID.get(Integer.valueOf(metadata));
  }
  
  public MachinePackage getPackage(String name)
  {
    return (MachinePackage)this.packages.get(name);
  }
  
  public String getUID()
  {
    return this.mod.getModID() + "." + this.uid;
  }
  
  public String getShortUID()
  {
    return this.uid;
  }
  
  boolean isTileEntityRenderered()
  {
    return this.renderedTileEntity;
  }
  
  public void renderAsBlock()
  {
    this.renderedTileEntity = false;
  }
  
  public void setCreativeTab(CreativeTabs tab)
  {
    this.block.setCreativeTab(tab);
  }
  
  public AbstractMod getMod()
  {
    return this.mod;
  }
}
