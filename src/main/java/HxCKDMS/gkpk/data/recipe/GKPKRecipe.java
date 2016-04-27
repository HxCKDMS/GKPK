package HxCKDMS.gkpk.data.recipe;

import HxCKDMS.gkpk.GKPK;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class GKPKRecipe {
    private static final GKPKRecipe ExtracterBase = new GKPKRecipe();
    private HashMap<extractRecipe, String> ExtractList = new HashMap<>();

    private class extractRecipe {
        Item inputA;
        int meta;
        extractRecipe(Item input, int meta) {
            this.inputA = input;
            this.meta = meta;
        }
    }

    public static GKPKRecipe Extracting(){
        return ExtracterBase;
    }

    public Map getExtractList(){
        return ExtractList;
    }

    public void registerExtractRecipe(Item input, String drug) {
        ExtractList.put(new extractRecipe(input, 0), drug);
    }

    public void registerExtractRecipe(Item input, int meta, String drug) {
        ExtractList.put(new extractRecipe(input, meta), drug);
    }

    public ItemStack getExtractingResult(ItemStack input) {
        final boolean[] recipeFound = {false};
        final String[] drg = {""};
        GKPKRecipe.Extracting().ExtractList.forEach((a, b) -> {
            if (a.inputA.getUnlocalizedName().equals(input.getItem().getUnlocalizedName())) {
                recipeFound[0] = true;
                drg[0] = b;
            }
        });

        if (recipeFound[0]) {
            ItemStack stack = new ItemStack(GKPK.registry.itemExtract, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("drugEffect", drg[0]);
            stack.setTagCompound(tag);
            return stack;
        } else {
            return null;
        }
    }
}
