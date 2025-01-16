package github.thelawf.gensokyoontology.common.events;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.nature.HotSpringBlock;
import github.thelawf.gensokyoontology.common.capability.entity.BeliefCapability;
import github.thelawf.gensokyoontology.common.capability.entity.GSKOPowerCapability;
import github.thelawf.gensokyoontology.common.capability.entity.SecularLifeCapability;
import github.thelawf.gensokyoontology.common.capability.world.BloodyMistCapability;
import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.world.ImperishableNightCapability;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CPowerChangedPacket;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.potion.HypnosisEffect;
import github.thelawf.gensokyoontology.common.potion.LovePotionEffect;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.GSKOSoundEvents;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.EffectRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = "gensokyoontology")
public class GSKOEntityEvents {

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)event.getEntity();
            player.getCapability(GSKOCapabilities.POWER).ifPresent(gskoCap -> {
                GSKONetworking.sendToClientPlayer(new CPowerChangedPacket(gskoCap.getCount()), player);
                GSKOPowerCapability.INSTANCE = gskoCap;
            });
            player.getCapability(GSKOCapabilities.SECULAR_LIFE).ifPresent(SecularLifeCapability::markDirty);
            player.getCapability(GSKOCapabilities.BELIEF).ifPresent(belief -> BeliefCapability.INSTANCE = belief);
        }
    }

    @SubscribeEvent
    public static void onPlayerLife(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntityLiving() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        if (player.getEntityWorld() instanceof ServerWorld) {
            World world = player.getEntityWorld();
            LazyOptional<SecularLifeCapability> cap = player.getCapability(GSKOCapabilities.SECULAR_LIFE);
            cap.ifPresent((capability -> {
                if (world.getGameTime() % 5 == 0) {
                    capability.addTime(1);
                }
                if (capability.getLifetime() == 45_000L) GSKOUtil.showChatMsg(player, "You Feel yourself have no time to live",1);
                if (capability.getLifetime() == 300L) player.setHealth(0);
            }));
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {

    }


    @SubscribeEvent
    public static void onHotSpringIn(LivingEvent.LivingUpdateEvent event) {
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
            if (blockState.getBlockState().getBlock().equals(BlockRegistry.SAKE_WINE_BLOCK.get()) &&
                    event.getEntityLiving() instanceof PlayerEntity) {
                event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.NAUSEA, 2 * 100));
            }
        }
    }

    @SubscribeEvent
    public static void onEnterNamelessHill(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() != null) {
            LivingEntity living = event.getEntityLiving();
            ResourceLocation biomeName = living.world.getBiome(living.getPosition()).getRegistryName();
            if (biomeName == null) return;
            if (!(living instanceof PlayerEntity)) return;
            if (living.getActivePotionEffects().contains(new EffectInstance(
                    EffectRegistry.HAKUREI_BLESS_EFFECT.get()))) return;

            PlayerEntity player = (PlayerEntity) living;
            if (biomeName.equals(GSKOBiomes.NAMELESS_HILL_KEY.getLocation())) {
                living.addPotionEffect(new EffectInstance(Effects.POISON, 2 * 20));
                player.sendStatusMessage(GensokyoOntology.fromLocaleKey(
                        "msg.", ".enter_danger_biome.nameless_hill"), true);
            }
        }
    }

    @SubscribeEvent
    public static void onAdvancementDone(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.player;
            PlayerAdvancements advancement = serverPlayer.getAdvancements();
            if (serverPlayer.world.getServer() == null) return;

            AdvancementManager manager = serverPlayer.world.getServer().getAdvancementManager();
            ResourceLocation location = new ResourceLocation(GensokyoOntology.MODID, "gensokyo_traveller");

            if (manager.getAdvancement(location) == null) return;
            AdvancementProgress progress = advancement.getProgress(manager.getAdvancement(location));
        }
    }

    @SubscribeEvent
    public static void onLivingEnterBiome(TickEvent.PlayerTickEvent event) {
        if (event.player.getEntityWorld() instanceof ServerWorld && event.player instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.player;
            ServerWorld serverWorld = (ServerWorld) event.player.world;
            ResourceLocation location = serverWorld.getBiome(player.getPosition()).getRegistryName();

            if (serverWorld.getBiome(player.getPosition()).getRegistryName() == GSKOBiomes.NAMELESS_HILL_KEY.getRegistryName()) {
                player.addPotionEffect(new EffectInstance(Effects.POISON, 2 * 50));
            }

            boolean precondition = player.ticksExisted % 20 == 0 && location != null && !player.isPotionActive(EffectRegistry.HAKUREI_BLESS_EFFECT.get());

            LazyOptional<BloodyMistCapability> cap = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
            cap.ifPresent((capability -> {
                List<String> biomes = capability.getBiomeRegistryNames();
                biomes.forEach((biomeRegistryName -> {
                    if (precondition && Objects.equals(location.toString(), biomeRegistryName) && capability.isTriggered()) {
                        player.sendStatusMessage(GensokyoOntology.fromLocaleKey(
                                "msg.", ".enter_danger_biome.scarlet_mansion_precincts"), true);
                        player.attackEntityFrom(DamageSource.IN_WALL, 1f);
                    }
                }));
            }));
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() == null) return;
        if (!(event.getEntityLiving().getEntityWorld() instanceof ServerWorld)) return;

        fairyDropDanmaku(event);
        dropLunarDanmaku(event.getEntityLiving(), (ServerWorld) event.getEntityLiving().world);

        if (event.getEntityLiving() instanceof PlayerEntity) {
            GSKOPowerCapability.INSTANCE.add(-1);
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            World world = player.getEntityWorld();
            PlayerInventory inventory = player.inventory;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                if (inventory.getStackInSlot(i).getItem() == ItemRegistry.EXTEND_ITEM.get()) {
                    player.setHealth(20F);
                    world.setEntityState(player, (byte) 35);
                    inventory.getStackInSlot(i).shrink(1);
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public static void playBGMToPlayer(TickEvent.PlayerTickEvent event) {
        if (event.player.getServer() == null) return;

        ServerWorld serverWorld = event.player.getServer().getWorld(GSKODimensions.GENSOKYO);
        if (serverWorld == null) return;
        if (event.player.getEntityWorld().getDimensionKey().equals(GSKODimensions.GENSOKYO) &&
                serverWorld.getGameTime() % GSKOMathUtil.randomRange(200, 1000) == 0) {
            event.player.playSound(GSKOSoundEvents.CICADA_AMBIENT.get(), 0.3f, 1f);
        }
    }

    public static void onHakureiBless(LivingEvent.LivingUpdateEvent event) {
        //if (event.getEntityLiving().getActivePotionEffect(EffectRegistry.HAKUREI_BLESS_EFFECT.get()) == null) return;
        EffectInstance effect = event.getEntityLiving().getActivePotionEffect(EffectRegistry.HAKUREI_BLESS_EFFECT.get());
        if (effect == null) return;
        World world = event.getEntity().world;
        LivingEntity living = event.getEntityLiving();
        if (effect.getDuration() > 0 && world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            LazyOptional<BloodyMistCapability> bloodyMist = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
            bloodyMist.ifPresent(capability -> capability.setTriggered(false));
        } else if (effect.getDuration() <= 0 && world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            LazyOptional<BloodyMistCapability> bloodyMist = serverWorld.getCapability(GSKOCapabilities.BLOODY_MIST);
            bloodyMist.ifPresent(capability -> capability.setTriggered(true));
        }
    }

    private static void fairyDropDanmaku(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof FairyEntity) {
            FairyEntity fairy = (FairyEntity) event.getEntityLiving();
            Random random = new Random();
            if (event.getSource() == GSKODamageSource.DANMAKU) {
                if (random.nextInt(100) < 8) {
                    // fairy.entityDropItem(new ItemStack(random.nextInt() % 2 == 0 ? ItemRegistry.LIFE_FRAGMENT.get() : ItemRegistry.BOMB_FRAGMENT.get()));
                    fairy.entityDropItem(new ItemStack(ItemRegistry.LIFE_FRAGMENT.get()));
                }
                for (int i = 0; i < random.nextInt(3); i++) {
                    List<Item> danmakuItems = DanmakuUtil.getAllDanmakuItem();
                    fairy.entityDropItem(danmakuItems.get(random.nextInt(danmakuItems.size())));
                }
            }
        }
    }

    private static void dropLunarDanmaku(LivingEntity living, ServerWorld serverWorld) {
        if (GSKOWorldUtil.isEntityInDimension(living, GSKODimensions.GENSOKYO)) {
            LazyOptional<ImperishableNightCapability> capability = serverWorld.getCapability(GSKOCapabilities.IMPERISHABLE_NIGHT);
            capability.ifPresent(cap -> {
                Random random = new Random();
                if (random.nextInt(100) < 23) {
                    living.entityDropItem(new ItemStack(ItemRegistry.FAKE_LUNAR_ITEM.get()));
                }
            });
        }
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
