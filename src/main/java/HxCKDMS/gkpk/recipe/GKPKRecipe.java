package HxCKDMS.gkpk.recipe;
import HxCKDMS.gkpk.GKPK;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class GKPKRecipe {
    private static final GKPKRecipe ExtracterBase = new GKPKRecipe();
    private HashMap<ItemStack, String> ExtractList = new HashMap<>();

    {
        ExtractList.put(new ItemStack(Blocks.tallgrass, 1, 2), "weed");
    }

    public static GKPKRecipe Extracting(){
        return ExtracterBase;
    }

    public Map getExtractList(){
        return ExtractList;
    }

    public void registerExtractRecipe(ItemStack input, String drug) {
        ExtractList.put(input, drug);
    }

    public ItemStack getExtractingResult(ItemStack input) {
        ItemStack stack = new ItemStack(GKPK.itemExtract);
        stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound tag = stack.getTagCompound();
        tag.setString("drugEffect", ExtractList.get(input));
        stack.setTagCompound(tag);
        return stack;
    }
}
