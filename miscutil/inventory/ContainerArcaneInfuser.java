package miscutil.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

import miscutil.handler.ArcaneInfuserRecipes;
import miscutil.tile_entity.TileEntityArcaneInfuser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerArcaneInfuser extends Container {
	
	private TileEntityArcaneInfuser tileInfuser;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;
	
	public ContainerArcaneInfuser(InventoryPlayer player, TileEntityArcaneInfuser tileEntityArcaneInfuser){
		this.tileInfuser = tileEntityArcaneInfuser;
		this.addSlotToContainer(new Slot(tileEntityArcaneInfuser, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntityArcaneInfuser, 1, 91, 65));
		this.addSlotToContainer(new SlotFurnace(player.player, tileEntityArcaneInfuser, 2, 130, 117));
		int i;
		
		for(i = 0; i < 5; ++i){
			for(int j = 0; j < 6; ++j){
				this.addSlotToContainer(new Slot(player, j + i * 6 + 6, -150 + j * 18, 84 + i * 18));
			}
		}
		
		for(i = 0; i < 9; ++i){
			this.addSlotToContainer(new Slot(player, i , -150 + i * 18 , 175));
		}
	}

	public void addCraftingToCrafters(ICrafting craft){
		super.addCraftingToCrafters(craft);
		craft.sendProgressBarUpdate(this, 0, this.tileInfuser.furnaceCookTime);
		craft.sendProgressBarUpdate(this, 1, this.tileInfuser.furnaceBurnTime);
		craft.sendProgressBarUpdate(this, 2, this.tileInfuser.currentBurnTime);
	}
	
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); ++i){
			ICrafting craft = (ICrafting) this.crafters.get(i);
			
			if(this.lastCookTime != this.tileInfuser.furnaceCookTime){
				craft.sendProgressBarUpdate(this, 0, this.tileInfuser.furnaceCookTime);
			}
			
			if(this.lastBurnTime != this.tileInfuser.furnaceBurnTime){
				craft.sendProgressBarUpdate(this, 1, this.tileInfuser.furnaceBurnTime);
			}
			
			if(this.lastItemBurnTime != this.tileInfuser.currentBurnTime){
				craft.sendProgressBarUpdate(this, 2, this.tileInfuser.currentBurnTime);
			}
		}
		
		this.lastBurnTime = this.tileInfuser.furnaceBurnTime;
		this.lastCookTime = this.tileInfuser.furnaceCookTime;
		this.lastItemBurnTime = this.tileInfuser.currentBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2){
		if(par1 == 0){
			this.tileInfuser.furnaceCookTime = par2;
		}
		
		if(par1 == 1){
			this.tileInfuser.furnaceBurnTime = par2;
		}
		
		if(par1 == 2){
			this.tileInfuser.currentBurnTime = par2;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileInfuser.isUseableByPlayer(player);
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
