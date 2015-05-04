package mod.learn.blocks;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mod.learn.misc.LearnRef;

public class BlockLearnDeco extends BlockBase {

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < LearnRef.COLOURS.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBgColorMultiplier(int meta) {
        return LearnRef.COLOURS[meta % LearnRef.COLOURS.length];
    }
}
