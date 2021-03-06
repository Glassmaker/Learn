package mod.learn;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

import mod.learn.items.ItemGeneral;
import mod.learn.items.ItemSemiTransparent;

public class LearnItems {

    public static Item gem;
    public static Item ingot;
    public static Item semiTransparent;

    public static void init() {
        LearnItems.gem = new ItemGeneral("gem");
        LearnItems.ingot = new ItemGeneral("ingot");
        LearnItems.semiTransparent = new ItemSemiTransparent();

        GameRegistry.registerItem(LearnItems.gem, "gem");
        GameRegistry.registerItem(LearnItems.ingot, "ingot");
        GameRegistry.registerItem(LearnItems.semiTransparent, "semi.transparent");
    }
}
