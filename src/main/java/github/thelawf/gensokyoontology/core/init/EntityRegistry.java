package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.*;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.MasterSparkEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> GSKO_ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, GensokyoOntology.MODID);

    public static final RegistryObject<EntityType<HumanResidentEntity>> HUMAN_RESIDENT_ENTITY = GSKO_ENTITIES.register(
            "human_resident", () -> HumanResidentEntity.HUMAN_RESIDENT);

    public static final RegistryObject<EntityType<FairyEntity>> FAIRY_ENTITY = GSKO_ENTITIES.register(
            "fairy", () -> FairyEntity.FAIRY);

    public static final RegistryObject<EntityType<KoishiEntity>> KOISHI_ENTITY = GSKO_ENTITIES.register(
            "komeiji_koishi", () -> KoishiEntity.KOISHI);

    public static final RegistryObject<EntityType<SumirekoEntity>> SUMIREKO_ENTITY = GSKO_ENTITIES.register(
            "usami_sumireko", () -> SumirekoEntity.SUMIREKO);

    public static final RegistryObject<EntityType<YukariEntity>> YUKARI_ENTITY = GSKO_ENTITIES.register(
            "yakumo_yukari", () -> YukariEntity.YUKARI);

    public static final RegistryObject<EntityType<DanmakuEntity>> DANMAKU_ENTITY = GSKO_ENTITIES.register(
            "danmaku", () -> DanmakuEntity.DANMAKU);

    public static final RegistryObject<EntityType<MasterSparkEntity>> MASTER_SPARK_ENTITY = GSKO_ENTITIES.register(
            "master_spark", () -> MasterSparkEntity.MASTER_SPARK);

    RegistryObject<EntityType<SpellCardEntity>> SPELL_CARD_ENTITY = GSKO_ENTITIES.register(
            "spell_card_entity", () -> SpellCardEntity.SPELL_CARD_ENTITY);

   // public static final RegistryObject<EntityType<FlyingSwordEntity>> FLY_SWORD_ENTITY = GSKO_ENTITIES.register(
   //         "flying_sword", () -> FlyingSwordEntity.FLY_SWORD_TYPE);

   // public static final RegistryObject<EntityType<PhantasmSphereEntity>> PH_SPHERE_ENTITY = GSKO_ENTITIES.register(
   //         "phantasm_sphere", () -> PhantasmSphereEntity.PH_SPHERE_TYPE);
}


