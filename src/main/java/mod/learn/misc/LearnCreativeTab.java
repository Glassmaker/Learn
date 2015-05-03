package mod.learn.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LearnCreativeTab extends CreativeTabs {

    private ItemStack tabStack = null;

    public LearnCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        if (tabStack == null || tabStack.getItem() == null)
            return new ItemStack(Items.apple);

        return tabStack.copy();
    }

    @Override
    public Item getTabIconItem() {
        if (tabStack == null || tabStack.getItem() == null)
            return Items.apple;

        return tabStack.getItem();
    }

    public void setTabStack(ItemStack stack) {
        this.tabStack = stack;
    }

}
