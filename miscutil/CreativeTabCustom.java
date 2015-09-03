package miscutil;

import miscutil.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCustom extends CreativeTabs {

	public CreativeTabCustom(int id, String unlocalizedName) {
		super(id, unlocalizedName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return ModItems.itemIngotBloodSteel;
	}


}
