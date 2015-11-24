package miscutil.item;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import miscutil.creativetabs.TMCreativeTabs;
import miscutil.lib.Strings;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModItems {

	//Tool Materials
	public static ToolMaterial tutMaterial = EnumHelper.addToolMaterial("BloodSteel Tool Material", 3, 200, 15.0F, 4.0F, 10);

	//Armour Materials
	public static ArmorMaterial tutArmorMaterial = EnumHelper.addArmorMaterial("BloodSteel Armor Material", 33, new int[]{2, 5, 4, 2}, 10);

	//Base Classes For Items
	public static Item tutPickaxe;
	public static Item tutAxe;
	public static Item tutSword;
	public static Item tutHoe;
	public static Item tutSpade;

	//Base Classes For Armour
	public static Item tutHelmet;
	public static Item tutPlate;
	public static Item tutPants;
	public static Item tutBoots;

	//EnderIO
	public static Item itemPlateSoularium;
	public static Item itemPlateRedstoneAlloy;
	public static Item itemPlateElectricalSteel;
	public static Item itemPlatePulsatingIron;
	public static Item itemPlateEnergeticAlloy;
	public static Item itemPlateVibrantAlloy;
	public static Item itemPlateConductiveIron;
	public static Item itemPlateDarkSteel;

	//Big Reactors
	public static Item itemPlateBlutonium;
	public static Item itemPlateCyanite;
	public static Item itemPlateLudicrite;

	//Thaumcraft
	public static Item itemPlateVoidMetal;

	//ExtraUtils
	public static Item itemPlateBedrockium;

	//Pneumaticraft
	public static Item itemPlateCompressedIron;

	//SimplyJetpacks
	public static Item itemPlateEnrichedSoularium;

	//rfTools
	public static Item itemPlateDimensionShard;

	//Misc Items
	public static Item itemIngotBloodSteel;
	public static Item itemPlateBloodSteel;



	//@SuppressWarnings("unused")
	public static final void init(){

		/*
		 * 
		 * Strings.DEBUG Parameters area
		 * 
		 */



		//Logs
		if (!Strings.DEBUG){
			FMLLog.info("Development mode not enabled.");
		}
		else if (Strings.DEBUG){
			FMLLog.info("Development mode enabled.");
		}
		else {
			FMLLog.info("Development mode not set.");
		}


		/*
		 * End Strings.DEBUG
		 */



		/*		//Blood Steel Equipment

		//Item Init
		tutPickaxe = new BloodSteelPickaxe(tutMaterial).setUnlocalizedName("BloodSteelPickaxe").setCreativeTab(TMCreativeTabs.tabTools).setTextureName(Strings.MODID + ":BloodSteelPickaxe");
		tutAxe = new BloodSteelAxe(tutMaterial).setUnlocalizedName("BloodSteelAxe").setCreativeTab(TMCreativeTabs.tabTools).setTextureName(Strings.MODID + ":BloodSteelAxe");
		tutSword = new BloodSteelSword(tutMaterial).setUnlocalizedName("BloodSteelSword").setCreativeTab(TMCreativeTabs.tabCombat).setTextureName(Strings.MODID + ":BloodSteelSword");
		tutHoe = new BloodSteelHoe(tutMaterial).setUnlocalizedName("BloodSteelHoe").setCreativeTab(TMCreativeTabs.tabTools).setTextureName(Strings.MODID + ":BloodSteelHoe");
		tutSpade = new BloodSteelSpade(tutMaterial).setUnlocalizedName("BloodSteelSpade").setCreativeTab(TMCreativeTabs.tabTools).setTextureName(Strings.MODID + ":BloodSteelSpade");
		tutHelmet = new BloodSteelArmor(tutArmorMaterial, MiscUtils.proxy.addArmor("BloodSteelArmor"), 0).setUnlocalizedName("BloodSteelHelmet").setCreativeTab(TMCreativeTabs.tabCombat).setTextureName(Strings.MODID + ":BloodSteelHelmet");
		tutPlate = new BloodSteelArmor(tutArmorMaterial, MiscUtils.proxy.addArmor("BloodSteelArmor"), 1).setUnlocalizedName("BloodSteelPlate").setCreativeTab(TMCreativeTabs.tabCombat).setTextureName(Strings.MODID + ":BloodSteelPlate");
		tutPants = new BloodSteelArmor(tutArmorMaterial, MiscUtils.proxy.addArmor("BloodSteelArmor"), 2).setUnlocalizedName("BloodSteelPants").setCreativeTab(TMCreativeTabs.tabCombat).setTextureName(Strings.MODID + ":BloodSteelPants");
		tutBoots = new BloodSteelArmor(tutArmorMaterial, MiscUtils.proxy.addArmor("BloodSteelArmor"), 3).setUnlocalizedName("BloodSteelBoots").setCreativeTab(TMCreativeTabs.tabCombat).setTextureName(Strings.MODID + ":BloodSteelBoots");

		//Registry
		GameRegistry.registerItem(tutPickaxe, tutPickaxe.getUnlocalizedName());
		GameRegistry.registerItem(tutAxe, tutAxe.getUnlocalizedName());
		GameRegistry.registerItem(tutSword, tutSword.getUnlocalizedName());
		GameRegistry.registerItem(tutHoe, tutHoe.getUnlocalizedName());
		GameRegistry.registerItem(tutSpade, tutSpade.getUnlocalizedName());
		GameRegistry.registerItem(tutHelmet, tutHelmet.getUnlocalizedName());
		GameRegistry.registerItem(tutPlate, tutPlate.getUnlocalizedName());
		GameRegistry.registerItem(tutPants, tutPants.getUnlocalizedName());
		GameRegistry.registerItem(tutBoots, tutBoots.getUnlocalizedName());	*/






		//EnderIO Resources
		if (Loader.isModLoaded("EnderIO") == true || Strings.DEBUG){
			if (Strings.DEBUG){
				FMLLog.info("EnderIO Found - Loading Resources");}
				//Item Init
				itemPlateSoularium = new Item().setUnlocalizedName("itemPlateSoularium").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateSoularium");;
				itemPlateRedstoneAlloy = new Item().setUnlocalizedName("itemPlateRedstoneAlloy").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateRedstoneAlloy");;
				itemPlateElectricalSteel = new Item().setUnlocalizedName("itemPlateElectricalSteel").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateElectricalSteel");;
				itemPlatePulsatingIron = new Item().setUnlocalizedName("itemPlatePulsatingIron").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlatePulsatingIron");;
				itemPlateEnergeticAlloy = new Item().setUnlocalizedName("itemPlateEnergeticAlloy").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateEnergeticAlloy");;
				itemPlateVibrantAlloy = new Item().setUnlocalizedName("itemPlateVibrantAlloy").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateVibrantAlloy");;
				itemPlateConductiveIron = new Item().setUnlocalizedName("itemPlateConductiveIron").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateConductiveIron");;
				itemPlateDarkSteel = new Item().setUnlocalizedName("itemPlateDarkSteel").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateDarkSteel");;


				//Registry
				GameRegistry.registerItem(itemPlateSoularium, "itemPlateSoularium");
				GameRegistry.registerItem(itemPlateRedstoneAlloy, "itemPlateRedstoneAlloy");
				GameRegistry.registerItem(itemPlateElectricalSteel, "itemPlateElectricalSteel");
				GameRegistry.registerItem(itemPlatePulsatingIron, "itemPlatePulsatingIron");
				GameRegistry.registerItem(itemPlateEnergeticAlloy, "itemPlateEnergeticAlloy");
				GameRegistry.registerItem(itemPlateVibrantAlloy, "itemPlateVibrantAlloy");
				GameRegistry.registerItem(itemPlateConductiveIron, "itemPlateConductiveIron");
				GameRegistry.registerItem(itemPlateDarkSteel, "itemPlateDarkSteel");
			}
			else {
				FMLLog.info("EnderIO not Found - Skipping Resources");
			}

			//Big Reactors
			if (Loader.isModLoaded("BigReactors") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("BigReactors Found - Loading Resources");}
				//Item Init
				itemPlateBlutonium = new Item().setUnlocalizedName("itemPlateBlutonium").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateBlutonium");;
				itemPlateCyanite = new Item().setUnlocalizedName("itemPlateCyanite").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateCyanite");;
				itemPlateLudicrite = new Item().setUnlocalizedName("itemPlateLudicrite").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateLudicrite");;

				//Registry
				GameRegistry.registerItem(itemPlateBlutonium, "itemPlateBlutonium");
				GameRegistry.registerItem(itemPlateCyanite, "itemPlateCyanite");
				GameRegistry.registerItem(itemPlateLudicrite, "itemPlateLudicrite");

			}
			else {
				FMLLog.info("BigReactors not Found - Skipping Resources");
			}

			//Thaumcraft
			if (Loader.isModLoaded("Thaumcraft") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("Thaumcraft Found - Loading Resources");}
				//Item Init
				itemPlateVoidMetal = new Item().setUnlocalizedName("itemPlateVoidMetal").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateVoidMetal");;

				//Registry
				GameRegistry.registerItem(itemPlateVoidMetal, "itemPlateVoidMetal");

			}
			else {
				FMLLog.info("Thaumcraft not Found - Skipping Resources");
			}

			//ExtraUtils
			if (Loader.isModLoaded("ExtraUtilities") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("ExtraUtils Found - Loading Resources");}
				//Item Init
				itemPlateBedrockium = new Item().setUnlocalizedName("itemPlateBedrockium").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateBedrockium");;

				//Registry
				GameRegistry.registerItem(itemPlateBedrockium, "itemPlateBedrockium");

			}
			else {
				FMLLog.info("ExtraUtils not Found - Skipping Resources");
			}

			//Pneumaticraft
			if (Loader.isModLoaded("PneumaticCraft") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("Pneumaticraft Found - Loading Resources");}
				//Item Init
				itemPlateCompressedIron = new Item().setUnlocalizedName("itemPlateCompressedIron").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateCompressedIron");;

				//Registry
				GameRegistry.registerItem(itemPlateCompressedIron, "itemPlateCompressedIron");

			}
			else {
				FMLLog.info("Pneumaticraft not Found - Skipping Resources");
			}

			//Simply Jetpacks
			if (Loader.isModLoaded("simplyjetpacks") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("SimplyJetpacks Found - Loading Resources");}
				//Item Init
				itemPlateEnrichedSoularium = new RarityUncommon().setUnlocalizedName("itemPlateEnrichedSoularium").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateSoularium");;

				//Registry
				GameRegistry.registerItem(itemPlateEnrichedSoularium, "itemPlateEnrichedSoularium");

			}
			else {
				FMLLog.info("SimplyJetpacks not Found - Skipping Resources");
			}


			//rfTools
			if (Loader.isModLoaded("rftools") == true || Strings.DEBUG){
				if (Strings.DEBUG){
					FMLLog.info("rfTools Found - Loading Resources");}
				//Item Init
				itemPlateDimensionShard = new Item().setUnlocalizedName("itemPlateDimensionShard").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateDimensionShard");;

				//Registry
				GameRegistry.registerItem(itemPlateDimensionShard, "itemPlateDimensionShard");

			}
			else {
				FMLLog.info("rfTools not Found - Skipping Resources");
			}

			/*
			 * Misc Items
			 */

			//Blood Steel Ingot
			itemIngotBloodSteel = new Item().setUnlocalizedName("itemIngotBloodSteel").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemIngotBloodSteel");;
			GameRegistry.registerItem(itemIngotBloodSteel, "itemIngotBloodSteel");
			//Blood Steel Ingot
			itemPlateBloodSteel = new Item().setUnlocalizedName("itemPlateBloodSteel").setCreativeTab(TMCreativeTabs.tabMisc).setTextureName(Strings.MODID + ":itemPlateBloodSteel");;
			GameRegistry.registerItem(itemPlateBloodSteel, "itemPlateBloodSteel");

		}

	}
