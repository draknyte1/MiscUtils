package binnie.core.machines.inventory;

import binnie.core.util.IValidator;
import forestry.api.core.INBTTagable;
import java.util.Collection;
import java.util.EnumSet;
import net.minecraftforge.common.util.ForgeDirection;

abstract class BaseSlot<T>
  implements INBTTagable, IValidator<T>
{
  private SidedAccess access;
  Validator<T> validator;
  private boolean readOnly;
  private int index;
  protected String unlocName;
  
  public BaseSlot(int index, String unlocName)
  {
    this.access = new SidedAccess();
    this.validator = null;
    this.readOnly = false;
    this.unlocName = "";
    setIndex(index);
    setUnlocalisedName(unlocName);
  }
  
  public void setReadOnly()
  {
    this.readOnly = true;
    forbidInsertion();
  }
  
  public boolean isValid(T item)
  {
    return (item == null) || (this.validator == null) || (this.validator.isValid(item));
  }
  
  public abstract T getContent();
  
  public abstract void setContent(T paramT);
  
  public void setValidator(Validator<T> val)
  {
    this.validator = val;
  }
  
  public boolean isEmpty()
  {
    return getContent() == null;
  }
  
  public boolean isReadOnly()
  {
    return this.readOnly;
  }
  
  public int getIndex()
  {
    return this.index;
  }
  
  private void setIndex(int index)
  {
    this.index = index;
  }
  
  public boolean canInsert()
  {
    return !this.access.getInsertionSides().isEmpty();
  }
  
  public boolean canExtract()
  {
    return !this.access.getExtractionSides().isEmpty();
  }
  
  public void forbidInteraction()
  {
    forbidInsertion();
    forbidExtraction();
  }
  
  public void setInputSides(EnumSet<ForgeDirection> sides)
  {
    for (ForgeDirection side : EnumSet.complementOf(sides)) {
      if (side != ForgeDirection.UNKNOWN) {
        this.access.setInsert(side, false);
      }
    }
  }
  
  public void setOutputSides(EnumSet<ForgeDirection> sides)
  {
    for (ForgeDirection side : EnumSet.complementOf(sides)) {
      if (side != ForgeDirection.UNKNOWN) {
        this.access.setExtract(side, false);
      }
    }
  }
  
  public void forbidExtraction()
  {
    this.access.setExtract(false);
    this.access.forbidExtractChange();
  }
  
  public void forbidInsertion()
  {
    this.access.setInsert(false);
    this.access.forbidInsertChange();
  }
  
  public boolean canInsert(ForgeDirection dir)
  {
    return this.access.canInsert(dir);
  }
  
  public boolean canExtract(ForgeDirection dir)
  {
    return this.access.canExtract(dir);
  }
  
  public Collection<ForgeDirection> getInputSides()
  {
    return this.access.getInsertionSides();
  }
  
  public Collection<ForgeDirection> getOutputSides()
  {
    return this.access.getExtractionSides();
  }
  
  public void setUnlocalisedName(String name)
  {
    this.unlocName = name;
  }
  
  public abstract String getName();
  
  public Validator<T> getValidator()
  {
    return this.validator;
  }
}
