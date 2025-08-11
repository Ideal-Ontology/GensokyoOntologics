package github.thelawf.gensokyoontology.common.entity.combat.spell;

import github.thelawf.gensokyoontology.common.entity.combat.AbstractSpellCardEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.RiceShotEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.SmallShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class FourOfAKindEntity extends AbstractSpellCardEntity {

    private int userIndex = 0;

    public static final DataParameter<Integer> DATA_INDEX = EntityDataManager.createKey(FourOfAKindEntity.class, DataSerializers.VARINT);

    public static final EntityType<FourOfAKindEntity> FOUR_OF_A_KIND =
            EntityType.Builder.<FourOfAKindEntity>create(FourOfAKindEntity::new, EntityClassification.MISC)
                    .size(1F, 1F).trackingRange(4).updateInterval(2).build("four_of_a_kind");

    public FourOfAKindEntity(EntityType<? extends AbstractSpellCardEntity> entityTypeIn, World worldIn) {
        super(FOUR_OF_A_KIND, worldIn);
    }

    public FourOfAKindEntity(World worldIn, LivingEntity living, int index) {
        super(FOUR_OF_A_KIND, worldIn, living);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_INDEX, this.userIndex);
    }

    public void onTick(World world, LivingEntity user, int ticksIn) {

        switch (this.dataManager.get(DATA_INDEX)) {
            case 0:
                break;
            case 1:
                SmallShotEntity smallShot = new SmallShotEntity(user, user.world, DanmakuType.SMALL_SHOT, DanmakuColor.BLUE);
                shootSpherical(smallShot, user);
                break;
            case 2:
                RiceShotEntity riceShot = new RiceShotEntity(user, user.world, DanmakuType.RICE_SHOT, DanmakuColor.RED);
                shootSpherical(riceShot, user);
                break;
        }
    }

    private <D extends AbstractDanmakuEntity> void shootSpherical(D danmaku, LivingEntity user) {
        Vector3d vector3d = new Vector3d(Vector3f.ZP).scale(2);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {

                vector3d = vector3d.rotatePitch((float) (Math.PI / 12 * i));
                vector3d = vector3d.rotateYaw((float) (Math.PI / 12 * j));

                // DanmakuUtil.shootDanmaku(this.world, user, danmaku, vector3d, 0.4f, 0f);
                // DanmakuUtil.shootDanmaku(this.world, user, danmaku, vector3d.inverse(), 0.4f, 0f);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.onTick(this.world, (LivingEntity) this.getOwner(), ticksExisted);
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
