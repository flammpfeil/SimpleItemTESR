package item_tesr.core;

import net.minecraftforge.fml.common.SidedProxy;

public class CoreProxy {
	@SidedProxy(clientSide = "item_tesr.core.CoreProxyClient", serverSide = "item_tesr.core.CoreProxy")
	public static CoreProxy proxy;

	public void initializeItemRenderer() {}

}
