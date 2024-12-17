package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MasterSparkEntity extends AffiliatedEntity implements IRayTraceReader {
    public static final float DISTANCE = 80F;
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
        if (ticksExisted < 10) return;
        List<Vector3d> startList = DanmakuUtil.ellipticPos(Vector2f.ZERO, 3, 20);
        // startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 3, 20));
        // startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 2.5, 30));
        startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 2, 20));
        // startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 1.5, 20));
        startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 1, 10));
        // 1startList.addAll(DanmakuUtil.ellipticPos(Vector2f.ZERO, 0.5, 10));
        List<Vector3d> endList = startList.stream().map(vector3d -> this.getLookVec().scale(DISTANCE).add(vector3d)).collect(Collectors.toList());

        Map<Vector3d, Vector3d> vectorMap = IntStream.range(0, startList.size()).boxed()
                .collect(Collectors.toMap(startList::get, endList::get));
        Predicate<Entity> canAttack = entity -> this.getOwnerID().isPresent() && entity.getUniqueID() != this.getOwnerID().get();

        if (ticksExisted == 12) {
            vectorMap.keySet().forEach(vector3d -> GSKOUtil.log(this.getClass(), vector3d));
            vectorMap.values().forEach(vector3d -> GSKOUtil.log(this.getClass(), vector3d));
        }

        for (Map.Entry<Vector3d, Vector3d> entry : vectorMap.entrySet()) {
            Vector3d start = entry.getKey().add(this.getPositionVec()).add(0, 1, 0);
            Vector3d end = entry.getValue().add(this.getPositionVec());

            if (this.ticksExisted % 2 == 0 && rayTrace(this.world, this, start, end).isPresent()) {
                rayTrace(this.world, this, start, end).ifPresent(entity -> {
                    if (canAttack.test(entity)) entity.attackEntityFrom(GSKODamageSource.LASER, 15F);
                });
            }
        }
    }
}
