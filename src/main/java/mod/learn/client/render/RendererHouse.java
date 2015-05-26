package mod.learn.client.render;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import mod.learn.misc.LearnRef;
import mod.learn.tileentities.TileEntityHouse;

public class RendererHouse extends TileEntitySpecialRenderer {

    private ResourceLocation texture = new ResourceLocation(LearnRef.MODID
            + ":textures/models/house.png");
    private ResourceLocation modelLocation = new ResourceLocation(LearnRef.MODID
            + ":models/house.obj");
    private IModelCustom model = AdvancedModelLoader.loadModel(modelLocation);

    private void renderTileEntityAt(TileEntityHouse house, double x, double y, double z,
            float renderTick) {


        int combinedLight =
                house.getWorldObj().getLightBrightnessForSkyBlocks((int) x, (int) y, (int) z, 0);
        int k = combinedLight % 65536;
        int l = combinedLight / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0F, l / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);


        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();


    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z,
            float renderTick) {
        this.renderTileEntityAt((TileEntityHouse) tileEntity, x, y, z, renderTick);
    }

}
