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

public class DreamSealEntity extends AffiliatedEntity {
    private DanmakuColor color = DanmakuColor.RED;
    public static final DataParameter<Integer> DATA_COLOR = EntityDataManager.createKey(DreamSealEntity.class, DataSerializers.VARINT);
    public DreamSealEntity(EntityType<?> entityTypeIn,World worldIn, Entity owner, DanmakuColor color) {
        super(entityTypeIn, owner, worldIn);
        this.setColor(color);
    }
    public DreamSealEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, null, worldIn);
        this.setColor(DanmakuColor.RED);
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_COLOR, this.getColor().ordinal());
    }

    public void setColor(DanmakuColor color) {
        this.color = color;
    }

    public DanmakuColor getColor() {
        return this.color;
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("color", this.color.ordinal());
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setColor(DanmakuColor.values()[compound.getInt("color")]);
    }
}
