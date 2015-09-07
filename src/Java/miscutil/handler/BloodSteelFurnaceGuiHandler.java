package miscutil.handler;

import miscutil.gui.GuiArcaneInfuser;
import miscutil.gui.GuiBloodSteelChest;
import miscutil.gui.GuiBloodSteelFurnace;
import miscutil.inventory.ContainerArcaneInfuser;
import miscutil.inventory.ContainerBloodSteelChest;
import miscutil.inventory.ContainerBloodSteelFurnace;
import miscutil.tile_entity.TileEntityArcaneInfuser;
import miscutil.tile_entity.TileEntityBloodSteelChest;
import miscutil.tile_entity.TileEntityBloodSteelFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class BloodSteelFurnaceGuiHandler implements IGuiHandler {

	public BloodSteelFurnaceGuiHandler (){
		
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			TileEntityBloodSteelFurnace tileEntityFurnace = (TileEntityBloodSteelFurnace) world.getTileEntity(x, y, z);
			return new ContainerBloodSteelFurnace(player.inventory, tileEntityFurnace);
		}
		else if(ID == 1){
			TileEntityArcaneInfuser tileEntityInfuser = (TileEntityArcaneInfuser) world.getTileEntity(x, y, z);
			return new ContainerArcaneInfuser(player.inventory, tileEntityInfuser);
		}
		else if(ID == 2){
			TileEntityBloodSteelChest tileEntityBSChest = (TileEntityBloodSteelChest) world.getTileEntity(x, y, z);
			return new ContainerBloodSteelChest(player.inventory, tileEntityBSChest);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			TileEntityBloodSteelFurnace tileEntityTestContainer = (TileEntityBloodSteelFurnace) world.getTileEntity(x, y, z);
			return new GuiBloodSteelFurnace(player.inventory, tileEntityTestContainer);
		}
		else if(ID == 1){
			TileEntityArcaneInfuser tileEntityTestContainer2 = (TileEntityArcaneInfuser) world.getTileEntity(x, y, z);
			return new GuiArcaneInfuser(player.inventory, tileEntityTestContainer2);
		}
		else if(ID == 2){
			TileEntityBloodSteelChest tileEntityTestContainer3 = (TileEntityBloodSteelChest) world.getTileEntity(x, y, z);
			return new GuiBloodSteelChest(player.inventory, tileEntityTestContainer3);
		}
		return null;
	}

}
