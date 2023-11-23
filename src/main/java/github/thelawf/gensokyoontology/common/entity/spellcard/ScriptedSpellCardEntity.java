package github.thelawf.gensokyoontology.common.entity.spellcard;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ScriptedSpellCardEntity extends SpellCardEntity{
    public ScriptedSpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn, LivingEntity living) {
        super(entityTypeIn, worldIn, living);
    }


    public ScriptedSpellCardEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    @NotNull
    public ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
