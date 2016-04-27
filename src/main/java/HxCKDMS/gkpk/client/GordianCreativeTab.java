package HxCKDMS.gkpk.client;

import HxCKDMS.gkpk.GKPK;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GordianCreativeTab {
    public static CreativeTabs gordianCreativeTab = new CreativeTabs("GKPK") {
        @Override
        public Item getTabIconItem() {
            return GKPK.registry.itemEthanol;
        }
    };
}
