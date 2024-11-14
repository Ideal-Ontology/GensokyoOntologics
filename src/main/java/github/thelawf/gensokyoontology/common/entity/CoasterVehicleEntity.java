package github.thelawf.gensokyoontology.common.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

// TODO: 实现过山车载具，包含模型、渲染和运行逻辑
public class CoasterVehicleEntity extends AbstractMinecartEntity {
    protected CoasterVehicleEntity(EntityType<?> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void moveAlongTrack(BlockPos pos, BlockState state) {
        super.moveAlongTrack(pos, state);
    }

    @Override
    @NotNull
    public Type getMinecartType() {
        return Type.RIDEABLE;
    }
}
