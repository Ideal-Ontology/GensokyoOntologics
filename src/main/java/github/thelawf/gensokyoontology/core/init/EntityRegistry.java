package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
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

    public static final RegistryObject<EntityType<DanmakuEntity>> DANMAKU_ENTITY = GSKO_ENTITIES.register(
            "danmaku", () -> DanmakuEntity.DANMAKU_TYPE);

    public static final RegistryObject<EntityType<FlyingSwordEntity>> FLY_SWORD_ENTITY = GSKO_ENTITIES.register(
            "flying_sword", () -> FlyingSwordEntity.FLY_SWORD_TYPE);

    public static final RegistryObject<EntityType<PhantasmSphereEntity>> PH_SPHERE_ENTITY = GSKO_ENTITIES.register(
            "phantasm_sphere", () -> PhantasmSphereEntity.PH_SPHERE_TYPE);
}


