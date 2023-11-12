package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.nbt.GensokyoOntologyNBT;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import net.minecraft.command.impl.SetIdleTimeoutCommand;
import net.minecraft.command.impl.TimeCommand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.UUID;

@Deprecated
@Mod.EventBusSubscriber(modid = "gensokyoontology", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GSKOEffectEvents {
    @SubscribeEvent
    public static void onLoveEffectActivate(PotionEvent event) {
        // 恋爱效果（迫真）的实现
        LivingEntity living = event.getEntityLiving();
        if (!(living instanceof PlayerEntity) &&
                event.getEntityLiving().isPotionActive(EffectRegistry.LOVE_EFFECT.get())) {
            System.out.println("OK");
        }
        if (living instanceof PlayerEntity) {
            UUID uuid = ((PlayerEntity) living).getGameProfile().getId();
            String name = ((PlayerEntity) living).getGameProfile().getName();
            if (Objects.equals(name, GensokyoOntologyNBT.getLovePotionMasterName().getString())) {
                return;
            }
        }
    }

    public static void onHypnosisEffectActivate(PotionEvent event) {
        // 更改玩家姿态的代码在 PlayerEntity.java的updatePose()方法中实现。
        // 在这个事件中我设置玩家睡觉的姿态，并跳过10000个游戏刻的时间。

        // PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        // if (event.getEntityLiving() instanceof ServerPlayerEntity) {
        //     if (event.getEntityLiving().isPotionActive(EffectRegistry.HYPNOSIS_EFFECT.get())) {
        //         ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
        //         serverWorld.setDayTime(13000);
        //         event.getEntityLiving().setPose(Pose.SLEEPING);
        //         event.getEntityLiving().startSleeping(event.getEntityLiving().getPosition());
        //     }
        // }
    }

    @SubscribeEvent
    public static void onInsomniaEffectActivate(PotionEvent event) {

    }

    @SubscribeEvent
    public static void onManiaEffectActivate(PotionEvent event) {

    }
}
