package HxCKDMS.gkpk.data;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.util.ShaderMan;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class Drug {
    private String name = "errorDefaultName", fx = "blur";
    private int RGB = 0x000000, shaderTimer, damage = 0;
    private PotionEffect[] pots = new PotionEffect[]{};

    public Drug (String name, int RGB, String shader, int shaderTime, int dmg, PotionEffect... pots) {
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
        if (damage > 0) {
            player.attackEntityFrom(new DamageSource(name), damage);
        } else if (damage < 0) {
            player.heal(-damage);
        }
    }

    public int getRGB() {
        return RGB;
    }

    public int getShaderTimer() {
        return shaderTimer;
    }

    public String getFx() {
        return fx;
    }

    public int getDamage() {
        return damage;
    }

    public PotionEffect[] getPots() {
        return pots;
    }

    public String getName() {
        return name;
    }
}
