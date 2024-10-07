package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.api.util.IRayTraceReader;
import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
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

    }

    private void generateRays() {
        for (int i = 0; i < 3; i++) {
            List<Vector3d> initPositions = DanmakuUtil.ellipticPos(new Vector2f((float) this.getPosX(), (float) this.getPosZ()), i, 10);
            List<Vector3d> endPositions = initPositions.stream().map(vector3d -> getLookEnd(vector3d, this.getLookVec(), this.getEyeHeight(), 50))
                    .collect(Collectors.toList());
        }
    }
}
