package github.thelawf.gensokyoontology.common.entity.misc;

import github.thelawf.gensokyoontology.common.entity.AffiliatedEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class DreamSealEntity extends AffiliatedEntity {
    private int color;
    public static final DataParameter<Integer> DATA_COLOR = EntityDataManager.createKey(DreamSealEntity.class, DataSerializers.VARINT);
    public DreamSealEntity(EntityType<?> entityTypeIn, World worldIn, UUID ownerId, DanmakuColor color) {
        super(entityTypeIn, ownerId, worldIn);
        this.setColor(color);
    }
    public DreamSealEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_COLOR, this.color);
    }

    public void setColor(DanmakuColor color) {
        this.dataManager.set(DATA_COLOR, color.ordinal());
    }

    public DanmakuColor getColor() {
        return DanmakuColor.values()[this.dataManager.get(DATA_COLOR)];
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("color", this.getColor().ordinal());
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setColor(DanmakuColor.values()[compound.getInt("color")]);
    }
}
