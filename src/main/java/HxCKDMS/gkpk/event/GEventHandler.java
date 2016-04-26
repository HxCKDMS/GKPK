package HxCKDMS.gkpk.event;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.network.PacketShader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.EventListener;
import java.util.HashMap;

public class GEventHandler implements EventListener {
    
    public static HashMap<EntityPlayerMP, Integer> PlayerCounter = new HashMap<EntityPlayerMP, Integer>();

    @SubscribeEvent
    public void onServerTick(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
            if (GKPK.playersWithShaders.keySet().contains(player)) {
                if (GKPK.playersWithShaders.get(player) == 0) {
                    GKPK.network.sendTo(new PacketShader(false), player);
                    GKPK.playersWithShaders.remove(player);
                } else {
                    GKPK.playersWithShaders.replace(player, GKPK.playersWithShaders.get(player)-1);
                }
            }
        }
    }

 /*   @SubscribeEvent // End shader effect on player death (NYI)
     public void playerDeath(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer ded = (EntityPlayer) event.entity;
            GKPK.network.sendTo(new PacketShader(false), ded);
        }
    }*/
}
