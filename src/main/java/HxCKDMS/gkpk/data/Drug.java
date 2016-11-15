package HxCKDMS.gkpk.data;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.util.ShaderMan;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class Drug {
    public String name = "errorDefaultName", fx = "blur";
    public int RGB = 0x000000, shaderTimer, damage = 0;
    public PotionEffect[] pots = new PotionEffect[]{};

    public Drug () {

    }

    public Drug (String name, int RGB, String shader, int shaderTime, int dmg, dummyPot... pots) {
        this.name = name;
        this.RGB = RGB;
        if (!shader.isEmpty())
            this.fx = shader;
        this.shaderTimer = shaderTime;
        this.pots = new PotionEffect[pots.length];
        for (int i = 0; i < pots.length; i++) {
            this.pots[i] = pots[i].getPot();
        }

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

    public static class dummyPot {
        int id, length, amplifier;
        boolean ambient;
        public dummyPot () {

        }

        public dummyPot (int id, int len, int amp) {
            this.id = id;
            this.length = len;
            this.amplifier = amp;
            this.ambient = false;
        }

        public dummyPot (int id, int len, int amp, boolean amb) {
            this.id = id;
            this.length = len;
            this.amplifier = amp;
            this.ambient = amb;
        }

        public PotionEffect getPot () {
            return new PotionEffect(id, length, amplifier, ambient);
        }
    }
}
