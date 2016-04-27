package HxCKDMS.gkpk.data;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.gkpk.block.BlockExtractor;
import HxCKDMS.gkpk.block.BlockFermenter;
import HxCKDMS.gkpk.event.GEventHandler;
import HxCKDMS.gkpk.items.ItemEthanol;
import HxCKDMS.gkpk.items.ItemExtract;
import HxCKDMS.gkpk.items.ItemPharmacyBook;
import HxCKDMS.gkpk.recipe.GKPKRecipe;
import HxCKDMS.gkpk.tile.TileEntityFermenter;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

public class Registry {
    public HashMap<String, Drug> drugs = new HashMap<String, Drug>();

    public BlockExtractor blockextractor = new BlockExtractor();
    public ItemEthanol itemEthanol = new ItemEthanol();
    public ItemExtract itemExtract = new ItemExtract();
    public BlockFermenter fermenter = new BlockFermenter();
    public ItemPharmacyBook pbook = new ItemPharmacyBook();

    public void preInit() {
        // Default compound set
        drugs.put("nodu", new Drug("Nodularin", 0xF4F4F4, "", 0, 0, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 4), new PotionEffect(Potion.digSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2)));
        drugs.put("ttx", new Drug("Tetrodotoxin", 0xFEFEFE, "desaturate", TimeConst.TICKS_PER_MINUTE, 0, new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE *2, 50), new PotionEffect(Potion.wither.getId(), TimeConst.TICKS_PER_MINUTE, 3)));
        drugs.put("ethanol", new Drug("Ethanol", 0x000000, "blur", TimeConst.TICKS_PER_MINUTE * 2, 4, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 2), new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2)));

        MinecraftForge.EVENT_BUS.register(new GEventHandler());
    }

    public void init() {
        GameRegistry.registerItem(itemEthanol, "gEthanol"); // Ethanol
        GameRegistry.registerItem(itemExtract, "gExtractItem"); // NBTBased Extract Item

        GameRegistry.registerBlock(blockextractor, "gExtractor"); // Extractor
        GameRegistry.registerBlock(fermenter, "gFermenter"); // Fermenter

        GameRegistry.registerTileEntity(TileEntityFermenter.class, "gkpktefermenter"); // TE for fermenter
    }

    public void postInit() {
        //Extractor Recipes
        GKPKRecipe.Extracting().registerExtractRecipe(Items.rotten_flesh, "nodu");

        LogHelper.info("There has been " + drugs.size() + " drugs initialized!", "GKPK");
        LogHelper.info("There has been " + GKPKRecipe.Extracting().getExtractList().size() + " Extractor recipes initialized!", "GKPK");
    }
}
