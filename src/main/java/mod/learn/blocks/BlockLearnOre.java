package mod.learn.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.learn.LearnItems;
import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;

public class BlockLearnOre extends BlockBase {

    private Random random = new Random();

    public BlockLearnOre() {
        this.setBlockTextureName(LearnRef.MODID + ":base_ore");
        this.setBackgroundTextureName(LearnRef.MODID + ":base_ore_filling");
        this.setBlockName(LearnRef.MODID + ".ore");
        this.setCreativeTab(LearnTabs.tabBlocks);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        int qtyDropped = quantityDropped(random);

        if (fortune < 1)
            return qtyDropped;

        int dropModifier = random.nextInt(fortune + 2) - 1;
        if (dropModifier < 0)
            dropModifier = 0;

        return qtyDropped * (dropModifier + 1);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {

        int qty = quantityDropped(metadata, fortune, world.rand);

        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(LearnItems.gem, qty, metadata));

        return drops;
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

    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {

        return random.nextInt(5) + 1;
    }
}
