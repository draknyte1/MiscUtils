package miscutil;

import miscutil.block.ModBlocks;
import miscutil.item.ModItems;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
		//NetworkRegistry.INSTANCE.registerGuiHandler(MiscUtils.instance, new BloodSteelFurnaceGuiHandler());
		
	}

	public void registerTileEntities(){
		//GameRegistry.registerTileEntity(TileEntityBloodSteelChest.class, "tileEntityBloodSteelChest");
		//GameRegistry.registerTileEntity(TileEntityBloodSteelFurnace.class, "tileEntityBloodSteelFurnace");
		//GameRegistry.registerTileEntity(TileEntityBloodSteelChest.class, Strings.MODID);
		//GameRegistry.registerTileEntity(TileEntityArcaneInfuser.class, "TileEntityArcaneInfuser");
	}
	
	public void registerRenderThings() {

	}

	public int addArmor(String armor) {
		return 0;
	}
    
}
