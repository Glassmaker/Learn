package mod.learn.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import mod.learn.blocks.BlockBase;
import mod.learn.proxy.ClientProxy;

public class RendererDoubleBlock implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        Float lighting = 1.0F;
        Tessellator tessellator = Tessellator.instance;
        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        // drawing background texture
        int bgColor = ((BlockBase) block).getBgColorMultiplier(metadata);
        float bgRed = (bgColor >> 16 & 255) / 255.0F;
        float bgGreen = (bgColor >> 8 & 255) / 255.0F;
        float bgBlue = (bgColor & 255) / 255.0F;

        GL11.glColor4f(bgRed, bgGreen, bgBlue, 1.0F);
        IIcon backgroundIcon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, backgroundIcon);
        tessellator.draw();
        GL11.glColor4f(1.0F * lighting, 1.0F * lighting, 1.0F * lighting, 1.0F);

        // outer texture
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();

        if (renderer.useInventoryTint) {
            int color = block.getRenderColor(metadata);
            float red = (color >> 16 & 255) / 255.0F;
            float green = (color >> 8 & 255) / 255.0F;
            float blue = (color & 255) / 255.0F;
            GL11.glColor4f(red * lighting, green * lighting, blue * lighting, 1.0F);
        }

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();

        if (renderer.useInventoryTint) {
            GL11.glColor4f(lighting, lighting, lighting, 1.0F);
        }

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D,
                renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();


        GL11.glTranslatef(0.5F, 0.5F, 0.5F);

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block,
            int modelId, RenderBlocks renderer) {

        int color = block.colorMultiplier(world, x, y, z);
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;

        int bgColor = ((BlockBase) block).getBgColorMultiplier(world, x, y, z);
        float bgRed = (bgColor >> 16 & 255) / 255.0F;
        float bgGreen = (bgColor >> 8 & 255) / 255.0F;
        float bgBlue = (bgColor & 255) / 255.0F;

        renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5F;
        float f4 = 1.0F;
        float f5 = 0.8F;
        float f6 = 0.6F;
        float f7 = f4 * red;
        float f8 = f4 * green;
        float f9 = f4 * blue;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;

        f10 = f3 * red;
        f11 = f5 * red;
        f12 = f6 * red;
        f13 = f3 * green;
        f14 = f5 * green;
        f15 = f6 * green;
        f16 = f3 * blue;
        f17 = f5 * blue;
        f18 = f6 * blue;

        int l = block.getMixedBrightnessForBlock(world, x, y, z);
        int bgBrightnes = 15728880;

        IIcon icon;

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceYNeg(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMinY > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));

            tessellator.setColorOpaque_F(f10, f13, f16);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 0);
            renderer.renderFaceYNeg(block, x, y, z, icon);
            flag = true;
        }

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceYPos(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMaxY < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));

            tessellator.setColorOpaque_F(f7, f8, f9);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 1);
            renderer.renderFaceYPos(block, x, y, z, icon);
            flag = true;
        }

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceZNeg(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMinZ > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));

            tessellator.setColorOpaque_F(f11, f14, f17);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 2);
            renderer.renderFaceZNeg(block, x, y, z, icon);
            flag = true;
        }

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceZPos(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMaxZ < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));

            tessellator.setColorOpaque_F(f11, f14, f17);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 3);
            renderer.renderFaceZPos(block, x, y, z, icon);

            flag = true;
        }

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceXNeg(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMinX > 0.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));

            tessellator.setColorOpaque_F(f12, f15, f18);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 4);
            renderer.renderFaceXNeg(block, x, y, z, icon);

            flag = true;
        }

        if (renderer.renderAllFaces
                || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5)) {

            tessellator.setBrightness(bgBrightnes);

            tessellator.setColorOpaque_F(bgRed, bgGreen, bgBlue);
            icon = renderer.getIconSafe(((BlockBase) block).getBackgroundIcon());
            renderer.renderFaceXPos(block, x, y, z, icon);

            tessellator.setBrightness(renderer.renderMaxX < 1.0D ? l : block
                    .getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));

            tessellator.setColorOpaque_F(f12, f15, f18);
            icon = renderer.getBlockIcon(block, renderer.blockAccess, x, y, z, 5);
            renderer.renderFaceXPos(block, x, y, z, icon);

            flag = true;
        }

        return flag;

    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return ClientProxy.doubleBlockRenderId;
    }

}
