package mod.learn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;
import mod.learn.tileentities.TileEntityHouse;

public class BlockHouse extends BlockContainer {

    public BlockHouse() {
        super(Material.rock);
        this.setCreativeTab(LearnTabs.tabBlocks);
        this.setBlockName(LearnRef.MODID + ".house");
        this.setBlockTextureName("stone");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityHouse();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isNormalCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

}
