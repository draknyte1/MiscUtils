package miscutil.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class ModBlocks {

	//Blood Steel
	public static Block blockBloodSteel;
	//public static Block blockBloodSteelChest;

	//BloodSteelorial Furnace
	//public static Block tutFurnace;
	//public static Block tutFurnaceActive;

	//BloodSteelorial Chest
	//public static Block tutChest;

	//Arcane Infuser
	//public static Block arcaneInfuser;
	//public static Block arcaneInfuserActive;


	public static void init() {
		//BloodSteelorial Furnace - Must Init blocks first as they're not static.
		/** if (Strings.DEBUG){
			FMLLog.info("Loading Furnace.");}
		tutFurnace= new BloodSteelFurnace(false).setBlockName("BloodSteelFurnace").setCreativeTab(TMCreativeTabs.tabBlock);
		tutFurnaceActive= new BloodSteelFurnace(true).setBlockName("BloodSteelFurnaceActive");

		//Arcane Infuser - Must Init blocks first as they're not static.
		if (Strings.DEBUG){
			FMLLog.info("Loading Arcane Infuser.");}
		arcaneInfuser = new ArcaneInfuser(false).setBlockName("ArcaneInfuser").setCreativeTab(TMCreativeTabs.tabBlock);
		arcaneInfuserActive = new ArcaneInfuser(true).setBlockName("ArcaneInfuserActive");

		//Blood Steel Chest
		if (Strings.DEBUG){
			FMLLog.info("Loading Blood Steel Chest.");}
		tutChest = new BloodSteelChest(0).setBlockName("BloodSteelChest").setCreativeTab(TMCreativeTabs.tabBlock);

		*/
		//Register Blocks next - TODO
		registerBlocks(); 
	}

	public static void registerBlocks(){
		
		//Blood Steel Block
				GameRegistry.registerBlock(blockBloodSteel = new BasicBlock("blockBloodSteel", Material.iron), "blockBloodSteel");
		
		/** TODO re-enable blocks when working.
		
		
		//Blood Steel Chest
		GameRegistry.registerBlock(tutChest, tutChest.getUnlocalizedName());

		//BloodSteelorial Furnace
		GameRegistry.registerBlock(tutFurnace, tutFurnace.getUnlocalizedName());
		GameRegistry.registerBlock(tutFurnaceActive, tutFurnaceActive.getUnlocalizedName());

		//Arcane Infuser
		GameRegistry.registerBlock(arcaneInfuser, arcaneInfuser.getUnlocalizedName());
		GameRegistry.registerBlock(arcaneInfuserActive, arcaneInfuserActive.getUnlocalizedName());
		 **/
	}

}