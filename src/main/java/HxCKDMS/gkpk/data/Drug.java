package HxCKDMS.gkpk.data;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.util.ShaderMan;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

public class Drug {
    public String name = "errorDefaultName";
    public int RGB = 0x000000;
    public String fx = "blur";
    public int shaderTimer;
    public PotionEffect[] pots = new PotionEffect[]{};
    public Drug (String name, int RGB, String shader, int shaderTime, PotionEffect... pots) {
        this.name = name;
        this.RGB = RGB;
        if (!shader.isEmpty())
            this.fx = shader;
        this.shaderTimer = shaderTime;
        this.pots = pots;
    }

    public void activateDrug(EntityPlayerMP player) {
        if (pots.length > 0)
            for (int i = 0; i < pots.length; i++)
                player.addPotionEffect(pots[i]);
        if (shaderTimer != 0) {
            ShaderMan.DoShader(shaderTimer, player, fx);
            GKPK.playersWithShaders.put(player, shaderTimer);
        }
    }
}
