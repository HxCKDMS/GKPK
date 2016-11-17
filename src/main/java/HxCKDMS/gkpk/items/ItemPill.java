package HxCKDMS.gkpk.items;

import HxCKDMS.gkpk.client.GordianCreativeTab;
import net.minecraft.item.ItemFood;

public class ItemPill extends ItemFood{
    public ItemPill(int a, int b, boolean c)
    {
        super(0, 0, false);
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        this.setUnlocalizedName("gPill");
        this.setTextureName("GKPK:pillitem");
        this.setMaxStackSize(64);
    }
}
