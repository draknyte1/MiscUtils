package miscutil.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import miscutil.gui.GuiArcaneInfuser;
import miscutil.inventory.ContainerArcaneInfuser;
import miscutil.tile_entity.TileEntityArcaneInfuser;

import cpw.mods.fml.common.network.IGuiHandler;

public class TMGuiHandler implements IGuiHandler {

	public TMGuiHandler (){
		
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			TileEntityArcaneInfuser tileEntityArcaneInfuser = (TileEntityArcaneInfuser) world.getTileEntity(x, y, z);
			return new ContainerArcaneInfuser(player.inventory, tileEntityArcaneInfuser);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == 0){
			TileEntityArcaneInfuser tileEntityTestContainer = (TileEntityArcaneInfuser) world.getTileEntity(x, y, z);
			return new GuiArcaneInfuser(player.inventory, tileEntityTestContainer);
		}
		return null;
	}

}
