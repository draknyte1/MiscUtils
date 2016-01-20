package binnie.craftgui.genetics.machine;

import binnie.core.AbstractMod;
import binnie.craftgui.core.geometry.Position;
import binnie.craftgui.minecraft.GUIIcon;
import binnie.craftgui.minecraft.MinecraftGUI.PanelType;
import binnie.craftgui.minecraft.Window;
import binnie.craftgui.minecraft.control.ControlEnergyBar;
import binnie.craftgui.minecraft.control.ControlErrorState;
import binnie.craftgui.minecraft.control.ControlIconDisplay;
import binnie.craftgui.minecraft.control.ControlPlayerInventory;
import binnie.craftgui.minecraft.control.ControlProgress;
import binnie.craftgui.minecraft.control.ControlSlot;
import binnie.craftgui.minecraft.control.ControlSlotArray;
import binnie.craftgui.minecraft.control.ControlSlotCharge;
import binnie.craftgui.resource.Texture;
import binnie.craftgui.resource.minecraft.StandardTexture;
import binnie.craftgui.window.Panel;
import binnie.extrabees.core.ExtraBeeTexture;
import binnie.genetics.Genetics;
import binnie.genetics.core.GeneticsTexture;
import binnie.genetics.machine.Analyser;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class WindowAnalyser
  extends WindowMachine
{
  public static Window create(EntityPlayer player, IInventory inventory, Side side)
  {
    return new WindowAnalyser(player, inventory, side);
  }
  
  public WindowAnalyser(EntityPlayer player, IInventory inventory, Side side)
  {
    super(220, 210, player, inventory, side);
  }
  
  public void initialiseClient()
  {
    ProgressBase = new StandardTexture(0, 51, 66, 40, GeneticsTexture.GUIProcess.getTexture());
    Progress = new StandardTexture(66, 51, 66, 40, GeneticsTexture.GUIProcess.getTexture());
    int x = 16;
    int y = 32;
    new ControlSlotArray(this, x, 32, 2, 3).create(Analyser.slotReserve);
    x += 28;
    new ControlSlot(this, x, 94.0F).assign(13);
    new ControlSlotCharge(this, x + 20, 94, 13).setColour(10040319);
    new ControlEnergyBar(this, x + 24 + 16, 95, 60, 16, Position.Left);
    new ControlErrorState(this, x + 24 + 16 + 60 + 16, 95.0F);
    x -= 28;
    new ControlIconDisplay(this, x + 36 + 2, 50.0F, GUIIcon.ArrowRight.getIcon());
    x += 56;
    new Panel(this, x, 32.0F, 76.0F, 50.0F, MinecraftGUI.PanelType.Tinted);
    new ControlProgress(this, x + 5, 37, ProgressBase, Progress, Position.Left);
    new ControlSlot(this, x + 38 - 9, 48.0F).assign(6);
    new ControlIconDisplay(this, x + 76 + 2, 50.0F, GUIIcon.ArrowRight.getIcon());
    x += 96;
    new ControlSlotArray(this, x, 32, 2, 3).create(Analyser.slotFinished);
    x += 52;
    setTitle("Analyser");
    new ControlPlayerInventory(this);
  }
  
  public String getTitle()
  {
    return "Analyser";
  }
  
  protected AbstractMod getMod()
  {
    return Genetics.instance;
  }
  
  protected String getName()
  {
    return "Analyser";
  }
  
  static Texture ProgressBase = new StandardTexture(0, 218, 142, 17, ExtraBeeTexture.GUIProgress.getTexture());
  static Texture Progress = new StandardTexture(0, 201, 142, 17, ExtraBeeTexture.GUIProgress.getTexture());
}
