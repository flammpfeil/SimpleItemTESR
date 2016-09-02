package item_tesr;

import item_tesr.core.CoreProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

@Mod(name = SimpleItemTESR.modname, modid = SimpleItemTESR.modid, version = SimpleItemTESR.version)
public class SimpleItemTESR {
	public static final String modname = "SimpleItemTESR";
    public static final String modid = "itemtesr";
    public static final String version = "@VERSION@";

    static public Item testitem;

    @Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
        testitem = (new Item(){
            @Override
            public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
                super.getSubItems(itemIn, tab, subItems);
                subItems.add(new ItemStack(itemIn,1,1));

                //meta range(0,1)
            }
        })
                .setHasSubtypes(true)
                .setUnlocalizedName(modid + ".test")
                .setCreativeTab(CreativeTabs.MISC)
                .setRegistryName("test");

        GameRegistry.register(testitem);

        CoreProxy.proxy.initializeItemRenderer();
    }
}
