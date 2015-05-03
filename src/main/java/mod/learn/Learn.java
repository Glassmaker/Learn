package mod.learn;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import mod.learn.misc.LearnRef;
import mod.learn.proxy.IProxy;
import mod.learn.utils.Debug;


@Mod(modid = LearnRef.MODID, name = LearnRef.MODNAME, version = LearnRef.MODVER,
        acceptedMinecraftVersions = LearnRef.MC_VER)
public class Learn {

    @Mod.Instance(LearnRef.MODID)
    public static Learn instance;

    @SidedProxy(modId = LearnRef.MODID, clientSide = "mod.learn.proxy.ClientProxy",
            serverSide = "mod.learn.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Debug.setLogger(event.getModLog());

        LearnItems.init();
        LearnBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LearnTabs.init();

        proxy.registerBlockRenderers();
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
