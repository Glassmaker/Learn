package mod.learn.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;

import mod.learn.client.render.RendererDoubleBlock;

public class ClientProxy implements IProxy {

    public static int doubleBlockRenderId = 0;

    @Override
    public void registerBlockRenderers() {
        ClientProxy.doubleBlockRenderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerBlockHandler(doubleBlockRenderId, new RendererDoubleBlock());
    }

}
