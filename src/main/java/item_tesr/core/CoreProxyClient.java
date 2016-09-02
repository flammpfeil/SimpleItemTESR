package item_tesr.core;


import com.google.common.collect.ImmutableMap;
import item_tesr.ModelBakeEventHandler;
import item_tesr.SimpleItemTESR;
import item_tesr.client.renderer.tileentity.TileEntityExRenderer;
import item_tesr.client.renderer.tileentity.TileEntityTestRenderer;
import item_tesr.tileentity.DummyTileEntity;
import item_tesr.tileentity.DummyTileEntityB;
import item_tesr.tileentity.DummyTileEntityC;
import item_tesr.tileentity.DummyTileEntityD;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.Map;

public class CoreProxyClient extends CoreProxy {

    Map<Integer,Class<? extends TileEntity>> keymap = ImmutableMap.of(
            0, DummyTileEntity.class,
            1, DummyTileEntityB.class);
    Map<Integer,Class<? extends TileEntity>> keymapEx = ImmutableMap.of(
            0, DummyTileEntityC.class,
            1, DummyTileEntityD.class);

    public static ModelResourceLocation simpleLoc;
    public static ModelResourceLocation exLoc;

    public static TileEntityExRenderer tesrEx;

	@Override
	public void initializeItemRenderer() {

        simpleLoc = new ModelResourceLocation(SimpleItemTESR.testitem.getRegistryName(),"inventory");
        for(Map.Entry<Integer,Class<? extends TileEntity>> key : keymap.entrySet())
        {
            int meta = key.getKey();
            Class tileClass = key.getValue();
            ModelLoader.setCustomModelResourceLocation(SimpleItemTESR.testitem, meta, simpleLoc);
            ForgeHooksClient.registerTESRItemStack(SimpleItemTESR.testitem, meta, tileClass);
            ClientRegistry.bindTileEntitySpecialRenderer(tileClass, new TileEntityTestRenderer(meta));
        }

        exLoc = new ModelResourceLocation(SimpleItemTESR.exitem.getRegistryName(),"inventory");
        tesrEx = new TileEntityExRenderer();
        for(Map.Entry<Integer,Class<? extends TileEntity>> key : keymapEx.entrySet())
        {
            int meta = key.getKey();
            Class tileClass = key.getValue();
            ModelLoader.setCustomModelResourceLocation(SimpleItemTESR.exitem, meta, exLoc);
            ForgeHooksClient.registerTESRItemStack(SimpleItemTESR.exitem, meta, tileClass);
            ClientRegistry.bindTileEntitySpecialRenderer(tileClass, tesrEx);
        }

        MinecraftForge.EVENT_BUS.register(new ModelBakeEventHandler());
    }
}
