package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.capability.GSKOCapabilities;
import github.thelawf.gensokyoontology.common.capability.ImperishableNightCapability;
import github.thelawf.gensokyoontology.common.network.packet.ImperishableNightPacket;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.command.impl.TimeCommand;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class EirinYagokoroArrowEntity extends AbstractArrowEntity {

    private static final int TICKS_TO_CAUSE_INCIDENT = 500;
    public static final DataParameter<Integer> DATA_CAUSING_TICKS = EntityDataManager.createKey(
            EirinYagokoroArrowEntity.class, DataSerializers.VARINT);

    protected EirinYagokoroArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    // 在这里注册将会引发永夜异变的时长
    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemRegistry.EIRIN_YAGOKORO_ARROW.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getShooter() == null)
            return;

        if (!this.world.isRemote && isTickSuccess(this.world.getDayTime())) {
            ServerWorld serverWorld = (ServerWorld) this.world;
            this.getShooter().sendMessage(new StringTextComponent("你发动了永夜异变"), this.getShooter().getUniqueID());
            LazyOptional<ImperishableNightCapability> capability = serverWorld.getCapability(GSKOCapabilities.IMPERISHABLE_NIGHT);
            capability.ifPresent(cap -> {
                cap.setTriggered(true);
                cap.setDayTime(18000);
                serverWorld.setDayTime(cap.getDayTime());
                serverWorld.getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE).set(cap.isTriggered(), serverWorld.getServer());
            });
        }
    }

    private boolean isTickSuccess(long time) {
        double moonAngle = Math.PI / 12000 * (this.world.getDayTime() - 12000);
        Vector3d lookVec = this.getLookVec();
        return time > 12000 && this.ticksExisted >= TICKS_TO_CAUSE_INCIDENT &&
                isWithinRange(lookVec.y, moonAngle - Math.PI / 10, moonAngle + Math.PI / 10) &&
                isWithinRange(lookVec.z, -0.1, 0.1);
    }

    private boolean isWithinRange(double f, double rangeMin, double rangeMax) {
        return f > rangeMin && f < rangeMax;
    }

    @Override
    public void setDamage(double damageIn) {
        super.setDamage(8);
    }

    // 在这里添加新的伤害来源，使得在虚假之月的事件之下，被该箭矢杀死的生物将掉落月之碎片。
    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        LivingEntity entityHit = (LivingEntity) result.getEntity();
        if (!(entityHit instanceof PlayerEntity)) {
            entityHit.attackEntityFrom(GSKODamageSource.IMPERISHABLE_NIGHT, (float) this.getDamage());
            this.remove();
        }
    }
}
