package mod.learn.items;

import net.minecraft.item.Item;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;

public class ItemSemiTransparent extends Item {

    public ItemSemiTransparent() {
        this.setTextureName(LearnRef.MODID + ":semi_transperant_item");
        this.setUnlocalizedName("semi.transperant");
        this.setCreativeTab(LearnTabs.tabItems);
    }
}
