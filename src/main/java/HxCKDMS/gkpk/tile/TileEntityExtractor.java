package HxCKDMS.gkpk.tile;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.recipe.GKPKRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import static HxCKDMS.gkpk.data.TimeConst.EXTRACTOR_TIME;

public class TileEntityExtractor extends TileEntity implements ISidedInventory {
    private ItemStack[] slots;

    public TileEntityExtractor() {slots = new ItemStack[3];}
    public int processing = -1;

    @Override
    public void updateEntity() {
        if (slots == null || slots[0] == null || slots[1] == null)
            processing = -1;
        else if (slots[0].getItem() == GKPK.registry.itemEthanol && GKPKRecipe.Extracting().getExtractingResult(slots[1]) != null) {
            if (processing > 0) {
                processing--;
            } else if ((slots[2] == null || slots[2].stackSize < GKPK.registry.itemExtract.getItemStackLimit()) && processing < 1) {
                if (processing == -1) {
                    processing = EXTRACTOR_TIME;
                } else {
                    ItemStack stack = GKPKRecipe.Extracting().getExtractingResult(slots[1]);
                    if (stack != null) {
                        if (slots[2] == null) {
                            slots[2] = stack;
                            processing = -1;
                            slots[0].stackSize--;
                            slots[1].stackSize--;
                        } else if (stack.getItem() == slots[2].getItem()) {
                            slots[2].stackSize += 1;

                            slots[0].stackSize--;
                            slots[1].stackSize--;
                            processing = -1;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
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
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (isItemValidForSlot(slot, stack)) {
            slots[slot] = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                stack.stackSize = getInventoryStackLimit();
            }
        }
    }

    @Override
    public String getInventoryName() {return "extractor";}

    @Override
    public boolean hasCustomInventoryName() {return false;}

    @Override
    public int getInventoryStackLimit() {return 64;}

    @Override
    public boolean isUseableByPlayer(EntityPlayer ply) {return true;}

    @Override public void openInventory() {}

    @Override public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        switch (slot) {
            case 0 : return stack.getItem() == GKPK.registry.itemEthanol;
            case 1 : return GKPKRecipe.Extracting().getExtractingResult(stack) != null;
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
        for (int i = 0; i < slots.length; i++){
            ItemStack stack = slots[i];
            if (stack != null){
             NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot",(byte)i);
                stack.writeToNBT(tag);
                itemlist.appendTag(tag);
            }

        }
        TagCompound.setTag("Inventory",itemlist);
    }
}
