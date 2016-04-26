package HxCKDMS.gkpk.items;

import HxCKDMS.gkpk.GordianCreativeTab;
import HxCKDMS.gkpk.data.TimeConst;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemEthanol extends Item implements IFuelHandler {

    public ItemEthanol() {
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        this.setUnlocalizedName("gEthanol");
        this.setTextureName("GKPK:product0");
        this.setMaxStackSize(32);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack item){return EnumAction.drink;}

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {return 32;}


    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
        if(player.capabilities.isCreativeMode) {
            this.onEaten(stack, world, player);
            return stack;
        }else{
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
            return stack;
        }
    }
    
    @Override
    public ItemStack onEaten(ItemStack item, World world, EntityPlayer player){ // TODO - trigger vodka achievement'
        player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2));
        player.attackEntityFrom(DamageSource.generic, 4);
        if(!player.capabilities.isCreativeMode) {
                player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return new ItemStack(item.getItem(), item.stackSize - 1);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        return 120;
    }
}
