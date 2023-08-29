package github.thelawf.gensokyoontology.common.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EirinYagokoroArrowEntity extends AbstractArrowEntity {

    protected EirinYagokoroArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected ItemStack getArrowStack() {
        return null;
    }
}
