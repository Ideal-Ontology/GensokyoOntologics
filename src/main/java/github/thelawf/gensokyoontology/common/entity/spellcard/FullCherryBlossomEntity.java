package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuData;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FullCherryBlossomEntity extends SpellCardEntity{

    public FullCherryBlossomEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, PlayerEntity player) {
        super(entityTypeIn, worldIn, player);
    }

    public FullCherryBlossomEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        // DanmakuData<RiceShotEntity> riceShotData = new DanmakuData<>(
        //         this.world, riceShot, this.getPositionVec(), Vector2f.UNIT_X)

        List<Vector3d> roseLinePos = DanmakuUtil.getRoseLinePos(5, 7, 5, 600);
        List<Vector2f> shootVectors = new ArrayList<>();
        roseLinePos.forEach(vector3d -> shootVectors.add(GSKOMathUtil.getEulerAngle(new Vector3d(Vector3f.ZP), vector3d)));

        if (ticksExisted % 8 == 0) {
            for (int i = 0; i < roseLinePos.size(); i++) {
                RiceShotEntity riceShot = new RiceShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
                setDanmakuInit(riceShot, roseLinePos.get(i), shootVectors.get(i));

                Vector3d vector3d = new Vector3d(shootVectors.get(i).x, shootVectors.get(i).y, 0);
                riceShot.shoot(vector3d.x, vector3d.y, vector3d.z, 0.3f, 0f);
                world.addEntity(riceShot);
            }
        }

    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
