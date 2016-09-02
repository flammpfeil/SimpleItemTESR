package item_tesr.core;


import com.google.common.collect.ImmutableMap;
import item_tesr.ModelBakeEventHandler;
import item_tesr.SimpleItemTESR;
import item_tesr.client.renderer.tileentity.TileEntityTestRenderer;
import item_tesr.tileentity.DummyTileEntity;
import item_tesr.tileentity.DummyTileEntityB;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.Map;

public class CoreProxyClient extends CoreProxy {

    Map<Integer,Class<? extends TileEntity>> keymap = ImmutableMap.of(
            0, DummyTileEntity.class,
            1, DummyTileEntityB.class);

    public static ModelResourceLocation loc;

	@Override
	public void initializeItemRenderer() {

        loc = new ModelResourceLocation(SimpleItemTESR.testitem.getRegistryName(),"inventory");

        for(Map.Entry<Integer,Class<? extends TileEntity>> key : keymap.entrySet())
        {
            int meta = key.getKey();
            Class tileClass = key.getValue();
            ModelLoader.setCustomModelResourceLocation(SimpleItemTESR.testitem, meta, loc);
            ForgeHooksClient.registerTESRItemStack(SimpleItemTESR.testitem, meta, tileClass);
            ClientRegistry.bindTileEntitySpecialRenderer(tileClass, new TileEntityTestRenderer(meta));
        }

        MinecraftForge.EVENT_BUS.register(new ModelBakeEventHandler());
    }
}
