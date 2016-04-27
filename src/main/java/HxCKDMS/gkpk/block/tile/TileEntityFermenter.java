package HxCKDMS.gkpk.block.tile;

import HxCKDMS.gkpk.GKPK;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

import static HxCKDMS.gkpk.data.TimeConst.FERMENTER_TIME;

public class TileEntityFermenter extends TileEntity implements ISidedInventory {
    private ItemStack[] slots;

    private List<Item> validIngredients = new ArrayList<>();
    {
        validIngredients.add(Items.wheat);
        validIngredients.add(Items.potato);
    }

    public TileEntityFermenter() {slots = new ItemStack[3];}
    public int processing = -1;

    @Override
    public void updateEntity() {
        if (slots == null || slots[0] == null || slots[1] == null)
            processing = -1;
        else if (validIngredients.contains(slots[0].getItem()) && slots[1].getItemDamage() == 0 && slots[1].getItem() == Items.potionitem) {
            if (processing > 0) {
                processing--;
            } else if ((slots[2] == null || slots[2].stackSize < GKPK.registry.itemEthanol.getItemStackLimit()) && processing < 1) {
                if (processing == -1) {
                    processing = FERMENTER_TIME;
                } else {
                    processing = -1;
                    if (!worldObj.isRemote) {
                        ItemStack stack = new ItemStack(GKPK.registry.itemEthanol);
                        if (slots[2] == null) {
                            slots[2] = stack;
                            slots[0].stackSize--;
                            slots[1] = null;
                        } else if (slots[2].getItem() == stack.getItem()) {
                            slots[2].stackSize += 1;

                            slots[0].stackSize--;
                            slots[1] = null;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 2;
    }

    @Override
    public int getSizeInventory() {return slots.length;}

    @Override
    public ItemStack getStackInSlot(int slotno) {return slots[slotno];}

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack itemStack = getStackInSlot(slot);
        if(itemStack != null){
            if(itemStack.stackSize <= amount){
                setInventorySlotContents(slot, null);
            }else{
                itemStack = itemStack.splitStack(amount);
                if(itemStack.stackSize == 0){
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack itemStack = getStackInSlot(slot);
        if(itemStack != null){
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        slots[slot] = itemStack;
        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {return "fermenter";}

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer ply) {
        return true;
    }

    @Override public void openInventory() {}

    @Override public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        switch (slot) {
            case 0 : return validIngredients.contains(stack.getItem());
            case 1 : return stack.getItem() == Items.potionitem && stack.getItemDamage() == 0;
            default : return false;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound TagCompound) {
        super.readFromNBT(TagCompound);
        NBTTagList taglist = TagCompound.getTagList("Inventory",10);
        for (int i = 0; i < taglist.tagCount(); i++) {
        NBTTagCompound tag = taglist.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < slots.length){
                slots[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound TagCompound) {
        super.writeToNBT(TagCompound);
        NBTTagList itemlist = new NBTTagList();
        for (int i = 0; i < slots.length; i++) {
            ItemStack stack = slots[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot",(byte)i);
                stack.writeToNBT(tag);
                itemlist.appendTag(tag);
            }
        }
        TagCompound.setTag("Inventory",itemlist);
    }
}
