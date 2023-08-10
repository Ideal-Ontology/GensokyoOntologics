package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SpectreEntity extends FlyingEntity implements IRendersAsItem {

    public static final EntityType<SpectreEntity> SPECTRE = EntityType.Builder.create(
                    SpectreEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.65f, 0.65f).trackingRange(10).build("spectre");

    protected SpectreEntity(EntityType<? extends FlyingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.WANDERING_SOUL.get());
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }
}
