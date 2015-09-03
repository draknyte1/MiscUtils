package miscutil.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RarityEpic extends Item {

	public RarityEpic(int par1){
		super();
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack){
		return EnumRarity.epic;
	}
	
	@Override
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}
	
}
