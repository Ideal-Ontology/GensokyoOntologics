package github.thelawf.gensokyoontology.common.entity.spellcard;

import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GalacticArmSpellEntity extends SpellCardEntity{
    public GalacticArmSpellEntity(World worldIn, LivingEntity living) {
        super(EntityRegistry.GALACTIC_ARM_SPELL_ENTITY.get(), worldIn, living);
    }

    public GalacticArmSpellEntity(EntityType<? extends SpellCardEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }


    @Override
    public ItemStack getItem() {
        return new ItemStack(ItemRegistry.SC_GALACTIC_SPIRAL_ARMS.get());
    }
}
