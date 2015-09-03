package miscutil;

import miscutil.block.ModBlocks;
import miscutil.handler.TMGuiHandler;
import miscutil.item.ModItems;
import miscutil.tile_entity.TileEntityArcaneInfuser;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	ModItems.init();
    	ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
    	
	public void registerNetworkStuff(){
		NetworkRegistry.INSTANCE.registerGuiHandler(MiscUtils.instance, new TMGuiHandler());
	}

	public void registerTileEntities(){
		//GameRegistry.registerTileEntity(TileEntityBloodSteelChest.class, Strings.MODID);
		GameRegistry.registerTileEntity(TileEntityArcaneInfuser.class, "TileEntityArcaneInfuser");
	}
	
	public void registerRenderThings() {

	}

	public int addArmor(String armor) {
		return 0;
	}
    
}
