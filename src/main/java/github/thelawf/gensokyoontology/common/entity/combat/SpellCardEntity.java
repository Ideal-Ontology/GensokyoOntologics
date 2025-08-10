package github.thelawf.gensokyoontology.common.entity.combat;

import com.google.gson.JsonObject;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class SpellCardEntity extends AbstractSpellCardEntity{
    private BiConsumer<World, LivingEntity> simpleBehavior;
    private Item spellItem;

    public SpellCardEntity(EntityType<AbstractSpellCardEntity> entityType, World world, Item spellItem, BiConsumer<World, LivingEntity> simpleBehavior) {
        super(entityType, world);
        this.simpleBehavior = simpleBehavior;
        this.spellItem = spellItem;
    }

    public SpellCardEntity(EntityType<AbstractSpellCardEntity> entityType, World world, Item spellItem, JsonObject jsonSpell) {
        super(entityType, world);
        this.spellItem = spellItem;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return this.spellItem.getDefaultInstance();
    }
}
