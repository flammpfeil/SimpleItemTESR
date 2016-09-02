package item_tesr;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import item_tesr.core.CoreProxyClient;
import net.minecraft.client.renderer.block.model.BuiltInModel;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

/**
 * Created by Furia on 2016/02/07.
 */
public class ModelBakeEventHandler {
    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event){

        {//simple
            IBakedModel bakedModel = event.getModelRegistry().getObject(CoreProxyClient.simpleLoc);

            Map<ItemCameraTransforms.TransformType, TRSRTransformation> transform = Maps.newHashMap();

            if(bakedModel instanceof IPerspectiveAwareModel){
                for(ItemCameraTransforms.TransformType type : ItemCameraTransforms.TransformType.values()){
                    transform.put(type, new TRSRTransformation(((IPerspectiveAwareModel) bakedModel).handlePerspective(type).getValue()));
                }
            }

            bakedModel = new IPerspectiveAwareModel.MapWrapper(
                    new BuiltInModel( bakedModel.getItemCameraTransforms(), bakedModel.getOverrides()),
                    ImmutableMap.copyOf(transform));

            event.getModelRegistry().putObject(CoreProxyClient.simpleLoc, bakedModel);
        }

        {//full state
            IBakedModel bakedModel = event.getModelRegistry().getObject(CoreProxyClient.exLoc);

            Map<ItemCameraTransforms.TransformType, TRSRTransformation> transform = Maps.newHashMap();

            if(bakedModel instanceof IPerspectiveAwareModel){
                for(ItemCameraTransforms.TransformType type : ItemCameraTransforms.TransformType.values()){
                    transform.put(type, new TRSRTransformation(((IPerspectiveAwareModel) bakedModel).handlePerspective(type).getValue()));
                }
            }

            bakedModel = new IPerspectiveAwareModel.MapWrapper(
                    new BuiltInModel( bakedModel.getItemCameraTransforms(), CoreProxyClient.tesrEx.stateHandler),
                    ImmutableMap.copyOf(transform));

            event.getModelRegistry().putObject(CoreProxyClient.exLoc, bakedModel);
        }
    }
}
