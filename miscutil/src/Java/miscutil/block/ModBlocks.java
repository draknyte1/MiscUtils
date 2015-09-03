package miscutil.block;

import cpw.mods.fml.common.registry.GameRegistry;
import miscutil.creativetabs.TMCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class ModBlocks {
	
	//Blood Steel
	public static Block blockBloodSteel;
	
	//Arcane Infuser
	public static Block arcaneInfuser;
	public static Block arcaneInfuserActive;
	
	
    public static final void init() {
    	
    	//Blood Steel
    	GameRegistry.registerBlock(blockBloodSteel = new BasicBlock("blockBloodSteel", Material.iron), "blockBloodSteel");
    	
    	//Arcane Infuser - Must Init blocks first as they're not static.
    	arcaneInfuser = new ArcaneInfuser(false).setBlockName("ArcaneInfuser").setCreativeTab(TMCreativeTabs.tabBlock);
    	arcaneInfuserActive = new ArcaneInfuser(true).setBlockName("ArcaneInfuserActive");
    	GameRegistry.registerBlock(arcaneInfuser, arcaneInfuser.getUnlocalizedName());
    	GameRegistry.registerBlock(arcaneInfuserActive, arcaneInfuserActive.getUnlocalizedName());
    	
    }

}