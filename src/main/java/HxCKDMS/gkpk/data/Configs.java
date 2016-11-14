package HxCKDMS.gkpk.data;

import HxCKDMS.gkpk.data.recipe.GKPKRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import hxckdms.hxcconfig.Config;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.LinkedHashMap;

@Config
public class Configs {
    public static LinkedHashMap<String, Drug> drugs = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> customRecipes = new LinkedHashMap<>();

    static {
        drugs.putIfAbsent("nodu", new Drug("Nodularin", 0xF4F4F4, "", 0, 0, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 4), new PotionEffect(Potion.digSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // From zombie flesh
        drugs.putIfAbsent("ttx", new Drug("Tetrodotoxin", 0xFEFEFE, "desaturate", TimeConst.TICKS_PER_MINUTE, 4, new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE *2, 50), new PotionEffect(Potion.wither.getId(), TimeConst.TICKS_PER_MINUTE, 3))); // From pufferfish
        drugs.putIfAbsent("ethanol", new Drug("Ethanol", 0x000000, "blur", TimeConst.TICKS_PER_MINUTE * 2, 4, new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_MINUTE, 2), new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // internal
        drugs.putIfAbsent("musc", new Drug("Muscimol", 0x30FF30, "sobel", TimeConst.TICKS_PER_MINUTE * 2, 2, new PotionEffect(Potion.damageBoost.getId(), TimeConst.TICKS_PER_MINUTE, 1), new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 2, 2))); // From red mushroom

        customRecipes.putIfAbsent("minecraft:potato:1", "ttx");
    }

    public void init() {
        customRecipes.forEach((in, out) -> {
            String[] t = in.split(":");
            if (t.length == 2)
                GKPKRecipe.Extracting().registerExtractRecipe(GameRegistry.findItem(t[0], t[1]), out);
            else GKPKRecipe.Extracting().registerExtractRecipe(GameRegistry.findItem(t[0], t[1]), Integer.parseInt(t[2]), out);
        });
    }
}
