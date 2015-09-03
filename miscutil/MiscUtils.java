package miscutil;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import miscutil.creativetabs.TMCreativeTabs;
import miscutil.entity.TMEntity;
import miscutil.lib.Strings;
import miscutil.main.CraftingManager;
import miscutil.main.TMHooks;
import miscutil.world.TMWorld;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid=Strings.MODID, name="Misc. Utils", version=Strings.VERSION)
public class MiscUtils
implements ActionListener
{ 
	
	//Vars

	private static final boolean DEBUG = false;	

	@Mod.Instance(Strings.MODID)
	public static MiscUtils instance;
	
	@SidedProxy(clientSide="miscutil.proxy.ClientProxy", serverSide="miscutil.proxy.ServerProxy")
	public static CommonProxy proxy;

	//Pre-Init
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
		TMCreativeTabs.initialiseTabs();
		proxy.preInit(event);
		proxy.registerTileEntities();
		proxy.registerRenderThings();
		TMEntity.mainRegistry();
		CraftingManager.mainRegistry();
		TMWorld.mainRegistry();
		TMHooks.mainRegistry();
		


	}

	//Init
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		proxy.registerNetworkStuff();
	}

	//Post-Init
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    proxy.postInit(event);
		if (DEBUG){
		//Loader.
		}
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{

		

	}

	@Mod.EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{


	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
