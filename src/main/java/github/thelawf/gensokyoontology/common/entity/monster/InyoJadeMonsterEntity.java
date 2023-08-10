package github.thelawf.gensokyoontology.common.entity.monster;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class InyoJadeMonsterEntity extends MonsterEntity implements IRendersAsItem {

    public static final EntityType<InyoJadeMonsterEntity> INYO_JADE_MONSTER = EntityType.Builder.create(
                    InyoJadeMonsterEntity::new, EntityClassification.CREATURE).updateInterval(2)
            .size(0.8f, 0.8f).trackingRange(10).build("human_resident");

    protected InyoJadeMonsterEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.INYO_JADE_BLUE.get());
    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(true);
    }
}
