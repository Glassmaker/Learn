package mod.learn.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;
import mod.learn.proxy.ClientProxy;

public class BlockBase extends Block {

    private IIcon background;
    private String backgroundName = LearnRef.MODID + ":base_ani";

    public BlockBase() {
        super(Material.rock);
        this.setBlockTextureName(LearnRef.MODID + ":base_ani");
        this.setBlockName(LearnRef.MODID + ".baseBlock");
        this.setCreativeTab(LearnTabs.tabBlocks);
    }

    public BlockBase setBackgroundTextureName(String textureName) {
        backgroundName = textureName;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {

        background = register.registerIcon(backgroundName);

        super.registerBlockIcons(register);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIcon() {
        return background;
    }

    @SideOnly(Side.CLIENT)
    public int getBgColorMultiplier(int meta) {
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    public int getBgColorMultiplier(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        return getBgColorMultiplier(meta);
    }

    @Override
    public int getRenderType() {
        return ClientProxy.doubleBlockRenderId;
    }

}
