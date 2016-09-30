package HxCKDMS.gkpk.data;

import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.gkpk.block.BlockExtractor;
import HxCKDMS.gkpk.block.BlockFermenter;
import HxCKDMS.gkpk.block.tile.TileEntityExtractor;
import HxCKDMS.gkpk.block.tile.TileEntityFermenter;
import HxCKDMS.gkpk.data.recipe.GKPKRecipe;
import HxCKDMS.gkpk.event.GEventHandler;
import HxCKDMS.gkpk.items.ItemEthanol;
import HxCKDMS.gkpk.items.ItemExtract;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

public class Registry {
    public HashMap<String, Drug> drugs = new HashMap<>();

    public BlockExtractor blockextractor = new BlockExtractor();
    public ItemEthanol itemEthanol = new ItemEthanol();
    public ItemExtract itemExtract = new ItemExtract();
    public BlockFermenter fermenter = new BlockFermenter();

    public void preInit() {
        // Default compound set
        drugs.put("nodu", new Drug("Nodularin", 0xF4F4F4, "", 0, 0, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 4), new PotionEffect(Potion.digSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // From zombie flesh
        drugs.put("ttx", new Drug("Tetrodotoxin", 0xFEFEFE, "desaturate", TimeConst.TICKS_PER_MINUTE, 4, new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE *2, 50), new PotionEffect(Potion.wither.getId(), TimeConst.TICKS_PER_MINUTE, 3))); // From pufferfish
        drugs.put("ethanol", new Drug("Ethanol", 0x000000, "blur", TimeConst.TICKS_PER_MINUTE * 2, 4, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 2), new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // internal 
        drugs.put("musc", new Drug("Muscimol", 0x30FF30, "sobel", TimeConst.TICKS_PER_MINUTE * 2, 2, new PotionEffect(Potion.damageBoost.getId(), TimeConst.TICKS_PER_MINUTE, 1), new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // From red mushroom
        MinecraftForge.EVENT_BUS.register(new GEventHandler());
    }

    public void init() {
        GameRegistry.registerItem(itemEthanol, "gEthanol"); // Ethanol
        GameRegistry.registerItem(itemExtract, "gExtractItem"); // NBTBased Extract Item

        GameRegistry.registerBlock(blockextractor, "gExtractor"); // Extractor
        GameRegistry.registerBlock(fermenter, "gFermenter"); // Fermenter

        GameRegistry.registerTileEntity(TileEntityFermenter.class, "GKPK_Fermenter"); // TE for fermenter
        GameRegistry.registerTileEntity(TileEntityExtractor.class, "GKPK_Extractor"); // TE for Extactor
    }

    public void postInit() {
        //Extractor Recipes
        GKPKRecipe.Extracting().registerExtractRecipe(Items.rotten_flesh, "nodu");
        GKPKRecipe.Extracting().registerExtractRecipe(ItemBlock.getItemFromBlock(Blocks.red_mushroom), "musc");
        GKPKRecipe.Extracting().registerExtractRecipe(Items.fish, 3, "ttx");

        LogHelper.info("There has been " + drugs.size() + " drugs added", "GKPK");
        LogHelper.info("There has been " + GKPKRecipe.Extracting().getExtractList().size() + " Extractor recipes initialized!", "GKPK");
    }
}
