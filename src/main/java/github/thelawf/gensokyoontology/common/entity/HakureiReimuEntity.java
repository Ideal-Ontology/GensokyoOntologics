package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class HakureiReimuEntity extends TameableEntity implements IAngerable {
    public HakureiReimuEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
        return null;
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int time) {

    }

    @Nullable
    @Override
    public UUID getAngerTarget() {
        return null;
    }

    @Override
    public void setAngerTarget(@Nullable UUID target) {

    }

    @Override
    public void func_230258_H__() {

    }
}
