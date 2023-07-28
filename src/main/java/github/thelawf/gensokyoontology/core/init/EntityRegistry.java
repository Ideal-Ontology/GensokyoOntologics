package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.*;
import github.thelawf.gensokyoontology.common.entity.projectile.*;
// import github.thelawf.gensokyoontology.common.entity.spellcard.IdonokaihoEntity;
import github.thelawf.gensokyoontology.common.entity.spellcard.*;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, GensokyoOntology.MODID);

    // ================================= 被动生物 ================================= //
    public static final RegistryObject<EntityType<HumanResidentEntity>> HUMAN_RESIDENT_ENTITY = ENTITIES.register(
            "human_resident", () -> HumanResidentEntity.HUMAN_RESIDENT);

    public static final RegistryObject<EntityType<CitizenEntity>> CITIZEN = ENTITIES.register(
            "citizen", () -> CitizenEntity.CITIZEN);

    // ================================ 怪物 ==================================== //
    public static final RegistryObject<EntityType<InyoJadeMonsterEntity>> INYO_JADE_ENTITY = ENTITIES.register(
            "inyo_jade", () -> InyoJadeMonsterEntity.INYO_JADE_MONSTER);

    public static final RegistryObject<EntityType<FairyEntity>> FAIRY_ENTITY = ENTITIES.register(
            "fairy", () -> FairyEntity.FAIRY);

    public static final RegistryObject<EntityType<SpectreEntity>> SPECTRE_ENTITY = ENTITIES.register(
            "spectre", () -> SpectreEntity.SPECTRE);

    // =============================== 可驯服的生物 ============================ //
    // public static final RegistryObject<EntityType<KoishiEntity>> KOISHI_ENTITY = ENTITIES.register(
    //         "komeiji_koishi", () -> KoishiEntity.KOISHI);
    // public static final RegistryObject<EntityType<SumirekoEntity>> SUMIREKO_ENTITY = ENTITIES.register(
    //         "usami_sumireko", () -> SumirekoEntity.SUMIREKO);
    // public static final RegistryObject<EntityType<YukariEntity>> YUKARI_ENTITY = ENTITIES.register(
    //         "yakumo_yukari", () -> YukariEntity.YUKARI);

    // =========================== 技术性实体：弹幕 ========================= //
    public static final RegistryObject<EntityType<DanmakuShotEntity>> DANMAKU_ENTITY = ENTITIES.register(
            "danmaku_shot", () -> DanmakuShotEntity.DANMAKU);
    public static final RegistryObject<EntityType<HeartShotEntity>> HERAT_SHOT_ENTITY = ENTITIES.register(
            "heart_shot", () -> HeartShotEntity.HEART_SHOT);
    public static final RegistryObject<EntityType<LargeShotEntity>> LARGE_SHOT_ENTITY = ENTITIES.register(
            "large_shot", () -> LargeShotEntity.LARGE_SHOT);
    public static final RegistryObject<EntityType<SmallShotEntity>> SMALL_SHOT_ENTITY = ENTITIES.register(
            "small_shot", () -> SmallShotEntity.SMALL_SHOT);
    public static final RegistryObject<EntityType<StarShotEntity>> STAR_SHOT_SMALL_ENTITY = ENTITIES.register(
            "star_shot_small", () -> StarShotEntity.STAR_SHOT_SMALL);
    public static final RegistryObject<EntityType<StarShotEntity>> STAR_SHOT_LARGE_ENTITY = ENTITIES.register(
            "start_shot_large", () -> StarShotEntity.STAR_SHOT_LARGE);
    public static final RegistryObject<EntityType<FakeLunarEntity>> FAKE_LUNAR_ENTITY = ENTITIES.register(
            "fake_lunar", () -> FakeLunarEntity.FAKE_LUNAR);

    // ================================ 不知道是什么的实体 ================================= //
    // public static final RegistryObject<EntityType<MasterSparkEntity>> MASTER_SPARK_ENTITY = ENTITIES.register(
    //         "master_spark", () -> MasterSparkEntity.MASTER_SPARK);
    // public static final RegistryObject<EntityType<NamespaceDomain>> NAMESPACE_DOMAIN = ENTITIES.register(
    //         "namespace_domain", () -> NamespaceDomain.NAMESPACE_DOMAIN);

    // ============================ 技术性实体：符卡 ============================= //
    public static final RegistryObject<EntityType<WaveAndParticleEntity>> WAVE_AND_PARTICLE_ENTITY = ENTITIES.register(
            "wave_and_particle", () -> WaveAndParticleEntity.WAVE_AND_PARTICLE);

    public static final RegistryObject<EntityType<IdonokaihoEntity>> IDO_NO_KAIHO_ENTITY =
            ENTITIES.register("ido_no_kaiho", () -> IdonokaihoEntity.IDONOKAIHO_ENTITY);

    public static final RegistryObject<EntityType<CircleCrossEntity>> CIRCLE_CROSS_ENTITY =
            ENTITIES.register("circle_cross", () -> CircleCrossEntity.CIRCLE_CROSS_ENTITY);

    public static final RegistryObject<EntityType<HellEclipseEntity>> HELL_ECLIPSE_ENTITY =
            ENTITIES.register("hell_eclipse", () -> HellEclipseEntity.HELL_ECLIPSE_ENTITY);

    public static final RegistryObject<EntityType<MountainOfFaithEntity>> MOUNTAIN_OF_FAITH_ENTITY =
            ENTITIES.register("mountain_of_faith", () -> MountainOfFaithEntity.MOUNTAIN_OF_FAITH_ENTITY);

    // public static final RegistryObject<EntityType<FlyingSwordEntity>> FLY_SWORD_ENTITY = GSKO_ENTITIES.register(
   //         "flying_sword", () -> FlyingSwordEntity.FLY_SWORD_TYPE);

   // public static final RegistryObject<EntityType<PhantasmSphereEntity>> PH_SPHERE_ENTITY = GSKO_ENTITIES.register(
   //         "phantasm_sphere", () -> PhantasmSphereEntity.PH_SPHERE_TYPE);
}


