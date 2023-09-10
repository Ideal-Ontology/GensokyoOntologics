package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.common.entity.projectile.HeartShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
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

public class ManiaDepressEntity extends SpellCardEntity{

    public static final EntityType<ManiaDepressEntity> MANIA_DEPRESS =
            EntityType.Builder.<ManiaDepressEntity>create(ManiaDepressEntity::new, EntityClassification.MISC)
                    .size(1F,1F).trackingRange(4).updateInterval(2).build("mania_depress");

    public ManiaDepressEntity(World worldIn, PlayerEntity player) {
        super(MANIA_DEPRESS, worldIn, player);
    }

    public ManiaDepressEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(MANIA_DEPRESS, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        List<Vector3d> positions = DanmakuUtil.getHeartLinePos(0.5f, 0.07);

        if (ticksExisted % 20 == 0) {
            for (Vector3d vector3d : positions) {
                HeartShotEntity heartShot = new HeartShotEntity((LivingEntity) this.getOwner(), world, DanmakuType.RICE_SHOT, DanmakuColor.PURPLE);
                Vector3d shootVec = new Vector3d(vector3d.x, vector3d.y, vector3d.z);
                vector3d = vector3d.add(this.getPositionVec());

                setDanmakuInit(heartShot, vector3d, new Vector2f((float) vector3d.x, (float) vector3d.y));
                heartShot.shoot(shootVec.x, shootVec.y, shootVec.z, 0.3f, 0f);
                world.addEntity(heartShot);
            }
        }
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SPELL_CARD_BLANK.get());
    }
}
