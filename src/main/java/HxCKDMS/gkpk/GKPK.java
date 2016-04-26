package HxCKDMS.gkpk;

import HxCKDMS.HxCCore.network.PacketPipeline;
import HxCKDMS.gkpk.block.BlockExtractor;
import HxCKDMS.gkpk.block.BlockFermenter;
import HxCKDMS.gkpk.data.Drug;
import HxCKDMS.gkpk.data.TimeConst;
import HxCKDMS.gkpk.event.GEventHandler;
import HxCKDMS.gkpk.items.ItemEthanol;
import HxCKDMS.gkpk.items.ItemExtract;
import HxCKDMS.gkpk.items.ItemPharmacyBook;
import HxCKDMS.gkpk.network.PacketShader;
import HxCKDMS.gkpk.proxy.IProxy;
import HxCKDMS.gkpk.recipe.GKPKRecipe;
import HxCKDMS.gkpk.tile.TileEntityFermenter;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;

@Mod (
        modid = "GKPK",
        name = "The Gordian Knot Pharmacy Kit",
        version = "0.01"

)

public class GKPK {
    public static PacketPipeline network = new PacketPipeline();
    @Mod.Instance("GKPK")
    public static GKPK instance;

    @SidedProxy(clientSide = "HxCKDMS.gkpk.proxy.ClientProxy", serverSide = "HxCKDMS.gkpk.proxy.ServerProxy")
    public static IProxy proxy;

    public static HashMap<String, Drug> drugs = new HashMap<String, Drug>();

    public static BlockExtractor blockextractor = new BlockExtractor();
    public static ItemEthanol itemEthanol = new ItemEthanol();
    public static ItemExtract itemExtract = new ItemExtract();
    public static BlockFermenter fermenter = new BlockFermenter();
    public static ItemPharmacyBook pbook = new ItemPharmacyBook();

    public static GKPKRecipe recipes = new GKPKRecipe();

    public static HashMap<EntityPlayerMP, Integer> playersWithShaders = new HashMap<>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(blockextractor, "gExtractor"); // Extractor
        GameRegistry.registerItem(itemEthanol, "gEthanol"); // Ethanol
        GameRegistry.registerItem(itemExtract, "gExtractItem"); // Metadata Item
        GameRegistry.registerBlock(fermenter, "gFermenter"); // Fermenter
        GameRegistry.registerTileEntity(TileEntityFermenter.class, "gkpktefermenter"); // TE for fermenter
        // GameRegistry.registerItem(pbook, "gPharmBook"); // wip book shit
        //NewRegistering System
        drugs.put("weed", new Drug("weed", 0x00FF00, "blur", 60, new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 4, 1), new PotionEffect(Potion.confusion.getId(), TimeConst.TICKS_PER_MINUTE * 4,1), new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_SECOND * 15,10), new PotionEffect(Potion.regeneration.getId(), TimeConst.TICKS_PER_SECOND * 15,4)));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        registerPackets();
        network.initialize("GKPK");
        MinecraftForge.EVENT_BUS.register(new GEventHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    network.postInitialize();
    }

    private void registerPackets(){
    network.addPacket(PacketShader.class);
    }
}
