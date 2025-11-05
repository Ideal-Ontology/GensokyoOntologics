package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.model.CoasterModel;
import github.thelawf.gensokyoontology.common.entity.misc.CoasterVehicle;
import github.thelawf.gensokyoontology.common.entity.misc.RailEntity;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.DerivativeInfo;
import github.thelawf.gensokyoontology.common.util.math.TimeDifferential;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class CoasterRenderer extends EntityRenderer<CoasterVehicle> {
    public static final ResourceLocation COASTER_TEXTURE = GSKOUtil.withRL("textures/entity/coaster_vehicle.png");
    private final CoasterModel model;
    public CoasterRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.model = new CoasterModel();
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(CoasterVehicle entity) {
        return COASTER_TEXTURE;
    }

    @Override
    public void render(CoasterVehicle entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//
//        Optional<Entity> nextRailOpt = entityIn.getPrevRail().flatMap(RailEntity::getTargetRail);
//        if (nextRailOpt.isPresent() && nextRailOpt.get() instanceof RailEntity) {
//            Entity next = nextRailOpt.get();
//            RailEntity nextRail = (RailEntity) next;
//            List<TimeDifferential> integral = entityIn.getIntegralOfDistanceAndTime(nextRail);
//            DerivativeInfo derivative = null;
//            for (TimeDifferential timeDifferential : integral) {
//                if (entityIn.getMotionTicker() >= Math.floor(timeDifferential.timePartial) &&
//                        entityIn.getMotionTicker() + partialTicks < timeDifferential.timePartial) {
//                    derivative = timeDifferential.derivativeInfo;
//                    break;
//                }
//            }

//
//            if (entityIn.getMotionTicker() >= entityIn.get.getDerivatives(end).size() * 3 - 1) return;
//            derivative = start.getDerivatives(end).get(this.getMotionTicker() / 3 + 1);
//            if (derivative == null) return;
//
//            if (entityIn.shouldMove()) {
//                entityIn.setPositionAndUpdate(derivative.position.getX(), derivative.position.getY(), derivative.position.getZ());
//            }
//        }


        matrixStackIn.push();
        matrixStackIn.translate(0.0D, -1.25D, 0.0D);
        this.model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucentCull(COASTER_TEXTURE)),
                packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }
}
