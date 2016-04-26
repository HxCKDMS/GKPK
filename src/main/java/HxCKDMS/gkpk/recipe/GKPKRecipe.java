package HxCKDMS.gkpk.recipe;

import HxCKDMS.gkpk.GKPK;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class GKPKRecipe {
    private static final GKPKRecipe ExtracterBase = new GKPKRecipe();
    private HashMap<Item, String> ExtractList = new HashMap<>();

    {
        ExtractList.put(new ItemStack(Blocks.brown_mushroom).getItem(), "weed");
        ExtractList.put(new ItemStack(Items.baked_potato).getItem(), "weed");//lel baked
    }

    public static GKPKRecipe Extracting(){
        return ExtracterBase;
    }

    public Map getExtractList(){
        return ExtractList;
    }

    public void registerExtractRecipe(Item input, String drug) {
        ExtractList.put(input, drug);
    }

    public ItemStack getExtractingResult(ItemStack input) {
        if (ExtractList.keySet().contains(input.getItem())) {
            ItemStack stack = new ItemStack(GKPK.itemExtract, 1);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("drugEffect", ExtractList.get(input.getItem()));
            stack.setTagCompound(tag);
            return stack;
        } else {
            return null;
        }
    }
}
