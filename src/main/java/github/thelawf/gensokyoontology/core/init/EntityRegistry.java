package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.HakureiReimuEntity;
import github.thelawf.gensokyoontology.common.entity.HaniwaEntity;
import github.thelawf.gensokyoontology.common.entity.misc.DreamSealEntity;
import github.thelawf.gensokyoontology.common.entity.monster.KomeijiKoishiEntity;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.entity.monster.*;
import github.thelawf.gensokyoontology.common.entity.passive.CitizenEntity;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.*;
// import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;

public final class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, GensokyoOntology.MODID);

    // ================================= 被动生物 ================================= //

    public static final RegistryObject<EntityType<HumanResidentEntity>> HUMAN_RESIDENT_ENTITY = ENTITIES.register(
            "human_resident", () -> EntityType.Builder.create(HumanResidentEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.55f, 1.8f).trackingRange(10).build("human_resident"));

    // ================================ 怪物 ==================================== //
    public static final RegistryObject<EntityType<InyoJadeMonsterEntity>> INYO_JADE_ENTITY = ENTITIES.register(
            "inyo_jade", () -> EntityType.Builder.create(InyoJadeMonsterEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.8f, 0.8f).trackingRange(10).build("inyo_jade_monster"));
    public static final RegistryObject<EntityType<FairyEntity>> FAIRY_ENTITY = ENTITIES.register(
            "fairy", () -> EntityType.Builder.create(FairyEntity::new, EntityClassification.MONSTER)
                    .updateInterval(2).size(0.6f, 1.5f).trackingRange(10).build("fairy"));
    public static final RegistryObject<EntityType<SunflowerFairyEntity>> SUNFLOWER_FAIRY_ENTITY = ENTITIES.register(
            "sunflower_fairy", () -> EntityType.Builder.create(SunflowerFairyEntity::new, EntityClassification.MONSTER)
                    .updateInterval(2).size(0.6f, 1.5f).trackingRange(10).build("sunflower_fairy"));
    public static final RegistryObject<EntityType<SpectreEntity>> SPECTRE_ENTITY = ENTITIES.register(
            "spectre", () -> EntityType.Builder.create(SpectreEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.65f, 0.65f).trackingRange(10).build("spectre"));
    public static final RegistryObject<EntityType<TsumiBukuroEntity>> TSUMI_BUKURO_ENTITY = ENTITIES.register(
            "tsumi_bukuro", () -> EntityType.Builder.create(TsumiBukuroEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.6f, 1.8f).trackingRange(10).build("tsumi_bukuro"));
    public static final RegistryObject<EntityType<LilyWhiteEntity>> LILY_WHITE_ENTITY = ENTITIES.register(
            "lily_white", () -> EntityType.Builder.create(LilyWhiteEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.6f, 1.5f).trackingRange(10).build("lily_white"));
    public static final RegistryObject<EntityType<FlandreScarletEntity>> FLANDRE_SCARLET = ENTITIES.register(
            "flandre_scarlet", () -> EntityType.Builder.create(FlandreScarletEntity::new, EntityClassification.CREATURE)
                    .setShouldReceiveVelocityUpdates(true).size(0.6f, 1.55f).trackingRange(10).build("flandre_scarlet"));
    public static final RegistryObject<EntityType<FlandreScarletEntity.Doppelganger>> FLANDRE_DOPPELDANGER = ENTITIES.register(
            "flandre_doppelganger", () -> EntityType.Builder.create(FlandreScarletEntity.Doppelganger::new, EntityClassification.CREATURE)
                    .setShouldReceiveVelocityUpdates(true).size(0.6f, 1.55f).trackingRange(10).build("flandre_doppelganger"));
    public static final RegistryObject<EntityType<RemiliaScarletEntity>> REMILIA_SCARLET = ENTITIES.register(
            "remilia_scarlet", () -> EntityType.Builder.create(RemiliaScarletEntity::new, EntityClassification.CREATURE)
                    .setShouldReceiveVelocityUpdates(true).size(0.6f, 1.58f).trackingRange(10).build("remilia_scarlet"));

    // =============================== 可驯服的生物 ============================ //
    public static final RegistryObject<EntityType<HakureiReimuEntity>> HAKUREI_REIMU = ENTITIES.register(
            "hakurei_reimu", () -> EntityType.Builder.create(HakureiReimuEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.6f, 1.5f).trackingRange(10).build("hakurei_reimu"));
    public static final RegistryObject<EntityType<KomeijiKoishiEntity>> KOMEIJI_KOISHI = ENTITIES.register(
            "komeiji_koishi", () -> EntityType.Builder.create(KomeijiKoishiEntity::new, EntityClassification.CREATURE)
                    .updateInterval(2).size(0.6f, 1.5f).trackingRange(10).build("komeiji_koishi"));

    // public static final RegistryObject<EntityType<FlandreScarletEntity>> SUMIREKO_ENTITY = ENTITIES.register(
    //         "usami_sumireko", () -> FlandreScarletEntity.SUMIREKO);
    // public static final RegistryObject<EntityType<YukariEntity>> YUKARI_ENTITY = ENTITIES.register(
    //         "yakumo_yukari", () -> YukariEntity.YUKARI);
    public static final RegistryObject<EntityType<HaniwaEntity>> HANIWA = ENTITIES.register("haniwa",
            () -> EntityType.Builder.create(HaniwaEntity::new, EntityClassification.CREATURE).updateInterval(10)
                    .size(0.6f, 0.8f).trackingRange(20).build("haniwa"));

    // =========================== 技术性实体：弹幕 ========================= //
    public static final RegistryObject<EntityType<DanmakuShotEntity>> DANMAKU_ENTITY = ENTITIES.register(
            "danmaku_shot", () -> EntityType.Builder.<DanmakuShotEntity>create(DanmakuShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("danmaku_shot"));
    public static final RegistryObject<EntityType<HeartShotEntity>> HEART_SHOT_ENTITY = ENTITIES.register(
            "heart_shot", () -> EntityType.Builder.<HeartShotEntity>create(HeartShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("heart_shot"));
    public static final RegistryObject<EntityType<LargeShotEntity>> LARGE_SHOT_ENTITY = ENTITIES.register(
            "large_shot", () -> EntityType.Builder.<LargeShotEntity>create(LargeShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("large_shot"));
    public static final RegistryObject<EntityType<SmallShotEntity>> SMALL_SHOT_ENTITY = ENTITIES.register(
            "small_shot", () -> EntityType.Builder.<SmallShotEntity>create(SmallShotEntity::new, EntityClassification.MISC)
                    .size(0.3F, 0.3F).trackingRange(4).updateInterval(2).build("small_shot"));
    public static final RegistryObject<EntityType<SmallStarShotEntity>> STAR_SHOT_SMALL_ENTITY = ENTITIES.register(
            "star_shot_small", () -> EntityType.Builder.<SmallStarShotEntity>create(SmallStarShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("star_shot_small"));
    public static final RegistryObject<EntityType<LargeStarShotEntity>> STAR_SHOT_LARGE_ENTITY = ENTITIES.register(
            "start_shot_large", () -> EntityType.Builder.<LargeStarShotEntity>create(LargeStarShotEntity::new, EntityClassification.MISC)
                    .size(2.8F, 2.8F).trackingRange(4).updateInterval(2).build("star_shot_large"));
    public static final RegistryObject<EntityType<RiceShotEntity>> RICE_SHOT_ENTITY = ENTITIES.register(
            "rice_shot", () -> EntityType.Builder.<RiceShotEntity>create(RiceShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("rice_shot"));
    public static final RegistryObject<EntityType<ScaleShotEntity>> SCALE_SHOT_ENTITY = ENTITIES.register(
            "scale_shot", () -> EntityType.Builder.<ScaleShotEntity>create(ScaleShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("scale_shot"));
    public static final RegistryObject<EntityType<TalismanShotEntity>> TALISMAN_SHOT_ENTITY = ENTITIES.register(
            "talisman_shot", () -> EntityType.Builder.<TalismanShotEntity>create(TalismanShotEntity::new, EntityClassification.MISC)
                    .size(0.5F, 0.5F).trackingRange(4).updateInterval(2).build("talisman_shot"));
    public static final RegistryObject<EntityType<FakeLunarEntity>> FAKE_LUNAR_ENTITY = ENTITIES.register(
            "fake_lunar", () -> EntityType.Builder.<FakeLunarEntity>create(FakeLunarEntity::new, EntityClassification.MISC).size(2.8F, 2.8F)
                    .trackingRange(4).updateInterval(2).build("fake_lunar"));
    public static final RegistryObject<EntityType<InYoJadeDanmakuEntity>> INYO_JADE_DANMAKU = ENTITIES.register(
            "inyo_jade_danmaku", () -> EntityType.Builder.<InYoJadeDanmakuEntity>create(InYoJadeDanmakuEntity::new, EntityClassification.MISC)
                    .size(4F, 4F).trackingRange(4).updateInterval(2).build("inyo_jade_entity")
    );

    // ================================ 不知道是什么的实体 ================================= //

    // public static final RegistryObject<EntityType<MasterSparkEntity>> MASTER_SPARK_ENTITY = ENTITIES.register(
    //         "master_spark", () -> MasterSparkEntity.MASTER_SPARK);
    // public static final RegistryObject<EntityType<NamespaceDomain>> NAMESPACE_DOMAIN = ENTITIES.register(
    //         "namespace_domain", () -> NamespaceDomain.NAMESPACE_DOMAIN);
    public static final RegistryObject<EntityType<DreamSealEntity>> DREAM_SEAL_ENTITY = ENTITIES.register(
            "dream_seal", () -> EntityType.Builder.<DreamSealEntity>create(DreamSealEntity::new, EntityClassification.MISC)
                    .size(2F, 2F).trackingRange(80).updateInterval(2).build("dream_seal"));

    // ============================ 技术性实体：符卡以及特殊技能 ============================= //
    public static final RegistryObject<EntityType<WaveAndParticleEntity>> WAVE_AND_PARTICLE_ENTITY = ENTITIES.register(
            "wave_and_particle", () -> EntityType.Builder.<WaveAndParticleEntity>create(WaveAndParticleEntity::new,
                            EntityClassification.MISC).size(1F, 1F).trackingRange(4)
                    .updateInterval(2).build("wave_and_particle"));

    public static final RegistryObject<EntityType<IdonokaihoEntity>> IDO_NO_KAIHO_ENTITY = ENTITIES.register(
            "ido_no_kaiho", () -> EntityType.Builder.<IdonokaihoEntity>create((entityTypeIn, worldIn) -> {
                                try {
                                    return new IdonokaihoEntity(entityTypeIn, worldIn);
                                } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                                         IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            },
                            EntityClassification.MISC).size(1F, 1F).trackingRange(4)
                    .updateInterval(2).build("ido_no_kaiho"));
    public static final RegistryObject<EntityType<SpiralWheelEntity>> SPIRAL_WHEEL_ENTITY = ENTITIES.register(
            "spiral_wheel", () -> EntityType.Builder.<SpiralWheelEntity>create(SpiralWheelEntity::new,
                    EntityClassification.MISC).size(1F, 1F).trackingRange(4).updateInterval(2).build("spiral_wheel"));

    // 这个符卡的注册名是不是写错了
    // 已修改
    public static final RegistryObject<EntityType<HellEclipseEntity>> HELL_ECLIPSE_ENTITY =
            ENTITIES.register("hell_eclipse", () -> EntityType.Builder.<HellEclipseEntity>create(HellEclipseEntity::new, EntityClassification.MISC)
                    .size(1F, 1F).trackingRange(4).updateInterval(2).build("hell_eclipse"));
    public static final RegistryObject<EntityType<MountainOfFaithEntity>> MOUNTAIN_OF_FAITH_ENTITY =
            ENTITIES.register("mountain_of_faith", () -> EntityType.Builder.<MountainOfFaithEntity>create(MountainOfFaithEntity::new,
                            EntityClassification.MISC).size(1F, 1F).trackingRange(4)
                    .updateInterval(2).build("mountain_of_faith"));
    public static final RegistryObject<EntityType<MobiusRingEntity>> MOBIUS_RING_WORLD_ENTITY =
            ENTITIES.register("mobius_ring_world", () -> EntityType.Builder.<MobiusRingEntity>create(MobiusRingEntity::new,
                            EntityClassification.MISC).size(1F, 1F).trackingRange(4)
                    .updateInterval(2).build("mobius_ring_world"));
    public static final RegistryObject<EntityType<FullCherryBlossomEntity>> FULL_CHERRY_BLOSSOM_ENTITY =
            ENTITIES.register("full_cherry_blossom", () -> EntityType.Builder.<FullCherryBlossomEntity>create(FullCherryBlossomEntity::new, EntityClassification.MISC)
                    .size(1F, 1F).trackingRange(4).updateInterval(2).build("full_cherry_blossom"));
    public static final RegistryObject<EntityType<HanaShigureSpellEntity>> HANA_SHIGURE_ENTITY =
            ENTITIES.register("hana_shigure", () -> EntityType.Builder.<HanaShigureSpellEntity>create(HanaShigureSpellEntity::new,
                    EntityClassification.MISC).size(1F, 1F).trackingRange(4)
                    .updateInterval(2).build("hana_shigure"));
    public static final RegistryObject<EntityType<ManiaDepressEntity>> MANIA_DEPRESS_ENTITY =
            ENTITIES.register("mania_depress", () -> EntityType.Builder.<ManiaDepressEntity>create(ManiaDepressEntity::new, EntityClassification.MISC)
                    .size(1F, 1F).trackingRange(4).updateInterval(2).build("mania_depress"));
    public static final RegistryObject<EntityType<ScarletPrisoner>> SCARLET_PRISONER_ENTITY =
            ENTITIES.register("scarlet_prisoner", () -> EntityType.Builder.<ScarletPrisoner>create(ScarletPrisoner::new,
                    EntityClassification.MISC).size(1F,1F).trackingRange(4).updateInterval(2).build("scarlet_prisoner"));
    public static final RegistryObject<EntityType<GalacticArmSpellEntity>> GALACTIC_ARM_SPELL_ENTITY =
            ENTITIES.register("galactic_arm_spell", () -> EntityType.Builder.<GalacticArmSpellEntity>create((entityTypeIn, worldIn) -> {
                        try {
                            return new GalacticArmSpellEntity(entityTypeIn, worldIn);
                        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                                 IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    EntityClassification.MISC).size(1F,1F).trackingRange(4).updateInterval(2).build("galactic_arm_spell"));
    public static final RegistryObject<EntityType<ScriptedSpellCardEntity>> SCRIPTED_SPELL_CARD_ENTITY =
            ENTITIES.register("scripted_spell_card", () -> EntityType.Builder.<ScriptedSpellCardEntity>create(ScriptedSpellCardEntity::new,
                    EntityClassification.MISC).size(1F,1F).trackingRange(4).updateInterval(2).build("scripted_spell_card"));

    // ============================================= 特殊能力实体 ====================================================//
    public static final RegistryObject<EntityType<LaserSourceEntity>> LASER_SOURCE_ENTITY = ENTITIES.register(
            "laser_source", () -> EntityType.Builder.<LaserSourceEntity>create(LaserSourceEntity::new,
                    EntityClassification.MISC).size(1F, 1F).trackingRange(4).updateInterval(2).build("laser_source"));
    public static final RegistryObject<EntityType<DestructiveEyeEntity>> DESTRUCTIVE_EYE_ENTITY =
            ENTITIES.register("destructive_eye", () -> EntityType.Builder.<DestructiveEyeEntity>create(DestructiveEyeEntity::new, EntityClassification.MISC)
                    .size(3F, 3F). trackingRange(10).updateInterval(2).build("destructive_eye"));

    // public static final RegistryObject<EntityType<FlyingSwordEntity>> FLY_SWORD_ENTITY = GSKO_ENTITIES.register(
    //         "flying_sword", () -> FlyingSwordEntity.FLY_SWORD_TYPE);

    // public static final RegistryObject<EntityType<PhantasmSphereEntity>> PH_SPHERE_ENTITY = GSKO_ENTITIES.register(
    //         "phantasm_sphere", () -> PhantasmSphereEntity.PH_SPHERE_TYPE);
}


