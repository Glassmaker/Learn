package mod.learn;

import net.minecraft.item.ItemStack;

import mod.learn.misc.LearnCreativeTab;
import mod.learn.misc.LearnRef;

public class LearnTabs {

    public static LearnCreativeTab tabItems = new LearnCreativeTab(LearnRef.MODID + ".items");
    public static LearnCreativeTab tabBlocks = new LearnCreativeTab(LearnRef.MODID + ".blocks");

    public static void init() {
        tabItems.setTabStack(new ItemStack(LearnItems.gem));
        tabBlocks.setTabStack(new ItemStack(LearnBlocks.ore));
    }
}
