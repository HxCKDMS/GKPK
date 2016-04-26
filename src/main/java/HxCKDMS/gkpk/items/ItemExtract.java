package HxCKDMS.gkpk.items;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.GordianCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemExtract extends Item { // This whole java file is a bit messy but it does the joj
    public ItemExtract() {
        setCreativeTab(GordianCreativeTab.gordianCreativeTab);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack item) {
        return EnumAction.drink;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.capabilities.isCreativeMode) {
            this.onEaten(stack, world, player);
            return stack;
        } else {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
            return stack;
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound().getString("drugEffect") != null)
            return stack.getTagCompound().getString("drugEffect");
        else return "invalid";
    }

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        icon = iconRegister.registerIcon("GKPK:product");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return icon;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            GKPK.drugs.get(stack.getTagCompound().getString("drugEffect")).activateDrug((EntityPlayerMP) player);
            if (!player.capabilities.isCreativeMode) {
                player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
            }
        }

        return new ItemStack(stack.getItem(), stack.stackSize - 1);
    }
}