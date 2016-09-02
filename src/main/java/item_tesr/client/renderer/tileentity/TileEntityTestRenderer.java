package item_tesr.client.renderer.tileentity;

import com.google.common.collect.ImmutableMap;
import item_tesr.tileentity.DummyTileEntity;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Map;

/**
 * Created by Furia on 2016/09/02.
 */
public class TileEntityTestRenderer extends TileEntitySpecialRenderer {
    int meta;
    public TileEntityTestRenderer(int meta){
        this.meta = meta;
    }

    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("textures/entity/chest/normal.png");
    private static final ResourceLocation ENDER_CHEST_TEXTURE = new ResourceLocation("textures/entity/chest/ender.png");

    static Map<Integer,ResourceLocation> texture = ImmutableMap.of(
            0, TEXTURE_NORMAL,
            1, ENDER_CHEST_TEXTURE);

    private ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntity tes, double x, double y, double z, float partialTicks, int destroyStage) {



        this.bindTexture(texture.get(meta));

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();


        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        //GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);

        if(tes != null) //ワールド設置時は、ブロックの角が0座標となるように
            GlStateManager.translate(0.5F, 0.5F, 0.5F);

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
