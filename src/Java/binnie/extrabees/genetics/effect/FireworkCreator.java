package binnie.extrabees.genetics.effect;

import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class FireworkCreator
{
  public static enum Shape
  {
    Ball,  LargeBall,  Star,  Creeper,  Burst;
    
    private Shape() {}
  }
  
  public static class Firework
  {
    boolean flicker;
    boolean trail;
    ArrayList<Integer> colors;
    byte shape;
    
    public Firework()
    {
      this.flicker = false;
      this.trail = false;
      this.colors = new ArrayList();
      this.shape = 0;
    }
    
    public void setFlicker()
    {
      this.flicker = true;
    }
    
    public void setTrail()
    {
      this.trail = true;
    }
    
    public void setShape(FireworkCreator.Shape shape)
    {
      this.shape = ((byte)shape.ordinal());
    }
    
    public void addColor(int color)
    {
      this.colors.add(Integer.valueOf(color));
    }
    
    NBTTagCompound getNBT()
    {
      NBTTagCompound nbt = new NBTTagCompound();
      if (this.flicker) {
        nbt.setBoolean("Flicker", true);
      }
      if (this.trail) {
        nbt.setBoolean("Trail", true);
      }
      if (this.colors.size() == 0) {
        addColor(16777215);
      }
      int[] array = new int[this.colors.size()];
      for (int i = 0; i < this.colors.size(); i++) {
        array[i] = ((Integer)this.colors.get(i)).intValue();
      }
      nbt.setIntArray("Colors", array);
      nbt.setByte("Type", this.shape);
      return nbt;
    }
    
    public ItemStack getFirework()
    {
      NBTTagCompound var15 = new NBTTagCompound();
      NBTTagCompound var16 = new NBTTagCompound();
      NBTTagList var17 = new NBTTagList();
      var17.appendTag(getNBT());
      var16.setTag("Explosions", var17);
      var16.setByte("Flight", (byte)0);
      var15.setTag("Fireworks", var16);
      ItemStack item = new ItemStack(Items.fireworks);
      item.setTagCompound(var15);
      return item;
    }
  }
}
