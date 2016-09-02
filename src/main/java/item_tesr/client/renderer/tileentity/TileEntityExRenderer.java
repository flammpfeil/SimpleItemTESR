package item_tesr.client.renderer.tileentity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Map;

/**
 * Created by Furia on 2016/09/02.
 */
public class TileEntityExRenderer extends TileEntitySpecialRenderer {

    public ItemOverrideList stateHandler;

    ItemStack _stack;
    World _world;
    EntityLivingBase _entity;


    public TileEntityExRenderer(){
        stateHandler = new ItemOverrideList(ImmutableList.<ItemOverride>of()){
            @Override
            public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity) {
                _stack = stack;
                _world = world;
                _entity = entity;
                return super.handleItemState(originalModel, stack, world, entity);
            }
        };
    }

    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("textures/entity/chest/normal.png");
    private static final ResourceLocation ENDER_CHEST_TEXTURE = new ResourceLocation("textures/entity/chest/ender.png");

    static Map<Integer,ResourceLocation> texture = ImmutableMap.of(
            0, TEXTURE_NORMAL,
            1, ENDER_CHEST_TEXTURE);

    private ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntity tes, double x, double y, double z, float partialTicks, int destroyStage) {

        this.bindTexture(texture.get(_stack.getMetadata()));

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        //GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);

        if(tes != null) //ワールド設置時は、ブロックの角が0座標となるように
            GlStateManager.translate(0.5F, 0.5F, 0.5F);

        /* 参照できるよ
        _stack @Nonnull
        _world @Nullable
        _entity @Nullable
        */

        //ブロックの向き補正
        GlStateManager.rotate(180, 0, 0, 1);
        GlStateManager.rotate(180, 0, 1, 0);

        //拡大の中心(0座標）をブロックの中心へ位置あわせ
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);



        //open
        this.modelChest.chestLid.rotateAngleX = -(float)(Math.PI / 180F) * 30.0f;
        this.modelChest.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        //super.renderTileEntityAt(tes, x, y, z, partialTicks, destroyStage);
    }
}
