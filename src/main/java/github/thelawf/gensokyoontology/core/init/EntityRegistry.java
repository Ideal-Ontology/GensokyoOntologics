package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.SpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.MasterSparkEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> GSKO_ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, GensokyoOntology.MODID);

    public static final RegistryObject<EntityType<FairyEntity>> FAIRY_ENTITY = GSKO_ENTITIES.register(
            "fairy_entity", () -> FairyEntity.FAIRY_ENTITY);

    public static final RegistryObject<EntityType<DanmakuEntity>> DANMAKU_ENTITY = GSKO_ENTITIES.register(
            "danmaku", () -> DanmakuEntity.DANMAKU_ENTITY);

    public static final RegistryObject<EntityType<MasterSparkEntity>> MASTER_SPARK_ENTITY = GSKO_ENTITIES.register(
            "master_spark", () -> MasterSparkEntity.MASTER_SPARK_ENTITY);

    RegistryObject<EntityType<SpellCardEntity>> SPELL_CARD_ENTITY = GSKO_ENTITIES.register(
            "spell_card_entity", () -> SpellCardEntity.SPELL_CARD_ENTITY);

   // public static final RegistryObject<EntityType<FlyingSwordEntity>> FLY_SWORD_ENTITY = GSKO_ENTITIES.register(
   //         "flying_sword", () -> FlyingSwordEntity.FLY_SWORD_TYPE);

   // public static final RegistryObject<EntityType<PhantasmSphereEntity>> PH_SPHERE_ENTITY = GSKO_ENTITIES.register(
   //         "phantasm_sphere", () -> PhantasmSphereEntity.PH_SPHERE_TYPE);
}


