package mod.learn;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

import mod.learn.blocks.BlockGate;
import mod.learn.blocks.BlockLearnDeco;
import mod.learn.blocks.BlockLearnOre;
import mod.learn.items.ItemBlockMulti;
import mod.learn.misc.LearnRef;
import mod.learn.tileentities.TileEntityGate;

public class LearnBlocks {

    public static Block bricksBig;
    public static Block bricksSmall;
    public static Block creeper;
    public static Block guard;
    public static Block layer;
    public static Block linesBig;
    public static Block linesSmall;
    public static Block ore;
    public static Block protection;
    public static Block shell;
    public static Block stand;
    public static Block wither;

    public static Block gate;

    public static void init() {
        LearnBlocks.bricksBig =
                new BlockLearnDeco().setBlockName("bricks.big").setBlockTextureName(
                        LearnRef.MODID + ":base_bricks3");
        LearnBlocks.bricksSmall =
                new BlockLearnDeco().setBlockName("bricks.small").setBlockTextureName(
                        LearnRef.MODID + ":base_bricks2");
        LearnBlocks.creeper =
                new BlockLearnDeco().setBlockName("creeper").setBlockTextureName(
                        LearnRef.MODID + ":base_creeper");
        LearnBlocks.guard =
                new BlockLearnDeco().setBlockName("guard").setBlockTextureName(
                        LearnRef.MODID + ":base_guard");
        LearnBlocks.layer =
                new BlockLearnDeco().setBlockName("layer").setBlockTextureName(
                        LearnRef.MODID + ":base_layer");
        LearnBlocks.linesBig =
                new BlockLearnDeco().setBlockName("lines.big").setBlockTextureName(
                        LearnRef.MODID + ":base_lines2");
        LearnBlocks.linesSmall =
                new BlockLearnDeco().setBlockName("lines.small").setBlockTextureName(
                        LearnRef.MODID + ":base_lines3");
        LearnBlocks.ore = new BlockLearnOre();
        LearnBlocks.protection =
                new BlockLearnDeco().setBlockName("protection").setBlockTextureName(
                        LearnRef.MODID + ":base_protection");
        LearnBlocks.shell =
                new BlockLearnDeco().setBlockName("shell").setBlockTextureName(
                        LearnRef.MODID + ":base_shell");
        LearnBlocks.stand =
                new BlockLearnDeco().setBlockName(LearnRef.MODID + ".stand").setBlockTextureName(
                        LearnRef.MODID + ":base_stand");;
        LearnBlocks.wither =
                new BlockLearnDeco().setBlockName(LearnRef.MODID + ".wither").setBlockTextureName(
                        LearnRef.MODID + ":base_wither");

        LearnBlocks.gate = new BlockGate();


        GameRegistry.registerBlock(bricksBig, ItemBlockMulti.class, "bricksBig");
        GameRegistry.registerBlock(bricksSmall, ItemBlockMulti.class, "bricksSmall");
        GameRegistry.registerBlock(creeper, ItemBlockMulti.class, "creeper");
        GameRegistry.registerBlock(guard, ItemBlockMulti.class, "guard");
        GameRegistry.registerBlock(layer, ItemBlockMulti.class, "layer");
        GameRegistry.registerBlock(linesBig, ItemBlockMulti.class, "linesBig");
        GameRegistry.registerBlock(linesSmall, ItemBlockMulti.class, "linesSmall");
        GameRegistry.registerBlock(ore, ItemBlockMulti.class, "ore");
        GameRegistry.registerBlock(protection, ItemBlockMulti.class, "protection");
        GameRegistry.registerBlock(shell, ItemBlockMulti.class, "shell");
        GameRegistry.registerBlock(stand, ItemBlockMulti.class, "stand");
        GameRegistry.registerBlock(wither, ItemBlockMulti.class, "wither");

        GameRegistry.registerBlock(gate, "gate");


        GameRegistry.registerTileEntity(TileEntityGate.class, LearnRef.MODID + ":gate");

    }
}
