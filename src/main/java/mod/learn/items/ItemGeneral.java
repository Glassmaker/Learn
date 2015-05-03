package mod.learn.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;

public class ItemGeneral extends Item {

    private IIcon[] icons = new IIcon[LearnRef.TYPES.length];
    private String itemType = "";

    public ItemGeneral(String type) {
        this.itemType = type;
        this.setCreativeTab(LearnTabs.tabItems);
        this.setTextureName(LearnRef.MODID + ":" + itemType + "_" + LearnRef.TYPES[0]);
        this.setUnlocalizedName(LearnRef.MODID + "." + itemType + "." + LearnRef.TYPES[0]);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() >= LearnRef.TYPES.length || stack.getItemDamage() < 0)
            return getUnlocalizedName();

        return "item." + LearnRef.MODID + "." + itemType + "." + LearnRef.TYPES[stack.getItemDamage()];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < LearnRef.TYPES.length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        for (int i = 0; i < LearnRef.TYPES.length; i++) {
            icons[i] =
                    register.registerIcon(LearnRef.MODID + ":" + itemType + "_" + LearnRef.TYPES[i]);
        }

        super.registerIcons(register);
    }

    @Override
    public IIcon getIconFromDamage(int damage) {

        if (damage >= LearnRef.TYPES.length || damage < 0)
            return super.getIconFromDamage(damage);

        return icons[damage];
    }
}
