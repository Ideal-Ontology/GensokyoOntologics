package github.thelawf.gensokyoontology.common.entity.spellcard;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class SuperEgoSpellEntity extends SpellCardEntity{
    public SuperEgoSpellEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, LivingEntity living) {
        super(entityTypeIn, worldIn, living);
    }

    public SuperEgoSpellEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
