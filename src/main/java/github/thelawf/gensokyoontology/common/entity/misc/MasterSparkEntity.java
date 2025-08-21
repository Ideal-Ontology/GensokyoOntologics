package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MasterSparkEntity extends AffiliatedEntity implements IRayTraceReader {
    public static final float DISTANCE = 50F;
    public MasterSparkEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public MasterSparkEntity(Entity owner, World worldIn) {
        super(EntityRegistry.MASTER_SPARK_ENTITY.get(), owner, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted >= 120) this.remove();
        if (ticksExisted < 40) return;
        if (world.isRemote) return;
        ServerWorld serverWorld = (ServerWorld) world;
        List<Entity> entities = rayTrace(serverWorld, this, DISTANCE, new Vector3d(0,0,0));
        List<Vector3d> startList = DanmakuUtil.ellipticPos(1.5, 10);
        startList.addAll(DanmakuUtil.ellipticPos(2.5, 10));

        startList.forEach(vector3d -> entities.addAll(rayTrace(serverWorld, this, DISTANCE, vector3d)));
        Predicate<Entity> canAttack = entity -> this.getOwnerID().isPresent() && entity.getUniqueID() != this.getOwnerID().get();

        entities.stream().filter(canAttack).forEach(entity -> entity.attackEntityFrom(GSKODamageSource.LASER, 10F));
        /*
        startList.replaceAll(vector3d -> vector3d.add(0,0.6,0));
        List<Vector3d> endList = startList.stream().map(vector3d -> this.getLookVec().scale(DISTANCE).add(vector3d)).collect(Collectors.toList());

        Map<Vector3d, Vector3d> vectorMap = IntStream.range(0, startList.size()).boxed()
                .collect(Collectors.toMap(startList::get, endList::get));
        Predicate<Entity> canAttack = entity -> this.getOwnerID().isPresent() && entity.getUniqueID() != this.getOwnerID().get();

        Vector3d start = this.getPositionVec();
        Vector3d end = this.getLookVec().scale(DISTANCE).add(this.getPositionVec());

        for (Map.Entry<Vector3d, Vector3d> entry : vectorMap.entrySet()) {
            Vector3d start1 = entry.getKey().add(this.getEyePosition(1));
            Vector3d end1 = entry.getValue().add(this.getEyePosition(1));

            serverWorld.getEntities()
                    .filter(entity -> canAttack.test(entity) && this.isIntersecting(start1, end1, entity.getBoundingBox()))
                    .forEachAct(entity -> entity.attackEntityFrom(GSKODamageSource.LASER, 15F));

            // this.getEntityInCylinder(this.world, this, canAttack, start1, end1, DISTANCE, 3).forEachAct(
            //         entity -> entity.attackEntityFrom(GSKODamageSource.LASER, 15F));
        }
         */
    }
}
