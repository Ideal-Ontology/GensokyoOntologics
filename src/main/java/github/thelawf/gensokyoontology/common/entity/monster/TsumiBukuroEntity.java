package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.common.entity.AbstractHumanEntity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TsumiBukuroEntity extends AbstractHumanEntity implements IAngerable {

    public TsumiBukuroEntity(EntityType<? extends AbstractHumanEntity> type, World worldIn) {
        super(type, worldIn);
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
