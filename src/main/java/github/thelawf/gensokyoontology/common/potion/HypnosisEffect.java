package github.thelawf.gensokyoontology.common.potion;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

public class HypnosisEffect extends Effect {

    public static final ResourceLocation HYPNOSIS_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "effect/hypnosis_effect");

    public HypnosisEffect() {
        super(EffectType.NEUTRAL, 0);
    }

    @Override
    public void performEffect(@NotNull LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if (entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
            serverWorld.setDayTime(13000);
            player.setPose(Pose.SLEEPING);
            player.startSleeping(player.getPosition());
        }
    }

}
