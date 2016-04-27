package HxCKDMS.gkpk.items;
import HxCKDMS.gkpk.client.GordianCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemPharmacyBook extends Item {

    public ItemPharmacyBook() {
        this.setTextureName("minecraft:book_written");
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        this.setUnlocalizedName("gPharmBook");
        this.setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack stack, EntityPlayer ply, List tooltip, boolean wtf) {
        tooltip.add("§6 " + StatCollector.translateToLocal("gkpk.book.tooltip"));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer ply) {
        //Open GUI
        return stack;
    }
}
