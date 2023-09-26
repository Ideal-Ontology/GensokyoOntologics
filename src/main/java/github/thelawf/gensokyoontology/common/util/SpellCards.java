package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.api.IDanmakuBehavior;
import github.thelawf.gensokyoontology.common.entity.spellcard.SpellCardEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SpellCards {
    public static <SC extends SpellCardEntity> SpellCardEntity newSpellCard(EntityType<SC> type, World worldIn,
                                                                            LivingEntity livingIn, ItemStack spellIcon) {
        return new SpellCardEntity(type, worldIn, livingIn) {
            @Override
            @NotNull
            public ItemStack getItem() {
                return spellIcon;
            }
        };
    }
}
