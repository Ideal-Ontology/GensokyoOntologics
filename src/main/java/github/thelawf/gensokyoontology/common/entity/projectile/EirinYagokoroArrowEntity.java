package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.common.network.packet.ImperishableNightPacket;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class EirinYagokoroArrowEntity extends AbstractArrowEntity {

    // private ItemStack arrowStack = new ItemStack(ItemRegistry);
    private static final int ticksToCauseIncident = 500;
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
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.world.getDayTime() > 12000) {
            double moonAngle = Math.PI / 12000 * (this.world.getDayTime() - 12000);
            if (this.rotationYaw > moonAngle - Math.PI / 10 &&
            this.rotationYaw < moonAngle + Math.PI / 10) {
                if (this.getShooter() != null) {
                    this.getShooter().sendMessage(new TranslationTextComponent("你发动了永夜异变"),
                            this.getShooter().getUniqueID());
                }
            }
        }
    }

    // 在这里添加新的伤害来源，使得在虚假之月的事件之下，被该箭矢杀死的生物将掉落月之碎片。
    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
    }
}
