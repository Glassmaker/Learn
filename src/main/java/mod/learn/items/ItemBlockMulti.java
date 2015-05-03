package mod.learn.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mod.learn.misc.LearnRef;

public class ItemBlockMulti extends ItemBlock {

    public ItemBlockMulti(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String colourName = LearnRef.TYPES[stack.getItemDamage() % LearnRef.TYPES.length];
        return this.field_150939_a.getUnlocalizedName() + "." + colourName;
    }

}
