package miscutil.creativetabs;

import miscutil.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MiscUtilCreativeTabMisc extends CreativeTabs {

	public MiscUtilCreativeTabMisc(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.itemIngotBloodSteel;
	}

}
