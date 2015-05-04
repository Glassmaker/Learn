package mod.learn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;
import mod.learn.tileentities.TileEntityGate;

public class BlockGate extends BlockContainer {

    // Will be using blockIcon for the front;
    protected IIcon iconBack;
    protected IIcon iconSide;

    public BlockGate() {
        super(Material.iron);
        this.setBlockTextureName(LearnRef.MODID + ":gate_front");
        this.setBlockName(LearnRef.MODID + ".gate");
        this.setCreativeTab(LearnTabs.tabBlocks);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGate();
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {

        iconBack = register.registerIcon(LearnRef.MODID + ":gate_back");
        iconSide = register.registerIcon(LearnRef.MODID + ":gate_side");

        super.registerBlockIcons(register); // registers blockIcon
    }

    @Override
    public IIcon getIcon(int side, int meta) {

        switch (side) {
            case 3:
                return blockIcon; // front
            case 2:
                return iconBack;
            default:
                return iconSide;
        }
    }

}
