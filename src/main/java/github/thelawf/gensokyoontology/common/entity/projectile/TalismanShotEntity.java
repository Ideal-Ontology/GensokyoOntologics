package github.thelawf.gensokyoontology.common.entity.projectile;

import github.thelawf.gensokyoontology.api.INormalVectorDanmaku;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TalismanShotEntity extends AbstractDanmakuEntity implements INormalVectorDanmaku{

    protected TalismanShotEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public void setRenderRotation() {

    }
}
