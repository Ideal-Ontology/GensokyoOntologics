package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FullCherryBlossomEntity extends SpellCardEntity{

    public static final EntityType<FullCherryBlossomEntity> FULL_CHERRY_BLOSSOM =
            EntityType.Builder.<FullCherryBlossomEntity>create(FullCherryBlossomEntity::new, EntityClassification.MISC)
                    .size(1F,1F).trackingRange(4).updateInterval(2).build("full_cherry_blossom");

    public FullCherryBlossomEntity(World worldIn, PlayerEntity player) {
        super(FULL_CHERRY_BLOSSOM, worldIn, player);
    }

    public FullCherryBlossomEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(FULL_CHERRY_BLOSSOM, worldIn);
    }

    @Override
    public void tick() {
        super.tick();


        List<Vector3d> roseLinePos = DanmakuUtil.getRoseLinePos((double) 3 / 2, 3, 0.05);

        if (ticksExisted % 40 == 0) {

            for (Vector3d vector3d : roseLinePos) {
                RiceShotEntity riceShot = new RiceShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
                Vector3d shootVec = new Vector3d(vector3d.x, vector3d.y, vector3d.z);
                vector3d = vector3d.add(this.getPositionVec());

                setDanmakuInit(riceShot, vector3d, new Vector2f((float) vector3d.x, (float) vector3d.y));
                riceShot.shoot(shootVec.x, shootVec.y, shootVec.z, 0.3f, 0f);
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
