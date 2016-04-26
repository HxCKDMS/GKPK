package HxCKDMS.gkpk.data;
import HxCKDMS.gkpk.util.ShaderMan;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DrugData {
    public static void doEffect(EnumDrug drug, EntityPlayerMP ply) {
        World world = ply.worldObj;
        switch (drug) {
            case weed:
                ShaderMan.DoShader(TimeConst.TICKS_PER_MINUTE * 3, ply, "blur");
                ply.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 4, 1));
                ply.addPotionEffect(new PotionEffect(Potion.confusion.getId(), TimeConst.TICKS_PER_MINUTE * 4,1));
                ply.addPotionEffect(new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_SECOND * 15,10));
                ply.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), TimeConst.TICKS_PER_SECOND * 15,4));
                break;
            case caff:
                ply.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), TimeConst.TICKS_PER_MINUTE * 5,1));
                ply.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), TimeConst.TICKS_PER_MINUTE * 5,1));
                break;
            case t2:
                // Set shader Desaturate for 3 min
                ply.addPotionEffect(new PotionEffect(Potion.wither.getId(), TimeConst.TICKS_PER_MINUTE * 4,2));
                break;
            case ttx:
                ShaderMan.DoShader(TimeConst.TICKS_PER_MINUTE * 3, ply, "blur");
                ply.addPotionEffect(new PotionEffect(Potion.wither.getId(), TimeConst.TICKS_PER_MINUTE * 4,1));
                ply.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 4,50));
                break;
            case lsd: // We do the random shader thing for this
                ply.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 4,3));
                ply.addPotionEffect(new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_SECOND * 15,10));
                break;
            case psilocybin:
                ply.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), TimeConst.TICKS_PER_MINUTE * 5,2));
                ply.addPotionEffect(new PotionEffect(Potion.hunger.getId(), TimeConst.TICKS_PER_SECOND * 9,10));
                // Set shader art for 7 min
                break;
            case muscimol:
                ShaderMan.DoShader(TimeConst.TICKS_PER_MINUTE * 4, ply, "sobel");
                ply.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), TimeConst.TICKS_PER_MINUTE * 6,1));
                ply.addPotionEffect(new PotionEffect(Potion.weakness.getId(), TimeConst.TICKS_PER_MINUTE * 6,8));
                ply.addPotionEffect(new PotionEffect(Potion.confusion.getId(), TimeConst.TICKS_PER_MINUTE * 6,1));
                break;
            default:
                if (!world.isRemote) {System.out.println("THIS SHOULD NOT HAPPEN");}
                break;
        }
    }
}
