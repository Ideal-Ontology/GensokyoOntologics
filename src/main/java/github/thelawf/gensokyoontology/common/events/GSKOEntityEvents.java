package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.common.block.HotSpringBlock;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import github.thelawf.gensokyoontology.common.potion.HypnosisEffect;
import github.thelawf.gensokyoontology.common.potion.LovePotionEffect;
import github.thelawf.gensokyoontology.core.init.FluidRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.client.particle.TotemOfUndyingParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "gensokyoontology",bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GSKOEntityEvents {
    @SubscribeEvent
    public static void onHotSpringIn(LivingEvent.LivingUpdateEvent event){
        if (event.getEntityLiving() != null && event.getEntityLiving().isInWater()) {
            BlockState blockState = event.getEntityLiving().getBlockState();
            if (blockState.getBlockState().getBlock() instanceof HotSpringBlock &&
                    !(event.getEntityLiving() instanceof IMob)) {
                event.getEntityLiving().heal(1.2F);
            }
        }

    }

    @SubscribeEvent
    public static void onWineIn(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() != null && event.getEntityLiving().isInWater()) {
            BlockState blockState = event.getEntityLiving().getBlockState();
            if (blockState.getBlockState().getBlock() instanceof HotSpringBlock &&
                    !(event.getEntityLiving() instanceof PlayerEntity)) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.NAUSEA));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() != null && event.getEntityLiving() instanceof FairyEntity) {
            FairyEntity fairy = (FairyEntity) event.getEntityLiving();
            if (event.getSource() == DamageSource.WITHER) {
                fairy.entityDropItem(new ItemStack(ItemRegistry.LARGE_SHOT_RED.get()));
            }
        }
        else if (event.getEntityLiving() != null && event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            IInventory inventory = player.inventory;


            // 循环获取玩家物品栏每个物品，如果玩家持有残机点数则恢复玩家生命值, 然后让玩家原地复活
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (stack.getItem().equals(ItemRegistry.EXTRA_LIFE_ITEM.get())) {
                    player.heal(20f);
                    event.getEntityLiving().getEntityWorld().setEntityState(player, (byte) 2);
                }
            }

        }
    }

    public static void onPotionActivate(LivingEvent.LivingUpdateEvent event) {
        event.getEntityLiving().getActivePotionEffects().forEach(effect -> {
            if (effect.getPotion() instanceof HypnosisEffect) {
                performHypnosis(event, (HypnosisEffect) effect.getPotion());
            }
        });
    }

    private static void performHypnosis(LivingEvent.LivingUpdateEvent event, HypnosisEffect effect) {

        if (event.getEntityLiving() == null) return;

        if (event.getEntityLiving() instanceof ServerPlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            if (player.isPotionActive(effect)) {
                ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
                serverWorld.setDayTime(13000);
                player.setPose(Pose.SLEEPING);
                player.startSleeping(player.getPosition());
            }
        } else if (event.getEntityLiving() instanceof VillagerEntity) {
            VillagerEntity villager = (VillagerEntity) event.getEntityLiving();
            if (villager.isPotionActive(effect)) {
                ServerWorld serverWorld = (ServerWorld) villager.getEntityWorld();
                serverWorld.setDayTime(13000);
                villager.setPose(Pose.SLEEPING);
                villager.startSleeping(villager.getPosition());
            }
        }
    }

    private static void performLovePotion(LivingEvent.LivingUpdateEvent event, LovePotionEffect effect) {

    }
}
