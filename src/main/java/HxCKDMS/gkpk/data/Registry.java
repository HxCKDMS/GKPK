package HxCKDMS.gkpk.data;

import HxCKDMS.gkpk.block.BlockExtractor;
import HxCKDMS.gkpk.block.BlockFermenter;
import HxCKDMS.gkpk.block.tile.TileEntityExtractor;
import HxCKDMS.gkpk.block.tile.TileEntityFermenter;
import HxCKDMS.gkpk.data.recipe.GKPKRecipe;
import HxCKDMS.gkpk.event.GEventHandler;
import HxCKDMS.gkpk.items.ItemEthanol;
import HxCKDMS.gkpk.items.ItemExtract;
import cpw.mods.fml.common.registry.GameRegistry;
import hxckdms.hxccore.utilities.Logger;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;

public class Registry {
    public BlockExtractor blockextractor = new BlockExtractor();
    public ItemEthanol itemEthanol = new ItemEthanol();
    public ItemExtract itemExtract = new ItemExtract();
    public BlockFermenter fermenter = new BlockFermenter();

    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new GEventHandler());
       // GameRegistry.addRecipe(new ItemStack(blockextractor), "opo", "pep", "opo", 'e', Items.ender_eye, 'o', Blocks.obsidian, 'p', Items.ender_pearl);
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
        GKPKRecipe.Extracting().registerExtractRecipe(Items.poisonous_potato, "solan");

        Configs.init();

        Logger.info("There has been " + Configs.drugs.size() + " drugs added", "GKPK");
        Logger.info("There has been " + GKPKRecipe.Extracting().getExtractList().size() + " Extractor recipes initialized!", "GKPK");
    }
}
