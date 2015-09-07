package miscutil.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import miscutil.handler.ArcaneInfuserRecipes;
import miscutil.tile_entity.TileEntityArcaneInfuser;
import miscutil.tile_entity.TileEntityBloodSteelChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBloodSteelChest extends Container {
	
	private TileEntityBloodSteelChest tileChest;
	public ContainerBloodSteelChest(InventoryPlayer player, TileEntityBloodSteelChest tileEntityBloodSteelChest){
		this.tileChest = tileEntityBloodSteelChest;
		
		
		this.addSlotToContainer(new Slot(tileEntityBloodSteelChest, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntityBloodSteelChest, 1, 91, 65));
		int i;
		for(i = 0; i < 3; ++i){
			for(int j = 0; j < 9; ++j){
				//this.addSlotToContainer(new Slot(player, j + i * 6 + 6, -150 + j * 18, 84 + i * 18));
				this.addSlotToContainer(new Slot(tileEntityBloodSteelChest, j + i * 9 + 9, 8 + j * 18, 17 + i * 18));//-- TODO -- This broke item handling for my Arcane Infuser
			}
		}
		
		for(i = 0; i < 3; ++i){
			for(int j = 0; j < 9; ++j){
				//this.addSlotToContainer(new Slot(player, j + i * 6 + 6, -150 + j * 18, 84 + i * 18));
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));//-- TODO -- This broke item handling for my Arcane Infuser
			}
		}
		
		for(i = 0; i < 9; ++i){
			this.addSlotToContainer(new Slot(player, i , 8 + i * 18 , 142));
			//this.addSlotToContainer(new Slot(player, i , -150 + i * 18 , 175)); //-- TODO -- This broke item handling for my Arcane Infuser
		}
	}

	public void addCraftingToCrafters(ICrafting craft){
		super.addCraftingToCrafters(craft);
	}
	
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2){
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileChest.isUseableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int par2){
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(par2 == 2){
				if(!this.mergeItemStack(itemstack1, 3, 39, true)){
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}else if(par2 != 1 && par2 != 0){
				if(ArcaneInfuserRecipes.smelting().getSmeltingResult(itemstack1) != null){
					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				}else if(TileEntityArcaneInfuser.isItemFuel(itemstack1)){
					if(!this.mergeItemStack(itemstack1, 1, 2, false)){
						return null;
					}
				}else if(par2 >=3 && par2 < 30){
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				}else if(par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
					return null;
				}
			}else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
				return null;
			}
			if(itemstack1.stackSize == 0){
				slot.putStack((ItemStack)null);
			}else{
				slot.onSlotChanged();
			}
			if(itemstack1.stackSize == itemstack.stackSize){
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}

}
