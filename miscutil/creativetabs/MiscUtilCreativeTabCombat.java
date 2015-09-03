package miscutil.creativetabs;

import miscutil.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MiscUtilCreativeTabCombat extends CreativeTabs {

	public MiscUtilCreativeTabCombat(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.tutSword;
	}

}
