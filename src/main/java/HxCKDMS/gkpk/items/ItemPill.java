package HxCKDMS.gkpk.items;

import HxCKDMS.gkpk.client.GordianCreativeTab;
import net.minecraft.item.ItemFood;

public class ItemPill extends ItemFood{
    public ItemPill()
    {
        super(0, 0, false);
        setMaxDamage(0);
        setMaxStackSize(32);
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        this.setUnlocalizedName("gPill");
        this.setTextureName("GKPK:pillitem");
        this.setMaxStackSize(32);
    }
}
