package miscutil.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class TMCreativeTabs {
	
	public static CreativeTabs tabBlock;
	public static CreativeTabs tabMisc;
	public static CreativeTabs tabCombat;
	public static CreativeTabs tabTools;
	
	public static void initialiseTabs(){
		tabBlock = new MiscUtilCreativeTabBlock("MiscUtilBlockTab");
		tabMisc = new MiscUtilCreativeTabMisc("MiscUtilMiscTab");
		tabCombat = new MiscUtilCreativeTabCombat("MiscUtilCombatTab");
		tabTools = new MiscUtilCreativeTabTools("MiscUtilToolsTab");
	}
}
