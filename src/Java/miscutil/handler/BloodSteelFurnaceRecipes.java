package miscutil.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import miscutil.block.ModBlocks;
import miscutil.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BloodSteelFurnaceRecipes {

	private static final BloodSteelFurnaceRecipes SMELTING_BASE = new BloodSteelFurnaceRecipes();

	@SuppressWarnings("rawtypes")
	private Map smeltingList = new HashMap();
	@SuppressWarnings("rawtypes")
	private Map experienceList = new HashMap();

	public static BloodSteelFurnaceRecipes smelting() {
		return SMELTING_BASE;
	}
	
	private BloodSteelFurnaceRecipes(){
		this.addRecipie(ModItems.itemIngotBloodSteel, new ItemStack(ModItems.itemPlateBloodSteel), 0.8F);
		this.addRecipie(Item.getItemFromBlock(ModBlocks.tutChest), new ItemStack(ModItems.tutHelmet), 0.8F);
	}
	
	public void addRecipie(Item item, ItemStack itemstack, float experience){
		this.addLists(item, itemstack, experience);
	}
	
	public void addLists(Item item, ItemStack itemstack, float experience){
		this.putLists(new ItemStack(item, 1, 32767), itemstack, experience);
	}
	
	@SuppressWarnings("unchecked")
	public void putLists(ItemStack itemstack, ItemStack itemstack2, float experience){
		this.smeltingList.put(itemstack, itemstack2);
		this.experienceList.put(itemstack2, Float.valueOf(experience));
	}

	@SuppressWarnings("rawtypes")
	public ItemStack getSmeltingResult(ItemStack itemstack) {
		Iterator iterator = this.smeltingList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}
			entry = (Entry) iterator.next();
		} while (!canBeSmelted(itemstack, (ItemStack) entry.getKey()));
		return (ItemStack) entry.getValue();
	}

	private boolean canBeSmelted(ItemStack itemstack, ItemStack itemstack2) {
		return itemstack2.getItem() == itemstack.getItem() && (itemstack2.getItemDamage() == 32767 || itemstack2.getItemDamage() == itemstack.getItemDamage());
	}
	
	@SuppressWarnings("rawtypes")
	public float giveExperience(ItemStack itemstack){
		Iterator iterator = this.experienceList.entrySet().iterator();
		Entry entry;
		
		do{
			if(!iterator.hasNext()){
				return 0.0f;
			}
			
			entry = (Entry) iterator.next();
		}
		while(!this.canBeSmelted(itemstack, (ItemStack) entry.getKey()));
		
		if(itemstack.getItem().getSmeltingExperience(itemstack) != -1){
				return itemstack.getItem().getSmeltingExperience(itemstack);
		}
		
		return ((Float) entry.getValue()).floatValue();
	}
}
