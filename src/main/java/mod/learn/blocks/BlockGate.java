package mod.learn.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity,
            ItemStack stack) {
        int direction = BlockPistonBase.determineOrientation(world, x, y, z, entity);

        TileEntityGate gate = (TileEntityGate) world.getTileEntity(x, y, z);

        gate.setFacing(ForgeDirection.getOrientation(direction));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGate();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {

        iconBack = register.registerIcon(LearnRef.MODID + ":gate_back");
        iconSide = register.registerIcon(LearnRef.MODID + ":gate_side");

        super.registerBlockIcons(register); // registers blockIcon
    }

    @Override
    @SideOnly(Side.CLIENT)
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

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {

        TileEntityGate gate = (TileEntityGate) access.getTileEntity(x, y, z);

        ForgeDirection front = gate.getFacing();

        if (side == front.ordinal())
            return blockIcon;

        if (side == front.getOpposite().ordinal())
            return iconBack;

        return iconSide;
    }

}
