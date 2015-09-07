package miscutil.tile_entity;

import cpw.mods.fml.common.FMLLog;
import miscutil.block.BloodSteelChest;
import miscutil.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityBloodSteelChest extends TileEntityChest{
	
	private String customName;
	private int cachedChestType;
	private ItemStack[] chestContents = new ItemStack[36];
	
	 /**
     * Contains the chest tile located adjacent to this one (if any)
     */
    public TileEntityBloodSteelChest adjacentChestZNeg;
    /**
     * Contains the chest tile located adjacent to this one (if any)
     */
    public TileEntityBloodSteelChest adjacentChestXPos;
    /**
     * Contains the chest tile located adjacent to this one (if any)
     */
    public TileEntityBloodSteelChest adjacentChestXNeg;
    /**
     * Contains the chest tile located adjacent to this one (if any)
     */
    public TileEntityBloodSteelChest adjacentChestZPos;
    
    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : "BloodSteel Chest";
    }
    
    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && this.customName.length() > 0;
    }

    public void func_145976_a(String p_145976_1_)
    {
        this.customName = p_145976_1_;
    }
    
    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
    	if (Strings.DEBUG){FMLLog.info("Read From NBT");}
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];

        if (p_145839_1_.hasKey("BloodSteel Chest", 8))
        {
        	if (Strings.DEBUG){FMLLog.info("Has Name: BloodSteel Chest");}
            this.customName = p_145839_1_.getString("BloodSteel Chest");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
        	if (Strings.DEBUG){FMLLog.info("Read from NBT "+i);}
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length)
            {
            	if (Strings.DEBUG){FMLLog.info("Not Sure?");}
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
    
    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
		if (Strings.DEBUG){FMLLog.info("Write To NBT Method");}
        super.writeToNBT(p_145841_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.chestContents.length; ++i)
        {
        	if (Strings.DEBUG){FMLLog.info("Check slot "+i);}
        	if (Strings.DEBUG && chestContents[i] != null){FMLLog.info(chestContents[i].getDisplayName());}
            if (this.chestContents[i] != null)
            {
            	if (Strings.DEBUG){FMLLog.info("Write slot "+i+" to NBT");}
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        p_145841_1_.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            p_145841_1_.setString("BloodSteel Chest", this.customName);
        }
    }
    
    private void func_145978_a(TileEntityBloodSteelChest p_145978_1_, int p_145978_2_)
    {
        if (p_145978_1_.isInvalid())
        {
            this.adjacentChestChecked = false;
        }
        else if (this.adjacentChestChecked)
        {
            switch (p_145978_2_)
            {
                case 0:
                    if (this.adjacentChestZPos != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 1:
                    if (this.adjacentChestXNeg != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 2:
                    if (this.adjacentChestZNeg != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }

                    break;
                case 3:
                    if (this.adjacentChestXPos != p_145978_1_)
                    {
                        this.adjacentChestChecked = false;
                    }
            }
        }
    }
    
    /**
     * Performs the check for adjacent chests to determine if this chest is double or not.
     */
    public void checkForAdjacentChests()
    {
        if (!this.adjacentChestChecked)
        {
            this.adjacentChestChecked = true;
            this.adjacentChestZNeg = null;
            this.adjacentChestXPos = null;
            this.adjacentChestXNeg = null;
            this.adjacentChestZPos = null;

            if (this.func_145977_a(this.xCoord - 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXNeg = (TileEntityBloodSteelChest)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
            }

            if (this.func_145977_a(this.xCoord + 1, this.yCoord, this.zCoord))
            {
                this.adjacentChestXPos = (TileEntityBloodSteelChest)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
            }

            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord - 1))
            {
                this.adjacentChestZNeg = (TileEntityBloodSteelChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
            }

            if (this.func_145977_a(this.xCoord, this.yCoord, this.zCoord + 1))
            {
                this.adjacentChestZPos = (TileEntityBloodSteelChest)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
            }

            if (this.adjacentChestZNeg != null)
            {
                this.adjacentChestZNeg.func_145978_a(this, 0);
            }

            if (this.adjacentChestZPos != null)
            {
                this.adjacentChestZPos.func_145978_a(this, 2);
            }

            if (this.adjacentChestXPos != null)
            {
                this.adjacentChestXPos.func_145978_a(this, 1);
            }

            if (this.adjacentChestXNeg != null)
            {
                this.adjacentChestXNeg.func_145978_a(this, 3);
            }
        }
    }

    private boolean func_145977_a(int p_145977_1_, int p_145977_2_, int p_145977_3_)
    {
        Block block = this.worldObj.getBlock(p_145977_1_, p_145977_2_, p_145977_3_);
        return block instanceof BloodSteelChest && ((BloodSteelChest)block).field_149956_a == this.func_145980_j();
    }
    
    public int func_145980_j()
    {
        if (this.cachedChestType == -1)
        {
            if (this.worldObj == null || !(this.getBlockType() instanceof BloodSteelChest))
            {
                return 0;
            }

            this.cachedChestType = ((BloodSteelChest)this.getBlockType()).field_149956_a;
        }

        return this.cachedChestType;
    }
    
	public void closeInventory() {
		this.markDirty();
	}

}
