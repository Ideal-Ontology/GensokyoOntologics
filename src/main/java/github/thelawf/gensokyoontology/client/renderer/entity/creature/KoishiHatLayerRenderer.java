package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.generators.loaders.OBJLoaderBuilder;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class KoishiHatLayerRenderer extends LayerRenderer<PlayerEntity, PlayerModel<PlayerEntity>> {
    private final ItemRenderer itemRenderer;
    public KoishiHatLayerRenderer(IEntityRenderer entityRendererIn, ItemRenderer itemRenderer) {
        super(entityRendererIn);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, PlayerEntity playerIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!playerIn.isInvisible()) {
            ItemStack helmetStack = playerIn.getItemStackFromSlot(EquipmentSlotType.HEAD);
            if (!helmetStack.isEmpty() && helmetStack.getItem().equals(ItemRegistry.KOISHI_HAT.get())) {
                // 获取玩家头部位置
                Minecraft mc = Minecraft.getInstance();
                EntityRenderer<?> renderer = mc.getRenderManager().getRenderer(playerIn);
                this.itemRenderer.renderItem(new ItemStack(ItemRegistry.KOISHI_HAT.get()), ItemCameraTransforms.TransformType.HEAD,
                        packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            }
        }
    }
}
