package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.DanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.FlyingSwordEntity;
import github.thelawf.gensokyoontology.common.entity.LovePotionEntity;
import github.thelawf.gensokyoontology.common.entity.PhantasmSphereEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> GSKO_ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, GensokyoOntology.MODID);

    /*
    public static final EntityType<SpaceFissureEntity> SPACE_FISSURE_TYPE = EntityType.Builder.<SpaceFissureEntity>create(
            SpaceFissureEntity::new, EntityClassification.create("symbol","ideology",
                    25,true,false,128)).size(1.8f,2.1f).build("space_fissure_entity");

    public static final RegistryObject<EntityType<SpaceFissureEntity>> SPACE_FISSURE_ENTITY = GSKO_ENTITIES.register(
            "space_fissure_entity",() -> SPACE_FISSURE_TYPE);
            */

    public static final EntityType<LovePotionEntity> LOVE_POTION_TYPE = EntityType.Builder.<LovePotionEntity>create(
            LovePotionEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).trackingRange(4)
            .updateInterval(10).build("love_potion_entity");

    public static final EntityType<DanmakuEntity> DANMAKU_ENTITY_TYPE = EntityType.Builder.<DanmakuEntity>create(
            DanmakuEntity::new, EntityClassification.MISC).size(0.5F,0.5F).trackingRange(4)
            .updateInterval(2).build("danmaku_entity");

    public static final EntityType<PhantasmSphereEntity> PHANTASM_SPHERE_TYPE = EntityType.Builder.<PhantasmSphereEntity>create(
                    PhantasmSphereEntity::new, EntityClassification.MISC).size(1f,1f).trackingRange(4)
            .updateInterval(6).build("phantasm_sphere_entity");


    public static final EntityType<FlyingSwordEntity> FLYING_SWORD_TYPE = EntityType.Builder.<FlyingSwordEntity>create(
                    FlyingSwordEntity::new, EntityClassification.MISC).size(1f,0.4f).trackingRange(4)
            .updateInterval(5).build("flying_sword_entity");
}


