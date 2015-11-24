package miscutil;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.UnknownFormatConversionException;

import miscutil.creativetabs.TMCreativeTabs;
import miscutil.lib.Strings;
import miscutil.main.CraftingManager;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid=Strings.MODID, name="Misc. Utils", version=Strings.VERSION)
public class MiscUtils
implements ActionListener
{ 
	
	//Vars
	

	@Mod.Instance(Strings.MODID)
	public static MiscUtils instance;
	
	@SidedProxy(clientSide="miscutil.proxy.ClientProxy", serverSide="miscutil.proxy.ServerProxy")
	public static CommonProxy proxy;
	

	//Pre-Init
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		if (Strings.DEBUG){
			FMLLog.info("Doing some house cleaning.");}
		TMCreativeTabs.initialiseTabs();
		//TMEntity.mainRegistry();
		CraftingManager.mainRegistry();
		//TMWorld.mainRegistry();
		//TMHooks.mainRegistry();
		proxy.registerTileEntities();
		proxy.registerRenderThings();
		proxy.preInit(event);
		


	}

	//Init
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		if (Strings.DEBUG){
			FMLLog.info("Double checking floating point precision.");}
			try {
				Thread.sleep(100);
				Benchmark GammeRayBurst = new Benchmark();
				GammeRayBurst.math();
			} catch (InterruptedException | ParseException | NumberFormatException | UnknownFormatConversionException e) {
				if (Strings.DEBUG){
					e.printStackTrace();
					FMLLog.info("Math went wrong somewhere.");}
				;
			}
		proxy.init(event);
		if (Strings.DEBUG){
			Benchmark GammeRayBurst = new Benchmark();
			String Insight = GammeRayBurst.superhash("This is Absolution");
			FMLLog.info(Insight);
			FMLLog.info("Math is ok.");}
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		proxy.registerNetworkStuff();
	}

	//Post-Init
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Strings.DEBUG){
			FMLLog.info("Tidying things up.");}
	    proxy.postInit(event);
	}
	
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{

		//while (Strings.DEBUG){
		//Thread.setDefaultUncaughtExceptionHandler(null);
		//}

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
