package HxCKDMS.gkpk;

import HxCKDMS.gkpk.client.GuiHandler;
import HxCKDMS.gkpk.data.Registry;
import HxCKDMS.gkpk.network.PacketShader;
import HxCKDMS.gkpk.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;

@Mod (
        modid = "GKPK",
        name = "The Gordian Knot Pharmacy Kit",
        version = "0.2"
)

public class GKPK {
    public static SimpleNetworkWrapper network = new SimpleNetworkWrapper("GKPKChan");

    @Mod.Instance("GKPK")
    public static GKPK instance;
    public static Registry registry = new Registry();

    @SidedProxy(clientSide = "HxCKDMS.gkpk.proxy.ClientProxy", serverSide = "HxCKDMS.gkpk.proxy.ServerProxy")
    public static IProxy proxy;

    public static HashMap<EntityPlayerMP, Integer> playersWithShaders = new HashMap<>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registry.preInit();
        network.registerMessage(PacketShader.handler.class, PacketShader.class, 0, Side.CLIENT);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        registry.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registry.postInit();
    }
}
