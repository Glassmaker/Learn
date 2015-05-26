package mod.learn.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import mod.learn.client.render.RendererDoubleBlock;
import mod.learn.client.render.RendererHouse;
import mod.learn.tileentities.TileEntityHouse;

public class ClientProxy implements IProxy {

    public static int doubleBlockRenderId = 0;

    @Override
    public void registerBlockRenderers() {
        ClientProxy.doubleBlockRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(doubleBlockRenderId, new RendererDoubleBlock());
    }

    @Override
    public void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHouse.class, new RendererHouse());
    }

}
