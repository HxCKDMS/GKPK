package HxCKDMS.gkpk.util;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.event.GEventHandler;
import HxCKDMS.gkpk.network.PacketShader;
import net.minecraft.entity.player.EntityPlayerMP;

public class ShaderMan {
    public static void DoShader(int time, EntityPlayerMP ply, String fx){
        GKPK.network.sendTo(new PacketShader(true,String.format("minecraft:shaders/post/%s.json",fx)),ply);
        GEventHandler.PlayerCounter.put(ply,time);
    }
}
