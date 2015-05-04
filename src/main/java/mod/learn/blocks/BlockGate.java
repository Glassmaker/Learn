package mod.learn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import mod.learn.LearnTabs;
import mod.learn.misc.LearnRef;
import mod.learn.tileentities.TileEntityGate;

public class BlockGate extends BlockContainer {

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

}
