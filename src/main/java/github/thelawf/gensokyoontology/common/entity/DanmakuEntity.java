package github.thelawf.gensokyoontology.common.entity;

import com.google.gson.Gson;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuStyle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class DanmakuEntity extends ThrowableEntity {
    // 设对象面朝的本地坐标轴为 z轴，对象左右手的本地坐标轴为 x轴，对象上下的本地坐标轴为 y轴
    // 那么用欧拉角表示物体的旋转为：
    // 绕 y轴旋转 - yaw：偏航角；绕 z轴旋转 - pitch：俯仰角；绕 x轴旋转 - roll：翻滚角

    // 可以去查看ProjectileEntity 里面的shoot()，setDirectionAndMovement()和
    // updatePitchAndYaw()方法, 还可以查看 ThrowableEntity 的 tick()方法，
    // Entity类里面的moveToBlockPosAndAngles(),setLocationAndAngles()方法，setPositionAndRotation()方法
    public static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(DanmakuEntity.class,DataSerializers.FLOAT);

    public DanmakuEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {

    }

    @Override
    public void tick() {
        super.tick();
    }

    public void getStyleFromJson() {
        ResourceLocation location = new ResourceLocation(GensokyoOntology.MODID,
                "danmaku/vortex_style.json");
        Gson gson = new Gson();
        DanmakuStyle style = gson.fromJson(location.toString(), DanmakuStyle.class);
    }


    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return null;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return super.serializeNBT();
    }

    @Override
    public void setShooter(@Nullable Entity entityIn) {
        super.setShooter(entityIn);
    }

    @Nullable
    @Override
    public Entity getShooter() {
        return super.getShooter();
    }
}
